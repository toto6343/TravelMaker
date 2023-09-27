package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("hotelpsvo")
@Getter
@Setter
public class Hotel_Potho_SequenceVO {
	
	//HOTEL_PHOTO_SEQ
	private int hotel_potho_seq; // 호텔 사진 시퀀스

}