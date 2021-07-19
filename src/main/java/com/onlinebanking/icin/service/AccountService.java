package com.onlinebanking.icin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.icin.dao.CheckingAccountDao;
import com.onlinebanking.icin.dao.SavingsAccountDao;
import com.onlinebanking.icin.entity.CheckingAccount;
import com.onlinebanking.icin.entity.CheckingTransaction;
import com.onlinebanking.icin.entity.SavingsAccount;
import com.onlinebanking.icin.entity.SavingsTransaction;
import com.onlinebanking.icin.entity.User;

@Service
public class AccountService {

	private static Integer currentCheckingAccoutNumber = 314001000;
	private static Integer currentSavingsAccoutNumber = 514001000;
	
	@Autowired
	private CheckingAccountDao checkingAccountDao;
		
	@Autowired
	private SavingsAccountDao savingsAccountDao;	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	private Integer getNextAvailableCheckingAccountNumber() {
		
		List<CheckingAccount> caList = (List<CheckingAccount>) checkingAccountDao.findAll();
		
		for (CheckingAccount ca : caList) {
			
			if (ca.getNumber() > currentCheckingAccoutNumber) {
				
				currentCheckingAccoutNumber = ca.getNumber();
			}
		}
		
		return ++currentCheckingAccoutNumber;
	}
	
	
	private Integer getNextAvailableSavingsAccountNumber() {

		List<SavingsAccount> saList = (List<SavingsAccount>) savingsAccountDao.findAll();

		for (SavingsAccount sa : saList) {
			
			if (sa.getNumber() > currentSavingsAccoutNumber) {
				
				currentSavingsAccoutNumber = sa.getNumber();
			}
		}

		return ++currentSavingsAccoutNumber;
	}
	
	public CheckingAccount createCheckingAccount() {
		
		CheckingAccount checkingAccount = new CheckingAccount(getNextAvailableCheckingAccountNumber(), 0.0);
		checkingAccountDao.save(checkingAccount);
		
		return checkingAccountDao.findByNumber(checkingAccount.getNumber());
	}
	
	public SavingsAccount createSavingsAccount() {
		
		SavingsAccount savingsAccount = new SavingsAccount(getNextAvailableSavingsAccountNumber(), 0.0);
		savingsAccountDao.save(savingsAccount);
		
		return savingsAccountDao.findByNumber(savingsAccount.getNumber());
	}
	
	public void deposit(String accountType, double amount, String username) {
        User user = userService.findByUsername(username);

        if (accountType.equalsIgnoreCase("Checking")) {

        	System.out.println("Checking account deposit of "+user.getUsername());
        	CheckingAccount checkingAccount = user.getCheckingAccount();
        	System.out.println("Original balance: "+checkingAccount.getBalance());
            
        	checkingAccount.setBalance(checkingAccount.getBalance() + amount);
            System.out.println("Updated balance: "+checkingAccount.getBalance());
            
            checkingAccountDao.save(checkingAccount);

            Date date = new Date();
            
            CheckingTransaction checkingTransaction = new CheckingTransaction(amount, checkingAccount.getBalance(), date, "Deposit to Checking Account", "Finished", "Account", checkingAccount);
            transactionService.saveCheckingTransaction(checkingTransaction);
            
            System.out.println("Transaction completed.");

        } else if (accountType.equalsIgnoreCase("Savings")) {
        	
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setBalance(savingsAccount.getBalance() + amount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(amount, savingsAccount.getBalance(), date, "Deposit to Savings Account", "Finished", "Account", savingsAccount);
            transactionService.saveSavingsTransaction(savingsTransaction);
        }
    }

	public void withdraw(String accountType, double amount, String username) {

		User user = userService.findByUsername(username);
		
		if (accountType.equalsIgnoreCase("Checking")) {
			
			System.out.println("Checking account withdraw of "+user.getUsername());
			CheckingAccount checkingAccount = user.getCheckingAccount();
			System.out.println("Original balance: "+checkingAccount.getBalance());
			
			checkingAccount.setBalance(checkingAccount.getBalance() - amount);
			System.out.println("Updated balance: "+checkingAccount.getBalance());
			
			checkingAccountDao.save(checkingAccount);
			
			Date date = new Date();
			
			CheckingTransaction checkingTransaction = new CheckingTransaction(-amount, checkingAccount.getBalance(), date, "Withdraw from Checking Account", "Finished", "Account", checkingAccount);
			transactionService.saveCheckingTransaction(checkingTransaction);
			
			System.out.println("Transaction completed.");
			
		} else if (accountType.equalsIgnoreCase("Savings")) {
			
			SavingsAccount savingsAccount = user.getSavingsAccount();
			savingsAccount.setBalance(savingsAccount.getBalance() - amount);
			savingsAccountDao.save(savingsAccount);
			
			Date date = new Date();
			SavingsTransaction savingsTransaction = new SavingsTransaction(-amount, savingsAccount.getBalance(), date, "Withdraw from Savings Account", "Finished", "Account", savingsAccount);
			transactionService.saveSavingsTransaction(savingsTransaction);
		}
	}
}
