package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.demo.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	CartItem findByCartIdAndItemId(Long cartId, Long ItemId);

}
