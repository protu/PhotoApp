package hr.dario.protulipac.photoapp.controller;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.domain.PictureInt;
import hr.dario.protulipac.photoapp.iterator.Actions;
import hr.dario.protulipac.photoapp.processing.*;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class EditController {

    private Logger log = LoggerFactory.getLogger(EditController.class);

    private PictureRepo pictureRepo;

    @Autowired
    public EditController(PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
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
            PictureInt pictureClone = new ImageProcessFactory(imageActions, picture).getNewPicture();
            pictureRepo.save(picture);
            model.addAttribute("message", "Picture " + picture.getName() + " is " + pictureClone.process());
            log.info("Picture " + picture.getName() + " is " + pictureClone.process());
        } else {
            List<String> actionNames = new ArrayList<>(Arrays.asList("blur", "sepia", "sharpen"));
            model.addAttribute("actionNames", actionNames);
        }
        return ("edit");
    }
}
