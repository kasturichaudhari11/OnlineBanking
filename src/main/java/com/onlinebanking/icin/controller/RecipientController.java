package com.onlinebanking.icin.controller;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinebanking.icin.entity.Recipient;
import com.onlinebanking.icin.entity.User;
import com.onlinebanking.icin.service.RecipientService;
import com.onlinebanking.icin.service.UserService;

@Controller
@RequestMapping("/recipient")
public class RecipientController {

    @Autowired
    private RecipientService recipientService;
    
    @Autowired
    private UserService userService;
    
	@RequestMapping(method = RequestMethod.GET)
	public String recipient(Model model, Principal principal) {

		List<Recipient> recipientList = recipientService.findRecipientList(principal.getName());

		Recipient recipient = new Recipient();

		model.addAttribute("recipientList", recipientList);
		model.addAttribute("recipient", recipient);

		return "recipient";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String recipientAdd(@ModelAttribute("recipient") Recipient recipient, Principal principal) {

		if (recipientService.findRecipientByName(recipient.getName()) == null) {
			User user = userService.findByUsername(principal.getName());
			recipient.setUser(user);
			recipientService.saveRecipient(recipient);
		}

		return "redirect:/recipient";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String recipientEdit(@RequestParam(value = "recipientName") String recipientName, Model model,
			Principal principal) {

		Recipient recipient = recipientService.findRecipientByName(recipientName);
		List<Recipient> recipientList = recipientService.findRecipientList(principal.getName());

		model.addAttribute("recipientList", recipientList);
		model.addAttribute("recipient", recipient);

		return "recipient";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@Transactional
	public String recipientDelete(@RequestParam(value = "recipientName") String recipientName, Model model,
			Principal principal) {

		recipientService.deleteRecipientByName(recipientName);

		List<Recipient> recipientList = recipientService.findRecipientList(principal.getName());

		Recipient recipient = new Recipient();
		model.addAttribute("recipient", recipient);
		model.addAttribute("recipientList", recipientList);

		return "recipient";
	}
}
