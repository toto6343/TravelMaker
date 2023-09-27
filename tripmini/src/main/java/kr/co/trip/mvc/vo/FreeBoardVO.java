package kr.co.trip.mvc.vo;

import org.springframework.web.multipart.MultipartFile;

import kr.co.trip.mvc.search.freeboard.SearchVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("fbvo")
@Getter
@Setter
public class FreeBoardVO extends SearchVO {

    private int fb_num; // 자유게시판 번호

    private String fb_subject; // 자유게시판 제목

    private String fb_writer; // 회원 닉네임과 fk

    private String fb_content; // 자유게시판 내용

    private int fb_hit; // 자유게시판 조회수

    private String fb_reip; // 자유게시판 아이피

    private String fb_date; // 자유게시판 작성일

    private String fb_update; // 자유게시판 수정일

    private String fb_file; // 자유게시판 파일

    private MultipartFile mfile; // 첨부파일
    
    private int pageNo;
	private int amount;

}


