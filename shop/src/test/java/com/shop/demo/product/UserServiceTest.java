package com.shop.demo.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shop.demo.user.User;
import com.shop.demo.user.UserDto;
import com.shop.demo.user.UserService;

@SpringBootTest
@Transactional
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User createUser() {
		UserDto userDto = new UserDto();
		
		userDto.setUsername("gomawoomi");
		userDto.setPassword("0000");
		userDto.setName("정진성");
		userDto.setEmail("goamwoomi@gmail.com");;
		
		return User.builder()
				.username(userDto.getUsername())
				.password(userDto.getPassword())
				.name(userDto.getName())
				.email(userDto.getEmail())
				.build();	
	}
	
	@Test
	@DisplayName("--- 회원가입테스트 ---")
	public void saveMemberTest() {
		
		User user = createUser();
		User savedUser = userService.join(user);

		assertEquals(user.getUsername(), savedUser.getUsername());
		assertEquals(user.getPassword(), savedUser.getPassword());
		assertEquals(user.getName(), savedUser.getName());
		assertEquals(user.getEmail(), savedUser.getEmail());
	}
}
