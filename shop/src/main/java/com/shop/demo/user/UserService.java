package com.shop.demo.user;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.demo.user.UserRepository;

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
	
	
}
