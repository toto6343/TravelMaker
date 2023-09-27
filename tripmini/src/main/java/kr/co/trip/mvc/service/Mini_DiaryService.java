package kr.co.trip.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.trip.mvc.dao.Mini_DiaryDao;
import kr.co.trip.mvc.vo.Mini_CalVO;
import kr.co.trip.mvc.vo.Mini_DiaryVO;
import kr.co.trip.mvc.vo.Mini_HomeVO;

@Service
public class Mini_DiaryService {
	
	@Autowired
	private Mini_DiaryDao MiniDiaryDao;
	
	// 미니 홈피 추가
	public void minihompyadd(Mini_HomeVO vo) {
		MiniDiaryDao.minihompyadd(vo);
	}
	
	// 미니 다이어리 추가
	public void diaryadd(Mini_DiaryVO vo) {
		MiniDiaryDao.diaryadd(vo);
		}
	
	public List<Mini_HomeVO> minihomelist(String mini_home_host) {
		return MiniDiaryDao.minihomelist(mini_home_host);
	}
	
	// 미니홈피 디테일
	
	public Mini_HomeVO minihomedetail(int num) {
		return MiniDiaryDao.minihomedetail(num);
		
	}
	
	/*public Mini_HomeVO modhometitle(int mini_home_num) {
		return MiniDiaryDao.modhometitle(mini_home_num);
	};*/
	
	// 미니홈피 타이틀 수정 삭제
	public Mini_HomeVO modhometitle(Map<String, Object> map) {
		return MiniDiaryDao.modhometitle(map);
	};
	public void updatehometitle(Mini_HomeVO vo) {
		MiniDiaryDao.updatehometitle(vo);
	};
	public Mini_HomeVO selecttitle(int mini_home_num) {
		return MiniDiaryDao.selecttitle(mini_home_num);
	};
	
	// 미니홈피 인사말 수정 삭제
	
	public Mini_HomeVO modhomecontent(Map<String, Object> map) {
		return MiniDiaryDao.modhomecontent(map);
	};
	
	public void updatehomecontent(Mini_HomeVO vo) {
		MiniDiaryDao.updatehomecontent(vo);
	};
	
	public Mini_HomeVO seleectcontent(int mini_home_num) {
		return MiniDiaryDao.seleectcontent(mini_home_num);
	}
	
	// 미니홈피 프로필 사진 수정 삭제
	public Mini_HomeVO modhomethumbnail(Map<String, Object> map) {
		return MiniDiaryDao.modhomethumbnail(map);
	};
	
	public void updatehomethumbnail(Mini_HomeVO vo) {
		MiniDiaryDao.updatehomethumbnail(vo);
	};
	
	public Mini_HomeVO selecthomehumbnail(int mini_home_num) {
		return MiniDiaryDao.selecthomehumbnail(mini_home_num);
	}
	
	
	// 캘린더
	public List<Mini_CalVO> calendarList(int mini_cal_code) {
		return MiniDiaryDao.calendarList(mini_cal_code);
	}
	
	public void calendaradd(Mini_CalVO vo) {
		MiniDiaryDao.calendaradd(vo);
	}
	

	
	
}
