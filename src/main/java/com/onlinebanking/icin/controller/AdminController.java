package com.onlinebanking.icin.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinebanking.icin.entity.CheckingCheckbook;
import com.onlinebanking.icin.entity.CheckingCheckbookRequest;
import com.onlinebanking.icin.entity.SavingsCheckbookRequest;
import com.onlinebanking.icin.entity.User;
import com.onlinebanking.icin.service.CheckbookRequestService;
import com.onlinebanking.icin.service.TransactionService;
import com.onlinebanking.icin.service.UserService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private CheckbookRequestService checkbookRequestService;

	@RequestMapping(method = RequestMethod.GET)
//    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
	public String checkingAccount(Model model, Principal principal) {

		List<User> userList = userService.findUserList();
		model.addAttribute("userList", userList);

		return "admin";
	}

	@RequestMapping(value = "/toggleStatus", method = RequestMethod.POST)
	public String toggleUserStatus(@ModelAttribute("username") String username) {

		User user = userService.findByUsername(username);

		if (user.isEnabled()) {

			userService.disableUser(username);

		} else {

			userService.enableUser(username);
		}

		return "redirect:/admin";
	}

	@RequestMapping(value = "/approveCheckbook", method = RequestMethod.GET)
	public String getCheckbookRequests(Model model, Principal principal) {

		List<CheckingCheckbookRequest> checkingCheckbookRequestList = checkbookRequestService.findCheckingCheckbookRequests();
		model.addAttribute("checkingCheckbookRequestList", checkingCheckbookRequestList);
		
		
		List<SavingsCheckbookRequest> savingsCheckbookRequestList = checkbookRequestService.findSavingsCheckbookRequests();
		model.addAttribute("savingsCheckbookRequestList", savingsCheckbookRequestList);
		
		return "approveCheckbook";
	}

	@RequestMapping(value = "/approveCheckbook", method = RequestMethod.POST)
	public String approveCheckbookRequests(@ModelAttribute("requestId") String requestId, @ModelAttribute("accountType") String accountType, Principal principal) {

		if (accountType.equalsIgnoreCase("checking")) {
			
			checkbookRequestService.approveNewCheckingCheckbookRequest(Integer.parseInt(requestId), userService.findByUsername(principal.getName()));
		
		} else if (accountType.equalsIgnoreCase("savings")) {
		
			checkbookRequestService.approveNewSavingsCheckbookRequest(Integer.parseInt(requestId), userService.findByUsername(principal.getName()));			
		}
		
		return "redirect:/admin/approveCheckbook";
	}
}
