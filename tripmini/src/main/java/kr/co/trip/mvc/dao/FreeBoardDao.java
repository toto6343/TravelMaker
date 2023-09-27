package kr.co.trip.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trip.mvc.vo.Mini_CalVO;
import kr.co.trip.mvc.vo.FreeBoardVO;
import kr.co.trip.mvc.vo.FreeBoard_CommVO;

@Mapper
public interface FreeBoardDao {
	// public resultType selectid(parameterType)
	public List<FreeBoardVO> flistback();
	public FreeBoardVO finfo(int fb_num);
	public void hit(int fb_num);
	public void fwrite(FreeBoardVO vo);
	public void del(int fb_num);
	public int chkpwd(FreeBoardVO vo);
	public void up(FreeBoardVO vo);
	public FreeBoardVO flist(Map<String, String> map);
	public int totalCount(Map<String, String> map);
	public void addComm(FreeBoard_CommVO vo);
	public List<FreeBoard_CommVO> commList(int fbc_ucode);
	public void delComm(int fbc_num);
	public void upComm(int fbc_num);
	public List<FreeBoardVO> getPagelist(Map<String, Integer> map);
	public int getCount();
	
	

	
	
	
	
}
