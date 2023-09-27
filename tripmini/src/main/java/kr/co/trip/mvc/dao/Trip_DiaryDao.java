package kr.co.trip.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trip.mvc.vo.Dia_CommVO;
import kr.co.trip.mvc.vo.Rec_HashTagVO;
import kr.co.trip.mvc.vo.Rec_NdayVO;
import kr.co.trip.mvc.vo.Rec_PhotoVO;
import kr.co.trip.mvc.vo.Rec_ScheduleVO;
import kr.co.trip.mvc.vo.Trip_DiaryVO;

@Mapper
public interface Trip_DiaryDao {
	
	/*
	public void addFBoard(FBoardVO vo);		// 입력
	public List<FBoardVO> listFBoard(); 	// 리스트
	public void updateHit(int num); 		// hit
	public FBoardVO datailFBoard(int num);  // 상세보기 및 수정	
	public boolean checkPwd(FBoardVO vo); 	// 비밀번호 조회	
	public void deleteFBoard(int num); 		// 삭제	
	public void updateFBoard(FBoardVO vo); 	//수정
	*/
	
	// 다이어리 
	public void addTripDiary(Trip_DiaryVO v);
	public void updateHitTripDiary(int num); 
	
	//--기록-------------------------------------------------------------------------------
	// 기록에 들어가는 테이블 insert
	public void addRecPhoto(List<Rec_PhotoVO> v); // 사진
	public void addRecSc(List<Rec_ScheduleVO> v); // 스케줄
	public void addRecHash(List<Rec_HashTagVO> v);// 해시태그
	public void addRecNday(List<Rec_NdayVO> v);// Nday
	
	// 다이어리 기록 List 
	public List<Trip_DiaryVO> diaryRecList(Map<String, String> map);
	public List<Rec_HashTagVO> hashtagRecList(Map<String, String> map);
	public int getTotal(Map<String, String> map);// 다이어리 기록 리스트
	
	// 기록 디테일, 삭제, 수정
	public Trip_DiaryVO recDetail(int num);
	public List<Rec_PhotoVO> photoList(int num); // 디테일 사진
	public List<Rec_NdayVO> ndayList(int num); // 일차 리스트
	public void deleteRec(int num); 		// 삭제	
	public void updateRec(Rec_ScheduleVO vo); 	//수정
	
	//--계획-------------------------------------------------------------------------------
	// 다이어리 계획 List 	[추후 계획 해시태그 테이블 및 vo 등 제작]
	public List<Trip_DiaryVO> diaryPlanList(Map<String, String> map);
	
	// 다이어리 댓글 insert
	public void adddiaComm(Dia_CommVO v); //댓글 입력
	public List<Dia_CommVO> diaCommList(Map<String, String> map); //댓글 리스트
	public int diaCommTotal(int num); //댓글 갯수
	public void delDiaComm(int num); //댓글 삭제
	public Dia_CommVO modDiaComm(Map<String, Object> map);
	public void upDiaComm(Dia_CommVO vo); //댓글 업데이트
	
	

}
