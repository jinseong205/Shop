package com.shop.demo.order;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shop.demo.user.User;

import lombok.Data;

@Entity
@Table
@Data
public class Order {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private Long id;
	
	@ManyToOne
	private User user;
	
	private LocalDateTime ordDt;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	private LocalDateTime regDt;
	
	private LocalDateTime updtDt;
	
	
}
