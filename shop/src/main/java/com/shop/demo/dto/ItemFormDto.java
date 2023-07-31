package com.shop.demo.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.shop.demo.constant.ItemSellStatus;
import com.shop.demo.entity.Item;

import lombok.Data;

@Data
public class ItemFormDto {
	/* 상품 생성, 조회 전용 DTO (req/res) */
	private Long id;
	
	@NotBlank(message = "상품명을 입력해주세요.")
	private String itemName;

	@NotNull(message = "가격을 입력해주세요.")
	private Integer price;

	@NotBlank(message = "상품 상세 설명을 입력해주세요.")
	private String itemDetail;

	@NotNull(message = "재고를 입력해주세요.")
	private Integer stockNum;

	private ItemSellStatus itemSellStatus;

	private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
	
	//private List<Long> itemImgIds = new ArrayList<>();
	
	private List<Long> updateItemImgIds = new ArrayList<>();
	
	private List<Long> deleteItemImgIds = new ArrayList<>();
	
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Item createItem() {
		return modelMapper.map(this, Item.class);
	}

	public static ItemFormDto of(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
	}
}
