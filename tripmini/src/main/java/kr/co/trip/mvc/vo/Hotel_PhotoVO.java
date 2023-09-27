package kr.co.trip.mvc.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Alias("hotelpvo")
@Getter
@Setter
public class Hotel_PhotoVO {
	
	//HOTEL_PHOTO_NUM,HOTEL_PHOTO_TITLE,HOTEL_PHOTO_SEQ
	
	private int hotel_potho_num; // 호텔 사진 번호
	private String hotel_photo_title; // 호텔 사진 이름
	private int hotel_photo_seq; // 호텔 사진 시퀀스
	
	private List<MultipartFile> hotelimgfile; 

}