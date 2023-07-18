package com.shop.demo.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class ItemMainDto {
	
	private Long id;
	
	private String itemName;
	
	private String itemDetail;
	
	private String imgUrl;
	
	private Integer price;

	@QueryProjection
	public ItemMainDto(Long id, String itemName, String itemDetail, String imgUrl, Integer price) {
		this.id = id;
		this.itemName = itemName;
		this.itemDetail = itemDetail;
		this.imgUrl = imgUrl;
		this.price = price;
	}
	
	
}
