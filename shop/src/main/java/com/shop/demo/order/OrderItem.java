package com.shop.demo.order;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.shop.demo.product.Product;

import lombok.Data;

@Entity
@Data
public class OrderItem {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name= "order_id")
	private Order order;

	private int orderPrice;

	private int count;

	private LocalDateTime crtDt;
	
	private LocalDateTime updtDt;
}
