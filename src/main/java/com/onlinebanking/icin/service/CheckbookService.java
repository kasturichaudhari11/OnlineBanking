package com.onlinebanking.icin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.icin.dao.CheckingCheckbookDao;
import com.onlinebanking.icin.dao.SavingsCheckbookDao;
import com.onlinebanking.icin.entity.CheckingCheckbook;
import com.onlinebanking.icin.entity.SavingsCheckbook;

@Service
public class CheckbookService {

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
}
