package kr.co.trip.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.trip.mvc.dao.FreeBoardDao;
import kr.co.trip.mvc.vo.Mini_CalVO;
import kr.co.trip.mvc.vo.FreeBoardVO;
import kr.co.trip.mvc.vo.FreeBoard_CommVO;

@Service
public class FreeBoardService {
	@Autowired
	private FreeBoardDao freeBoardDao;

	public List<FreeBoardVO> flistback() {
		return freeBoardDao.flistback();
	}
	public FreeBoardVO finfo(int fb_num) {
		return freeBoardDao.finfo(fb_num);
	}
	public void hit(int fb_num) {
		freeBoardDao.hit(fb_num);
	}
	
	public void fwrite(FreeBoardVO vo) {
		freeBoardDao.fwrite(vo);
	}
	public void del(int fb_num) {
		freeBoardDao.del(fb_num);
	}
	public int chkpwd(FreeBoardVO vo) {
		return freeBoardDao.chkpwd(vo);
	}
	public void up(FreeBoardVO vo) {
		freeBoardDao.up(vo);
	}
	
	public FreeBoardVO flist(Map<String, String> map) {
		return freeBoardDao.flist(map);
	}
	
	public int totalCount(Map<String, String> map) {
		return freeBoardDao.totalCount(map);
	}
	public void addComm(FreeBoard_CommVO vo) {
		freeBoardDao.addComm(vo);
	}
	public List<FreeBoard_CommVO> commList(int fbc_ucode) {
		return freeBoardDao.commList(fbc_ucode);
	}
	public void delComm(int fbc_num) {
		freeBoardDao.delComm(fbc_num);
	}
	
	public void upComm(int fbc_num) {
		freeBoardDao.upComm(fbc_num);
	}
	
	public List<FreeBoardVO> getPagelist(Map<String, Integer> map) {
		return freeBoardDao.getPagelist(map);
	}
	
	public int getCount() {
		return freeBoardDao.getCount();
	}



}
