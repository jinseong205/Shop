package com.shop.demo.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.shop.demo.user.User;

import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	private LocalDateTime ordDt;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)	//연관관계의 주인이 아님. 
	private List<OrderItem> orderProducts = new ArrayList<>();
	
	private LocalDateTime crtDt;
	
	private LocalDateTime updtDt;
	
	
}
