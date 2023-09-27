package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;


@Alias("mdvo")
@Getter
@Setter
public class Mini_DiaryVO {

	private int mini_dia_num;
	private String mini_dia_nick;
	private String mini_dia_sub;
	private String mini_dia_content;
	private String mini_dia_file;
	private String mini_dia_date;
	private MultipartFile mfile;
	

}
