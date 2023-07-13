package com.shop.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop.demo.constant.ItemSellStatus;
import com.shop.demo.entity.Item;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

	@GetMapping("api/test/ex1")
	public ResponseEntity<?> test1() {
		Item i = new Item();
		i.setId(1L);
		i.setItemName("TEST");
		i.setItemDetail("TEST DESC");
		i.setItemSellStatus(ItemSellStatus.SELL);
		return new ResponseEntity<>(i, HttpStatus.OK);
	}

	@PostMapping(value = "/api/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> handleFileUpload(@RequestPart("dto") String dto, @RequestParam("file") MultipartFile file) {
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}