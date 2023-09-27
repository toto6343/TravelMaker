package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("mcvo")
@Setter
@Getter
public class Mini_CalVO {
	private int mini_cal_no;
	private String mini_cal_title;
	private String mini_cal_memo;
	private String mini_cal_start;
	private String mini_cal_end;
	private int mini_cal_code;
}
