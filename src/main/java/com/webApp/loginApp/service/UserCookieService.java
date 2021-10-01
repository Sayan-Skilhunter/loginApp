package com.webApp.loginApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webApp.loginApp.entity.UserCookie;
import com.webApp.loginApp.repository.UserCookieRepository;

@Service
public class UserCookieService {

	@Autowired
	UserCookieRepository userCookieRepository;
	
	public UserCookie saveCookie(UserCookie userCookie) {
		return userCookieRepository.save(userCookie);
	}
	
	public UserCookie getCookieByValue(String cookieValue) {
		return userCookieRepository.findByValue(cookieValue);
	}
	
	public List<UserCookie> getAllCookie() {
		return userCookieRepository.findAll();
	}
}
