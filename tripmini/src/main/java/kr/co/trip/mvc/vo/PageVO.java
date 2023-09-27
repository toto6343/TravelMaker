package kr.co.trip.mvc.vo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class PageVO {
	// 페이징 처리를 위한 속성
	private int nowPage;		// 현재 페이지 값 -> 마이페이지에 연동되는 변수
	private int nowBlock;		// 현재 블럭 -> [][][][][] -> 1block
	private int totalRecord;		// 총 게시물 수 .Dao로 부터 받음
	private int numPerPage;	// 한 페이지당 보여질 게시물 수
	private int pagePerBlock;	// 한 블럭당 보여질 페이지 수
	private int totalPage;			// 전체 페이지 수 -> totalRecord/numPerPage
	private int totalBlock;			// 전체 블록 수
	private int beginPerPage;		// 각 페이지별 시작 게시물의 index값
	private int endPerPage;			// 각 페이지별 마지막 게시물의 index값
	

}
