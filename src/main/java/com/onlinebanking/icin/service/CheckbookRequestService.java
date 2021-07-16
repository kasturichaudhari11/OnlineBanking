package com.onlinebanking.icin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.icin.dao.CheckingCheckbookDao;
import com.onlinebanking.icin.dao.SavingsCheckbookDao;
import com.onlinebanking.icin.entity.CheckingAccount;
import com.onlinebanking.icin.entity.CheckingCheckbook;
import com.onlinebanking.icin.entity.CheckingCheckbookRequest;
import com.onlinebanking.icin.entity.SavingsAccount;
import com.onlinebanking.icin.entity.SavingsCheckbook;
import com.onlinebanking.icin.entity.SavingsCheckbookRequest;
import com.onlinebanking.icin.entity.User;

@Service
public class CheckbookRequestService {

	@Autowired
	CheckingCheckbookDao checkingCheckbookDao;

	@Autowired
	SavingsCheckbookDao savingsCheckbookDao;

	public CheckingCheckbook createCheckingCheckbook(CheckingCheckbook checkingCheckbook) {

		return checkingCheckbookDao.save(checkingCheckbook);
	}
	
	public SavingsCheckbook createSavingsCheckbook(SavingsCheckbook savingsCheckbook) {
		
		return savingsCheckbookDao.save(savingsCheckbook);
	}
	
	public CheckingCheckbookRequest requestNewCheckingCheckbook(CheckingAccount ca, CheckingCheckbook ccb) {
		
		return (new CheckingCheckbookRequest("04-15-2021", "Pending", ca, ccb));
	}

	public SavingsCheckbookRequest requestNewSavingsCheckbook(SavingsAccount sa, SavingsCheckbook scb) {
		
		return (new SavingsCheckbookRequest("04-15-2021", "Pending", sa, scb));
	}
	
	public void approveNewCheckingCheckbookRequest(CheckingAccount ca, User authorizer) {
		
		if (authorizer.getRole().equals("Admin") ) {
			
			List<CheckingCheckbookRequest> ccrList = ca.getCheckingCheckbookRequestList();
			
			for (CheckingCheckbookRequest ccr: ccrList) {
				
				if (ccr.isRequestApproved()) {
					
					ccr.setRequestApproved(true);
					ccr.setAuthorizer(authorizer);
					ccr.setDateApproved((new Date()).toString());
					ccr.setStatus("Approved");
				}
			}
		}
	}
	
	public void approveNewSavingsCheckbookRequest(SavingsAccount sa, User authorizer) {
		
		if (authorizer.getRole().equals("Admin") ) {
			
			List<SavingsCheckbookRequest> scrList = sa.getSavingsCheckbookRequestList();
			
			for (SavingsCheckbookRequest scr: scrList) {
				
				if (scr.isRequestApproved()) {
					
					scr.setRequestApproved(true);
					scr.setAuthorizer(authorizer);
					scr.setDateApproved((new Date()).toString());
					scr.setStatus("Approved");
				}
			}
		}
	}
}
