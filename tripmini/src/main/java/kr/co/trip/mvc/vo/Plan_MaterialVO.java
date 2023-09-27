package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("pmtrvo")
@Getter
@Setter
public class Plan_MaterialVO {
	private int plan_mtr_num; // 준비물 번호
	private int plan_mtr_code; // 계획 번호와 fk
	private String plan_mtr_class; // 준비물 대분류
	
	
}
