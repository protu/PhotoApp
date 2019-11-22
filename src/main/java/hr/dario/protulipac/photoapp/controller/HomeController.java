package hr.dario.protulipac.photoapp.controller;
//marin je bio ovdje
import hr.dario.protulipac.photoapp.domain.Picture;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return ("home");
    }

 }
