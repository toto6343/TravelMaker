package kr.co.trip.mvc.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import kr.co.trip.mvc.vo.Trip_MemberVO;

@Mapper
public interface TripMemberDao {
	public void add(Trip_MemberVO vo);
	public int idCheck(String mem_id);
	public Trip_MemberVO mypage(String mem_id);
	public int emailCheck(String mem_email);
	public int nickCheck(String mem_nick);
	public Trip_MemberVO loginchk(Trip_MemberVO vo);
	public List<Trip_MemberVO> list(Map<String, String> map);
	public int totalCount(Map<String, String> map);
}


