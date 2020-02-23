package hr.dario.protulipac.photoapp.controller;
//marin je bio ovdje

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.iterator.Actions;
import hr.dario.protulipac.photoapp.iterator.Iterator;
import hr.dario.protulipac.photoapp.processing.BlurAction;
import hr.dario.protulipac.photoapp.processing.ImageAction;
import hr.dario.protulipac.photoapp.processing.SephiaAction;
import hr.dario.protulipac.photoapp.processing.SharpenAction;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final PictureRepo pictureRepo;
    private Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    public HomeController(PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Picture> pictures = new ArrayList<>();
        pictureRepo.findAll().forEach(pictures::add);
        model.addAttribute("pictures", pictures);
        showActions();
        return ("home");
    }

    private void showActions() {
        Class<ImageAction>[] actions = new Class[]{BlurAction.class, SephiaAction.class, SharpenAction.class};
        Actions<Class> actionsList = new Actions<>(actions);
        Iterator actionIterator = actionsList.getIterator();
        while (actionIterator.hasNext()) {
            String className = ((Class)actionIterator.next()).getName();
            log.info(className);
        }
    }

 }
