package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;


@Alias("pmtinforvo")
@Getter
@Setter
public class Plan_MateriallistVO {
	private int plan_mtrlist_num;
	private int plan_mtrlist_code;
	private String plan_mtr_material; // 준비물
	private int plan_mtr_check; // 준비물 챙김 여부
	
}
