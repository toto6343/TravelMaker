package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("dcvo")
@Getter
@Setter
public class Dia_CommVO {
	/*
	 * dia_comm_num  number,
  dia_comm_code number NOT NULL,
  dia_comm_nick varchar2(30) NOT NULL,
  dia_comm_cont varchar2(500),
	 */
	
	private int dia_comm_num; // 다이어리 댓글 번호
	private int dia_comm_code; // 다이어리 번호와 fk
	private String dia_comm_nick; // 회원 닉네임과 fk
	private String dia_comm_cont; // 댓글 내용
	private String dia_comm_date; // 댓글 단 날짜
	private String dia_comm_update; // 댓글 수정 날짜
	private String dia_comm_ip; // 댓글 ip
	
}
