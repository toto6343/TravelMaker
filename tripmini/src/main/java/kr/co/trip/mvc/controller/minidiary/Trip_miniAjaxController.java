package kr.co.trip.mvc.controller.minidiary;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.trip.mvc.service.Mini_DiaryService;
import kr.co.trip.mvc.vo.Dia_CommVO;
import kr.co.trip.mvc.vo.Mini_HomeVO;

@RestController
@RequestMapping("miniajax")
public class Trip_miniAjaxController{
	
	@Autowired
	private Mini_DiaryService mini_DiaryService;
	
	@Value("${spring.servlet.multipart.location}")
	private String filePath;

	@PutMapping("upThumbnail")
	public ResponseEntity<?> upThumbnail(@RequestBody Mini_HomeVO vo, int num) {
		System.out.println("넘버 : " + vo.getMini_home_num());
		System.out.println("썸네일 : "+vo.getMini_home_thumbnail());
		System.out.println("사진파일"+vo.getMfile());
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("oriFn : "+oriFn);
		
		StringBuffer path = new StringBuffer(); // [서버경로]
		path.append(filePath).append("\\");
		path.append(oriFn);
		System.out.println("FullPath : "+path);
		
		File f = new File(path.toString());
		try {
			mf.transferTo(f);
			vo.setMini_home_thumbnail(oriFn);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	    try {
	    	mini_DiaryService.updatehomethumbnail(vo);
	        System.out.println(vo.getMini_home_num() + "번 썸네일 업데이트에 성공했습니다.");
	        return ResponseEntity.ok().body("{\"success\": true}");
	    } catch (Exception e) {
	        System.out.println(vo.getMini_home_num() + "번 썸네일 업데이트에 실패했습니다.");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
	    }
	}
	@GetMapping(value = "modThumbnail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> modThumbnail(@PathVariable @RequestParam int num) {
		 System.out.println("편집 메서드 실행:"+num);
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("mini_home_num", num);
	        System.out.println("mini_home_num :"+params.get("mini_home_num"));
	    
	    try {
	    	Mini_HomeVO minivo = mini_DiaryService.modhomethumbnail(params);
	        if (minivo != null) {
	            ObjectMapper mapper = new ObjectMapper();
	            String json = mapper.writeValueAsString(minivo);
	            return ResponseEntity.ok()
	                    .contentType(MediaType.APPLICATION_JSON_UTF8) // Content-Type을 명시적으로 설정
	                    .body("{\"success\": true, \"comment\": " + json + "}")
	                    ;
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"success\": false}");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
	    }
	}	
	
	
	
	
	
	
	@PutMapping("/upMiniTitle")
	public ResponseEntity<?> upMiniTitle(@RequestBody Mini_HomeVO vo, int num) {
		System.out.println("넘버 : " + vo.getMini_home_num());
		System.out.println("타이틀 : "+vo.getMini_home_title());
	    try {
	        // 업데이트 작업 수행
	        // updatedComm 객체에는 클라이언트로부터 받은 업데이트된 데이터가 담겨 있습니다.
	        // 예를 들어, updatedComm.getContent()로 업데이트된 댓글 내용에 접근할 수 있습니다.
	    	mini_DiaryService.updatehometitle(vo);

	        // 업데이트가 성공적으로 이루어졌음을 JSON 형태로 응답합니다.
	        System.out.println(vo.getMini_home_num() + "번 댓글 업데이트에 성공했습니다.");
	        return ResponseEntity.ok().body("{\"success\": true}");
	    } catch (Exception e) {
	        // 업데이트 작업이 실패했음을 JSON 형태로 응답합니다.
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
	    }
	}
	@GetMapping(value = "/modTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> modTitle(@PathVariable @RequestParam int num) {
		 System.out.println("편집 메서드 실행:"+num);
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("mini_home_num", num);
	        System.out.println("mini_home_num :"+params.get("mini_home_num"));
	    
	    try {
	    	Mini_HomeVO minivo = mini_DiaryService.modhometitle(params);
	        if (minivo != null) {
	            // Jackson ObjectMapper를 사용하여 Dia_CommVO 객체를 JSON 형태로 변환합니다.
	            ObjectMapper mapper = new ObjectMapper();
	            String json = mapper.writeValueAsString(minivo);
	            // 댓글 정보를 성공적으로 가져왔음을 JSON 형태로 응답합니다.
	            return ResponseEntity.ok()
	                    .contentType(MediaType.APPLICATION_JSON_UTF8) // Content-Type을 명시적으로 설정
	                    .body("{\"success\": true, \"comment\": " + json + "}")
	                    ;
	        } else {
	            // 댓글 정보를 가져오는데 실패했음을 JSON 형태로 응답합니다.
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"success\": false}");
	        }
	    } catch (Exception e) {
	        // 예외가 발생했음을 JSON 형태로 응답합니다.
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
	    }
	}

	/* 추후에 지은이 확인해볼 코드
	@GetMapping(value = "modTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> modTitle(@RequestParam int num) {
	    System.out.println("번호"+num);
	    
	    try {
            System.out.println("122");
	    	Mini_HomeVO minivo = mini_DiaryService.modhometitle(num);
	    	System.out.println("내용 : "+minivo.getMini_home_title());
	        if (minivo != null) {
	            // Jackson ObjectMapper를 사용하여 Dia_CommVO 객체를 JSON 형태로 변환합니다.
	            ObjectMapper mapper = new ObjectMapper();
	            System.out.println("1");
	            String json = mapper.writeValueAsString(minivo);
	            System.out.println("12");
	            // 댓글 정보를 성공적으로 가져왔음을 JSON 형태로 응답합니다.
	            return ResponseEntity.ok()
	                    .contentType(MediaType.APPLICATION_JSON_UTF8) // Content-Type을 명시적으로 설정
	                    .body("{\"success\": true, \"comment\": " + json + "}")
	                    ;
	        } else {
	            // 댓글 정보를 가져오는데 실패했음을 JSON 형태로 응답합니다.
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"success\": false}");
	        }
	    } catch (Exception e) {
	        // 예외가 발생했음을 JSON 형태로 응답합니다.
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
	    }
	}*/
}
