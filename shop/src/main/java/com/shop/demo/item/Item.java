package com.shop.demo.item;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.shop.demo.constant.ItemSellStatus;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class Item {
	private Long id;
	private String itemName;
	private int pirce;
	private int stockNum;
	private String Detail;
	private ItemSellStatus itemSellStatus;
	private LocalDateTime crtDt;
	private LocalDateTime updtDt;
	
}
