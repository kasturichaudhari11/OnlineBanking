package com.onlinebanking.icin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinebanking.icin.entity.User;
import com.onlinebanking.icin.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
      
    	User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/profile")
    public String profilePost(@ModelAttribute("user") User updateUser, Model model) {
    	
        User user = userService.findByUsername(updateUser.getUsername());
        user.setUsername(updateUser.getUsername());
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setEmail(updateUser.getEmail());
        user.setPhone(updateUser.getPhone());

        model.addAttribute("user", user);
        userService.save(user);

        return "profile";
    }
}
