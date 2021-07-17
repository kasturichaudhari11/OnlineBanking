package com.onlinebanking.icin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
