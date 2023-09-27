package kr.co.trip.mvc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.co.trip.mvc.vo.Plan_HashtagVO;
import kr.co.trip.mvc.vo.Plan_MaterialVO;
import kr.co.trip.mvc.vo.Plan_MateriallistVO;
import kr.co.trip.mvc.vo.Plan_PhotoVO;
import kr.co.trip.mvc.vo.Plan_ScheduleVO;
import kr.co.trip.mvc.vo.Trip_DiaryVO;

@Mapper
public interface TripPlanDiaryDao {
	// 계획에 들어가는 테이블 insert
	public void diaryadd(Trip_DiaryVO vo);
	public void planHashTagadd(List<Plan_HashtagVO> vo);// 해시태그
	public void planScadd(List<Plan_ScheduleVO> vo); // 스케줄
	public void planMtradd(List<Plan_MaterialVO> vo);// 준비물
	public void planMtrInfoadd(List<Plan_MateriallistVO> vo);// 준비물 세부
	public void planPhotoadd(List<Plan_PhotoVO> vo); // 사진
}