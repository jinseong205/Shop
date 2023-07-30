package com.shop.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.shop.demo.config.auth.PrincipalDetails;
import com.shop.demo.dto.CartDetailDto;
import com.shop.demo.dto.CartItemDto;
import com.shop.demo.dto.CartOrderDto;
import com.shop.demo.service.CartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
	
    @GetMapping(value = "api/cart")
	public ResponseEntity<?> cart(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
		List<CartDetailDto> cartDetailList = cartService.getCartList(principalDetails.getUser());
		return new ResponseEntity<List<?>>(cartDetailList, HttpStatus.OK);
	}
	
    
	@PostMapping(value = "api/cart")
	public ResponseEntity<?> cart(@RequestBody @Valid CartItemDto cartItemDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
		
	    if(bindingResult.hasErrors())
	    	throw new Exception(bindingResult.getFieldError().getDefaultMessage());
	    Long cartItemId = cartService.addCart(cartItemDto, principalDetails.getUser());
	    
		return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
	}
	

	@PatchMapping(value = "api/cartItem/{id}")
	public ResponseEntity<?> updateCartItem(@PathVariable Long id, int count, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		
		if(count <= 0)
			throw new Exception("최소 1개 이상 담아주세요.");
		else if(!cartService.validateCartItem(id, principalDetails.getUser()))
			throw new Exception("수정 권한이 없습니다.");
			
		cartService.updateCartItem(id, count);
		return new ResponseEntity<Long>(id, HttpStatus.OK);	
	}
	
	@DeleteMapping(value = "api/cartItem/{id}")
	public ResponseEntity<?> deleteCartItem(@PathVariable Long id, int count, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		if(!cartService.validateCartItem(id, principalDetails.getUser()))
			throw new Exception("삭제 권한이 없습니다.");
		cartService.deleteCartItem(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);	
	}
	
	@PostMapping(value="api/cart/orders")
	public ResponseEntity<?> orderCartItem(@RequestBody CartOrderDto cartOrderDto, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
		
		List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderList();
		
		if(cartOrderDtoList == null || cartOrderDtoList.size() == 0)
			throw new Exception("주문할 상품을 선택해 주세요.");
		
		for(CartOrderDto cartOrder : cartOrderDtoList) {
			if(!cartService.validateCartItem(cartOrder.getCartItemId(), principalDetails.getUser()))
				throw new Exception("주문 권한이 없습니다.");
		}
		
		Long orderId = cartService.orderCartItem(cartOrderDtoList, principalDetails.getUser());
		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}
}
