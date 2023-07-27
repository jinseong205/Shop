package com.shop.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.shop.demo.config.auth.PrincipalDetails;
import com.shop.demo.dto.OrderDto;
import com.shop.demo.dto.OrderHistDto;
import com.shop.demo.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping(value = "api/order")
	public ResponseEntity<?> order(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {

		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}

		Long orderId;
		orderId = orderService.order(orderDto, principalDetails.getUser());

		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}

	@GetMapping(value = { "api/orders", "api/orders/{page}" })
	public ResponseEntity<?> orderHist(@PathVariable("page") Optional<Integer> page,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {

		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);

		Page<OrderHistDto> orderHistDtoList = orderService.getOrderList(principalDetails.getUser(), pageable);

		return new ResponseEntity<Page<OrderHistDto>>(orderHistDtoList, HttpStatus.OK);
	}

	@PutMapping("/order/{orderId}")
	public ResponseEntity<?> cnacelOrder (@PathVariable Long orderId, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
		
		if(orderService.validateOrder(orderId, principalDetails.getUser())) {
			throw new Exception("주문 취소 권한이 없습니다.");
		}
		
		orderService.cancelOrder(orderId);
		
		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}
	
}
