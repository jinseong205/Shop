package com.shop.demo.product;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.demo.constant.productSellStatus;

@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;

	@DisplayName("--- 상품 저장 테스트 ---")
	public void createProductTest() {

		for (int i = 0; i < 10; i++) {

			Product product = new Product();
			product.setProductName("Test Item" + i);
			product.setPrice(10000 * i);
			product.setProductDetail("Test Desc" + i);
			product.setProductSellStatus(productSellStatus.SELL);
			product.setStockNum(100 * i);
			product.setCrtDt(LocalDateTime.now());
			product.setUpdtDt(LocalDateTime.now());

			productRepository.save(product);
		}
	}

	@DisplayName("--- 상품명 조회 테스트 ---")
	public void findByProductList() {

		this.createProductTest();
		
		List<Product> productList = productRepository.findByProductName("Test Item1");
		for(Product product : productList) {
			System.out.println(product.toString());
		}
		
	}
	
	@DisplayName("--- 상품명, 상품상세설명 or 조회 테스트 ---")
	public void findByProductNameOrProductDetail() {

		this.createProductTest();
		
		List<Product> productList = productRepository.findByProductNameOrProductDetail("Test Item2","Test Desc3");
		for(Product product : productList) {
			System.out.println(product.toString());
		}
		
	}
	
	@DisplayName("--- 가격 LessThan 조회 테스트 ---")
	public void findByPriceLessThanTest() {

		this.createProductTest();
		
		List<Product> productList = productRepository.findByPriceLessThan(60000);
		for(Product product : productList) {
			System.out.println(product.toString());
		}
		
	}
	
	@DisplayName("--- 가격 LessThan 내림차순 조회 테스트 ---")
	public void findByPriceLessThanOrderByPriceTest() {

		this.createProductTest();
		
		List<Product> productList = productRepository.findByPriceLessThanOrderByPriceDesc(60000);
		for(Product product : productList) {
			System.out.println(product.toString());
		}
		
	}
	
	@Test
	@DisplayName("--- 상품상세설명 조회 테스트 ---")
	public void findByProductDetail() {

		this.createProductTest();
		
		List<Product> productList = productRepository.findByProdctDetail("Desc");
		for(Product product : productList) {
			System.out.println(product.toString());
		}
		
	}

}
