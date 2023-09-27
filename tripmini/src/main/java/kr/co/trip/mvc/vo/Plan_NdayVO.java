package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("pndayvo")
@Getter
@Setter
public class Plan_NdayVO {
	private int plan_nday_num; // 계획 일차 번호
	private int plan_nday_code; // 다이어리 번호와 fk
	private int plan_nday_nday; // 여행일차
	

	
	
}
