package kr.co.trip.mvc.controller.freeboard;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.trip.mvc.page.freeboard.PageDTO;
import kr.co.trip.mvc.service.FreeBoardService;
import kr.co.trip.mvc.util.ClientInfo;
import kr.co.trip.mvc.vo.FreeBoardVO;
import kr.co.trip.mvc.vo.FreeBoard_CommVO;

@Controller
@RequestMapping("/board")
public class FreeboardController {
	//application.properties의 key값으로 설정값을 가져와서 변수에 저장한다.
	@Value("${spring.servlet.multipart.location}")
	private String filePath;
	 
	@Autowired
	private FreeBoardService freeBoardService;

	@RequestMapping("/boardlist")
	public String fBoardList(Model model, @RequestParam(required = false, defaultValue = "1") int pageNo) {		
		 // 전체 게시물 수 조회
	    int totalCount = freeBoardService.getCount();
	    
	    // 한 페이지에 보여줄 항목 수
	    int pageSize = 10;
	    
	    // PageDTO 객체를 사용하여 페이징 정보 계산
	    PageDTO page = new PageDTO(pageNo, pageSize, totalCount);
	    
	    // 시작 번호와 끝 번호를 계산하여 데이터를 조회
	    Map<String, Integer> map = new HashMap<>();
	    map.put("startNo", page.getStartNo());
	    map.put("endNo", page.getEndNo());
	    List<FreeBoardVO> flist = freeBoardService.flistback();
	    List<FreeBoardVO> plist = freeBoardService.getPagelist(map);
	    
	    // 결과를 모델에 추가
	    model.addAttribute("flist", flist);
	    model.addAttribute("plist", plist);
	    model.addAttribute("page", page);
	    
	    return "/board/bbslist";
	}

	
	@GetMapping("/boardwrite")
	public String fboardForm() {		
		return "/board/bbswrite";
	}
	
	@PostMapping("/fboardInsert")
	public String fboardInsert(Model model, @ModelAttribute("flist") FreeBoardVO vo) {
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("oriFn : "+oriFn);
		

		StringBuffer path = new StringBuffer(); // [서버경로]
		path.append(filePath).append("\\");
		path.append(oriFn);
		System.out.println("FullPath : "+ path);
		
		File f = new File(path.toString());
		try {
			mf.transferTo(f);
			vo.setFb_file(oriFn);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}	
		
		ClientInfo clientInfo = new ClientInfo();
	    String clientIp = clientInfo.getIpAddr();
	    vo.setFb_reip(clientIp);
		
	    //Database연동은 여러분이 팀 프로젝트에 맞게 구현합니다.
		freeBoardService.fwrite(vo);
		return "redirect:boardlist";
	}
	
	@GetMapping("/boardDetail")
	public String fboardCommList(Model model, @RequestParam(required = true) int num, HttpServletRequest request, FreeBoard_CommVO cvo) {
	    FreeBoardVO detail = freeBoardService.finfo(num);
	    List<FreeBoard_CommVO> listComm = freeBoardService.commList(num);

	    model.addAttribute("detail", detail);
	    model.addAttribute("listComm", listComm);
	    return "/board/bbdetail";
	}

	
	@GetMapping("/upHit")
	public String fBoardHit(Model model, int num) {
		freeBoardService.hit(num);
		return "redirect:boardDetail?num=" + num;
	}
	
	@GetMapping("/chkPwdForm")
	public String boardChkPwdForm(Model model, @RequestParam Map<String, String> param) {
		System.out.println("num:" + param.get("num")); 
		System.out.println("job:"+param.get("job")); 
		model.addAttribute("map", param);
		return "/board/chkpwdForm";
	}
	
	@GetMapping("/fbdel")
	public String fboardDelete(Model model, int num, HttpSession session) {
			if(session.getAttribute("sessionID") == null) {
				model.addAttribute("errMsg","삭제 권한이 없습니다.");
				return "/board/error";
			}else {
				freeBoardService.del(num);
				return "redirect:boardlist";
			}
			
	}
	
	@GetMapping("/fbmod")
	public String fboardModify(Model model, int num) {
		FreeBoardVO vo = freeBoardService.finfo(num);
		model.addAttribute("vo", vo);
		return "board/bbsmodify";
	}
	
	
	@PostMapping("/fbUpdate")	
	public String fboardUpdate(FreeBoardVO vo) {
		freeBoardService.up(vo);
		return "redirect:boardDetail?num="+vo.getFb_num();
	}
	
	@PostMapping("/fCommInsert")
	public String fboardCommAdd(FreeBoard_CommVO fcvo, Model m) {
		ClientInfo clientInfo = new ClientInfo();
	    String clientIp = clientInfo.getIpAddr();
	    fcvo.setFbc_reip(clientIp);
				
		freeBoardService.addComm(fcvo);
		return "redirect:boardDetail?num="+fcvo.getFbc_ucode()+"&type=comm#comm";
		
	}
	
	@GetMapping("/fbcdel")
	public String fboardCommDel(Model model, int num, HttpSession session,FreeBoardVO vo) {
		if(session.getAttribute("sessionID") == null) {
				model.addAttribute("errMsg","삭제 권한이 없습니다.");
				return "/board/error";
			}else {
				freeBoardService.delComm(num);
				return "redirect:boardDetail?num="+vo.getFb_num();
			}
			
	}
	
	@GetMapping("/fbcoomMod")
	public String fboardCommModify(Model model, int num, HttpSession session) {
		freeBoardService.upComm(num);
		return "/board/bbdetailCommModify";
	}
	
	@PostMapping("/fbcUpdate")
	public String fbcboardUpdate(int num) {
		freeBoardService.upComm(num);
		return "redirect:boardlist";
	}
	
	
}
	









