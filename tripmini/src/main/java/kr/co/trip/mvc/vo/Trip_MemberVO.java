package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;
import lombok.Getter;
import lombok.Setter;

@Alias("tmvo")
@Getter
@Setter
public class Trip_MemberVO {
	private int mem_num; // 회원 번호
	private String mem_id; // 회원 아이디
	private String mem_nick; // 회원 닉네임
	private String mem_name; // 회원 이름
	private String mem_email; // 회원 이메일
	private String mem_pwd; // 회원 비밀번호
	private String mem_ip; // 회원 아이피
	private String mem_date; // 회원 가입일
	private String mem_grade; // 회원 등급
	private String mem_mypic;//
}



