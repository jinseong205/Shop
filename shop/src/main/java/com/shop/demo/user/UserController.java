package com.shop.demo.user;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/api/join")
	public ResponseEntity<?> join(@RequestBody @Valid UserFormDto userFormDto, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}

		User user;
		user = User.builder().username(userFormDto.getUsername()).password(userFormDto.getPassword())
				.name(userFormDto.getName()).email(userFormDto.getAddr()).addr(userFormDto.getAddr()).build();
		userService.join(user);
		return new ResponseEntity<>(null , HttpStatus.OK);
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
