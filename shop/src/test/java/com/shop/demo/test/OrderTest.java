package com.shop.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.demo.constant.ItemSellStatus;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.Order;
import com.shop.demo.entity.OrderItem;
import com.shop.demo.repository.ItemRepository;
import com.shop.demo.repository.OrderRepository;

@SpringBootTest
@Transactional
public class OrderTest {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@PersistenceContext
	EntityManager em;
	
	@DisplayName("--- 상품 저장 테스트 ---")
	public Item createItem(){

			Item product = new Item();
			product.setItemName("Test Item" );
			product.setPrice(10000 );
			product.setItemDetail("Test Desc" );
			product.setItemSellStatus(ItemSellStatus.SELL);
			product.setStockNum(100);
			product.setCrtDt(LocalDateTime.now());
			product.setUpdtDt(LocalDateTime.now());

			return product;
	}

	@Test
	@DisplayName("--- 상품 저장 테스트1 ---")
	public void cascadeTest() {
		Order order = new Order();

		for(int i = 0; i < 3; i++) {
			Item item = this.createItem();
			itemRepository.save(item);
			
			OrderItem orderItem = new OrderItem();
			orderItem.setItem(item);		
			orderItem.setCount(10);
			orderItem.setOrderPrice(1000);
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}

		orderRepository.saveAndFlush(order);
		em.clear();
		
		Order savedOrder = orderRepository.findById(order.getId())
							.orElseThrow(EntityNotFoundException::new);
		
		assertEquals(3, savedOrder.getOrderItems().size());
	}
	
	
}
