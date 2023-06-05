package com.shop.demo.order;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.shop.demo.product.Product;

import lombok.Data;

@Entity
@Data
public class OrderProduct {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name= "id")
	private Order order;

	private int orderPrice;

	private int count;

	private LocalDateTime regDt;
	
	private LocalDateTime updtDt;
}
