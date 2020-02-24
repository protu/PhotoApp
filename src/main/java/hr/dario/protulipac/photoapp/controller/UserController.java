package hr.dario.protulipac.photoapp.controller;

import hr.dario.protulipac.photoapp.domain.Users;
import hr.dario.protulipac.photoapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Users> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        model.addAttribute("users", users);
        return ("users");
    }
}
