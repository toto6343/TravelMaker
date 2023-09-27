package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("rndayvo")
@Getter
@Setter
public class Rec_NdayVO {
	
	private int rec_nday_num; // 기록 일차 번호
	private int rec_nday_code; // 다이어리 번호와 fk
	private int rec_nday_nday; // 여행일차

}
