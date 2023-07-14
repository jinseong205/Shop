package com.shop.demo.dto;

import org.modelmapper.ModelMapper;

import com.shop.demo.entity.ItemImg;

import lombok.Data;

@Data
public class ItemImgDto {

	private Long Id;
	
	private String imgName;
	
	private String oriImgName;
	
	private String imgUrl;
	
	private String repImgYn;
	
	private	static ModelMapper modelMapper = new ModelMapper();
	
	public static ItemImgDto of(ItemImg itemImg) {
		return modelMapper.map(itemImg, ItemImgDto.class);
	}
}
