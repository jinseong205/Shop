package com.shop.demo.controller;

import java.rmi.ServerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.demo.constant.ItemSellStatus;
import com.shop.demo.entity.Item;

@RestController
public class TestController {
	
	@GetMapping("api/test/ex0")
	public ResponseEntity<?> test0() throws ServerException{

		throw new ServerException("hi");
		
		//return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	@GetMapping("api/test/ex1")
	public ResponseEntity<?> test1(){
		
		Item i = new Item();
		i.setId(1L);
		i.setItemName("TEST");
		i.setItemDetail("TEST DESC");
		i.setItemSellStatus(ItemSellStatus.SELL);
		
		
		return new ResponseEntity<>(i,HttpStatus.OK);
	}

}