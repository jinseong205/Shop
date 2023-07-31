package com.shop.demo.dto;

import org.springframework.data.domain.Page;

import com.shop.demo.constant.ItemSellStatus;
import com.shop.demo.entity.Item;

import lombok.Data;

@Data
public class ItemSearchDto {
	/* 상품 검색 조회 전용 DTO (req) */ 
	private String searchDateType;
	
	private ItemSellStatus searchSellStatus;	
	
	private String searchBy;
	
	private String searchQuery = "";
	
	private Page<?> items;
	
	
}
