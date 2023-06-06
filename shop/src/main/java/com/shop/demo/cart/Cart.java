package com.shop.demo.cart;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.shop.demo.user.User;

import lombok.Data;

@Entity
@Table(name="cart")
@Data
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	
}
