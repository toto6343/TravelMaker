package kr.co.trip.mvc.controller.diary;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.trip.mvc.service.Trip_DiaryService;
import kr.co.trip.mvc.vo.Plan_HashtagVO;
import kr.co.trip.mvc.vo.Plan_MaterialVO;
import kr.co.trip.mvc.vo.Plan_MateriallistVO;
import kr.co.trip.mvc.vo.Plan_PhotoVO;
import kr.co.trip.mvc.vo.Plan_ScheduleVO;
import kr.co.trip.mvc.vo.Trip_DiaryVO;


@Controller
@RequestMapping("/diaryplan")
public class Trip_PlanDiaryController {
	 
	@Value("${spring.servlet.multipart.location}")
	private String filePath;
	
	@Autowired
	private Trip_DiaryService tripDiaryService;
	

	@GetMapping("/planform")
	public String planForm() {
		return "diary/planwrite";
	}
	
	
	// 다이어리 처리 완료하기
	@PostMapping("/addPlanDia")
	public String addPlanDiary(Model m, Trip_DiaryVO vo, HttpServletRequest request, MultipartHttpServletRequest mhsr) {
		int i;
		System.out.println("시작");
		// 계획 스케줄
		String[] planscloca = request.getParameterValues("plan_sc_loca");
		String[] plansctime = request.getParameterValues("plan_sc_time");
		String[] planscaddr = request.getParameterValues("plan_sc_addr");
		String[] plansctype = request.getParameterValues("plan_sc_type");
		String[] plansccost = request.getParameterValues("plan_sc_cost");
		String[] rsccostmemo = request.getParameterValues("plan_sc_memo");
		Map<String, String> scMap = new HashMap<String, String>();
		List<Plan_ScheduleVO> planSclist = new ArrayList<>();
		for (i=0; i<planscloca.length; i++) {
			Plan_ScheduleVO planscvo = new Plan_ScheduleVO();
			scMap.put("plan_sc_loca", String.valueOf(planscloca[i]));
			scMap.put("plan_sc_time", String.valueOf(plansctime[i]));
			scMap.put("plan_sc_addr", String.valueOf(planscaddr[i]));
			scMap.put("plan_sc_type", String.valueOf(plansctype[i]));
			scMap.put("plan_sc_cost", String.valueOf(plansccost[i]));
			scMap.put("plan_sc_memo", String.valueOf(rsccostmemo[i]));
			
			planscvo.setPlan_sc_loca(scMap.get("plan_sc_loca"));
			planscvo.setPlan_sc_time(scMap.get("plan_sc_time"));
			planscvo.setPlan_sc_addr(scMap.get("plan_sc_addr"));
			planscvo.setPlan_sc_type(scMap.get("plan_sc_type"));
			planscvo.setPlan_sc_cost(Integer.parseInt(scMap.get("plan_sc_cost")));
			planscvo.setPlan_sc_memo(scMap.get("plan_sc_memo"));
			planSclist.add(planscvo);
		}
		
		// 계획 준비물
		String[] planmtclass = request.getParameterValues("plan_mtr_class");
		Map<String, String> mtrMap = new HashMap<String, String>();
		List<Plan_MaterialVO> planMtrlist = new ArrayList<>();
		System.out.println(planmtclass.length);
		for (i=0; i<planmtclass.length; i++) {
			Plan_MaterialVO planmtrvo = new Plan_MaterialVO();
			mtrMap.put("plan_mtr_class", String.valueOf(planmtclass[i]));
			planmtrvo.setPlan_mtr_class(mtrMap.get("plan_mtr_class"));
			planMtrlist.add(planmtrvo);
		}
		// 계획 준비물
		String[] planmtmtr = request.getParameterValues("plan_mtr_material");
		String[] planmtcheck = request.getParameterValues("plan_mtr_check");
		System.out.println(planmtcheck);
		Map<String, String> mtrInfoMap = new HashMap<String, String>();
		List<Plan_MateriallistVO> planMtrInfolist = new ArrayList<>();
		for (i=0; i<planmtmtr.length; i++) {
			Plan_MateriallistVO planmtrinfovo = new Plan_MateriallistVO();
			mtrInfoMap.put("plan_mtr_material", String.valueOf(planmtmtr[i]));
			//mtrInfoMap.put("plan_mtr_check", String.valueOf(planmtcheck[i]));
			
			planmtrinfovo.setPlan_mtr_material(mtrInfoMap.get("plan_mtr_material"));
			//planmtrinfovo.setPlan_mtr_check(Integer.parseInt(mtrInfoMap.get("plan_mtr_check")));
			planMtrInfolist.add(planmtrinfovo);
		}
		
		
		// 계획 사진
		//String[] rscphotonday = request.getParameterValues("rec_photo_nday");
		
		// 경로테스트 : 이미지가 저장할 경로
		List<Plan_PhotoVO> planphotolist = new ArrayList<>();
		Map<String, String> planPnMap = new HashMap<String, String>();
		
		// Multipart 객체에서 이미지 이름 확인
		List<MultipartFile> pvmfs = mhsr.getFiles("recimgfile");
		if(pvmfs.size()>0) {
			for(i=0; i<pvmfs.size(); i++) {
				MultipartFile pvmf = pvmfs.get(i);
				String pvOriFn = pvmf.getOriginalFilename();
				System.out.println("pvOriFn1 : " + pvOriFn);
				// 이미지 사이즈 및 contentType확인
				//long pvsize = pv.getRecimgfile().getSize();
				long pvsize = pvmf.getSize();
				String pvContentType = pvmf.getContentType();
				
				System.out.println("파일 크기 : " + pvsize);
				System.out.println("파일의 type : " + pvContentType);
				
				StringBuffer pvpath = new StringBuffer(); // [서버경로]
				pvpath.append(filePath).append("\\");
				pvpath.append(pvOriFn);
				System.out.println("FullPath : " + pvpath);
				// 추상경로(이미지를 저장할 경로) File 객체로 생성
				File pvf = new File(pvpath.toString());
				// 임시 메모리에 담긴 즉 업로드한 파일의 값 -> File 클래스의 경로로 복사
				try {
					Plan_PhotoVO pv = new Plan_PhotoVO();
					pvmf.transferTo(pvf);
					// 이미지 이름을 DB에 저장하기 위해서 vo값에 재설정
					//pv.setRec_photo_title(pvOriFn); // [이 부분 미설정 시 이미지가 DB에 들어가지 않으니 반드시 작업해줘야됨!!]
					planPnMap.put("pvOriFn", pvOriFn);
					pv.setPlan_photo_title(planPnMap.get("pvOriFn"));
					planphotolist.add(pv);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				// 이미지 업로드 확인
				System.out.println("업로드 파일 목록1 =======================");
				StringBuffer pvpath2 = new StringBuffer(); // [서버경로]
				pvpath2.append(filePath).append("\\");
				File pvf2 = new File(pvpath2.toString());
				File[] flist2 = pvf2.listFiles();
				for (File e : flist2) {
					System.out.println(e.getName());
				}
				System.out.println("업로드 파일 목록1 끝 =======================");
				}
		}	// 계획 사진 끝
		
		// 이미지 업로드
		
		// Multipart 객체에서 이미지 이름 확인
		vo.setMfile(mhsr.getFile("mfile"));
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("썸네일oriFn : "+oriFn);
		
		// 이미지 사이즈 및 contentType확인
		long size = vo.getMfile().getSize();
		String contentType = mf.getContentType();
		System.out.println("파일 크기 : "+size);
		System.out.println("파일의 type : "+contentType);
		
		// 메모리상(임시저장장소)에 파일을 우리가 설정한 경로에 복사하겠다.
		// 이미지가 저장될 경로 만들기
		StringBuffer path = new StringBuffer(); // [서버경로]
		path.append(filePath).append("\\");
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
		System.out.println("업로드 파일 목록2 =======================");
		StringBuffer path2= new StringBuffer(); // [서버경로]
		path2.append(filePath).append("\\");
		File f2 = new File(path2.toString());
		File[] flist = f2.listFiles();
		for(File e : flist) {
			System.out.println(e.getName());
		}
		System.out.println("업로드 파일 목록 끝2 =======================");
				
		// 이미지 업로드 끝
		
		

		// 계획 해시태그
		String[] planhashtag = request.getParameterValues("plan_hash_tag");
		
		Map<String, String> hashMap = new HashMap<String, String>();
		List<Plan_HashtagVO> planhataglist = new ArrayList<>();
		
		for (i=0; i<planhashtag.length; i++) {
			Plan_HashtagVO htagvo = new Plan_HashtagVO();
			hashMap.put("plan_hash_tag", String.valueOf(planhashtag[i]));
			htagvo.setPlan_hash_tag(hashMap.get("plan_hash_tag"));
			planhataglist.add(htagvo);
		}
			
		
		String startDateString = request.getParameter("startDate");
		String endDateString = request.getParameter("endDate");
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

		tripDiaryService.addPlanDiary(vo, planSclist, planhataglist,planMtrlist, planMtrInfolist, planphotolist);
				
		return "redirect:plandiaryList";
	}
}