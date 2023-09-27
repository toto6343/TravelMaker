package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("psvo")
@Getter
@Setter
public class Plan_ScheduleVO {
	private int plan_sc_num; // 계획 번호
	private int plan_sc_code; // 다이어리 번호와 fk
	//private int plan_sc_nday; // 계획 일차
	private String plan_sc_loca; // 계획 일정
	private String plan_sc_time; // 계획 여행시간
	private String plan_sc_addr; // 계획 주소
	private String plan_sc_type; // 계획 경비종류
	private int plan_sc_cost; // 계획 경비
	private String plan_sc_memo; // 계획 메모
	
	
}
