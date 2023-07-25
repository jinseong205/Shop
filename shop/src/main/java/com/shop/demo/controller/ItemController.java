package com.shop.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop.demo.config.auth.PrincipalDetails;
import com.shop.demo.dto.ItemFormDto;
import com.shop.demo.dto.ItemMainDto;
import com.shop.demo.dto.ItemSearchDto;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.User;
import com.shop.demo.repository.ItemRepository;
import com.shop.demo.service.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

	private final ItemService itemService;
	private final ItemRepository itemRepository;
	
	@GetMapping(value = "/api/items")
	public ResponseEntity<?> itemMain(ItemSearchDto itemSearchDto, Optional<Integer> page){
		
		Pageable pegealbe = PageRequest.of(page.isPresent()? page.get(): 0,9);
		Page<ItemMainDto> items = itemService.getItemMainPage(itemSearchDto, pegealbe);
		
		itemSearchDto.setItems(items);
		
	    return new ResponseEntity<>(itemSearchDto , HttpStatus.OK);
	}
	
	
	@GetMapping(value = "api/item/{id}")
	public ResponseEntity<?> itemNew(@PathVariable long id) throws Exception {
		ItemFormDto itemFormDto = itemService.getItemDtl(id);
	    return new ResponseEntity<>(itemFormDto , HttpStatus.OK);
	}
	
	@PostMapping(value = "api/manager/item", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> itemNew(@RequestPart("itemFormDto") @Valid ItemFormDto itemFormDto, BindingResult bindingResult, @RequestPart(name = "itemImgFile") List<MultipartFile> itemImgFileList) throws Exception {
	    
	    if(bindingResult.hasErrors()) {
	    	throw new Exception(bindingResult.getFieldError().getDefaultMessage());
	    }
	    
		if(itemImgFileList.get(0).isEmpty()) {
			throw new Exception("첫번째 상품이미지는 필수 입력 값입니다.");
		}
		
		Item item = itemService.saveItem(itemFormDto, itemImgFileList);
	    
	    return new ResponseEntity<>(item , HttpStatus.OK);
	}

	@PutMapping(value = "api/manager/item/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> itemUpdate(@PathVariable long id, @RequestPart(name = "itemFormDto") @Valid ItemFormDto itemFormDto, BindingResult bindingResult, @RequestPart(name = "itemImgFile", required =  false) List<MultipartFile> itemImgFileList) throws Exception {
	    
	    if(bindingResult.hasErrors()) {
	    	throw new Exception(bindingResult.getFieldError().getDefaultMessage());
	    }

		Item item = itemService.updateItem(itemFormDto, itemImgFileList);
	    return new ResponseEntity<>(item , HttpStatus.OK);
	}
	
	@GetMapping(value = {"/api/manager/items", "/api/manager/items/{page}"})
	public ResponseEntity<?> itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		Pageable pegealbe = PageRequest.of(page.isPresent()? page.get(): 0,3);
		User user = principalDetails.getUser();
		
		Page<Item> items = itemService.getItemManagePage(itemSearchDto, pegealbe, user);
		
		itemSearchDto.setItems(items);
		
	    return new ResponseEntity<>(itemSearchDto , HttpStatus.OK);
	}
	

}

	