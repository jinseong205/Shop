package com.shop.demo.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.demo.config.dto.UserFormDto;
import com.shop.demo.entity.User;
import com.shop.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@PostMapping("/api/join")
	public ResponseEntity<?> join(@RequestBody @Valid UserFormDto userFormDto, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}

		User user;
		user = User.builder().username(userFormDto.getUsername()).password(bCryptPasswordEncoder.encode(userFormDto.getPassword()))
				.name(userFormDto.getName()).email(userFormDto.getAddr()).addr(userFormDto.getAddr()).roles("ROLE_USER").build();
		userService.join(user);
		return new ResponseEntity<>(null , HttpStatus.OK);
	}

	@GetMapping("/api/user")
	public String user() {
		return "user";
	}

	@GetMapping("/api/manager")
	public String manager() {
		return "manager";
	}

	@GetMapping("/api/admin")
	public String admin() {
		return "admin";
	}

}
