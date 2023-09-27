package kr.co.trip.mvc.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.trip.mvc.dao.TripMemberDao;
import kr.co.trip.mvc.vo.Trip_MemberVO;

@Service
public class TripMemberService{
	@Autowired
	private TripMemberDao tripMemberDao;
	
	public void add(Trip_MemberVO vo) {
		tripMemberDao.add(vo);
	};
	public int idCheck(String mem_id) {
		return tripMemberDao.idCheck(mem_id);
	};
	public Trip_MemberVO mypage(String mem_id) {
		return tripMemberDao.mypage(mem_id);
	};
	public int emailCheck(String mem_email) {
		return tripMemberDao.emailCheck(mem_email);
	};
	public int nickCheck(String mem_nick) {
		return tripMemberDao.nickCheck(mem_nick);
	};
	public Trip_MemberVO loginchk(Trip_MemberVO vo) {
		return tripMemberDao.loginchk(vo);
	};
	public List<Trip_MemberVO> list(Map<String, String> map){
		return tripMemberDao.list(map);
	};
	public int totalCount(Map<String, String> map) {
		return tripMemberDao.totalCount(map);
	};
}



