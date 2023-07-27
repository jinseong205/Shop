package com.shop.demo.controller;

import lombok.Data;

@Data
public class CartDetilDto {
	private Long cartItemId;
	private String itemName;
	private int price;
	private int count;
	private String imgUrl;
	
	public CartDetilDto(Long cartItemId, String itemName, int price, int count, String imgUrl) {
		super();
		this.cartItemId = cartItemId;
		this.itemName = itemName;
		this.price = price;
		this.count = count;
		this.imgUrl = imgUrl;
	}
	
}
