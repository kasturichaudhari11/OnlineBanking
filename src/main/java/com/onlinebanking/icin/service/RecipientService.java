package com.onlinebanking.icin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.icin.dao.RecipientDao;
import com.onlinebanking.icin.entity.Recipient;

@Service
public class RecipientService {

	@Autowired
	RecipientDao recipientDao;

	public List<Recipient> findRecipientList(String username) {

		List<Recipient> recipientList = new ArrayList<Recipient>();

		for (Recipient rec : (List<Recipient>) recipientDao.findAll()) {
			
			if (rec.getUser().getUsername().equals(username)) {
				
				recipientList.add(rec);
			}
		}
		
		return recipientList;
	}

	public Recipient saveRecipient(Recipient recipient) {
		return recipientDao.save(recipient);
	}

	public Recipient findRecipientByName(String recipientName) {
		return recipientDao.findByName(recipientName);
	}

	public void deleteRecipientByName(String recipientName) {
		recipientDao.deleteByName(recipientName);
	}
}
