package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("mhvo")
@Getter
@Setter
public class Mini_HompyVO {
	  //mini_home_title varchar2(20), 
	  //mini_home_nick  varchar2(30) NOT NULL
	private String mini_home_title;
	private String mini_home_nick;
	

}
