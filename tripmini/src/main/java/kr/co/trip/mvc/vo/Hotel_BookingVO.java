package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("hotelbkvo")
@Getter
@Setter
public class Hotel_BookingVO {
	
	//BOOKING_NUM,MEM_NUM,ROOM_NUM,HOTEL_NAME,ROOM_NAME,BOOKING_CHECKIN,BOOKING_CHECKOUT,BOOKING_STATUS,BOOKING_PAYMENT,BOOKING_PRICE,BOOKING_DATE
	private int booking_num; // 예약 번호
	private int mem_code; // 예약 회원 번호
	private int room_code; // 예약 객실 번호
	private int booking_status; // 예약 상태
	private int booking_price; // 예약 금액
	private String hotel_name; // 호텔명
	private String room_name; // 객실명
	private String booking_checkin; // 예약 체크인
	private String booking_checkout; // 예약 체크아웃
	private String booking_payment; // 예약 결제수단
	private String booking_date; // 예약일

}