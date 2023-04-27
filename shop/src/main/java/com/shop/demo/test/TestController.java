package com.shop.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("home")
	public String home() {
		return "<h1>home</h1>";
	}

	@PostMapping("token")
	public String token() {
		return "<h1>token</h1>";
	}

}
