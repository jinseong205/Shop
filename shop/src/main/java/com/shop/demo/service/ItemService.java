package com.shop.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shop.demo.config.dto.ItemFormDto;
import com.shop.demo.config.dto.ItemImgDto;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.ItemImg;
import com.shop.demo.repository.ItemImgRepository;
import com.shop.demo.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	private final ItemImgService itemImgService;
	private final ItemImgRepository itemImgRepository;

	public Item saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws IOException, Exception {
		Item item = itemFormDto.createItem();
		itemRepository.save(item);

		for (int i = 0; i < itemImgFileList.size(); i++) {
			ItemImg itemImg = new ItemImg();
			itemImg.setItem(item);
			if (i == 0)
				itemImg.setRepImgYn("Y");
			else
				itemImg.setRepImgYn("N");
			itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
		}
		return item;
	}

	@Transactional(readOnly = true)
	public ItemFormDto getItemDtl(Long itemId) throws Exception {
		
		List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
		List<ItemImgDto> itemImgDtoList = new ArrayList<>();
		
		
		for (ItemImg itemImg : itemImgList) {
			ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
			itemImgDtoList.add(itemImgDto);
		}
		
		Item item = itemRepository.findById(itemId)
				.orElseThrow(()-> new Exception("해당 상품을 찾을 수 없습니다. itemdId : " + itemId));		
		ItemFormDto itemFormDto = ItemFormDto.of(item);
		itemFormDto.setItemImgDtoList(itemImgDtoList);
		
		return itemFormDto;
	}

}
