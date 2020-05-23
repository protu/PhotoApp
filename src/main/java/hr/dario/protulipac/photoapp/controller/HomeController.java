package hr.dario.protulipac.photoapp.controller;
//marin je bio ovdje

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
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
    private final JmsTemplate jmsTemplate;

    private Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    public HomeController(PictureRepo pictureRepo, JmsTemplate jmsTemplate) {
        this.pictureRepo = pictureRepo;
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Picture> pictures = new ArrayList<>();
        pictureRepo.findAll().forEach(pictures::add);
        model.addAttribute("pictures", pictures);
        jmsTemplate.convertAndSend("Home page loaded");
        return ("home");
    }

 }
