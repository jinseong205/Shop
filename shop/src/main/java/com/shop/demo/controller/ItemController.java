package com.shop.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop.demo.config.dto.ItemImgDto;
import com.shop.demo.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;
	
	@PostMapping("api/manager/itemImg")
	public ResponseEntity<?> itemImg(@Valid ItemImgDto itemImgDto, BindingResult bindingResul, @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList){

		
		
		return new ResponseEntity<>(null,HttpStatus.OK);
	}

	
}
