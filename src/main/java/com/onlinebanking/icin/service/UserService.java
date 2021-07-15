package com.onlinebanking.icin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.icin.dao.UserDao;
import com.onlinebanking.icin.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

    @Autowired
    private AccountService accountService;

    public void save(User user) {
    	userDao.save(user);
    }
    
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    public User createUser(User user) {
    	
    	User findUser = userDao.findByUsername(user.getUsername());
    	System.out.println("User searched");
    	if (findUser == null) {
    		
    		System.out.println("User not found");
    		user.setCheckingAccount(accountService.createCheckingAccount());
    		user.setSavingsAccount(accountService.createSavingsAccount());
    		
    		findUser = userDao.save(user);
    	}
    	
    	return findUser;
    }
}
