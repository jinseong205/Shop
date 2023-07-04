package com.shop.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class OrderItem {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name= "order_id")
	private Order order;

	private int orderPrice;

	private int count;

	@CreationTimestamp	
	@Column(name="CRT_DT")
	private LocalDateTime crtDt;
	
	@CreationTimestamp	
	@Column(name="UPDT_DT")
	private LocalDateTime updtDt;
}
