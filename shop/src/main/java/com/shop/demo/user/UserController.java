package com.shop.demo.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping("join")
	public ResponseEntity<?> join(@RequestBody User user){
		return new ResponseEntity<>(userService.join(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/api/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/api/manager")
	public String manager() {
		return "user";
	}
	
	@GetMapping("/api/admin")
	public String admin() {
		return "user";
	}
	
}
