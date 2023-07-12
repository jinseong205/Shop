package com.shop.demo.controller;

import java.rmi.ServerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop.demo.config.dto.ItemFormDto;
import com.shop.demo.constant.ItemSellStatus;
import com.shop.demo.entity.Item;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

	@GetMapping("api/test/ex0")
	public ResponseEntity<?> test0() throws ServerException {

		throw new ServerException("hi");

		// return new ResponseEntity<>(null,HttpStatus.OK);
	}

	@GetMapping("api/test/ex1")
	public ResponseEntity<?> test1() {

		Item i = new Item();
		i.setId(1L);
		i.setItemName("TEST");
		i.setItemDetail("TEST DESC");
		i.setItemSellStatus(ItemSellStatus.SELL);

		return new ResponseEntity<>(i, HttpStatus.OK);
	}

	@PostMapping(value = "api/test/file")
	public ResponseEntity<?> itemNew(@RequestParam("itemFormDto") ItemFormDto itemFormDto,
			@RequestParam("itemImgFile") MultipartFile itemImgFile) throws Exception {

		log.debug("-----" + itemFormDto.toString());
		log.debug("-----" + itemImgFile.getSize());
		return new ResponseEntity<>("OOO", HttpStatus.OK);
	}


	@PostMapping(value = "/api/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> handleFileUpload(@RequestParam("dto") String dto, @RequestParam("file") MultipartFile file) {
	 
		// DTO 및 파일을 처리하는 로직을 여기에 작성하세요.
		// 파일 저장, 데이터베이스에 저장 등의 작업을 수행할 수 있습니다.

		// 예시로서, 성공적인 응답을 반환합니다.
		return new ResponseEntity<>("OOO", HttpStatus.OK);
	}

}