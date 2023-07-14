package com.shop.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.shop.demo.constant.ItemSellStatus;
import com.shop.demo.dto.ItemFormDto;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.ItemImg;
import com.shop.demo.repository.ItemImgRepository;
import com.shop.demo.repository.ItemRepository;
import com.shop.demo.service.ItemService;
import com.shop.demo.test.custom.WithMockCustomUser;

@SpringBootTest
@Transactional
public class ItemServiceTest {

	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemImgRepository itemImgRepository;
	
	List<MultipartFile> createMultipartFiles() throws Exception {
		List<MultipartFile> multipartFileList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			String path = "C:/shop/item/";
			String imageName = "image" + i + ".jpg";
			MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[] {1,2,3,4});
			multipartFileList.add(multipartFile);
		}
		return multipartFileList;
	}
	
	
	@Test
	@DisplayName("상품 등록 테스트")
	@WithMockCustomUser
	void saveItme() throws Exception{
		ItemFormDto itemFormDto = new ItemFormDto();
		itemFormDto.setItemName("테스트 상품");
		itemFormDto.setItemSellStatus(ItemSellStatus.SELL);	
		itemFormDto.setItemDetail("테스트 상품입니다.");
		itemFormDto.setPrice(1000);
		itemFormDto.setStockNum(100);
		
		List<MultipartFile> multipartFileList = createMultipartFiles();
		
		Long itemId = itemService.saveItem(itemFormDto, multipartFileList).getId();
		
		List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
		
		Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);	
		
		assertEquals(itemFormDto.getItemName(), item.getItemName());
		assertEquals(itemFormDto.getItemSellStatus(), item.getItemSellStatus());
		assertEquals(itemFormDto.getItemDetail(), item.getItemDetail());
		assertEquals(itemFormDto.getPrice(), item.getPrice());
		assertEquals(itemFormDto.getStockNum(), item.getStockNum());
		assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriImgName());	
		}
	
}
