package com.shop.demo.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.shop.demo.constant.ItemSellStatus;

import lombok.Data;
import lombok.ToString;

@Data
public class ProductDto {
	
	private Long id;
	
	private String productName;
	
	private int price;
	
	private int stockNum;

	private String productDetail;

	private ItemSellStatus productSellStatus;
	
	private LocalDateTime crtDt;
	
	private LocalDateTime updtDt;
	
	private String attr1;

	private String attr2;

	private String attr3;

	private String attr4;

	private String attr5;

	private String attr6;

	private String attr7;

	private String attr8;

	private String attr9;

	private String attr10;

	
	
}

