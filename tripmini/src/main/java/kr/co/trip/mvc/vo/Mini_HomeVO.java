package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Alias("minihvo")
@Getter
@Setter
public class Mini_HomeVO {
	  /*
	  mini_home_num NUMBER(10) PRIMARY KEY,
	  mini_home_title VARCHAR2(255),
	  mini_home_content VARCHAR(255),
	  mini_home_thumbnail VARCHAR2(255),
	  mini_home_host VARCHAR2(255)
	  mini_home_date
	  */
	private int mini_home_num;
	private String mini_home_title;
	private String mini_home_content;
	private String mini_home_thumbnail;
	private String mini_home_host;
	private String mini_home_date;
	
	private MultipartFile mfile;
	

}
