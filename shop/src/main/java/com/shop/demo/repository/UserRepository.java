package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.demo.entity.User;


//CURD 함수를 JpaRepository 가 들고 있음
//User Model, UserModel PK Type
public interface UserRepository extends JpaRepository<User, Long> {
	//findBy규칙 ->Username 문법
	//select * from user where username = (username)?
	public User findByUsername(String username);		//Jpa query methods
	
	
}
