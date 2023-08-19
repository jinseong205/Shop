package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.demo.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);	
}
