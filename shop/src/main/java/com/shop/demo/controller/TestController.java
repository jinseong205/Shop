package com.shop.demo.controller;

import java.rmi.ServerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.demo.entity.Product;

@RestController
public class TestController {
	
	@GetMapping("api/test/ex0")
	public ResponseEntity<?> test0() throws ServerException{

		throw new ServerException("hi");
		
		//return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	@GetMapping("api/test/ex1")
	public ResponseEntity<?> test1(){
		
		Product p = new Product();
		p.setId(1L);
		p.setProductName("TEST");
		p.setProductDetail("TEST DESC");
		
		return new ResponseEntity<>(p,HttpStatus.OK);
	}

}