package hr.dario.protulipac.photoapp.controller;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditController {

    private PictureRepo pictureRepo;

    @Autowired
    public EditController(PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

    @GetMapping("/")
    public String goHome() {
        return ("redirect:/");
    }

    @PostMapping("/edit")
    public String editPicture(@RequestParam("id") long id) {
        return ("edit");
    }
}
