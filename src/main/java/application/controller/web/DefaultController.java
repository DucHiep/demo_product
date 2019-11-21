package application.controller.web;

import application.constant.StatusRegisterUserEnum;
import application.data.model.Role;
import application.data.model.User;
import application.data.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by ManhNguyen on 9/21/17.
 */

@Controller
public class DefaultController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String index(Model model) {
        return "anonymous/home";
    }




    @GetMapping("/login")
    public String login() {
        return "/logindemo";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping(path="/register-user")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/registerdemo";
    }


    @RequestMapping(path="/register-user", method = RequestMethod.POST)
    public String registerNewUser(@Valid @ModelAttribute("user")User user){
        userService.registerNewUser(user);
        return "redirect:/login";
    }



}