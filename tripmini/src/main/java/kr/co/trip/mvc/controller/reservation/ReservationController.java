package kr.co.trip.mvc.controller.reservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@GetMapping("/hotel")
	public String hotel() {
		return "reservation/hotel";
	}
	
	@GetMapping("/reserdetail")
	public String reserdetail() {
		return "reservation/reserdetail";
	}
	 
	@GetMapping("/review_input")
	public String review_input() {
		return "reservation/review_input";
	}
	
	@GetMapping("/rooms")
	public String hotelrooms() {
		return "reservation/roomsInsert";
	}
	
	@GetMapping("/roomsUpdate")
	public String roomsUpdate() {
		return "reservation/roomsList";
	}
	
	@GetMapping("/hotelRegist")
	public String hotelRegist() {
		return "reservation/hotelRegistForm";
	}
}