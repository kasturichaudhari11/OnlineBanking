package com.onlinebanking.icin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.icin.dao.CheckingAccountDao;
import com.onlinebanking.icin.dao.SavingsAccountDao;
import com.onlinebanking.icin.entity.CheckingAccount;
import com.onlinebanking.icin.entity.SavingsAccount;

@Service
public class AccountService {

	private static Integer currentAccoutNumber = 314001000;
	
	@Autowired
	private CheckingAccountDao checkingAccountDao;
		
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	
	private Integer getNextAvailableAccountNumber() {
		
		return ++currentAccoutNumber;
	}
	
	public CheckingAccount createCheckingAccount() {
		
		CheckingAccount checkingAccount = new CheckingAccount(getNextAvailableAccountNumber(), 0.0);
		checkingAccountDao.save(checkingAccount);
		
		return checkingAccountDao.findByNumber(checkingAccount.getNumber());
	}
	
	public SavingsAccount createSavingsAccount() {
		
		SavingsAccount savingsAccount = new SavingsAccount(getNextAvailableAccountNumber(), 0.0);
		savingsAccountDao.save(savingsAccount);
		
		return savingsAccountDao.findByNumber(savingsAccount.getNumber());
	}
}
