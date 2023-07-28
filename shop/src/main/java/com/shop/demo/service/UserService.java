package com.shop.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.demo.entity.User;
import com.shop.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	//private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//회원 가입
	public User join(User user) {
		validateUser(user);
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//user.setRoles("ROLE_USER");
		return userRepository.save(user);
	}

	public void validateUser(User user) {
		User findUser = userRepository.findByUsername(user.getUsername());
		if(findUser != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}
	
	
	@Transactional(readOnly = true)
	public Page<User> getUsers(Pageable pageable ){
		Page<User> users = userRepository.findAll(pageable);
		for(User u : users) 
			u.setPassword(null);
		return users;
	}
	
}
