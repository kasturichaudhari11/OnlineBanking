package com.onlinebanking.icin.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlinebanking.icin.entity.CheckingAccount;


public interface CheckingAccountDao extends CrudRepository<CheckingAccount, Integer> {

    CheckingAccount findByNumber(Integer number);
}
