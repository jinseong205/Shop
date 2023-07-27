package com.shop.demo.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.shop.demo.config.auth.PrincipalDetails;
import com.shop.demo.dto.CartItemDto;
import com.shop.demo.repository.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartSerivce;

	@PostMapping(value = "/cart")
	public ResponseEntity<?> cart(@RequestBody @Valid CartItemDto cartItemDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails ) throws Exception{
		
	    if(bindingResult.hasErrors()) {
	    	throw new Exception(bindingResult.getFieldError().getDefaultMessage());
	    }
		
	    Long cartItemId = cartSerivce.addCart(cartItemDto, principalDetails.getUser());
	    
		return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
	}
	
}
