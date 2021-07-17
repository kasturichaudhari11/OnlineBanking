package com.onlinebanking.icin.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinebanking.icin.entity.CheckingAccount;
import com.onlinebanking.icin.entity.Recipient;
import com.onlinebanking.icin.entity.SavingsAccount;
import com.onlinebanking.icin.entity.User;
import com.onlinebanking.icin.service.RecipientService;
import com.onlinebanking.icin.service.TransactionService;
import com.onlinebanking.icin.service.UserService;

@Controller
@RequestMapping("/transfer")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private RecipientService recipientService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.GET)
    public String betweenAccounts(Model model) {
        model.addAttribute("transferFrom", "");
        model.addAttribute("transferTo", "");
        model.addAttribute("amount", "");

        return "betweenAccounts";
    }

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public String betweenAccountsPost(
            @ModelAttribute("transferFrom") String transferFrom,
            @ModelAttribute("transferTo") String transferTo,
            @ModelAttribute("amount") String amount,
            Principal principal) throws NumberFormatException, Exception {
    	
        User user = userService.findByUsername(principal.getName());
        CheckingAccount checkingAccount = user.getCheckingAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();
        transactionService.betweenAccountsTransfer(transferFrom, transferTo, Double.parseDouble(amount), checkingAccount, savingsAccount);

        return "redirect:/homepage";
    }
    
    @RequestMapping(value = "/recipient", method = RequestMethod.GET)
    public String recipient(Model model, Principal principal) {
    	
        List<Recipient> recipientList = recipientService.findRecipientList(principal.getName());

        Recipient recipient = new Recipient();

        model.addAttribute("recipientList", recipientList);
        model.addAttribute("recipient", recipient);

        return "recipient";
    }

    @RequestMapping(value = "/recipient/add", method = RequestMethod.POST)
    public String recipientPost(@ModelAttribute("recipient") Recipient recipient, Principal principal) {

    	if (recipientService.findRecipientByName(recipient.getName()) == null)
    	{
	        User user = userService.findByUsername(principal.getName());
	        recipient.setUser(user);
	        recipientService.saveRecipient(recipient);
    	}

        return "redirect:/transfer/recipient";
    }

    @RequestMapping(value = "/recipient/edit", method = RequestMethod.GET)
    public String recipientEdit(@RequestParam(value = "recipientName") String recipientName, Model model, Principal principal) {

        Recipient recipient = recipientService.findRecipientByName(recipientName);
        List<Recipient> recipientList = recipientService.findRecipientList(principal.getName());

        model.addAttribute("recipientList", recipientList);
        model.addAttribute("recipient", recipient);

        return "recipient";
    }
//
//    @RequestMapping(value = "/recipient/delete", method = RequestMethod.GET)
//    @Transactional
//    public String recipientDelete(@RequestParam(value = "recipientName") String recipientName, Model model, Principal principal) {
//
//        transactionService.deleteRecipientByName(recipientName);
//
//        List<Recipient> recipientList = transactionService.findRecipientList(principal);
//
//        Recipient recipient = new Recipient();
//        model.addAttribute("recipient", recipient);
//        model.addAttribute("recipientList", recipientList);
//
//
//        return "recipient";
//    }
    
    
}
