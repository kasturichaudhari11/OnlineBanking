package com.onlinebanking.icin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
import com.onlinebanking.icin.entity.CheckingTransaction;
import com.onlinebanking.icin.entity.SavingsAccount;
import com.onlinebanking.icin.entity.SavingsTransaction;
import com.onlinebanking.icin.entity.User;
import com.onlinebanking.icin.service.AccountService;
import com.onlinebanking.icin.service.TransactionService;
import com.onlinebanking.icin.service.UserService;

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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
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
	
	@Test
	void addNewUser() {
 
		Long currentUserCount = userDao.count();
		User user = new User("username1", "password1", "firstName1", "lastName1", "first.last@email.com", "8379478838", "Address1", "customer", true);
		userService.createUser(user);
		assertEquals(currentUserCount + 1, userDao.count());
		user = new User("username2", "password2", "firstName2", "lastName2", "first2.last2@email.com", "9379479938", "Address2", "customer", true);
		userService.createUser(user);
		assertEquals(currentUserCount + 2, userDao.count());

		user = new User("username3", "password3", "firstName3", "lastName3", "first3.last3@email.com", "8379478838", "Address3", "customer", true);
		userService.createUser(user);
		user = new User("username4", "password4", "firstName4", "lastName4", "first4.last4@email.com", "9379479938", "Address4", "customer", true);
		userService.createUser(user);
		assertEquals(currentUserCount + 4, userDao.count());
	}
	
	@Test
	void addNewCheckingTransaction() {
		
		CheckingAccount ca = accountService.createCheckingAccount();
		ca.setBalance(33333.33);
		Double amount = 2500.00;
		Date date = new Date();
		
		CheckingTransaction checkingTransaction = new CheckingTransaction(amount, ca.getBalance() + amount, date, "Deposit to Checking Account", "Finished", "Account", ca);
        transactionService.saveCheckingTransaction(checkingTransaction);
        
        checkingTransaction = ctDao.findById((int)(ctDao.count())).get();
        
        assertEquals((Double)(33333.33 + amount), checkingTransaction.getAvailableBalance());
        assertEquals((Double)(amount), checkingTransaction.getAmount());
	}
	
	@Test
	void addNewSavingsTransaction() {
		
		SavingsAccount sa = accountService.createSavingsAccount();
		sa.setBalance(33333.33);
		Double amount = 2500.00;
		Date date = new Date();
		
		SavingsTransaction savingsTransaction = new SavingsTransaction(amount, sa.getBalance() + amount, date, "Deposit to Checking Account", "Finished", "Account", sa);
		transactionService.saveSavingsTransaction(savingsTransaction);
		
		savingsTransaction = stDao.findById((int)(stDao.count())).get();
		
		assertEquals((Double)(33333.33 + amount), savingsTransaction.getAvailableBalance());
		assertEquals((Double)(amount), savingsTransaction.getAmount());
	}
	
	@Test
	void depositToCheckingAccount() {

		User user = new User("username1", "password1", "firstName1", "lastName1", "first.last@email.com", "8379478838", "Address1", "customer", true);
		userService.createUser(user);
		user = new User("username2", "password2", "firstName2", "lastName2", "first2.last2@email.com", "9379479938", "Address2", "customer", true);
		userService.createUser(user);
		
		user = userDao.findByUsername("username2");
		Double balance = user.getCheckingAccount().getBalance();
		
		accountService.deposit("Checking", 200.00, "username2");		
		user = userDao.findByUsername("username2");		
		assertEquals((Double)(balance + 200.0), (Double)user.getCheckingAccount().getBalance());

		accountService.deposit("Checking", 500.00, "username2");		
		user = userDao.findByUsername("username2");		
		assertEquals((Double)(balance + 200.0 + 500.00), (Double)user.getCheckingAccount().getBalance());
	}
	
	
	@Test
	void depositToSavingsAccount() {
		
		User user = new User("username1", "password1", "firstName1", "lastName1", "first.last@email.com", "8379478838", "Address1", "customer", true);
		userService.createUser(user);
		user = new User("username2", "password2", "firstName2", "lastName2", "first2.last2@email.com", "9379479938", "Address2", "customer", true);
		userService.createUser(user);
		
		user = userDao.findByUsername("username2");
		Double balance = user.getSavingsAccount().getBalance();
		
		accountService.deposit("Savings", 200.00, "username2");		
		user = userDao.findByUsername("username2");		
		assertEquals((Double)(balance + 200.0), (Double)user.getSavingsAccount().getBalance());
		
		accountService.deposit("Savings", 500.00, "username2");		
		user = userDao.findByUsername("username2");		
		assertEquals((Double)(balance + 200.0 + 500.00), (Double)user.getSavingsAccount().getBalance());
	}
	
	@Test
	void withdrawFromCheckingAccount() {

		User user = new User("username3", "password3", "firstName3", "lastName3", "first.last@email.com", "8379478838", "Address3", "customer", true);
		userService.createUser(user);
		user = new User("username4", "password4", "firstName4", "lastName4", "first4.last4@email.com", "9379479938", "Address4", "customer", true);
		userService.createUser(user);
		
		user = userDao.findByUsername("username4");
		CheckingAccount ca = user.getCheckingAccount();
		ca.setBalance(2000.0);
		caDao.save(ca);
		user = userDao.findByUsername("username4");
		Double balance = user.getCheckingAccount().getBalance();
		
		accountService.withdraw("Checking", 400.00, "username4");		
		user = userDao.findByUsername("username4");		
		assertEquals((Double)(balance - 400.0), (Double)user.getCheckingAccount().getBalance());

		accountService.withdraw("Checking", 500.00, "username4");		
		user = userDao.findByUsername("username4");		
		assertEquals((Double)(balance - 400.0 - 500.00), (Double)user.getCheckingAccount().getBalance());
	}
	
	
	@Test
	void withdrawFromSavingsAccount() {
		
		User user = new User("username3", "password3", "firstName3", "lastName3", "first.last@email.com", "8379478838", "Address3", "customer", true);
		userService.createUser(user);
		user = new User("username4", "password4", "firstName4", "lastName4", "first4.last4@email.com", "9379479938", "Address4", "customer", true);
		userService.createUser(user);

		user = userDao.findByUsername("username4");
		SavingsAccount sa = user.getSavingsAccount();
		sa.setBalance(3000.0);
		saDao.save(sa);
		user = userDao.findByUsername("username4");
		Double balance = user.getSavingsAccount().getBalance();
		
		accountService.withdraw("Savings", 400.00, "username4");		
		user = userDao.findByUsername("username4");		
		assertEquals((Double)(balance - 400.0), (Double)user.getSavingsAccount().getBalance());
		
		accountService.withdraw("Savings", 500.00, "username4");		
		user = userDao.findByUsername("username4");		
		assertEquals((Double)(balance - 400.0 - 500.00), (Double)user.getSavingsAccount().getBalance());
	}
	
	@Test
	void checkingTransactionListUpdate() {
		
		User user = userDao.findByUsername("username2");
		Double balance = user.getCheckingAccount().getBalance();
		
		Integer ctListSize = transactionService.findCheckingTransactionList("username2").size();
		
		accountService.deposit("Checking", 200.00, "username2");		
		user = userDao.findByUsername("username2");		
		assertEquals((Double)(balance + 200.00), (Double)user.getCheckingAccount().getBalance());
		
		balance = user.getCheckingAccount().getBalance();
		accountService.withdraw("Checking", 200.00, "username2");		
		user = userDao.findByUsername("username2");		
		assertEquals((Double)(balance - 200.00), (Double)user.getCheckingAccount().getBalance());
		
//		System.out.println(user.getCheckingAccount());
		
		assertEquals((int)(ctListSize + 2), user.getCheckingAccount().getCheckingTransactionList().size());
		assertFalse((int)(ctListSize + 3) == user.getCheckingAccount().getCheckingTransactionList().size());
	}
	
	@Test
	void betweenAccountsTransfer() throws Exception {
		
		User user = userDao.findByUsername("username2");
		CheckingAccount ca = user.getCheckingAccount();
		SavingsAccount sa = user.getSavingsAccount();
		
		Double checkingBalance = ca.getBalance();
		Double savingsBalance = sa.getBalance();
		
		transactionService.betweenAccountsTransfer("Checking", "Savings", 100.00, ca, sa);
		
		user = userDao.findByUsername("username2");
		ca = user.getCheckingAccount();
		sa = user.getSavingsAccount();

		assertEquals((Double)(checkingBalance - 100.00), ca.getBalance());
		assertEquals((Double)(savingsBalance + 100.00), sa.getBalance());
	}
	
//	@Sql({"/populateCheckbookRequests.sql"})
//	@Test
//	void populateAndCountCheckbookRequests() {
//		
//		assertEquals(6, ccbrDao.count());
//		assertEquals(4, scbrDao.count());
//	}
	
}
