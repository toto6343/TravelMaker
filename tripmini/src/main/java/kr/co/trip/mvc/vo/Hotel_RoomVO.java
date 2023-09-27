package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("hotelroomvo")
@Getter
@Setter
public class Hotel_RoomVO {
	//ROOM_NUM,HOTEL_NUM,HOTEL_PHOTO_SEQ,ROOM_MAX,ROOM_NAME,ROOM_PRICE,ROOM_CHECKIN,ROOM_CHECKOUT,ROOM_INFO
	private int room_num; // 객실 번호
	private int hotel_code; // 호텔 번호
	private int hotel_potho_seq; // 호텔 사진 시퀀스
	private int room_max; // 객실 최대인원
	private int room_price; // 객실 가격
	private String room_name; // 객실 이름
	private String room_info; // 객실 정보
	
}