package com.shop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.shop.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>{
	
	List<Item> findByItemName(String itemName);

	List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);
	
	List<Item> findByPriceLessThan(Integer price);
	
	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
	
	@Query(value="SELECT * FROM item i WHERE i.itemDetail like %?1% ORDER BY i.price DESC", nativeQuery = true)
	List<Item> findByProdctDetail(@Param("itemDetail") String itemDetail);
}
