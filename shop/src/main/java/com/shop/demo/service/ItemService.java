package com.shop.demo.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.demo.config.dto.ItemFormDto;
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

	public Long saveIttem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFIleList) throws IOException, Exception {
		Item item = itemFormDto.createItem();
		itemRepository.save(item);

		for (int i = 0; i < itemImgFIleList.size(); i++) {
			ItemImg itemImg = new ItemImg();
			itemImg.setItem(item);
			if (i == 0)
				itemImg.setRepImgYn("Y");
			else
				itemImg.setRepImgYn("N");
			itemImgService.saveItemImg(itemImg, itemImgFIleList.get(i));
		}
		return item.getId();
	}

}
