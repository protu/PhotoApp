package hr.dario.protulipac.photoapp.controller;

import hr.dario.protulipac.photoapp.builder.AdminRoleBuilder;
import hr.dario.protulipac.photoapp.builder.UserRoleBuilder;
import hr.dario.protulipac.photoapp.domain.User;
import hr.dario.protulipac.photoapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        model.addAttribute("users", users);
        return ("users");
    }

    @PostMapping("/users")
    public String saveNewUser(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam(value = "enabled", required = false, defaultValue = "false") Boolean enabled,
                              @RequestParam("authority") String  authority) {
        if (authority.equals("ROLE_USER")) {
            UserRoleBuilder userRoleBuilder = new UserRoleBuilder();
            userRoleBuilder.setEnabled(enabled);
            userRoleBuilder.setUsername(username);
            userRoleBuilder.setPassword(password);
            userRepo.save(userRoleBuilder.getBuilder());
        }else if (authority.equals("ROLE_ADMIN")){
            AdminRoleBuilder adminRoleBuilder = new AdminRoleBuilder();
            adminRoleBuilder.setEnabled(enabled);
            adminRoleBuilder.setUsername(username);
            adminRoleBuilder.setPassword(password);
            userRepo.save(adminRoleBuilder.getBuilder());
        }
        return ("redirect:/users");
    }

    @GetMapping("/adduser")
    public String addNewUser(Model model) {
        String[] authorities = {"ROLE_USER", "ROLE_ADMIN"};
        model.addAttribute("user", new User());
        model.addAttribute("authorties", authorities);
        return ("/addusers");
    }
}
