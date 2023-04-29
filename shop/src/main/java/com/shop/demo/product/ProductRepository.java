package com.shop.demo.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByProductName(String productName);

	List<Product> findByProductNameOrProductDetail(String productName, String productDetail);
	
	List<Product> findByPriceLessThan(Integer price);
}
