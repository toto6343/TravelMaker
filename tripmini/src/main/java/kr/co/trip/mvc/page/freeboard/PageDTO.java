package kr.co.trip.mvc.page.freeboard;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageDTO {
	private int pageNo;
	private int pageSize;
	private int totalCount;
	
	private int startNo;
	private int endNo;
	private int startPage;
	private int endPage;
	private int totalPage;
	
	public PageDTO(int pageNo, int pageSize, int totalCount) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		
	totalPage = (totalCount - 1) / pageSize + 1;
	
	this.pageNo = (pageNo < 1) ? 1 : pageNo;
	this.pageNo = (pageNo > totalPage) ? totalPage : pageNo;
	
	startNo = (this.pageNo - 1) * pageSize + 1;
	endNo = startNo + (pageSize - 1);
	this.endNo = this.endNo > totalCount ? totalCount : this.endNo;
	
	startPage = (this.pageNo - 1) / 10 * 10 + 1;
	endPage = startPage + 9;
	this.endPage = this.endPage > totalPage ? totalPage : this.endPage;
				
	}
}
