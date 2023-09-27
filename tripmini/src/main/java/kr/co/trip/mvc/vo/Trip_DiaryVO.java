package kr.co.trip.mvc.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Alias("divo")
@Getter
@Setter
public class Trip_DiaryVO {

	private int dia_num; // 다이어리 번호
	private String dia_nick; // 닉네임
	private String dia_sub; // 다이어리 제목
	private String dia_summ; // 다이어리에 대한 한줄평
	private String dia_start; // 시작날짜
	private String dia_end; // 마지막 날짜
	private String dia_thumbnail; // 썸네일(imgn)
	private int dia_hit; // 조회수
	private int dia_like; // 좋아요
	private int dia_star; // 별점
	
	private List<Rec_ScheduleVO> rscvolist;
	private List<Plan_HashtagVO> planvolist;
	
	private MultipartFile mfile; // mfile

}
