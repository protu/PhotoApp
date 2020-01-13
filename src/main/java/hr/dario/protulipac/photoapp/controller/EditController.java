package hr.dario.protulipac.photoapp.controller;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String editPicture(@RequestParam("id") long id, @RequestParam(value = "change", required = false) Integer change, Model model, Picture pictnew) {
        Picture picture = pictureRepo.findById(id).get();
        model.addAttribute("picture", picture);
        if (change != null && change == 1) {
            picture.setDescription(pictnew.getDescription());
            picture.setName(pictnew.getName());
            pictureRepo.save(picture);
            model.addAttribute("message", "Picture" + picture.getName() + " is changed");
            log.info("Picture" + picture.getName() + " is changed");
        }
        return ("edit");
    }
}
