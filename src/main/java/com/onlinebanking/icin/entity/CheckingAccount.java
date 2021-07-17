package com.onlinebanking.icin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class CheckingAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer number;
	private Double balance;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "checkingAccount", cascade = CascadeType.ALL)
	private List<CheckingTransaction> checkingTransactionList;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "checkingAccount", cascade = CascadeType.ALL)
	private List<CheckingCheckbook> checkingCheckbookList;	
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "checkingAccount", cascade = CascadeType.ALL)
	private List<CheckingCheckbookRequest> checkingCheckbookRequestList;	
	
	public CheckingAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(Integer number, Double balance) {
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

	public List<CheckingTransaction> getCheckingTransactionList() {
		return checkingTransactionList;
	}

	public void setCheckingTransactionList(List<CheckingTransaction> checkingTransactionList) {
		this.checkingTransactionList = checkingTransactionList;
	}

	public List<CheckingCheckbook> getCheckingCheckbookList() {
		return checkingCheckbookList;
	}

	public void setCheckingCheckbookList(List<CheckingCheckbook> checkingCheckbookList) {
		this.checkingCheckbookList = checkingCheckbookList;
	}

	public List<CheckingCheckbookRequest> getCheckingCheckbookRequestList() {
		return checkingCheckbookRequestList;
	}

	public void setCheckingCheckbookRequestList(List<CheckingCheckbookRequest> checkingCheckbookRequestList) {
		this.checkingCheckbookRequestList = checkingCheckbookRequestList;
	}

	@Override
	public String toString() {
		return "CheckingAccount [id=" + id + ", number=" + number + ", balance=" + balance
				+ ", checkingTransactionList=" + checkingTransactionList + ", checkingCheckbookList="
				+ checkingCheckbookList + ", checkingCheckbookRequestList=" + checkingCheckbookRequestList + "]";
	}
}
