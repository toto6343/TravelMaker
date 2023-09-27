package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("mhcvo")
@Getter
@Setter
public class Mini_CommentVO {
	private String mini_com_content;
	private String mini_com_nick;
}
