package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.demo.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
