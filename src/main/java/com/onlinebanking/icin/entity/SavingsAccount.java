package com.onlinebanking.icin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SavingsAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer number;
	private Double balance;
	
	@OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SavingsTransaction> savingsTransactionList;

	@OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SavingsCheckbook> savingsCheckbookList;	
	
	@OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SavingsCheckbookRequest> savingsCheckbookRequestList;	

	public SavingsAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SavingsAccount(Integer number, Double balance) {
		super();
		this.number = number;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<SavingsTransaction> getSavingsTransactionList() {
		return savingsTransactionList;
	}

	public void setSavingsTransactionList(List<SavingsTransaction> savingsTransactionList) {
		this.savingsTransactionList = savingsTransactionList;
	}

	public List<SavingsCheckbook> getSavingsCheckbookList() {
		return savingsCheckbookList;
	}

	public void setSavingsCheckbookList(List<SavingsCheckbook> savingsCheckbookList) {
		this.savingsCheckbookList = savingsCheckbookList;
	}

	public List<SavingsCheckbookRequest> getSavingsCheckbookRequestList() {
		return savingsCheckbookRequestList;
	}

	public void setSavingsCheckbookRequestList(List<SavingsCheckbookRequest> savingsCheckbookRequestList) {
		this.savingsCheckbookRequestList = savingsCheckbookRequestList;
	}
}
