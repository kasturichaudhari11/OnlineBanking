package com.onlinebanking.icin.service;

import java.util.List;

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
    
    public boolean usernameExists(String username) {
    	
    	return null != findByUsername(username);
    	
    }
    
    public boolean emailExists(String email) {
    	
    	return null != findByEmail(email);
    	
    }
    
    public boolean userExists(String username, String email) {
     
    	return usernameExists(username) || emailExists(username);
    }
    
    public List<User> findUserList() {
    	
        return (List<User>) userDao.findAll();
    }

    public void enableUser(String username) {
    	
        User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    public void disableUser(String username) {
    	
        User user = findByUsername(username);
        user.setEnabled(false);
        userDao.save(user);
    }    
}
