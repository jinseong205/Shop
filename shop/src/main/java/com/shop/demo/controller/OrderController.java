package com.shop.demo.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.shop.demo.config.auth.PrincipalDetails;
import com.shop.demo.dto.OrderItemDto;
import com.shop.demo.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping(value= "/order")
	public ResponseEntity<?> order(@RequestBody @Valid OrderItemDto orderItemDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails ) throws Exception{
			
		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}	
		
		return null;
	}


}
	