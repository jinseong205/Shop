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
	private Long id;
	
	@NotBlank(message = "상품명은 필수 입력 값입니다.")
	private String itemName;

	@NotNull(message = "가격은 필수 입력 값입니다.")
	private Integer price;

	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String itemDetail;

	@NotNull(message = "재고는 필수 입력 값입니다.")
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
