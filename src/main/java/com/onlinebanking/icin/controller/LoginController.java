package com.onlinebanking.icin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlinebanking.icin.entity.CheckingAccount;
import com.onlinebanking.icin.entity.SavingsAccount;
import com.onlinebanking.icin.entity.User;
import com.onlinebanking.icin.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/homepage")
    public String homepage(Principal principal, Model model) {
    
    	 User user = userService.findByUsername(principal.getName());
         CheckingAccount checkingAccount = user.getCheckingAccount();
         SavingsAccount savingsAccount = user.getSavingsAccount();

         model.addAttribute("checkingAccount", checkingAccount);
         model.addAttribute("savingsAccount", savingsAccount);

    	return "homepage";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
    	
        User user = new User();

        model.addAttribute("user", user);

        return "signup";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (userService.userExists(user.getUsername(), user.getEmail())) {

            if (userService.emailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.usernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            return "signup";

        } else {
  
        	user.setRole("CUSTOMER");
            userService.createUser(user);

            return "redirect:/";
        }
    }
}
