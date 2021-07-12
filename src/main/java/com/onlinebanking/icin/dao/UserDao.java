package com.onlinebanking.icin.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlinebanking.icin.entity.User;

public interface UserDao extends CrudRepository<User, Integer>{

	User findByUsername(String username);
	User findByEmail(String email);
}
