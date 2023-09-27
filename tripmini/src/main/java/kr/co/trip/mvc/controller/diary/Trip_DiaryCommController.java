package kr.co.trip.mvc.controller.diary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import kr.co.trip.mvc.service.Trip_DiaryService;
import kr.co.trip.mvc.vo.Dia_CommVO;
import kr.co.trip.mvc.vo.PageVO;

@RestController
@RequestMapping("diaComm")
public class Trip_DiaryCommController {
	 
	@Autowired
	Trip_DiaryService diaryService;
	
	@Autowired
	private PageVO pagevo;
	
	@PostMapping("/insertDiaComm")
	public String insertDiaComm(@RequestBody Dia_CommVO vo, HttpSession session) {
		System.out.println("댓글 등록 통신 성공");
		//request.getAttribute("dia_comm_nick");
		System.out.println("코드 :"+ vo.getDia_comm_code());
		System.out.println("닉네임 :"+ vo.getDia_comm_nick());
		System.out.println("내용 :"+ vo.getDia_comm_cont());
		System.out.println("아이피 :"+ vo.getDia_comm_ip());
		
		
		if(session.getAttribute("sessionNick") == null) {
			return "fail";
		} else {
			System.out.println("로그인 함. 스크랩 진행");
			
			/*diaryService*/
			diaryService.adddiaComm(vo);
			System.out.println("댓글 등록 서비스 성공");
			return "InsertSuccess";
		}
	}
	
	@GetMapping("/diaCommList/{dia_comm_code}")
	public Map<String, Object> getList(@PathVariable int dia_comm_code, Model model, @RequestParam String cPage) {
		System.out.println("댓글 목록 컨트롤러 동작");
		System.out.println("cPage :" + cPage);
		int total = diaryService.diaCommTotal(dia_comm_code);
		
		//댓글 페이징 처리
		pagevo.setTotalRecord(total);
		int totalRecord = pagevo.getTotalRecord();
		System.out.println("1. TotalComm :" + totalRecord);
		
		pagevo.setTotalPage((int)Math.ceil(totalRecord/(double)pagevo.getNumPerPage()));
		System.out.println("2. totalCommPage : "+pagevo.getTotalPage());
		
		pagevo.setTotalBlock((int) Math.ceil((double) pagevo.getTotalPage() / pagevo.getPagePerBlock()));
		
		if (cPage != null) {
			pagevo.setNowPage(Integer.parseInt(cPage));
		}else {
			pagevo.setNowPage(1);
		}
		System.out.println("4. CommnowPage : "+pagevo.getNowPage());
		
		pagevo.setBeginPerPage((pagevo.getNowPage()-1) * pagevo.getNumPerPage()+1);
		pagevo.setEndPerPage((pagevo.getBeginPerPage()-1) + pagevo.getNumPerPage());
		System.out.println("5. CommbeginPerPage : "+pagevo.getBeginPerPage());
		System.out.println("5. CommendPerPage : "+pagevo.getEndPerPage());
		
		
		// 페이징 테스트
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pagevo.getBeginPerPage()));
		map.put("end", String.valueOf(pagevo.getEndPerPage()));
		map.put("dia_comm_code", String.valueOf(dia_comm_code));
		
		System.out.println("========Map All=========");
		for(Map.Entry<String, String> e : map.entrySet()) {
			System.out.println(e.getKey()+","+e.getValue());
		}
		
		List<Dia_CommVO> diacommlist = diaryService.diaCommList(map);
		
		int startPage = (int)((pagevo.getNowPage() - 1)/pagevo.getPagePerBlock()) * pagevo.getPagePerBlock()+1;
		int endPage = startPage + pagevo.getPagePerBlock()-1;
		if(endPage > pagevo.getTotalPage()) {
			endPage = pagevo.getTotalPage(); }
		System.out.println("6. CommstartPage : "+startPage);
		System.out.println("6. CommendPage : "+endPage);
		//페이징 처리 끝
		
		ModelAndView view = new ModelAndView();
		System.out.println("댓글 갯수 " + total);
		view.setViewName("/dairy/detailDiary");
		Map<String, Object> commmap = new HashMap<>();
		commmap.put("startPage", startPage);// 블록의 시작페이지값
		commmap.put("endPage", endPage);// 블록의 끝 값
		commmap.put("page", pagevo);// nowPage, pagePerBlock, totalPage
		commmap.put("diacommlist", diacommlist);
		commmap.put("total", total);
		
		return commmap;
	}
	
	@GetMapping(value = "/modDiaComm/{dia_comm_num}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> modDiaComm(@PathVariable int dia_comm_num, @RequestParam int dia_comm_code) {
        System.out.println("편집 메서드 실행:"+dia_comm_num);
        System.out.println("편집 메서드 실행:"+dia_comm_code);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("dia_comm_num", dia_comm_num);
        params.put("dia_comm_code", dia_comm_code);
        System.out.println("dia_comm_num :"+params.get("dia_comm_num"));
        System.out.println("dia_comm_code :"+params.get("dia_comm_code"));
                
        try {
            Dia_CommVO dcvo = diaryService.modDiaComm(params);
            if (dcvo != null) {
                // Jackson ObjectMapper를 사용하여 Dia_CommVO 객체를 JSON 형태로 변환합니다.
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(dcvo);
                // 댓글 정보를 성공적으로 가져왔음을 JSON 형태로 응답합니다.
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8) // Content-Type을 명시적으로 설정
                        .body("{\"success\": true, \"comment\": " + json + "}");
            } else {
                // 댓글 정보를 가져오는데 실패했음을 JSON 형태로 응답합니다.
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"success\": false}");
            }
        } catch (Exception e) {
            // 예외가 발생했음을 JSON 형태로 응답합니다.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
        }
    }

	@DeleteMapping("/delDiaComm/{dia_comm_num}")
	public ResponseEntity<?> delDiaComm(@PathVariable int dia_comm_num) {
	    try {
	        // 삭제 작업 수행
	        diaryService.delDiaComm(dia_comm_num);
	        // 삭제가 성공적으로 이루어졌음을 JSON 형태로 응답합니다.
	        System.out.println(dia_comm_num+"번 댓글 삭제에 성공했습니다.");
	        return ResponseEntity.ok().body("{\"success\": true}");
	    } catch (Exception e) {
	        // 삭제 작업이 실패했음을 JSON 형태로 응답합니다.
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
	    }
	}
	
	@PutMapping("/upDiaComm/{dia_comm_num}")
	public ResponseEntity<?> upDiaComm(@PathVariable int dia_comm_num, @RequestBody Dia_CommVO vo) {
		System.out.println("수정 ajax 확인" + dia_comm_num);
		System.out.println("vo의 넘버"+vo.getDia_comm_num());
		System.out.println("vo의 컨텐트"+vo.getDia_comm_cont());
	    try {
	        // 업데이트 작업 수행
	        // updatedComm 객체에는 클라이언트로부터 받은 업데이트된 데이터가 담겨 있습니다.
	        // 예를 들어, updatedComm.getContent()로 업데이트된 댓글 내용에 접근할 수 있습니다.
	        diaryService.upDiaComm(vo);

	        // 업데이트가 성공적으로 이루어졌음을 JSON 형태로 응답합니다.
	        System.out.println(dia_comm_num + "번 댓글 업데이트에 성공했습니다.");
	        return ResponseEntity.ok().body("{\"success\": true}");
	    } catch (Exception e) {
	        // 업데이트 작업이 실패했음을 JSON 형태로 응답합니다.
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
	    }
	}

}
