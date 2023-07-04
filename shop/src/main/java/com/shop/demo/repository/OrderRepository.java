package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
