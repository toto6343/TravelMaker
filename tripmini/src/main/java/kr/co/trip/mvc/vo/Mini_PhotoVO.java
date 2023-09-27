package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Alias("mpvo")
@Getter
@Setter
public class Mini_PhotoVO {
	
	
	private int mini_pho_num;
	private String mini_pho_nick;
	private String mini_pho_sub;
	private String mini_pho_content;
	private String mini_pho_file;
	private String mini_pho_date;
	private MultipartFile mfile;
	
}
