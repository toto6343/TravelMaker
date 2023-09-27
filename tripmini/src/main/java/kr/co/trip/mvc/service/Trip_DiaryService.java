package kr.co.trip.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.trip.mvc.dao.TripPlanDiaryDao;
import kr.co.trip.mvc.dao.Trip_DiaryDao;
import kr.co.trip.mvc.vo.Dia_CommVO;
import kr.co.trip.mvc.vo.Plan_HashtagVO;
import kr.co.trip.mvc.vo.Plan_MaterialVO;
import kr.co.trip.mvc.vo.Plan_MateriallistVO;
import kr.co.trip.mvc.vo.Plan_PhotoVO;
import kr.co.trip.mvc.vo.Plan_ScheduleVO;
import kr.co.trip.mvc.vo.Rec_HashTagVO;
import kr.co.trip.mvc.vo.Rec_NdayVO;
import kr.co.trip.mvc.vo.Rec_PhotoVO;
import kr.co.trip.mvc.vo.Rec_ScheduleVO;
import kr.co.trip.mvc.vo.Trip_DiaryVO;

@Service
public class Trip_DiaryService {

	@Autowired
	private Trip_DiaryDao tripDiaryDao;
	
	@Autowired
	private TripPlanDiaryDao tripPlanDiaryDao;
	// 다이어리 
	@Transactional
	public void addRecDiary(Trip_DiaryVO vo, List<Rec_ScheduleVO> recsc
			,List<Rec_PhotoVO> recphoto,List<Rec_HashTagVO> htag, List<Rec_NdayVO> ndaylist) {
		System.out.println("서비스 확인");
		System.out.println(vo.getDia_sub());
		
		tripDiaryDao.addTripDiary(vo);
		tripDiaryDao.addRecNday(ndaylist);
		tripDiaryDao.addRecSc(recsc);
		tripDiaryDao.addRecPhoto(recphoto);
		tripDiaryDao.addRecHash(htag);
	};
	
	public void updateHitTripDiary(int num) {
		tripDiaryDao.updateHitTripDiary(num);
	}; 
	
	//--기록-------------------------------------------------------------------------------
	// 기록에 들어가는 테이블 insert
	public void addRecPhoto(List<Rec_PhotoVO> v) {
		tripDiaryDao.addRecPhoto(v);
	}; // 사진
	public void addRecSc(List<Rec_ScheduleVO> v) {
		tripDiaryDao.addRecSc(v);
	}; // 스케줄
	public void addRecHash(List<Rec_HashTagVO> v) {
		tripDiaryDao.addRecHash(v);
	};// 해시태그
	public void addRecNday(List<Rec_NdayVO> v) {
		tripDiaryDao.addRecNday(v);
	};// Nday
	
	// 다이어리 기록 List 
	public List<Trip_DiaryVO> diaryRecList(Map<String, String> map) {
		return tripDiaryDao.diaryRecList(map);
	};
	public List<Rec_HashTagVO> hashtagRecList(Map<String, String> map){
		return tripDiaryDao.hashtagRecList(map);
	};
	public int getTotal(Map<String, String> map) {
		return tripDiaryDao.getTotal(map);
	};// 다이어리 기록 리스트
	
	// 기록 디테일, 삭제, 수정
	public Trip_DiaryVO recDetail(int num) {
		return tripDiaryDao.recDetail(num);
	};
	public List<Rec_PhotoVO> photoList(int num) {
		return tripDiaryDao.photoList(num);
	}; // 디테일 사진
	public List<Rec_NdayVO> ndayList(int num) {
		return tripDiaryDao.ndayList(num);
	}; // 일차 리스트
	public void deleteRec(int num) {
		tripDiaryDao.deleteRec(num);
	}; 		// 삭제	
	public void updateRec(Rec_ScheduleVO vo) {
		tripDiaryDao.updateRec(vo);
	}; 	//수정
	
	//--계획-------------------------------------------------------------------------------
	// 다이어리 계획 List 	[추후 계획 해시태그 테이블 및 vo 등 제작]
	public List<Trip_DiaryVO> diaryPlanList(Map<String, String> map) {
		return tripDiaryDao.diaryPlanList(map);
	};
	
	// 다이어리 댓글 insert
	public void adddiaComm(Dia_CommVO v) {
		tripDiaryDao.adddiaComm(v);
	}; //댓글 입력
	public List<Dia_CommVO> diaCommList(Map<String, String> map) {
		return tripDiaryDao.diaCommList(map);
	}; //댓글 리스트
	public int diaCommTotal(int num) {
		return tripDiaryDao.diaCommTotal(num);
	}; //댓글 갯수
	public void delDiaComm(int num) {
		tripDiaryDao.delDiaComm(num);
	}; //댓글 삭제
	public Dia_CommVO modDiaComm(Map<String, Object> map) {
		return tripDiaryDao.modDiaComm(map);
	};
	public void upDiaComm(Dia_CommVO vo) {
		tripDiaryDao.upDiaComm(vo);
	}; //댓글 업데이트

	
	//여행계획
	public void addPlanDiary(Trip_DiaryVO vo, List<Plan_ScheduleVO> plansc, List<Plan_HashtagVO> planhtag
			, List<Plan_MaterialVO> planmtr, List<Plan_MateriallistVO> planmtrinfo, List<Plan_PhotoVO> planphoto) {
		tripPlanDiaryDao.diaryadd(vo);
		tripPlanDiaryDao.planScadd(plansc);
		tripPlanDiaryDao.planHashTagadd(planhtag);
		tripPlanDiaryDao.planMtradd(planmtr);
		tripPlanDiaryDao.planMtrInfoadd(planmtrinfo);
		tripPlanDiaryDao.planPhotoadd(planphoto);
	}
}
