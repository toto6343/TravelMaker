package kr.co.trip.mvc.controller.diary;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.trip.mvc.service.Trip_DiaryService;
import kr.co.trip.mvc.vo.PageVO;
import kr.co.trip.mvc.vo.Rec_HashTagVO;
import kr.co.trip.mvc.vo.Rec_NdayVO;
import kr.co.trip.mvc.vo.Rec_PhotoVO;
import kr.co.trip.mvc.vo.Rec_ScheduleVO;
import kr.co.trip.mvc.vo.Trip_DiaryVO;

@Controller
@RequestMapping("/diaryrec")
public class Trip_DiaryController {
	 
	@Autowired
	Trip_DiaryService diaryService;
	
	@Autowired(required=true)
	private PageVO pagevo;
	
	@Value("${spring.servlet.multipart.location}")
	private String filepath;
	
	@GetMapping("/recordeform")
	public String diaryform() {
		return "diary/recwrite";
	}
	
	// 다이어리 처리 완료하기
	@PostMapping("/addDiaryRec")
	public String addDiaryRec(Model m, Trip_DiaryVO vo, HttpServletRequest request, MultipartHttpServletRequest mhsr) {
		int i;
		
		System.out.println("nick"+vo.getDia_nick());
		System.out.println("nick"+vo.getDia_sub());
		System.out.println("nick"+vo.getDia_summ());
		
		//Nday
		String[] recnday = request.getParameterValues("rec_nday_nday");
		Map<String, String> ndayMap = new HashMap<String, String>();
		List<Rec_NdayVO> ndaylist = new ArrayList<Rec_NdayVO>();
		for(i=0; i<recnday.length; i++) {
			Rec_NdayVO ndayvo = new Rec_NdayVO();
			System.out.println("ndayvo : " + ndayvo);
			ndayMap.put("rec_nday_nday", String.valueOf(recnday[i]));
			ndayvo.setRec_nday_nday(Integer.parseInt(ndayMap.get("rec_nday_nday")));
			ndaylist.add(ndayvo);
		}
				
		//rec
		String[] rscnday = request.getParameterValues("rec_sc_nday");
		String[] rscloca = request.getParameterValues("rec_sc_loca");
		String[] rsctime = request.getParameterValues("rec_sc_time");
		String[] rscaddr = request.getParameterValues("rec_sc_addr");
		String[] rsctype = request.getParameterValues("rec_sc_type");
		String[] rsccost = request.getParameterValues("rec_sc_cost");
		String[] rscmemo = request.getParameterValues("rec_sc_memo");
		Map<String, String> scMap = new HashMap<String, String>();
		List<Rec_ScheduleVO> recSclist = new ArrayList<>();
		for (i=0; i<rscnday.length; i++) {
			Rec_ScheduleVO rscvo = new Rec_ScheduleVO();
			System.out.println("drnday:" +String.valueOf(rscnday[i]));
			scMap.put("rec_sc_nday", String.valueOf(rscnday[i]));
			scMap.put("rec_sc_loca", String.valueOf(rscloca[i]));
			scMap.put("rec_sc_time", String.valueOf(rsctime[i]));
			scMap.put("rec_sc_addr", String.valueOf(rscaddr[i]));
			scMap.put("rec_sc_type", String.valueOf(rsctype[i]));
			scMap.put("rec_sc_cost", String.valueOf(rsccost[i]));
			scMap.put("rec_sc_memo", String.valueOf(rscmemo[i]));
			rscvo.setRec_sc_nday(Integer.parseInt((scMap.get("rec_sc_nday"))));
			rscvo.setRec_sc_loca(scMap.get("rec_sc_loca"));
			rscvo.setRec_sc_time(scMap.get("rec_sc_time"));
			rscvo.setRec_sc_addr(scMap.get("rec_sc_addr"));
			rscvo.setRec_sc_type(scMap.get("rec_sc_type"));
			rscvo.setRec_sc_cost(Integer.parseInt(scMap.get("rec_sc_cost")));
			rscvo.setRec_sc_memo(scMap.get("rec_sc_memo"));
			recSclist.add(rscvo);
		}
				
		// rec_photo
		String[] rscphotonday = request.getParameterValues("rec_photo_nday");
		
		// 경로테스트 : 이미지가 저장할 경로
		List<Rec_PhotoVO> recphoto = new ArrayList<>();
		Map<String, String> recPnMap = new HashMap<String, String>();
		
		// Multipart 객체에서 이미지 이름 확인
		List<MultipartFile> pvmfs = mhsr.getFiles("recimgfile");
		if(pvmfs.size()>0) {
			for(i=0; i<pvmfs.size(); i++) {
				MultipartFile pvmf = pvmfs.get(i);
				String pvOriFn = pvmf.getOriginalFilename();
				System.out.println("pvOriFn : " + pvOriFn);
				// 이미지 사이즈 및 contentType확인
				//long pvsize = pv.getRecimgfile().getSize();
				long pvsize = pvmf.getSize();
				String pvContentType = pvmf.getContentType();
				
				System.out.println("파일 크기 : " + pvsize);
				System.out.println("파일의 type : " + pvContentType);
				
				StringBuffer pvpath = new StringBuffer(); // [서버경로]
				pvpath.append(filepath).append("\\");
				pvpath.append(pvOriFn);
				System.out.println("FullPath : " + pvpath);
				// 추상경로(이미지를 저장할 경로) File 객체로 생성
				File pvf = new File(pvpath.toString());
				// 임시 메모리에 담긴 즉 업로드한 파일의 값 -> File 클래스의 경로로 복사
				try {
					Rec_PhotoVO pv = new Rec_PhotoVO();
					pvmf.transferTo(pvf);
					// 이미지 이름을 DB에 저장하기 위해서 vo값에 재설정
					//pv.setRec_photo_title(pvOriFn); // [이 부분 미설정 시 이미지가 DB에 들어가지 않으니 반드시 작업해줘야됨!!]
					recPnMap.put("pvOriFn", pvOriFn);
					pv.setRec_photo_title(recPnMap.get("pvOriFn"));
					pv.setRec_photo_nday(Integer.parseInt(rscphotonday[i]));
					recphoto.add(pv);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				// 이미지 업로드 확인
				System.out.println("업로드 파일 목록 =======================");
				StringBuffer pvpath2 = new StringBuffer(); // [서버경로]
				pvpath2.append(filepath).append("\\");
				File pvf2 = new File(pvpath2.toString());
				File[] flist2 = pvf2.listFiles();
				for (File e : flist2) {
					System.out.println(e.getName());
				}
				System.out.println("업로드 파일 목록 끝 =======================");
				}
		}
		// rec_photo 끝
		
		
		// 썸네일 이미지 업로드
		// Multipart 객체에서 이미지 이름 확인
		vo.setMfile(mhsr.getFile("mfile"));
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("oriFn : "+oriFn);
		
		// 이미지 사이즈 및 contentType확인
		long size = vo.getMfile().getSize();
		String contentType = mf.getContentType();
		System.out.println("파일 크기 : "+size);
		System.out.println("파일의 type : "+contentType);
		
		// 메모리상(임시저장장소)에 파일을 우리가 설정한 경로에 복사하겠다.
		// 이미지가 저장될 경로 만들기
		StringBuffer path = new StringBuffer(); // [서버경로]
		path.append(filepath).append("\\");
		path.append(oriFn);
		System.out.println("FullPath : "+path);
		
		// 추상경로(이미지를 저장할 경로) File 객체로 생성
		File f = new File(path.toString());
		try {
			mf.transferTo(f);
			// 이미지 이름을 DB에 저장하기 위해서 vo값에 재설정
			vo.setDia_thumbnail(oriFn); 
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		// 이미지 업로드 확인
		System.out.println("업로드 파일 목록 =======================");
		StringBuffer path2= new StringBuffer(); // [서버경로]
		path2.append(filepath).append("\\");
		// 이미지 업로드 끝
		
		

		//rec_hashtag
		String[] hashtag = request.getParameterValues("rec_hash_tag");
		
		Map<String, String> hashMap = new HashMap<String, String>();
		List<Rec_HashTagVO> rechtag = new ArrayList<>();
		
		for (i=0; i<hashtag.length; i++) {
			Rec_HashTagVO htagvo = new Rec_HashTagVO();
			System.out.println("rec_hash_tag:" +String.valueOf(hashtag[i]));
			hashMap.put("rec_hash_tag", String.valueOf(hashtag[i]));
			htagvo.setRec_hash_tag(hashMap.get("rec_hash_tag"));
			rechtag.add(htagvo);
		}
		//rec_hashtag 끝
		
		
		// dia_start, dia_end(여행날짜)
		String startDateString = request.getParameter("startDate");
		String endDateString = request.getParameter("endDate");
		// VO 객체 생성 및 값 설정
		String dia_start = startDateString;
		String dia_end = endDateString;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
		    Date startDateObj = sdf.parse(startDateString);
		    Date endDateObj = sdf.parse(endDateString);
		    dia_start = sdf.format(startDateObj);
		    dia_end = sdf.format(endDateObj);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		vo.setDia_start(dia_start);
		vo.setDia_end(dia_end);
		System.out.println("시작 일정 : " + dia_start);
		System.out.println("마지막 일정 : " + dia_end);

		
		diaryService.addRecDiary(vo, recSclist, recphoto,rechtag, ndaylist);
		
		
		return "redirect:recdiaryList";
	}
	
	@RequestMapping("/recdiaryList")
	public String diaryList(Model model, @RequestParam Map<String, String> param, HttpSession session) {
		
		String stype = param.get("searchType");
		String sname = param.get("searchValue");
		String cPage = param.get("cPage");
		System.out.println("파라미터 출력 : "+ cPage);
		System.out.println("검색 시, 링크 시 넘어오는 파라미터 출력 : "+stype);
		System.out.println("검색 시, 링크 시 넘어오는 파라미터 출력 : "+sname);
		System.out.println("****************************************");
		pagevo.setTotalRecord(diaryService.getTotal(param));
		int totalRecord = pagevo.getTotalRecord();
		System.out.println("1. TotalRecord :" + totalRecord);
		
		System.out.println("2. totalPage : "+pagevo.getTotalPage());
		pagevo.setTotalPage((int)Math.ceil(totalRecord/(double)pagevo.getNumPerPage()));
		
		pagevo.setTotalBlock((int) Math.ceil((double) pagevo.getTotalPage() / pagevo.getPagePerBlock()));
		
		if (cPage != null) {
			pagevo.setNowPage(Integer.parseInt(cPage));
		}else {
			pagevo.setNowPage(1);
		}
		System.out.println("4. nowPage : "+pagevo.getNowPage());
		
		pagevo.setBeginPerPage((pagevo.getNowPage()-1) * pagevo.getNumPerPage()+1);
		pagevo.setEndPerPage((pagevo.getBeginPerPage()-1) + pagevo.getNumPerPage());
		System.out.println("5. beginPerPage : "+pagevo.getBeginPerPage());
		System.out.println("5. endPerPage : "+pagevo.getEndPerPage());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pagevo.getBeginPerPage()));
		map.put("end", String.valueOf(pagevo.getEndPerPage()));
		map.put("dia_nick", (String) session.getAttribute("sessionNick"));
		
		map.putAll(param);
		
		System.out.println("========Map All=========");
		for(Map.Entry<String, String> e : map.entrySet()) {
			System.out.println(e.getKey()+","+e.getValue());
		}

		List<Trip_DiaryVO> list = diaryService.diaryRecList(map);
		
		//List<Rec_HashTagVO> taglist = trip_DiaryDaoInter.hashtagList(list.get());
		
		int startPage = (int)((pagevo.getNowPage() - 1)/pagevo.getPagePerBlock()) * pagevo.getPagePerBlock()+1;
		int endPage = startPage + pagevo.getPagePerBlock()-1;
		if(endPage > pagevo.getTotalPage()) {
			endPage = pagevo.getTotalPage(); }
		System.out.println("6. startPage : "+startPage);
		System.out.println("6. endPage : "+endPage);
		
		// 검색값을 view로 전달
		model.addAttribute("searchType", map.get("searchType"));
		model.addAttribute("searchValue", map.get("searchValue"));
		
		model.addAttribute("startPage", startPage);	// 블록의 시작페이지값
		model.addAttribute("endPage", endPage);		// 블록의 끝 값
		model.addAttribute("page", pagevo);	// nowPage, pagePerBlock, totalPage
		model.addAttribute("diaRecList", list);
		
		List<Rec_HashTagVO> taglist = diaryService.hashtagRecList(map);
		model.addAttribute("taglist", taglist);
		
		return "diary/myPageDiaLi";
	}
	
