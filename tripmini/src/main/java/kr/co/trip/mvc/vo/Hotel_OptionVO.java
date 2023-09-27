package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("hotelotvo")
@Getter
@Setter
public class Hotel_OptionVO {
	
	//HOTEL_CODE,OPTION_SPA,OPTION_PARKING,OPTION_WIFI,OPTION_BRUNCH,OPTION_RESTAURANT,OPTION_SWIM
	
	private int hotel_code; // 옵션선택 호텔 코드
	private int option_spa; // 스파
	private int option_parking; // 주차
	private int option_wifi; // 와이파이
	private int option_brunch; // 브런치
	private int option_restaurant; // 레스토랑
	private int option_swim; // 수영장

}