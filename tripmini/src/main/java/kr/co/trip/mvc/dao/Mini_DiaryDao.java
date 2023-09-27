package kr.co.trip.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trip.mvc.vo.Mini_CalVO;
import kr.co.trip.mvc.vo.Mini_CommentVO;
import kr.co.trip.mvc.vo.Mini_DiaryVO;
import kr.co.trip.mvc.vo.Mini_HomeVO;
import kr.co.trip.mvc.vo.Mini_PhotoVO;

@Mapper
public interface Mini_DiaryDao {
	// public resultType selectid(parameterType)
	
	// 미니 다이어리 추가
	public void diaryadd(Mini_DiaryVO vo); 
	public List<Mini_DiaryVO> minidiarylist();
	
	//  캘린더
	public List<Mini_CalVO> calenList();
	
	// 미니 사진첩 추가
	public void miniphoto(Mini_PhotoVO vo); 
	public List<Mini_PhotoVO> photolist();
		
	
	// 미니 홈피 인사말 추가
	public void hompycomadd(Mini_CommentVO vo);
	public Mini_CommentVO hompycomlist();
	

	// 미니홈피 추가 및 미니홈피 리스트
	public void minihompyadd(Mini_HomeVO vo);
	public List<Mini_HomeVO> minihomelist(String mini_home_host);
	
	
	// 미니홈피 디테일
	public Mini_HomeVO minihomedetail(int num);
	
	// 미니홈피 업데이트
	//public Mini_HomeVO modhometitleBack(int mini_home_num);
	public Mini_HomeVO modhometitle(Map<String, Object> map);
	public void updatehometitle(Mini_HomeVO minihvo);
	public Mini_HomeVO selecttitle(int mini_home_num);
	
	public Mini_HomeVO modhomecontent(Map<String, Object> map);
	public void updatehomecontent(Mini_HomeVO minihvo);
	public Mini_HomeVO seleectcontent(int mini_home_num);
	
	public Mini_HomeVO modhomethumbnail(Map<String, Object> map);
	public void updatehomethumbnail(Mini_HomeVO minihvo);
	public Mini_HomeVO selecthomehumbnail(int mini_home_num);
	
	public List<Mini_CalVO> calendarList(int mini_cal_code);
	public void calendaradd(Mini_CalVO vo);
	
	
	
	
	
}
