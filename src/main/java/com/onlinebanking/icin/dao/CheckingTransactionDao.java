package com.onlinebanking.icin.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlinebanking.icin.entity.CheckingTransaction;

public interface CheckingTransactionDao extends CrudRepository<CheckingTransaction, Integer> {

}
