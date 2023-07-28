package com.shop.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartOrderDto {
	private Long cartItemId;
	private List<CartOrderDto> cartOrderList;
}
