package com.onlinebanking.icin.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlinebanking.icin.entity.SavingsAccount;


public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Integer> {

	SavingsAccount findByNumber(Integer number);
}
