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
import com.shop.demo.dto.ItemMainDto;
import com.shop.demo.dto.ItemSearchDto;
import com.shop.demo.dto.QItemMainDto;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.QItem;
import com.shop.demo.entity.QItemImg;
import com.shop.demo.entity.User;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

	private JPAQueryFactory queryFactory;

	public ItemRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	// 상품 판매상태 기준 조회
	private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
		return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
	}

	// 상품 등록일 기준 조회
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

	// 상품명 or 등록자 기준 조회
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {

		if (searchBy != null) {
			if (searchBy.equals("itemName"))
				return QItem.item.itemName.like("%" + searchQuery + "%");
			else if (searchBy.equals("createdBy"))
				return QItem.item.crtName.like("%" + searchQuery + "%");
		}
		return null;
	}

	// 상품 등록자 기준 조회
	private BooleanExpression searchByCrtId(String crtNmae) {
		return QItem.item.crtName.eq(crtNmae);
	}

	// 상품명 기준 조회
	private BooleanExpression itemNameLike(String searchQuery) {
		return searchQuery == null || searchQuery.equals("") ? null : QItem.item.itemName.like("%" + searchQuery + "%");
	}

	// 상품 관리 조회
	@Override
	public Page<Item> getItemManagePage(ItemSearchDto itemSearchDto, Pageable pageable, User user) {

		QueryResults<Item> results;
		List<Item> content;
		long total;

		if (user.getRoles().contains("ROLE_ADMIN")) {
			results = queryFactory.selectFrom(QItem.item)
					.where(crtDtsAfter(itemSearchDto.getSearchDateType()),
							searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
							searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
					.orderBy(QItem.item.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize())
					.fetchResults();

			content = results.getResults();
			total = results.getTotal();

			return new PageImpl<>(content, pageable, total);
		} else {
			results = queryFactory.selectFrom(QItem.item)
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

	// 상품 메인 페이지 조회
	@Override
	public Page<ItemMainDto> getItemMainPage(ItemSearchDto itemSearchDto, Pageable pageable) {

		QItem item = QItem.item;
		QItemImg itemImg = QItemImg.itemImg;

		QueryResults<ItemMainDto> results = queryFactory
				.select(new QItemMainDto(item.id, item.itemName, item.itemDetail, itemImg.imgUrl, item.price))
				.from(itemImg).join(itemImg.item, item).where(itemImg.repImgYn.eq("Y"))
				.where(itemNameLike(itemSearchDto.getSearchQuery())).orderBy(item.id.desc())
				.offset(pageable.getOffset()).limit(pageable.getPageSize()).fetchResults();

		List<ItemMainDto> content = results.getResults();
		long total = results.getTotal();

		return new PageImpl<ItemMainDto>(content, pageable, total);
	}

}
