package com.webApp.loginApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webApp.loginApp.entity.UserCookie;

public interface UserCookieRepository extends JpaRepository<UserCookie,Integer> {

	UserCookie findByValue(String cookieValue);
}
