package com.shop.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {

	public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {

		UUID uuid = UUID.randomUUID();
		String extendsion = originalFileName.substring(originalFileName.lastIndexOf("."));
		String savedFileName = uuid.toString() + extendsion;
		String fileUploadFullUrl = uploadPath + "/" + savedFileName;

		try {
			FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
			fos.write(fileData);
			fos.close();
		} catch (Exception e) {
			throw new Exception("파일 등록 오류가 발생 하였습니다.");
		}

		return savedFileName;
	}

	public void delteFile(String filePath) throws Exception {
		File deleteFile = new File(filePath);

		if (deleteFile.exists()) {
			deleteFile.delete();
			log.debug("파일을 삭제하였습니다. --- " + filePath);
		} else {
			log.debug("파일이 존재하지 않습니다. --- " + filePath);
		}

	}
}
