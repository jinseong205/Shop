package com.shop.demo.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import com.shop.demo.dto.ItemSearchDto;
import com.shop.demo.entity.Item;

public interface ItemRepositoryCustom {

	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
