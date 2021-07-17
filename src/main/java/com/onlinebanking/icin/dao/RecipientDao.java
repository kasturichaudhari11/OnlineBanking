package com.onlinebanking.icin.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlinebanking.icin.entity.Recipient;

public interface RecipientDao extends CrudRepository<Recipient, Integer>{

	Recipient findByName(String recipientName);

	void deleteByName(String recipientName);
}
