package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("hotelvo")
@Setter
@Getter
public class HotelVO {
	/*
	 * HOTEL_NUM,HOTEL_PHOTO_SEQ,HOTEL_ID,HOTEL_PWD,HOTEL_EMAIL,HOTEL_TEL,HOTEL_OWNER,
	 * HOTEL_ADDR,HOTEL_CATEGORI,HOTEL_NAME,HOTEL_INFO,HOTEL_GUIDE,HOTEL_GRADE
	 */
	
	private int hotel_num; // 호텔 넘버
	private int hotel_photo_seq; // 호텔 사진 시퀀스
	private int hotel_grade; // 호텔 등급
	private String hotel_id; // 사업자 아이디
	private String hotel_pwd; // 사업자 비밀번호
	private String hotel_email; // 사업자 이메일
	private String hotel_tel; // 사업자 전화번호
	private String hotel_owner; // 사업자명
	private String hotel_addr; // 호텔 주소
	private String hotel_categori; // 호텔 종류
	private String hotel_name; // 호텔 이름
	private String hotel_info; // 호텔 정보
	private String hotel_guide; // 호텔 이용가이드

}