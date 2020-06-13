package hr.dario.protulipac.photoapp.controller;
//marin je bio ovdje

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
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
    private MeterRegistry meterRegistry;
    private Counter homeHitCounter;

    @Autowired
    public HomeController(PictureRepo pictureRepo, MeterRegistry meterRegistry) {
        this.pictureRepo = pictureRepo;
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/")
    public String home(Model model) {
        if (homeHitCounter == null) {
            initCounters();
        }
        System.out.println("Hello Home page");
        List<Picture> pictures = new ArrayList<>();
        pictureRepo.findAll().forEach(pictures::add);
        model.addAttribute("pictures", pictures);
        homeHitCounter.increment(1);
        return ("home");
    }

    private void initCounters() {
        homeHitCounter = this.meterRegistry.counter("photoapp", "web", "home");
    }

 }
