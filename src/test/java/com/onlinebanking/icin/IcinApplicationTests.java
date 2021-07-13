package com.onlinebanking.icin;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.onlinebanking.icin.dao.CheckingAccountDao;
import com.onlinebanking.icin.dao.CheckingCheckBookDao;
import com.onlinebanking.icin.dao.CheckingCheckBookRequestDao;
import com.onlinebanking.icin.dao.CheckingTransactionDao;
import com.onlinebanking.icin.dao.RecipientDao;
import com.onlinebanking.icin.dao.SavingsAccountDao;
import com.onlinebanking.icin.dao.SavingsCheckBookDao;
import com.onlinebanking.icin.dao.SavingsCheckBookRequestDao;
import com.onlinebanking.icin.dao.SavingsTransactionDao;
import com.onlinebanking.icin.dao.UserDao;
import com.onlinebanking.icin.entity.CheckingAccount;
import com.onlinebanking.icin.entity.SavingsAccount;

//@Sql({"/populateAccounts.sql", "/populateTransactions.sql"})
@SpringBootTest
class IcinApplicationTests {

	@Autowired
	private CheckingAccountDao caDao;
	
	@Autowired
	private SavingsAccountDao saDao;
	
	@Autowired
	private CheckingTransactionDao ctDao;

	@Autowired
	private SavingsTransactionDao stDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RecipientDao recDao;
	
	@Autowired
	private CheckingCheckBookDao ccbDao;

	@Autowired
	private CheckingCheckBookRequestDao ccbrDao;
	
	@Autowired
	private SavingsCheckBookDao scbDao;
	
	@Autowired
	private SavingsCheckBookRequestDao scbrDao;
	
	@Test
	void contextLoads() {
	}

	@Test
	void checkTablesAreCreated() {
		
		assertEquals(0, caDao.count());
		assertEquals(0, saDao.count());
		assertEquals(0, ctDao.count());
		assertEquals(0, stDao.count());
		assertEquals(0, userDao.count());
		assertEquals(0, recDao.count());
		assertEquals(0, ccbDao.count());
		assertEquals(0, ccbrDao.count());
		assertEquals(0, scbDao.count());
		assertEquals(0, scbrDao.count());
	}
	
	@Sql({"/populateAccounts.sql"})
	@Test
	void populateAndCountAccounts() {
		
		System.out.println("Checking account count: "+caDao.count());
		for (CheckingAccount ca : caDao.findAll()) {
			System.out.println(ca);
		}
		
		System.out.println("Savings account count: "+saDao.count());
		for (SavingsAccount sa : saDao.findAll()) {
			System.out.println(sa);
		}
		
		assertEquals(4, caDao.count());
		assertEquals(3, saDao.count());
	}

	@Sql({"/populateTransactions.sql"})
	@Test
	void populateAndCountTransactions() {
		
		System.out.println("transactions");

		assertEquals(4, caDao.count());
		assertEquals(3, saDao.count());
		assertEquals(6, ctDao.count());
		assertEquals(5, stDao.count());
	}
	
	@Sql({"/populateUsers.sql"})
	@Test
	void populateAndCountUsersAndRecipients() {
	
		assertEquals(2, userDao.count());
		assertEquals(3, recDao.count());
	}
	
//	@Sql({"/populateCheckbooks.sql"})
//	@Test
//	void populateAndCountCheckbooks() {
//	
//	}
}