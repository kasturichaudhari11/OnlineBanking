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

import com.onlinebanking.icin.entity.CheckingAccount;
import com.onlinebanking.icin.entity.Recipient;
import com.onlinebanking.icin.entity.SavingsAccount;
import com.onlinebanking.icin.entity.User;
import com.onlinebanking.icin.service.RecipientService;
import com.onlinebanking.icin.service.TransactionService;
import com.onlinebanking.icin.service.UserService;

@Controller
@RequestMapping("/transfer")
public class TransferController {

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
    

    @RequestMapping(value = "/toRecipient", method = RequestMethod.GET)
    public String toSomeoneElse(Model model, Principal principal) {
    	
        List<Recipient> recipientList = recipientService.findRecipientList(principal.getName());

        model.addAttribute("recipientList", recipientList);
        model.addAttribute("accountType", "");

        return "toRecipient";
    }

    @RequestMapping(value = "/toRecipient", method = RequestMethod.POST)
    public String toSomeoneElsePost(@ModelAttribute("recipientName") String recipientName, @ModelAttribute("accountType") String accountType, @ModelAttribute("amount") String amount, Principal principal) {
      
    	User user = userService.findByUsername(principal.getName());
        Recipient recipient = recipientService.findRecipientByName(recipientName);
        transactionService.transferToRecipient(recipient, accountType, Double.parseDouble(amount), user.getCheckingAccount(), user.getSavingsAccount());

        return "redirect:/homepage";
    }
}
