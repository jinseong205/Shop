package com.shop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.demo.dto.UserFormDto;
import com.shop.demo.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	Cart findByUserId(Long userId);


	
}
