package com.shop.demo.product;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.demo.constant.productSellStatus;

@SpringBootTest
public class ProductRepositoryTest {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	ProductRepository productRepository;

	@DisplayName("--- 상품 저장 테스트1 ---")
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
	
	@DisplayName("--- 상품 저장 테스트2 ---")
	public void createProductTest2() {

		for (int i = 0; i < 7; i++) {
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
		
		for (int i = 7; i < 10; i++) {
			Product product = new Product();
			product.setProductName("Test Item" + i);
			product.setPrice(10000 * i);
			product.setProductDetail("Test Desc" + i);
			product.setProductSellStatus(productSellStatus.SOLD_OUT);
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
	
	@DisplayName("--- 상품상세설명 조회 테스트 ---")
	public void findByProductDetail() {

		this.createProductTest();
		
		List<Product> productList = productRepository.findByProdctDetail("Desc");
		for(Product product : productList) {
			System.out.println(product.toString());
		}
		
	}

	@DisplayName("--- QueryDsl 조회 테스트1 ---")
	public void queryDslTest() {
		
		this.createProductTest();
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QProduct qProduct = QProduct.product;
		
		JPAQuery<Product> query = queryFactory.selectFrom(qProduct)
				.where(qProduct.productSellStatus.eq(productSellStatus.SELL))
				.where(qProduct.productDetail.like("%" + "Desc" + "%"))
				.orderBy(qProduct.price.desc());
		
		List<Product> productList = query.fetch();

		for(Product product : productList) {
			System.out.println(product.toString());
		}
		
	}
	
	@Test
	@DisplayName("--- QueryDsl 조회 테스트2 ---")
	public void queryDslTest2(){
		
		this.createProductTest2();
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QProduct product = QProduct.product;
		
		String productDetail = "Desc";
		int price = 10000;
		//String productSellStat = "SELL";
		
		booleanBuilder.and(product.productDetail.like("%" + productDetail + "%"));
		booleanBuilder.and(product.price.gt(price));
		booleanBuilder.and(product.productSellStatus.eq(productSellStatus.SELL));
	
		Pageable pagealbe = PageRequest.of(0, 5);
		Page<Product> productPagingResult = productRepository.findAll(booleanBuilder, pagealbe);
		
		System.out.println("total elements : " + productPagingResult.getTotalElements());
		
		List<Product> resultProductList= productPagingResult.getContent();
		
		for(Product p : resultProductList) {
			System.out.println(p.toString());
		}
		
	}
	
	
	
	
}
