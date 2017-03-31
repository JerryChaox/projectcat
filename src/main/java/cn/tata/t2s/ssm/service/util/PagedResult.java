package cn.tata.t2s.ssm.service.util;

import java.util.List;

import org.junit.Assert;
import org.springframework.stereotype.Component;

@Component("pagedResult")
public class PagedResult<T> {
	private final static int DEFAULT_PAGE_SIZE = 10;
	
	private List<T> data;
	private long totalPages;
	private long totalRecords;
	private int pageSize;
	private int pageNumber;
	
	public PagedResult() {}
	
	public PagedResult(int pageSize, int pageNumber) {
		if(pageSize == 0) 
			this.pageSize = DEFAULT_PAGE_SIZE;
		else
			this.pageSize = pageSize;
		
		this.pageNumber = pageNumber;
	}
	
	public int getStartPosition() {
		return pageSize * (pageNumber - 1);
	}
	
	
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(long totalRecords, long pageSize) {
    	
    	Assert.assertNotEquals(0, pageSize);
    	
    	if((totalRecords % pageSize) > 0)
    		this.totalPages =  (totalRecords / pageSize) + 1;
    	else
    		this.totalPages = (totalRecords / pageSize);
	}
	
	public long getTotalRecords() {
		return totalRecords;
	}


	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public String toString() {
		return "PagedResult [data=" + data + ", totalPages=" + totalPages + ", totalRecords=" + totalRecords
				+ ", pageSize=" + pageSize + ", pageNumber=" + pageNumber + "]";
	}
	
	

}
