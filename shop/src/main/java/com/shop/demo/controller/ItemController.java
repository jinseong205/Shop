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

import com.shop.demo.config.dto.ItemFormDto;
import com.shop.demo.config.dto.ItemImgDto;
import com.shop.demo.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;
	
	@PostMapping("api/manager/item/new")
	//public ResponseEntity<?> itemImg(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList) throws Exception{
	public ResponseEntity<?> itemNew( @RequestParam("itemFormDto") ItemFormDto itemFormDto, BindingResult bindingResult, @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList) throws Exception{

		
		/*
		if(bindingResult.hasErrors()) {			
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}
		*/
		
		if(itemImgFileList.get(0).isEmpty()) {
			throw new Exception("첫번째 상품이미지는 필수 입력 값입니다.");
		}
		
		itemService.saveIttem(itemFormDto, itemImgFileList);
		
		return new ResponseEntity<>(null,HttpStatus.OK);
	}

	
}
