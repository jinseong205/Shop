package com.shop.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.shop.demo.config.dto.ItemFormDto;
import com.shop.demo.constant.ItemSellStatus;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name="item")
@Entity
public class Item extends BaseEntity{	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private Long id;
	
	@Column(name="ITEM_NAME", nullable = false, length = 100)
	private String itemName;
	
	@Column(nullable = false)
	private int price;
	
	@Column(name="STOCK_NUM", nullable = false)
	private int stockNum;

	@Lob
    @Column(name ="ITEM_DETAIL")
	private String itemDetail;
	
	@Enumerated(EnumType.STRING)
    @Column(name ="IMG_SELL_STATUS")
	private ItemSellStatus itemSellStatus;
	
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

	public void updateItem(ItemFormDto itemFormDto) {
		this.itemName = itemFormDto.getItemName();
		this.price = itemFormDto.getPrice();
		this.stockNum = itemFormDto.getStockNum();
		this.itemDetail = itemFormDto.getItemDetail();
		this.itemSellStatus = itemFormDto.getItemSellStatus();
	}
	
}

