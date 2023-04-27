package com.shop.demo.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;
	
	@Test
	@DisplayName("--- 상품 저장 테스트 ---")
	public void createItmeTest() {
		Item item = new Item();
		item.setItemName("Test Item");
		item.setPirce(10000);
		item.setDetail("Test Desc");
	}
	
}
