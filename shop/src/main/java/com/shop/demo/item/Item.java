package com.shop.demo.item;

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
@ToString
@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String itemName;
	
	@Column(nullable = false)
	private int pirce;
	
	@Column(nullable = false)
	private int stockNum;

	@Lob
	private String Detail;
	
	private ItemSellStatus itemSellStatus;
	
	@Enumerated(EnumType.STRING)
	private LocalDateTime crtDt;
	
	private LocalDateTime updtDt;
	
	@Column(length = 200)
	private String attr1;

	@Column(length = 200)
	private String attr2;

	@Column(length = 200)
	private String attr3;

	@Column(length = 200)
	private String attr4;

	@Column(length = 200)
	private String attr5;

	@Column(length = 200)
	private String attr6;

	@Column(length = 200)
	private String attr7;

	@Column(length = 200)
	private String attr8;

	@Column(length = 200)
	private String attr9;

	@Column(length = 200)
	private String attr10;

	
	
}

