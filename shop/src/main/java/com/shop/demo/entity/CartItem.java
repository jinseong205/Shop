package com.shop.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class CartItem {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "cart_id")
	private Cart order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	private int count;

}
