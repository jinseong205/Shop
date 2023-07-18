package com.shop.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.demo.constant.ItemSellStatus;
import com.shop.demo.dto.ItemSearchDto;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.QItem;
import com.shop.demo.entity.User;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

	private JPAQueryFactory queryFactory;

	public ItemRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
		return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
	}

	private BooleanExpression crtDtsAfter(String searchDateType) {

		LocalDateTime dateTime = LocalDateTime.now();

		if (searchDateType == null || searchDateType.equals("all"))
			return null;
		else if (searchDateType.equals("1d"))
			dateTime = dateTime.minusDays(1);
		else if (searchDateType.equals("1w"))
			dateTime = dateTime.minusWeeks(1);
		else if (searchDateType.equals("1m"))
			dateTime = dateTime.minusMonths(1);
		else if (searchDateType.equals("6m"))
			dateTime = dateTime.minusMonths(6);

		return QItem.item.crtDt.after(dateTime);
	}

	private BooleanExpression searchByLike(String searchBy, String searchQuery) {

		if (searchBy != null) {
			if (searchBy.equals("itemName"))
				return QItem.item.itemName.like("%" + searchQuery + "%");
			else if (searchBy.equals("createBy"))
				return QItem.item.itemName.like("%" + searchQuery + "%");
		}
		return null;
	}
	
	private BooleanExpression searchByCrtId(String crtNmae) {
		return QItem.item.crtName.eq(crtNmae);
	}
	

	@Override
	public Page<Item> getMangerItemPage(ItemSearchDto itemSearchDto, Pageable pageable, User user) {

		QueryResults<Item> results;
		List<Item> content;
		long total;
		
		if (user.getRoles().contains("ROLE_ADMIN")) {
			results = queryFactory.select(QItem.item)
					.where(crtDtsAfter(itemSearchDto.getSearchDateType()),
							searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
							searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
					.orderBy(QItem.item.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize())
					.fetchResults();

			content = results.getResults();
			total = results.getTotal();

			return new PageImpl<>(content, pageable, total);
		} else{
			results = queryFactory.select(QItem.item)
					.where(crtDtsAfter(itemSearchDto.getSearchDateType()),
							searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
							searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()),
							searchByCrtId(user.getUsername()))
					.orderBy(QItem.item.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize())
					.fetchResults();
			content = results.getResults();
			total = results.getTotal();

			return new PageImpl<>(content, pageable, total);
		}

	}

}
