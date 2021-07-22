package com.onlinebanking.icin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

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
import com.onlinebanking.icin.entity.CheckingCheckbook;
import com.onlinebanking.icin.entity.CheckingCheckbookRequest;
import com.onlinebanking.icin.entity.CheckingTransaction;
import com.onlinebanking.icin.entity.Recipient;
import com.onlinebanking.icin.entity.SavingsAccount;
import com.onlinebanking.icin.entity.SavingsCheckbook;
import com.onlinebanking.icin.entity.SavingsCheckbookRequest;
import com.onlinebanking.icin.entity.SavingsTransaction;
import com.onlinebanking.icin.entity.User;
import com.onlinebanking.icin.service.AccountService;
import com.onlinebanking.icin.service.CheckbookRequestService;
import com.onlinebanking.icin.service.TransactionService;
import com.onlinebanking.icin.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IcinApplication.class)
@TestPropertySource(locations="/application-test.properties")
@ActiveProfiles("test")
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
	
	@Autowired
	private CheckbookRequestService checkbookRequestService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@BeforeEach
	void createUsers() {
		
		User user = new User("username1", bCryptPasswordEncoder.encode("password1"), "firstName1", "lastName1", "first.last@email.com", "8379478838", "Address1", "admin", true);
		if (userDao.findByUsername("username1") == null)
		{
			userService.createUser(user);
		}
		System.out.println(user.getUsername() + ": " + user.getPassword());
		
		user = new User("username2", bCryptPasswordEncoder.encode("password2"), "firstName2", "lastName2", "first2.last2@email.com", "9379479938", "Address2", "customer", true);
		if (userDao.findByUsername("username2") == null)
		{
			userService.createUser(user);
		}
		System.out.println(user.getUsername() + ": " + user.getPassword());
		
		user = new User("username3", bCryptPasswordEncoder.encode("password3"), "firstName3", "lastName3", "first3.last3@email.com", "8379478838", "Address3", "customer", true);
		if (userDao.findByUsername("username3") == null)
		{
			userService.createUser(user);
		}
		System.out.println(user.getUsername() + ": " + user.getPassword());
		
		user = new User("username4", bCryptPasswordEncoder.encode("password4"), "firstName4", "lastName4", "first4.last4@email.com", "9379479938", "Address4", "customer", true);
		if (userDao.findByUsername("username4") == null)
		{
			userService.createUser(user);
		}
		System.out.println(user.getUsername() + ": " + user.getPassword());

		user = new User("adminUser", bCryptPasswordEncoder.encode("admin"), "AdminFirst", "AdminLast", "admin@icin.com", "9980873879", "AdminAddress", "admin", true);
		if (userDao.findByUsername("adminUser") == null)
		{
			userService.createUser(user);
		}
		System.out.println(user.getUsername() + ": " + user.getPassword());
		System.out.println("Password: " + bCryptPasswordEncoder.encode("password"));
	}
	
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
		User user = new User("username1", bCryptPasswordEncoder.encode("password1"), "firstName1", "lastName1", "first.last@email.com", "8379478838", "Address1", "customer", true);
		if (userDao.findByUsername("username1") == null)
		{
			userService.createUser(user);
			currentUserCount++;
		}
		assertEquals(currentUserCount, (Long)userDao.count());
		System.out.println(user.getUsername() + ": " + user.getPassword());

		user = new User("username2", bCryptPasswordEncoder.encode("password2"), "firstName2", "lastName2", "first2.last2@email.com", "9379479938", "Address2", "customer", true);
		if (userDao.findByUsername("username2") == null)
		{
			userService.createUser(user);
			currentUserCount++;
		}
		assertEquals(currentUserCount, (Long)userDao.count());
		System.out.println(user.getUsername() + ": " + user.getPassword());
		
		user = new User("username5", bCryptPasswordEncoder.encode("password5"), "firstName5", "lastName5", "first5.last5@email.com", "8379433838", "Address5", "admin", true);
		if (userDao.findByUsername("username5") == null)
		{
			userService.createUser(user);
			currentUserCount++;
		}
		assertEquals(currentUserCount, (Long)userDao.count());
		System.out.println(user.getUsername() + ": " + user.getPassword());
		
		user = new User("username6", bCryptPasswordEncoder.encode("password6"), "firstName6", "lastName6", "first6.last6@email.com", "9379479938", "Address6", "customer", true);
		if (userDao.findByUsername("username6") == null)
		{
			userService.createUser(user);
			currentUserCount++;
		}
		assertEquals(currentUserCount, (Long)userDao.count());
		System.out.println(user.getUsername() + ": " + user.getPassword());
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

//		User user = new User("username1", bCryptPasswordEncoder.encode("password1", "firstName1", "lastName1", "first.last@email.com", "8379478838", "Address1", "customer", true);
//		userService.createUser(user);
//		user = new User("username2", bCryptPasswordEncoder.encode("password2", "firstName2", "lastName2", "first2.last2@email.com", "9379479938", "Address2", "customer", true);
//		userService.createUser(user);
		
		User user = userDao.findByUsername("username2");
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
		
