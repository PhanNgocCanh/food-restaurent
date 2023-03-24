package com.thuctaptotnghiep.food.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	public void saveImage(MultipartFile multipartFile,String fileName) throws IOException {			
		String UploadDir = "src/main/resources/static/images";
		Path path = Paths.get(UploadDir);
		if(!Files.exists(path)) {
			Files.createDirectories(path);
		}
		InputStream inputStream = multipartFile.getInputStream();
		try {
			Path filePath = path.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);						
		} catch (IOException|UncheckedIOException e) {
			System.err.println(e.getMessage());
		}finally {
			inputStream.close();
		}
	}
}
