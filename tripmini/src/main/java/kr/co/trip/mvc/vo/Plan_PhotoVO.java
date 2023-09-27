package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
@Alias("pphvo")
@Getter
@Setter
public class Plan_PhotoVO {
	private int plan_photo_num; // 기록 사진 번호
	private int plan_photo_code; // 다이어리 번호와 fk
	private String plan_photo_title; // 사진 파일명
	//private MultipartFile mfile;

}