//		User user = new User("username1", bCryptPasswordEncoder.encode("password1", "firstName1", "lastName1", "first.last@email.com", "8379478838", "Address1", "customer", true);
//		userService.createUser(user);
//		user = new User("username2", bCryptPasswordEncoder.encode("password2", "firstName2", "lastName2", "first2.last2@email.com", "9379479938", "Address2", "customer", true);
//		userService.createUser(user);
		
		User user = userDao.findByUsername("username2");
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

//		User user = new User("username3", bCryptPasswordEncoder.encode("password3", "firstName3", "lastName3", "first.last@email.com", "8379478838", "Address3", "customer", true);
//		if (userDao.findByUsername("username3") == null)
//		{
//			userService.createUser(user);
//		}
//		user = new User("username4", bCryptPasswordEncoder.encode("password4", "firstName4", "lastName4", "first4.last4@email.com", "9379479938", "Address4", "customer", true);
//		if (userDao.findByUsername("username4") == null)
//		{
//			userService.createUser(user);
//		}
		
		User user = userDao.findByUsername("username4");
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
		
//		User user = new User("username3", bCryptPasswordEncoder.encode("password3", "firstName3", "lastName3", "first.last@email.com", "8379478838", "Address3", "customer", true);
//		if (userDao.findByUsername("username3") == null)
//		{
//			userService.createUser(user);
//		}
//		user = new User("username4", bCryptPasswordEncoder.encode("password4", "firstName4", "lastName4", "first4.last4@email.com", "9379479938", "Address4", "customer", true);
//		if (userDao.findByUsername("username4") == null)
//		{
//			userService.createUser(user);
//		}
		
		User user = userDao.findByUsername("username4");
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
		
		Integer ctListSize = transactionService.findCheckingTransactionList("username2").size();
		User user = userDao.findByUsername("username2");
		Double balance = user.getCheckingAccount().getBalance();
		
		accountService.deposit("Checking", 200.00, "username2");		
		user = userDao.findByUsername("username2");		
		assertEquals((Double)(balance + 200.00), (Double)user.getCheckingAccount().getBalance());
		
		balance = user.getCheckingAccount().getBalance();
		accountService.withdraw("Checking", 200.00, "username2");		
		user = userDao.findByUsername("username2");		
		assertEquals((Double)(balance - 200.00), (Double)user.getCheckingAccount().getBalance());
		
