package com.onlinebanking.icin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CheckingCheckbook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer numberOfPages;
	private String type;

	@ManyToOne
	@JoinColumn(name = "checking_account_id")
	private CheckingAccount checkingAccount;

	public CheckingCheckbook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckingCheckbook(Integer numberOfPages, String type, CheckingAccount checkingAccount) {
		super();
		this.numberOfPages = numberOfPages;
		this.type = type;
		this.checkingAccount = checkingAccount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}

	public void setCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
}
