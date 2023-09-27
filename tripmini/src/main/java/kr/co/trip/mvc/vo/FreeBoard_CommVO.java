package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;
import lombok.Getter;
import lombok.Setter;
@Alias("fbcommvo")
@Getter
@Setter
public class FreeBoard_CommVO {
	private int fbc_num; // 자유게시판 댓글 번호
	private int fbc_ucode; // 자유게시판 번호와 fk
	private String fbc_uwriter; // 회원 닉네임과 fk 
	private String fbc_ucontent; // 댓글 내용
	private String  fbc_reip; // 댓글 아이피
	private String fbc_uregdate; // 댓글 날짜
}
