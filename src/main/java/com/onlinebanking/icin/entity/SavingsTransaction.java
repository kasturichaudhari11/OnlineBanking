package com.onlinebanking.icin.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SavingsTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double amount;
	private Double availableBalance;
	private Date date;
	private String description;
	private String status;
	private String type;

	@ManyToOne
	@JoinColumn(name = "savings_account_id")
	private SavingsAccount savingsAccount;

	public SavingsTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SavingsTransaction(Double amount, Double availableBalance, Date date, String description, String status,
			String type, SavingsAccount savingsAccount) {
		super();
		this.amount = amount;
		this.availableBalance = availableBalance;
		this.date = date;
		this.description = description;
		this.status = status;
		this.type = type;
		this.savingsAccount = savingsAccount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}
}
