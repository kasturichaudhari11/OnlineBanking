package com.onlinebanking.icin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlinebanking.icin.dao.UserDao;
import com.onlinebanking.icin.entity.User;

@Service
public class UserSecurityService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        User user = userDao.findByUsername(username);
        
        if (user != null && user.isEnabled()) {
        	
        	LOG.info("User {} found and is enabled.", username);
        
        } else {
        	
        	LOG.warn("Username {} not found or account is disabled", username);
            throw new UsernameNotFoundException("Username " + username + " not found or account is disabled");
        }
        
        return user;
    }
}