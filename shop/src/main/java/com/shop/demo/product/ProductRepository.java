package com.shop.demo.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product>{
	
	List<Product> findByProductName(String productName);

	List<Product> findByProductNameOrProductDetail(String productName, String productDetail);
	
	List<Product> findByPriceLessThan(Integer price);
	
	List<Product> findByPriceLessThanOrderByPriceDesc(Integer price);
	
	@Query(value="SELECT * FROM product p WHERE p.productDetail like %?1% ORDER BY p.price DESC", nativeQuery = true)
	List<Product> findByProdctDetail(@Param("productDetail") String productDetail);
}
