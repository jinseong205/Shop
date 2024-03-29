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

import com.shop.demo.constant.ItemSellStatus;
import com.shop.demo.dto.ItemFormDto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "item")
@Entity
public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;

	@Column(name = "ITEM_NAME", nullable = false, length = 100)
	private String itemName;

	@Column(nullable = false)
	private int price;

	@Column(name = "STOCK_NUM", nullable = false)
	private int stockNum;

	@Lob
	@Column(name = "ITEM_DETAIL")
	private String itemDetail;

	@Enumerated(EnumType.STRING)
	@Column(name = "IMG_SELL_STATUS")
	private ItemSellStatus itemSellStatus;

	public void updateItem(ItemFormDto itemFormDto) {
		this.itemName = itemFormDto.getItemName();
		this.price = itemFormDto.getPrice();
		this.stockNum = itemFormDto.getStockNum();
		this.itemDetail = itemFormDto.getItemDetail();
		this.itemSellStatus = itemFormDto.getItemSellStatus();
	}

	public void removeStock(int stockNum) throws Exception {
		int restStock = this.stockNum - stockNum;

		if (restStock < 0)
			throw new Exception("상품의 재고가 부족합니다. \n(현재 재고수량 : " + this.stockNum + ")");

		this.stockNum = restStock;
	}

	public void addStock(int stockNumber) {
		this.stockNum += stockNumber;
	}

}
