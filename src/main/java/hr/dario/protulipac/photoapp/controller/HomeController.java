package hr.dario.protulipac.photoapp.controller;
//marin je bio ovdje
import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final PictureRepo pictureRepo;

    @Autowired
    public HomeController(PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Picture> pictures = new ArrayList<>();
        pictureRepo.findAll().forEach(pictures::add);
        model.addAttribute("pictures", pictures);
        return ("home");
    }

 }
