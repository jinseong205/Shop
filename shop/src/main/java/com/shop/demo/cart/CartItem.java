package com.shop.demo.cart;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.shop.demo.order.Order;
import com.shop.demo.product.Product;

import lombok.Data;

@Entity
@Data
public class CartItem {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private Long id;

	@ManyToOne
	@JoinColumn(name= "cart_id")
	private Cart order;

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	private int count;

}
