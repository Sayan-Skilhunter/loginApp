package com.webApp.loginApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webApp.loginApp.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	User findByEmail(String email);

}
