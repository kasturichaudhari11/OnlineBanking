package com.onlinebanking.icin.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlinebanking.icin.entity.CheckingCheckbookRequest;
import com.onlinebanking.icin.entity.SavingsCheckbookRequest;
import com.onlinebanking.icin.entity.User;
import com.onlinebanking.icin.service.CheckbookRequestService;
import com.onlinebanking.icin.service.UserService;

@Controller
@RequestMapping("/checkbook")
public class CheckbookRequestController {

	@Autowired
	CheckbookRequestService checkbookRequestService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String checkbook(Model model, Principal principal) {

		User user = userService.findByUsername(principal.getName());
		List<CheckingCheckbookRequest> checkingCheckbookRequestList = user.getCheckingAccount().getCheckingCheckbookRequestList();
		List<SavingsCheckbookRequest> savingsCheckbookRequestList = user.getSavingsAccount().getSavingsCheckbookRequestList();

		model.addAttribute("checkingCheckbookRequestList", checkingCheckbookRequestList);
		model.addAttribute("savingsCheckbookRequestList", savingsCheckbookRequestList);

		return "checkbook";
	}

    @RequestMapping(value = "/requestCheckbook", method = RequestMethod.GET)
    public String requestCheckbook(Model model, Principal principal) {
    	
        model.addAttribute("accountType", "");
        model.addAttribute("checkbookType", "");
        model.addAttribute("checkbookPages", "");

        return "requestCheckbook";
    }
    
    @RequestMapping(value = "/requestCheckbook", method = RequestMethod.POST)
    public String createRequest(@ModelAttribute("accountType") String accountType, @ModelAttribute("checkbookType") String checkbookType, @ModelAttribute("checkbookPages") String checkbookPages, Principal principal) {
        
		checkbookRequestService.requestNewCheckbook(accountType, checkbookType, checkbookPages, principal.getName());
        return "redirect:/checkbook";
    }
}
