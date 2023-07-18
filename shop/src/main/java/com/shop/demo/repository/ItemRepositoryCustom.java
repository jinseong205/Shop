package com.shop.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.demo.dto.ItemSearchDto;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.User;

public interface ItemRepositoryCustom {

	Page<Item> getMangerItemPage(ItemSearchDto itemSearchDto, Pageable pageable, User user);
}
