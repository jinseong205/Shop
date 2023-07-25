package com.shop.demo.dto;

import com.shop.demo.entity.OrderItem;

import lombok.Data;

@Data
public class OrderItemDto {

	public OrderItemDto(OrderItem orderItem, String imgUrl) {
		this.itemName = orderItem.getItem().getItemName();
		this.count = orderItem.getCount();
		this.orderPrice = orderItem.getOrderPrice();
		this.imgUrl = imgUrl;
	}

	private String itemName;
	private int count;
	private int orderPrice;
	private String imgUrl;
	
}
