package kr.co.trip.mvc.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	@Value("${spring.servlet.multipart.location}")
	private String filePath;
	
	public void uploadFile(MultipartFile file) {
		String oriFn = file.getOriginalFilename();
		System.out.println("oriFn : " + oriFn);

		StringBuffer path = new StringBuffer(); // [서버경로]
		path.append(filePath).append(File.separator); // File.separator를 사용하여 경로를 구분합니다.
		path.append(oriFn);
		System.out.println("FullPath : " + path);
		
		File f = new File(path.toString());
		try {
			file.transferTo(f);
			// 파일 업로드 후 추가 작업을 수행해야 한다면 여기에 추가하세요.
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
}
