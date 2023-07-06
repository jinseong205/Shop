package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.demo.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