	@GetMapping("/detailDiary")
	public String recodeInfo(Model m, @RequestParam(required = true)int num) throws ParseException {
		
		List<Rec_PhotoVO> ptvo = diaryService.photoList(num);
		m.addAttribute("photolist", ptvo);
		
		List<Rec_NdayVO> ndayvo = diaryService.ndayList(num);
		m.addAttribute("ndayvo", ndayvo);
		
		Trip_DiaryVO tdvo = diaryService.recDetail(num);
		m.addAttribute("tdvo", tdvo);
		
		// 시작 날짜
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // SimpleDateFormat 객체 생성
	    
	    String tdvoDiaStartStr = tdvo.getDia_start(); // ${tdvo.dia_start} 값을 String 변수에 할당
	    Date tdvoDiaStartDate = sdf.parse(tdvoDiaStartStr); // String을 Date로 변환

		
		// 마지막 날짜
	    String tdvoDiaEndStr = tdvo.getDia_end(); // ${tdvo.dia_end} 값을 String 변수에 할당
	    Date tdvoDiaEndDate = sdf.parse(tdvoDiaEndStr); // String을 Date로 변환
	    System.out.println(tdvoDiaEndDate);
	    
	    // 초 차이
	    long diffSec = (tdvoDiaEndDate.getTime()- tdvoDiaStartDate.getTime())/1000;
	    
	    // 일수차이
	    long diffDays = diffSec/(24*60*60);
	    System.out.println(diffDays+2);
	    
	    List<String> dayList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tdvoDiaStartDate); // 변환한 Date 객체(tdvoDiaStartDate)를 Calendar 객체에 설정하여 해당 날짜 정보를 Calendar에 반영
        for (int i=1; i<diffDays+2; i++) {
			tdvoDiaStartDate = calendar.getTime();
			calendar.add(Calendar.DATE, 1); // 변경된 Calendar 객체의 날짜 정보를 다시 Date 객체(tdvoDiaStartDate)에 반영
			System.out.println("날짜 : "+i+tdvoDiaStartDate);
			String strDate = sdf.format(tdvoDiaStartDate); // Date를 yyyy-MM-dd 형식의 String으로 변환
			dayList.add(strDate);
		}
	    
        m.addAttribute("strDate", dayList);
		
		return "diary/recDetail";
	}
	@RequestMapping("/maindiarylist")
	public String maindiarylist( ) {
		
		return "diary/maindiarylist";
	}
	
}
