package com.onlinebanking.icin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.Assert.assertEquals;

import com.onlinebanking.icin.dao.CheckingAccountDao;
import com.onlinebanking.icin.dao.SavingsAccountDao;

@SpringBootTest
class IcinApplicationTests {

	@Autowired
	private CheckingAccountDao caDao;
	private SavingsAccountDao saDao;
	
	@Test
	void contextLoads() {
	}

	@Sql("/populateAccounts.sql")
	@Test
	void populateAndCountAccounts() {
		
		assertEquals(4, caDao.count());
		assertEquals(3, saDao.count());
	}
	
	
}
