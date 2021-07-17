package com.onlinebanking.icin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlinebanking.icin.entity.CheckingAccount;
import com.onlinebanking.icin.entity.SavingsAccount;
import com.onlinebanking.icin.entity.User;
import com.onlinebanking.icin.service.TransactionService;
import com.onlinebanking.icin.service.UserService;

@Controller
@RequestMapping("/transfer")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

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
}
