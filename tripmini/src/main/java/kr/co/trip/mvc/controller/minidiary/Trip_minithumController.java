package kr.co.trip.mvc.controller.minidiary;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.trip.mvc.service.Mini_DiaryService;
import kr.co.trip.mvc.vo.Dia_CommVO;
import kr.co.trip.mvc.vo.Mini_HomeVO;

@RestController
@RequestMapping("minithum")
public class Trip_minithumController{
	
	@Autowired
	private Mini_DiaryService mini_DiaryService;
	
	@Value("${spring.servlet.multipart.location}")
	private String filePath;

	@PutMapping("/upMinithum")
	public ResponseEntity<?> upMiniTitle(@RequestBody Mini_HomeVO vo, int num) {
		System.out.println("넘버 : " + vo.getMini_home_num());
		System.out.println("타이틀 : "+vo.getMini_home_thumbnail());
	    try {
	    	mini_DiaryService.updatehometitle(vo);
	        System.out.println(vo.getMini_home_num() + "번 댓글 업데이트에 성공했습니다.");
	        return ResponseEntity.ok().body("{\"success\": true}");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
	    }
	}
	@GetMapping(value = "/modThum", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> modTitle(@PathVariable @RequestParam int num) {
		 System.out.println("편집 메서드 실행:"+num);
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("mini_home_num", num);
	        System.out.println("mini_home_num :"+params.get("mini_home_num"));
	    
	    try {
	    	Mini_HomeVO minivo = mini_DiaryService.modhomethumbnail(params);
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

}
