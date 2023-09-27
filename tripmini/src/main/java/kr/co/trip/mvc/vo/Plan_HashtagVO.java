package kr.co.trip.mvc.vo;


import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("phtvo")
@Getter
@Setter
public class Plan_HashtagVO {
	private int plan_hash_num;
	private int plan_hash_code;
	private String plan_hash_tag;
}