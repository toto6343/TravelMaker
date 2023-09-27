package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("hotelrevvo")
@Getter
@Setter
public class Hotel_ReviewVO {
	//REVIEW_NUM,MEM_NUM,HOTEL_NUM,BOOKING_NUM,REVIEW_CONT,REVIEW_DATE,REVIEW_POINT
	private int review_num; // 리뷰 번호
	private int mem_code; // 리뷰남긴 회원 번호
	private int hotel_code; // 호텔 번호
	private int booking_code; // 예약 번호
	private int review_point; // 리뷰 점수
	private String review_cont; // 리뷰 내용
	private String review_date; // 리뷰 작성일

}