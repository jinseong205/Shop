package com.shop.demo.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shop.demo.entity.ItemImg;
import com.shop.demo.repository.ItemImgRepository;
import com.shop.demo.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

	@Value("${itemImgLocation}")
	private String itemImgLocation;

	private final ItemImgRepository itemImgRepository;

	private final FileService fileService;

	public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws IOException, Exception {
		String oriImgName = itemImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";

		// fileUpload
		if (oriImgName != null && !oriImgName.equals("")) {
			imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			imgUrl = "/images/item/" + imgName;
		}

		// 상품 이미지 정보 저장
		itemImg.updateItemImg(imgName, oriImgName, imgUrl);
		itemImgRepository.save(itemImg);
	}

	public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception {
		if (!itemImgFile.isEmpty()) {
			ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
					.orElseThrow(() -> new Exception("해당 상품을 찾을 수 없습니다."));

			String tempName = savedItemImg.getImgName();
			if (tempName != null && tempName != "") {
				fileService.delteFile(itemImgLocation + "/" + tempName);
			}

			String oriImgName = itemImgFile.getOriginalFilename();
			String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			String imgUrl = "/images/item/" + imgName;

			savedItemImg.updateItemImg(imgName, oriImgName, imgUrl);
		}
	}

	public void deleteItemImg(Long itemImgId) throws Exception {
		
		ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
				.orElseThrow(() -> new Exception("해당 상품을 찾을 수 없습니다."));

		
		try {
			String tempName = savedItemImg.getImgName();
			if (tempName != null && tempName != "") {
			fileService.delteFile(itemImgLocation + "/" + tempName);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			itemImgRepository.deleteById(itemImgId);
		}

	}
}
