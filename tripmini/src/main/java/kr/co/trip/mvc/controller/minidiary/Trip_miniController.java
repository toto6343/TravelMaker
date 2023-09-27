package kr.co.trip.mvc.controller.minidiary;

import java.io.File;
import java.io.IOException;
import java.util.List;


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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.trip.mvc.service.Mini_DiaryService;
import kr.co.trip.mvc.service.TripMemberService;
import kr.co.trip.mvc.vo.Mini_CalVO;
import kr.co.trip.mvc.vo.Mini_DiaryVO;
import kr.co.trip.mvc.vo.Mini_HomeVO;
import kr.co.trip.mvc.vo.Trip_MemberVO;

@Controller
@RequestMapping("/mini")
public class Trip_miniController{
	
	@Autowired
	private Mini_DiaryService mini_DiaryService;

	@Value("${spring.servlet.multipart.location}")
	private String filePath;
	
	@GetMapping("/mdiaryhome")
	public String minidiary() {
		return "mini/mdiaryhome";
	}
	
	@GetMapping("/mdiaryform")
	public String minidiaryForm() {
		return "mini/mdiaryform";
	}

	@RequestMapping("/minidiaryDetail")
	public String minidiaryDetail(Model model, int num, HttpServletRequest request) {
		Mini_HomeVO detail = mini_DiaryService.minihomedetail(num);	
		//System.out.println(detail.getMini_home_content());
		//System.out.println(detail);
		model.addAttribute("detail", detail);		
		return "mini/mdiaryhome";
	}

	@GetMapping("/mphotoform")
	public String mphotoform() {
		return "mini/mphotoform";
	}

	@GetMapping("/minimap")
	public String minimap() {
		return "mini/minimap";
	}
	
	// 미니 홈피 추가 및 미니 홈피 리스트
	@RequestMapping("/mini_diarylist")
	public String mini_diarylist(Model model, HttpSession session) {
		String mini_home_host = (String) session.getAttribute("sessionNick");
		List<Mini_HomeVO> mlist = mini_DiaryService.minihomelist(mini_home_host);
		model.addAttribute("mlist", mlist);
		return "mini/mini_diarylist";
	}
	
	@GetMapping("/mini_diary_add")
	public String mini_diary_add() {
		return "mini/mini_diary_add";
	}
	
	
	@PostMapping("/mini_diary_Insert")
	public String mini_diary_Insert(Mini_HomeVO vo) {
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
		System.out.println(vo.getMini_home_content());
		mini_DiaryService.minihompyadd(vo);
		return "redirect:mini_diarylist";
	}
	
	// 미니 사진첩 리스트 
	@RequestMapping("/mphotoList")
	public String mphotoList(Model model, int num, HttpServletRequest request) {
		Mini_HomeVO detail = mini_DiaryService.minihomedetail(num);	
		//System.out.println(detail.getMini_home_content());
		//System.out.println(detail);
		model.addAttribute("detail", detail);		
		return "mini/mphotoList";
	}
	
	// 미니 다이어리 리스트
	@RequestMapping("/mdiaryList")
	public String mdiaryList(Model model, int num, HttpServletRequest request) {
		Mini_HomeVO detail = mini_DiaryService.minihomedetail(num);	
		//System.out.println(detail.getMini_home_content());
		//System.out.println(detail);
		model.addAttribute("detail", detail);		
		return "mini/mdiaryList";
	}
	
	// 미니 다이어리 폼 작성
	@PostMapping("/mdiaryInsert")
	public String mdiaryInsert(Mini_DiaryVO vo) {
		
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
			vo.setMini_dia_file(oriFn);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		mini_DiaryService.diaryadd(vo);
		return "redirect:mdiaryList";
	}
	
	@RequestMapping("/mcalList")
	public String mcalList(Model model, HttpSession session) {
	    Integer mini_cal_code = (Integer) session.getAttribute("sessionNum"); // Integer로 선언
	    if (mini_cal_code == null) {
	        // sessionNum이 null인 경우에 대한 예외 처리를 추가할 수 있습니다.
	        // 필요에 따라 로직을 추가하세요.
	        // 예를 들어, 오류 페이지로 리다이렉트하거나 다른 처리를 수행할 수 있습니다.
	        return "redirect:/errorPage"; // 예시로 리다이렉트하는 방법을 사용하였습니다.
	    }

	    List<Mini_CalVO> cal = mini_DiaryService.calendarList(mini_cal_code);
	    System.out.println(mini_cal_code);

	    System.out.println(cal);
	    model.addAttribute("cal", cal);
	    return "mini/mcalList";
	}


	
	@GetMapping(params = "/mcalResult")
	public String data(Model model) {
		return "mini/mcalResult";
	}
	
	@GetMapping("/mcalForm")
    public String mcalForm(Model model, HttpSession session, @ModelAttribute("mini_CalVO") Mini_CalVO vo) {
        // 세션에 저장된 사용자 번호를 가져옵니다.
        Integer sessionNum = (Integer) session.getAttribute("sessionNum");
        System.out.println(sessionNum);

        // Mini_CalVO 리스트를 가져옵니다.
        List<Mini_CalVO> voList = mini_DiaryService.calendarList(sessionNum);

        // 세션에 저장된 사용자 번호를 뷰에 전달합니다.
        model.addAttribute("sessionNum", sessionNum);

        // Mini_CalVO 리스트를 뷰에 전달합니다.
        model.addAttribute("voList", voList);

        // Trip_MemberVO를 세션에서 가져와서 mem_num을 Mini_CalVO에 설정합니다.
        Trip_MemberVO tvo = (Trip_MemberVO) session.getAttribute("sessionNum");
        if (tvo != null) {
            vo.setMini_cal_code(tvo.getMem_num());
        }

        return "mini/mcalform";
    }

    @PostMapping("/mcalInsert")
    public String mcalInsert(@ModelAttribute("mini_CalVO") Mini_CalVO cvo, HttpSession session) {
        // 세션에 저장된 사용자 번호를 vo에 설정
        Integer sessionNum = (Integer) session.getAttribute("sessionNum");
        System.out.println(sessionNum);

        // Trip_MemberVO를 세션에서 가져와서 mem_num을 Mini_CalVO에 설정합니다.
        Trip_MemberVO tvo = (Trip_MemberVO) session.getAttribute("sessionUserInfo");
        if (tvo != null) {
            cvo.setMini_cal_code(tvo.getMem_num());
        }

        // mini_CalVO를 사용하여 데이터베이스에 추가하는 서비스 메서드 호출
        mini_DiaryService.calendaradd(cvo);

        // 입력 후 mcalList로 리다이렉트
        return "redirect:/mini/mcalList";
    }




	
	
}
	
