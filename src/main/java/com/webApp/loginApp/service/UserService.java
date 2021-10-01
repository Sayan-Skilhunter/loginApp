package com.webApp.loginApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webApp.loginApp.entity.User;
import com.webApp.loginApp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
