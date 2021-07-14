package com.onlinebanking.icin;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.onlinebanking.icin.dao.CheckingAccountDao;
import com.onlinebanking.icin.dao.CheckingCheckbookDao;
import com.onlinebanking.icin.dao.CheckingCheckbookRequestDao;
import com.onlinebanking.icin.dao.CheckingTransactionDao;
import com.onlinebanking.icin.dao.RecipientDao;
import com.onlinebanking.icin.dao.SavingsAccountDao;
import com.onlinebanking.icin.dao.SavingsCheckbookDao;
import com.onlinebanking.icin.dao.SavingsCheckbookRequestDao;
import com.onlinebanking.icin.dao.SavingsTransactionDao;
import com.onlinebanking.icin.dao.UserDao;
import com.onlinebanking.icin.entity.CheckingAccount;
import com.onlinebanking.icin.entity.SavingsAccount;
import com.onlinebanking.icin.service.AccountService;

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
	private CheckingCheckbookDao ccbDao;

	@Autowired
	private CheckingCheckbookRequestDao ccbrDao;
	
	@Autowired
	private SavingsCheckbookDao scbDao;
	
	@Autowired
	private SavingsCheckbookRequestDao scbrDao;
	
	@Autowired
	private AccountService accountService;
	
	@Test
	void contextLoads() {
	}

//	@Test
//	void checkTablesAreCreated() {
//		
//		assertEquals(0, caDao.count());
//		assertEquals(0, saDao.count());
//		assertEquals(0, ctDao.count());
//		assertEquals(0, stDao.count());
//		assertEquals(0, userDao.count());
//		assertEquals(0, recDao.count());
//		assertEquals(0, ccbDao.count());
//		assertEquals(0, ccbrDao.count());
//		assertEquals(0, scbDao.count());
//		assertEquals(0, scbrDao.count());
//	}
//	
//	@Sql({"/populateAccounts.sql"})
//	@Test
//	void populateAndCountAccounts() {
//		
//		System.out.println("Checking account count: "+caDao.count());
//		for (CheckingAccount ca : caDao.findAll()) {
//			System.out.println(ca);
//		}
//		
//		System.out.println("Savings account count: "+saDao.count());
//		for (SavingsAccount sa : saDao.findAll()) {
//			System.out.println(sa);
//		}
//		
//		assertEquals(4, caDao.count());
//		assertEquals(3, saDao.count());
//	}
//
//	@Sql({"/populateTransactions.sql"})
//	@Test
//	void populateAndCountTransactions() {
//		
//		System.out.println("transactions");
//
//		assertEquals(4, caDao.count());
//		assertEquals(3, saDao.count());
//		assertEquals(6, ctDao.count());
//		assertEquals(5, stDao.count());
//	}
//	
//		
//	@Sql({"/populateCheckbooks.sql"})
//	@Test
//	void populateAndCountCheckbooks() {
//		
//		assertEquals(6, ccbDao.count());
//		assertEquals(6, scbDao.count());
//	}
//	
//	@Sql({"/populateUsers.sql"})
//	@Test
//	void populateAndCountUsersAndRecipients() {
//		
//		assertEquals(2, userDao.count());
//		assertEquals(3, recDao.count());
//		assertEquals(6, ccbrDao.count());
//		assertEquals(4, scbrDao.count());
//	}
	
//	@Test
//	void addNewCheckingAccount() {
//		
//		Long currentCAcount = caDao.count();
//		caDao.save(new CheckingAccount(1100110015, 22200.00));
//		assertEquals(currentCAcount + 1, caDao.count());
//	}
	
	@Test
	void addNewCheckingAccount() {
		
		Long currentCAcount = caDao.count();
		int numAccountsToAdd = 5;
		for (int i = 0; i < numAccountsToAdd; ++i)
			accountService.createCheckingAccount();
		assertEquals(currentCAcount + numAccountsToAdd, caDao.count());
	}
	
	@Test
	void addNewSavingsAccount() {
		
		Long currentCAcount = saDao.count();
		int numAccountsToAdd = 15;
		for (int i = 0; i < numAccountsToAdd; ++i)
			accountService.createSavingsAccount();
		assertEquals(currentCAcount + numAccountsToAdd, saDao.count());
	}
	
	
//	@Sql({"/populateCheckbookRequests.sql"})
//	@Test
//	void populateAndCountCheckbookRequests() {
//		
//		assertEquals(6, ccbrDao.count());
//		assertEquals(4, scbrDao.count());
//	}
	
}
