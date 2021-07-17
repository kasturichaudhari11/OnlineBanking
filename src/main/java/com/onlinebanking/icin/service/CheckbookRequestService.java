package com.onlinebanking.icin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.icin.dao.CheckingCheckbookDao;
import com.onlinebanking.icin.dao.CheckingCheckbookRequestDao;
import com.onlinebanking.icin.dao.SavingsCheckbookDao;
import com.onlinebanking.icin.dao.SavingsCheckbookRequestDao;
import com.onlinebanking.icin.dao.UserDao;
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
	
	public CheckingCheckbookRequest requestNewCheckingCheckbook(String type, String pages, String username) {

		User user = userService.findByUsername(username);
			
		CheckingCheckbook checkingCheckbook = new CheckingCheckbook(Integer.parseInt(pages), type, user.getCheckingAccount());
		return requestNewCheckingCheckbook(checkingCheckbook);
	}

	public SavingsCheckbookRequest requestNewSavingsCheckbook(String type, String pages, String username) {
	
		User user = userService.findByUsername(username);
		
		SavingsCheckbook savingsCheckbook = new SavingsCheckbook(Integer.parseInt(pages), type, user.getSavingsAccount());
		return requestNewSavingsCheckbook(savingsCheckbook);
	}
	
	public CheckingCheckbookRequest requestNewCheckingCheckbook(CheckingCheckbook ccb) {
		
		CheckingCheckbookRequest ccr = new CheckingCheckbookRequest((new Date()).toString(), "Pending", ccb.getCheckingAccount(), ccb);
		return checkingCheckbookRequestDao.save(ccr);
	}

	public SavingsCheckbookRequest requestNewSavingsCheckbook(SavingsCheckbook scb) {
		
		return savingsCheckbookRequestDao.save(new SavingsCheckbookRequest((new Date()).toString(), "Pending", scb.getSavingsAccount(), scb));
	}
	
	public void approveNewCheckingCheckbookRequest(CheckingAccount ca, User authorizer, boolean approve) {
		
		if (authorizer.getRole().equalsIgnoreCase("admin")) {
			
			List<CheckingCheckbookRequest> ccrList = ca.getCheckingCheckbookRequestList();
			System.out.println("checkingBook request approval by "+authorizer.getUsername());
			System.out.println("checkingBook request list:  "+ccrList);
			
			for (CheckingCheckbookRequest ccr: ccrList) {
				
				System.out.println("ProcessingCCR: "+ccr);
				if (!ccr.isRequestApproved() && approve) {
					
					System.out.println("Approved");
					ccr.setRequestApproved(true);
					ccr.setAuthorizer(authorizer);
					ccr.setDateApproved((new Date()).toString());
					ccr.setStatus("Approved");
					checkingCheckbookRequestDao.save(ccr);
				}
			}
		}
	}
	
	public void approveNewSavingsCheckbookRequest(SavingsAccount sa, User authorizer, boolean approve) {
		
		System.out.println("Authorizer" + authorizer.getUsername() + " role: " + authorizer.getRole());
		if (authorizer.getRole().equalsIgnoreCase("admin")) {
			
			List<SavingsCheckbookRequest> scrList = sa.getSavingsCheckbookRequestList();
			
			for (SavingsCheckbookRequest scr: scrList) {
				
				if (!scr.isRequestApproved() && approve) {
					
					scr.setRequestApproved(true);
					scr.setAuthorizer(authorizer);
					scr.setDateApproved((new Date()).toString());
					scr.setStatus("Approved");
					savingsCheckbookRequestDao.save(scr);
				}
			}
		}
	}
}
