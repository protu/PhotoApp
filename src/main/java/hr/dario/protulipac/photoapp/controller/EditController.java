package hr.dario.protulipac.photoapp.controller;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.domain.PictureInt;
import hr.dario.protulipac.photoapp.processing.*;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import hr.dario.protulipac.photoapp.service.ActionService;
import hr.dario.protulipac.photoapp.service.CloneStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EditController {

    private Logger log = LoggerFactory.getLogger(EditController.class);

    private PictureRepo pictureRepo;
    private ActionService actionService;
    private CloneStore cloneStore;

    @Autowired
    public EditController(PictureRepo pictureRepo, ActionService actionService, CloneStore cloneStore) {
        this.pictureRepo = pictureRepo;
        this.actionService = actionService;
        this.cloneStore = cloneStore;
    }

    @GetMapping("/edit")
    public String goHome() {
        return ("redirect:/");
    }


    @PostMapping("/edit")
    public String editPicture(@RequestParam("id") long id, @RequestParam(value = "change", required = false) Integer change,
                              @RequestParam(value = "imageActions", required = false) String[] imageActions,
                              Model model, Picture pictureNew, ArrayList<String> actNames) {
        Picture picture = pictureRepo.findById(id).get();
        model.addAttribute("picture", picture);
        if (change != null && change == 1) {
            picture.setDescription(pictureNew.getDescription());
            picture.setName(pictureNew.getName());
            PictureInt pictureClone = new PhotoProcessFactory(imageActions, picture).getProcessedPicture();
            pictureRepo.save(picture);
            cloneStore.putClone(picture.getId(), pictureClone);
            model.addAttribute("message", "Picture " + picture.getName() + " is " + pictureClone.process());
            log.info("Picture " + picture.getName() + " is " + pictureClone.process());
            List<PictureInt> clones = cloneStore.getClones(picture.getId());
            clones.forEach(c -> log.info("Proto: " + c.process()));
        } else {
            model.addAttribute("actionNames", actionService.getActionList());
        }
        return ("edit");
    }
}
