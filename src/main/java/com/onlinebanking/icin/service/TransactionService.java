package com.onlinebanking.icin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.icin.dao.CheckingAccountDao;
import com.onlinebanking.icin.dao.CheckingTransactionDao;
import com.onlinebanking.icin.dao.SavingsAccountDao;
import com.onlinebanking.icin.dao.SavingsTransactionDao;
import com.onlinebanking.icin.entity.CheckingAccount;
import com.onlinebanking.icin.entity.CheckingTransaction;
import com.onlinebanking.icin.entity.Recipient;
import com.onlinebanking.icin.entity.SavingsAccount;
import com.onlinebanking.icin.entity.SavingsTransaction;
import com.onlinebanking.icin.entity.User;

@Service
public class TransactionService {

	@Autowired
	private UserService userService;

	@Autowired
	private CheckingTransactionDao checkingTransactionDao;

	@Autowired
	private SavingsTransactionDao savingsTransactionDao;

	@Autowired
	private CheckingAccountDao checkingAccountDao;

	@Autowired
	private SavingsAccountDao savingsAccountDao;

	public void saveCheckingTransaction(CheckingTransaction checkingTransaction) {
		checkingTransactionDao.save(checkingTransaction);
	}

	public void saveSavingsTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
	}

	public List<CheckingTransaction> findCheckingTransactionList(String username) {
		User user = userService.findByUsername(username);
		System.out.println("User found in find checking list");
		List<CheckingTransaction> checkingTransactionList = user.getCheckingAccount().getCheckingTransactionList();
		System.out.println(checkingTransactionList);

		return checkingTransactionList;
	}

	public List<SavingsTransaction> findSavingsTransactionList(String username) {
		User user = userService.findByUsername(username);
		List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();

		return savingsTransactionList;
	}

	public void betweenAccountsTransfer(String transferFrom, String transferTo, Double amount, CheckingAccount checkingAccount, SavingsAccount savingsAccount) throws Exception {
     
    	if (transferFrom.equalsIgnoreCase("Checking") && transferTo.equalsIgnoreCase("Savings")) {
            checkingAccount.setBalance(checkingAccount.getBalance() - amount);
            savingsAccount.setBalance(savingsAccount.getBalance() + amount);
            checkingAccountDao.save(checkingAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            CheckingTransaction checkingTransaction = new CheckingTransaction(amount, checkingAccount.getBalance(), date, "Between account transfer from " + transferFrom + " to " + transferTo, "Transfer", "Finished", checkingAccount);
            SavingsTransaction savingsTransaction = new SavingsTransaction(amount, savingsAccount.getBalance(), date, "Between account transfer to " + transferTo + " from " + transferFrom, "Transfer", "Finished", savingsAccount);
            checkingTransactionDao.save(checkingTransaction);
            savingsTransactionDao.save(savingsTransaction);
            
        } else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Checking")) {
            checkingAccount.setBalance(checkingAccount.getBalance() + amount);
            savingsAccount.setBalance(savingsAccount.getBalance() - amount);
            checkingAccountDao.save(checkingAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(amount, savingsAccount.getBalance(), date, "Between account transfer from " + transferFrom + " to " + transferTo, "Transfer", "Finished", savingsAccount);
            CheckingTransaction checkingTransaction = new CheckingTransaction(amount, checkingAccount.getBalance(), date, "Between account transfer to " + transferTo + " from " + transferFrom, "Transfer", "Finished", checkingAccount);
            savingsTransactionDao.save(savingsTransaction);
            checkingTransactionDao.save(checkingTransaction);
            
        } else {
            throw new Exception("Invalid Transfer");
        }
    }

	public void transferToRecipient(Recipient recipient, String accountType, Double amount, CheckingAccount checkingAccount, SavingsAccount savingsAccount) {

		if (accountType.equalsIgnoreCase("Checking")) {
			
            checkingAccount.setBalance(checkingAccount.getBalance() - amount);
            checkingAccountDao.save(checkingAccount);

            Date date = new Date();
            CheckingTransaction checkingTransaction = new CheckingTransaction(amount, checkingAccount.getBalance(), date, "Transfer to recipient " + recipient.getName(), "Transfer", "Finished", checkingAccount);
            checkingTransactionDao.save(checkingTransaction);
       
		} else if (accountType.equalsIgnoreCase("Savings")) {
        
			savingsAccount.setBalance(savingsAccount.getBalance() - amount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(amount, savingsAccount.getBalance(), date, "Transfer to recipient " + recipient.getName(), "Transfer", "Finished", savingsAccount);
            savingsTransactionDao.save(savingsTransaction);
        }
    }

    
}
