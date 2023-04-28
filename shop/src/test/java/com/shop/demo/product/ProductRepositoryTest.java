package com.shop.demo.product;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.demo.constant.productSellStatus;


@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	@DisplayName("--- 상품 저장 테스트 ---")
	public void createItmeTest() {
		Product product = new Product();
		product.setProductName("Test Item");
		product.setPirce(10000);
		product.setDetail("Test Desc");
		product.setProductSellStatus(productSellStatus.SELL);
		product.setStockNum(1000);
		product.setCrtDt(LocalDateTime.now());
		product.setUpdtDt(LocalDateTime.now());
		
		productRepository.save(product);
	}
	
}
