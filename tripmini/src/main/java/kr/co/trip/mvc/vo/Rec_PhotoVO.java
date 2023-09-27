package kr.co.trip.mvc.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Alias("rphvo")
@Getter
@Setter
public class Rec_PhotoVO {
	/*
	  rec_photo_num   number ,
	  rec_photo_code  number NOT NULL,
	  rec_photo_title VARCHAR2(1000) NOT NULL,
	  rec_photo_nday number NOT NULL,
	 */
	
	private int rec_photo_num; // 기록 사진 번호
	private int rec_photo_code; // 다이어리 번호와 fk
	private int  rec_photo_nday; // 사진 일차
	private String rec_photo_title; // 사진 파일명
	private List<MultipartFile> recimgfile; 
	
}
