package hr.dario.protulipac.photoapp.controller;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeleteController {

    private Logger log = LoggerFactory.getLogger(DeleteController.class);

    private PictureRepo pictureRepo;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public DeleteController(PictureRepo pictureRepo, JmsTemplate jmsTemplate) {
        this.pictureRepo = pictureRepo;
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/delete")
    public String goHome() {
        return ("redirect:/");
    }

    @PostMapping("/delete")
    public String editPicture(@RequestParam("id") long id, @RequestParam(value = "change", required = false) Integer change, Model model, Picture pictnew) {
        if (!pictureRepo.findById(id).isEmpty()) {
            pictureRepo.deleteById(id);
        }
        List<Picture> pictures = new ArrayList<>();
        pictureRepo.findAll().forEach(pictures::add);
        model.addAttribute("pictures", pictures);
        return ("home");
    }
}
