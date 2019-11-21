package application.controller.web;

import application.data.model.User;
import application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/detail")
    private String userDetail(Model model){
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);
        model.addAttribute("user",user);
        return "/user/userdetail";
    }

    @RequestMapping(path = "/detail", method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("user")User user) {
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userExist = userService.findUserByUsername(username);
        userExist.setAddress(user.getAddress());
        userExist.setEmail(user.getEmail());
        userExist.setAvatar(user.getAvatar());
        userExist.setFirstName(user.getFirstName());
        userExist.setLastName(user.getLastName());
        userExist.setGender(user.getGender());
        userExist.setPhone(user.getPhone());
        userService.updateUser(userExist);
        return "/user/userdetail";
    }
}
