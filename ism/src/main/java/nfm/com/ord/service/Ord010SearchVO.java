package nfm.com.ord.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ord010SearchVO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건*/
    private String search_key1 = "";
    private String search_filename = "";
    private String orderbyclause = "a.shopmallname";

    /** 현재페이지 */
    private int pageIndex = 1;
    /** 페이지갯수 */
    private int pageUnit = 10;
    /** 페이지사이즈 */
    private int pageSize = 10;
    /** firstIndex */
    private int firstIndex = 0;
    /** lastIndex */
    private int lastIndex = 1;
    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    

	public String getSearch_key1() {
		return search_key1;
	}



	public void setSearch_key1(String search_key1) {
		this.search_key1 = search_key1;
	}



	public String getSearch_filename() {
		return search_filename;
	}



	public void setSearch_filename(String search_filename) {
		this.search_filename = search_filename;
	}



	public String getOrderbyclause() {
		return orderbyclause;
	}



	public void setOrderbyclause(String orderbyclause) {
		this.orderbyclause = orderbyclause;
	}



	public int getPageIndex() {
		return pageIndex;
	}



	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}



	public int getPageUnit() {
		return pageUnit;
	}



	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public int getFirstIndex() {
		return firstIndex;
	}



	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}



	public int getLastIndex() {
		return lastIndex;
	}



	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}



	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}



	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}



	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
