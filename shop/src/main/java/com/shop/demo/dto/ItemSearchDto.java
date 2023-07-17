package com.shop.demo.dto;

import com.shop.demo.constant.ItemSellStatus;

import com.shop.demo.constant.ItemSellStatus;

import lombok.Data;

@Data
public class ItemSearchDto {

	private String searchDateType;
	
	private ItemSellStatus searchSellStatus;
	
	private String searchBy;
	
	private String searchQuery = "";
	
	
	
}
