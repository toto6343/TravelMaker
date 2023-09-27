package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("rsvo")
@Getter
@Setter
public class Rec_ScheduleVO {
	
	private int rec_sc_num; // 기록 스케줄 번호
	private int rec_sc_code; // 다이어리 번호와 fk
	private int rec_sc_nday; // 스케줄 일차
	private int rec_sc_cost; // 스케줄 경비
	private String rec_sc_loca;  // 스케줄 일정
	private String rec_sc_time;  // 스케줄 시간
	private String rec_sc_addr; // 스케줄 주소
	private String  rec_sc_type; // 스케줄 경비타입
	private String  rec_sc_memo; // 스케줄 메모

}
