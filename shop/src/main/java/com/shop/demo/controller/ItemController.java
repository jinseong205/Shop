package com.shop.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop.demo.config.dto.ItemFormDto;
import com.shop.demo.service.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

	private final ItemService itemService;
	
	@PostMapping(value = "api/manager/item/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> itemNew(@RequestPart("itemFormDto") ItemFormDto itemFormDto, @RequestPart(name = "itemImgFile") List<MultipartFile> itemImgFileList) throws Exception {
	    log.debug("-----" + itemFormDto.toString());
	    log.debug("-----" + itemImgFileList.size());
	    return new ResponseEntity<>("OOO", HttpStatus.OK);
	}
	
	@PostMapping("api/manager/item/new1")
	//public ResponseEntity<?> itemImg(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList) throws Exception{
	public ResponseEntity<?> itemNew1( @RequestPart(name = "itemFormDto") ItemFormDto itemFormDto, @RequestParam(name = "itemImgFileList")List<MultipartFile> itemImgFileList) throws Exception{
		
		log.debug("-----" + itemFormDto.toString());	
		
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
