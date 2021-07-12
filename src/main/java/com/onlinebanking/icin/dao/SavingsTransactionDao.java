package com.onlinebanking.icin.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlinebanking.icin.entity.SavingsTransaction;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Integer> {

}
