package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("hotelfvvo")
@Getter
@Setter
public class Hotel_FavorateVO {
	
	//MEM_NUM,FAVORATE_HOTEL
	private int mem_code; // 찜한 회원 번호
	private int hotel_code; // 찜한 호텔 번호

}