//		System.out.println(user.getCheckingAccount());
		
		assertEquals((int)(ctListSize + 2), transactionService.findCheckingTransactionList("username2").size());
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
	
	@Test
	void transferToRecipient() {
		
		
//		User user = new User("username1", bCryptPasswordEncoder.encode("password1", "firstName1", "lastName1", "first.last@email.com", "8379478838", "Address1", "customer", true);
//		userService.createUser(user);
//		user = new User("username2", bCryptPasswordEncoder.encode("password2", "firstName2", "lastName2", "first2.last2@email.com", "9379479938", "Address2", "customer", true);
//		userService.createUser(user);
//	
//		User user2 = userDao.findByUsername("username1");
//		CheckingAccount ca2 = user2.getCheckingAccount();
//		User user = userDao.findByUsername("username2");
//		if (user == null) System.out.println("***********No user found.******");
//		user = new User("username2", bCryptPasswordEncoder.encode("password2", "firstName2", "lastName2", "first2.last2@email.com", "9379479938", "Address2", "customer", true);
//		userService.createUser(user);
		User user = userDao.findByUsername("username2");
		CheckingAccount ca = user.getCheckingAccount();
		SavingsAccount sa = user.getSavingsAccount();
		ca.setBalance(2000.00);
		sa.setBalance(20000.00);
		caDao.save(ca);
		saDao.save(sa);
		
		user = userDao.findByUsername("username2");
		ca = user.getCheckingAccount();
		sa = user.getSavingsAccount();
		Double checkingBalance = ca.getBalance();
		
		Recipient recipient = new Recipient("RecpientName", "rec.name@email.com", "6479388478", ca.getNumber().toString(), "Settle splitwise", user);
		if (recDao.findByName(recipient.getName()) == null) recDao.save(recipient);
		recipient = recDao.findByName(recipient.getName());
		
		transactionService.transferToRecipient(recipient, "Checking", 100.00, ca, sa);
		
		user = userDao.findByUsername("username2");
		ca = user.getCheckingAccount();

		assertEquals((Double)(checkingBalance - 100.00), ca.getBalance());
	}
	
	@Test
	void requestCheckingCheckbook() {
		
//		User user = new User("username3", bCryptPasswordEncoder.encode("password3", "firstName3", "lastName3", "first.last@email.com", "8379478838", "Address3", "customer", true);
//		if (userDao.findByUsername("username3") == null)
//		{
//			userService.createUser(user);
//		}
//		user = new User("username4", bCryptPasswordEncoder.encode("password4", "firstName4", "lastName4", "first4.last4@email.com", "9379479938", "Address4", "customer", true);
//		if (userDao.findByUsername("username4") == null)
//		{
//			userService.createUser(user);
//		}
//		
		User user = userDao.findByUsername("username3");
		CheckingAccount ca = user.getCheckingAccount();
	
		long initialCcbCount = ccbDao.count();
		CheckingCheckbook ccb = checkbookRequestService.createCheckingCheckbook(new CheckingCheckbook(42, "standard", ca));
		
		long initialCcbrCount = ccbrDao.count();
		CheckingCheckbookRequest ccr = checkbookRequestService.requestNewCheckingCheckbook(ccb);
		
		assertFalse(initialCcbCount == ccbDao.count());
		assertFalse(initialCcbrCount == ccbrDao.count());
		
		assertEquals(initialCcbCount + 1, ccbDao.count());
		assertEquals(initialCcbrCount + 1, ccbrDao.count());
	
		assertFalse(ccr.isRequestApproved());;
	}
	
	@Test
	void requestSavingsCheckbook() {
		
		User user = userDao.findByUsername("username3");
		SavingsAccount ca = user.getSavingsAccount();
	
		long initialCcbCount = scbDao.count();
		SavingsCheckbook scb = checkbookRequestService.createSavingsCheckbook(new SavingsCheckbook(42, "standard", ca));
		SavingsCheckbook scb2 = checkbookRequestService.createSavingsCheckbook(new SavingsCheckbook(24, "customized", ca));
		
		long initialCcbrCount = scbrDao.count();
		SavingsCheckbookRequest scr = checkbookRequestService.requestNewSavingsCheckbook(scb);
		SavingsCheckbookRequest scr2 = checkbookRequestService.requestNewSavingsCheckbook(scb2);
		
		assertFalse(initialCcbCount == scbDao.count());
		assertFalse(initialCcbrCount == scbrDao.count());
		
		assertEquals(initialCcbCount + 2, scbDao.count());
		assertEquals(initialCcbrCount + 2, scbrDao.count());
		
		assertFalse(scr.isRequestApproved());
		assertFalse(scr2.isRequestApproved());
	}

	@Test
	void approveCheckingCheckbook() {
		
		User user = userDao.findByUsername("username3");
		CheckingAccount ca = user.getCheckingAccount();
	
		CheckingCheckbook ccb = checkbookRequestService.createCheckingCheckbook(new CheckingCheckbook(42, "standard", ca));
		CheckingCheckbookRequest ccr = checkbookRequestService.requestNewCheckingCheckbook(ccb);
		assertFalse(ccr.isRequestApproved());
		
		User unauthorizedUser = userDao.findByUsername("username4");
		User authorizedUser = userDao.findByUsername("username1");
		
		user = userDao.findByUsername("username3");
		ca = user.getCheckingAccount();
	
		List<CheckingCheckbookRequest> ccbrList = ca.getCheckingCheckbookRequestList();
		
		for (CheckingCheckbookRequest ccbr: ccbrList)
		{
			if(!ccbr.isRequestApproved()) {
			
				ccbr =  checkbookRequestService.approveNewCheckingCheckbookRequest(ccbr.getId(), unauthorizedUser);
				assertFalse(ccbr.isRequestApproved());
			}
		}		
		for (CheckingCheckbookRequest ccbr: ccbrList)
		{
			if(!ccbr.isRequestApproved()) {

				ccbr =  checkbookRequestService.approveNewCheckingCheckbookRequest(ccbr.getId(), authorizedUser);
				assertTrue(ccbr.isRequestApproved());
			}
		}
	}

	@Test
	void approveSavingsCheckbook() {
		
		User user = userDao.findByUsername("username3");
		SavingsAccount sa = user.getSavingsAccount();
	
		SavingsCheckbook ccb = checkbookRequestService.createSavingsCheckbook(new SavingsCheckbook(42, "standard", sa));
		SavingsCheckbookRequest scr = checkbookRequestService.requestNewSavingsCheckbook(ccb);
		assertFalse(scr.isRequestApproved());
		
		User unauthorizedUser = userDao.findByUsername("username4");
		User authorizedUser = userDao.findByUsername("username1");

		user = userDao.findByUsername("username3");
		sa = user.getSavingsAccount();
		
		List<SavingsCheckbookRequest> scbrList = sa.getSavingsCheckbookRequestList();

		
		for (SavingsCheckbookRequest scbr: scbrList)
		{
			if(!scbr.isRequestApproved()) {
				scbr = checkbookRequestService.approveNewSavingsCheckbookRequest(scbr.getId(), unauthorizedUser);
				assertFalse(scbr.isRequestApproved());
			}
		}
		
		for (SavingsCheckbookRequest scbr: scbrList)
		{
			if(!scbr.isRequestApproved()) {
				
				scbr = checkbookRequestService.approveNewSavingsCheckbookRequest(scbr.getId(), authorizedUser);
				assertTrue(scbr.isRequestApproved());
			}
		}
	}
}
