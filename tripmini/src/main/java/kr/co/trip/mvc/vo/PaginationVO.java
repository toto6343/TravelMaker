package kr.co.trip.mvc.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("pgvo")
public class PaginationVO {
	private int page = 1;
    private int size = 10;
    private int recordCount;
    private int start = (page - 1) * size + 1;
    private int end = page * size;
    private int totalPages;

    public PaginationVO() {
        this.start = (page - 1) * size + 1;
        this.end = page * size;
    }

    public void calculateTotalPages() {
        if (recordCount % size == 0) {
            totalPages = recordCount / size;
        } else {
            totalPages = (recordCount / size) + 1;
        }
    }

}
