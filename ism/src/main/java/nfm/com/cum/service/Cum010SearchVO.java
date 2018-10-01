package nfm.com.cum.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Cum010SearchVO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건*/
	private int cum010id = 0;
	private String search_coname = "";
	private String accountamt = "0";
    private String orderbyclause = "a.coname";

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

	public int getCum010id() {
		return cum010id;
	}

	public void setCum010id(int cum010id) {
		this.cum010id = cum010id;
	}

	public String getSearch_coname() {
		return search_coname;
	}

	public void setSearch_coname(String search_coname) {
		this.search_coname = search_coname;
	}

	public String getAccountamt() {
		return accountamt;
	}

	public void setAccountamt(String accountamt) {
		this.accountamt = accountamt;
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
