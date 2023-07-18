package com.shop.demo.dto;

import org.springframework.data.domain.Page;

import com.shop.demo.constant.ItemSellStatus;
import com.shop.demo.entity.Item;

import lombok.Data;

@Data
public class ItemSearchDto {

	private String searchDateType;
	
	private ItemSellStatus searchSellStatus;	
	
	private String searchBy;
	
	private String searchQuery = "";
	
	private Page<Item> items;
	
	
}
