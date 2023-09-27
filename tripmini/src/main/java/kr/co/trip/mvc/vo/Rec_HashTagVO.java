package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("htvo")
@Getter
@Setter
public class Rec_HashTagVO {
	
	private	int rec_hash_num; // 해시태그 번호
	private	int rec_hash_code; // 다이어리 번호와 fk
	private	String rec_hash_tag; // 해시태그
	
}
