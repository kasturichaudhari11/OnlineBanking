package com.onlinebanking.icin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.icin.dao.CheckingCheckbookDao;
import com.onlinebanking.icin.dao.CheckingCheckbookRequestDao;
import com.onlinebanking.icin.dao.SavingsCheckbookDao;
import com.onlinebanking.icin.dao.SavingsCheckbookRequestDao;
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
	
	@Autowired
	CheckingCheckbookRequestDao checkingCheckbookRequestDao;
	
	@Autowired
	SavingsCheckbookRequestDao savingsCheckbookRequestDao;
	
	@Autowired
	UserService userService;

	public CheckingCheckbook createCheckingCheckbook(CheckingCheckbook checkingCheckbook) {

		return checkingCheckbookDao.save(checkingCheckbook);
	}
	
	public SavingsCheckbook createSavingsCheckbook(SavingsCheckbook savingsCheckbook) {
		
		return savingsCheckbookDao.save(savingsCheckbook);
	}
	
	public void requestNewCheckbook(String accountType, String checkbookType, String pages, String username) {

		User user = userService.findByUsername(username);
		
		if (accountType.equalsIgnoreCase("checking")) {
			
			CheckingCheckbook checkingCheckbook = createCheckingCheckbook(new CheckingCheckbook(Integer.parseInt(pages), checkbookType, user.getCheckingAccount()));
			requestNewCheckingCheckbook(checkingCheckbook);
	
		} else if (accountType.equalsIgnoreCase("savings")) {
			
			SavingsCheckbook savingsCheckbook = createSavingsCheckbook(new SavingsCheckbook(Integer.parseInt(pages), checkbookType, user.getSavingsAccount()));
			requestNewSavingsCheckbook(savingsCheckbook);
		}
	}
	
	public CheckingCheckbookRequest requestNewCheckingCheckbook(CheckingCheckbook ccb) {
		
		CheckingCheckbookRequest ccr = new CheckingCheckbookRequest((new Date()).toString(), "Pending", ccb.getCheckingAccount(), ccb);
		return checkingCheckbookRequestDao.save(ccr);
	}

	public SavingsCheckbookRequest requestNewSavingsCheckbook(SavingsCheckbook scb) {
		
		return savingsCheckbookRequestDao.save(new SavingsCheckbookRequest((new Date()).toString(), "Pending", scb.getSavingsAccount(), scb));
	}
	
	public CheckingCheckbookRequest approveNewCheckingCheckbookRequest(Integer requestId, User authorizer) {
		
		CheckingCheckbookRequest ccr = checkingCheckbookRequestDao.findById(requestId).get();

		if (authorizer.getRole().equalsIgnoreCase("admin")) {
			
			
			if (!ccr.isRequestApproved()) {
					
					System.out.println("Approved");
					ccr.setRequestApproved(true);
					ccr.setAuthorizer(authorizer);
					ccr.setDateApproved((new Date()).toString());
					ccr.setStatus("Approved");
					checkingCheckbookRequestDao.save(ccr);
			}
		}
		
		return ccr;
	}

	public SavingsCheckbookRequest approveNewSavingsCheckbookRequest(Integer requestId, User authorizer) {
		
		SavingsCheckbookRequest scr = savingsCheckbookRequestDao.findById(requestId).get();

		if (authorizer.getRole().equalsIgnoreCase("admin")) {
			
			
			if (!scr.isRequestApproved()) {
				
				System.out.println("Approved");
				scr.setRequestApproved(true);
				scr.setAuthorizer(authorizer);
				scr.setDateApproved((new Date()).toString());
				scr.setStatus("Approved");
				scr = savingsCheckbookRequestDao.save(scr);
			}
		}
		
		return scr;
	}
	
	public List<CheckingCheckbookRequest> findCheckingCheckbookRequests() {
		
		return (List<CheckingCheckbookRequest>) checkingCheckbookRequestDao.findAll();
	}
	
	public CheckingCheckbookRequest findCheckingCheckbookRequestById(Integer requestId) {
	
		return checkingCheckbookRequestDao.findById(requestId).get();
	}
	
	public List<SavingsCheckbookRequest> findSavingsCheckbookRequests() {
		
		return (List<SavingsCheckbookRequest>) savingsCheckbookRequestDao.findAll();
	}
	
	public SavingsCheckbookRequest findSavingsCheckbookRequestById(Integer requestId) {
		
		return savingsCheckbookRequestDao.findById(requestId).get();
	}
}
