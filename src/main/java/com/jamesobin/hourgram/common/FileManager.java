package com.jamesobin.hourgram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	public final static String FILE_UPLOAD_PATH = "E:\\웹개발\\06.springProject\\upload\\HOURgram"; 

	public static String saveFile(int userId, MultipartFile file) {
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 디렉토리 만들기
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath);
			
			Files.write(path, bytes);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			// 파일 저장 실패
			return null;	
		}
		
		// E:\\dulumaryT\\web\\20240729\\springProject\\upload\\memo/2_238545309/test.png
		// /images/2_238545309/test.png
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	}
	
	public static boolean removeFile(String filePath) { // /images/2_89723498273/test.png
		if(filePath == null) {
			return false;
		}
		
		// E:\\웹개발\\06.springProject\\upload\\memo/2_89723498273/test.png
		
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		Path path = Paths.get(fullFilePath);
		
		Path directoryPath = path.getParent();
		
		try {
			Files.delete(path);
			Files.delete(directoryPath);
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
}
