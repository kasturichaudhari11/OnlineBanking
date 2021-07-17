package com.onlinebanking.icin.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
//	@RequestMapping(value = "/checking-list", method = RequestMethod.GET)
	@RequestMapping(method = RequestMethod.GET)
	public String checkbook(Model model, Principal principal) {

		User user = userService.findByUsername(principal.getName());
		List<CheckingCheckbookRequest> checkingCheckbookRequestList = user.getCheckingAccount().getCheckingCheckbookRequestList();
		List<SavingsCheckbookRequest> savingsCheckbookRequestList = user.getSavingsAccount().getSavingsCheckbookRequestList();

		model.addAttribute("checkingCheckbookRequestList", checkingCheckbookRequestList);
		model.addAttribute("savingsCheckbookRequestList", savingsCheckbookRequestList);

		return "checkbook";
	}
 
//    @RequestMapping(value = "/checking", method = RequestMethod.POST)
//    public String createCheckingRequest(@ModelAttribute("type") String type, @ModelAttribute("pages") String pages, @ModelAttribute("accountType") String accountType, Principal principal) {
//        
//		checkbookRequestService.requestNewCheckingCheckbook(type, pages, principal.getName());
//        return "redirect:/homepage";
//    }
//    
//    @RequestMapping(value = "/savings", method = RequestMethod.POST)
//    public String createSavingsRequest(@ModelAttribute("type") String type, @ModelAttribute("pages") String pages, @ModelAttribute("accountType") String accountType, Principal principal) {
//    	
//    	checkbookRequestService.requestNewSavingsCheckbook(type, pages, principal.getName());
//    	return "redirect:/homepage";
//    }
}
