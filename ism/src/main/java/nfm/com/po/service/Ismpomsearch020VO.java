package nfm.com.po.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismpomsearch020VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 100;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 0;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    /** 검색KeywordFrom */
    private String searchKeywordFrom = "";    

	/** 검색KeywordTo */
    private String searchKeywordTo = "";  
    
    /** 검색시작일 */
    private String dtSearch_frPoDt = "";
    
    /** 검색종료일 */
    private String dtSearch_toPoDt = "";
    
    /** 매입처 */
    private String dtSearch_bycNm = ""; 
    
    /** 발신인 */
    private String dtSearch_sndNm = "";
    
    
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

	public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
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

    
    /**
	 * searchKeywordFrom attribute를 리턴한다.
	 * @return String
	 */
	public String getSearchKeywordFrom() {
		return searchKeywordFrom;
	}

	/**
	 * searchKeywordFrom attribute 값을 설정한다.
	 * @param searchKeywordFrom String
	 */
	public void setSearchKeywordFrom(String searchKeywordFrom) {
		this.searchKeywordFrom = searchKeywordFrom;
	}

	/**
	 * searchKeywordTo attribute를 리턴한다.
	 * @return String
	 */
	public String getSearchKeywordTo() {
		return searchKeywordTo;
	}

	/**
	 * searchKeywordTo attribute 값을 설정한다.
	 * @param searchKeywordTo String
	 */
	public void setSearchKeywordTo(String searchKeywordTo) {
		this.searchKeywordTo = searchKeywordTo;
	}
	
	/**
	 * dtSearch_frPoDt attribute를 리턴한다.
	 * @return String
	 */
	public String getDtSearch_frPoDt() {
		return dtSearch_frPoDt;
	}

	/**
	 * dtSearch_frPoDt attribute 값을 설정한다.
	 * @param dtSearch_frPoDt String
	 */
	public void setDtSearch_frPoDt(String dtSearch_frPoDt) {
		this.dtSearch_frPoDt = dtSearch_frPoDt;
	}
    
	/**
	 * dtSearch_toPoDt attribute를 리턴한다.
	 * @return String
	 */
	public String getDtSearch_toPoDt() {
		return dtSearch_toPoDt;
	}

	/**
	 * dtSearch_frPoDt attribute 값을 설정한다.
	 * @param dtSearch_frPoDt String
	 */
	public void setDtSearch_toPoDt(String dtSearch_toPoDt) {
		this.dtSearch_toPoDt = dtSearch_toPoDt;
	}
    
	/**
	 * dtSearch_bycNm attribute를 리턴한다.
	 * @return String
	 */
	public String getDtSearch_bycNm() {
		return dtSearch_bycNm;
	}

	/**
	 * dtSearch_bycNm attribute 값을 설정한다.
	 * @param dtSearch_bycNm String
	 */
	public void setDtSearch_bycNm(String dtSearch_bycNm) {
		this.dtSearch_bycNm = dtSearch_bycNm;
	}
    
	/**
	 * dtSearch_sndNm attribute를 리턴한다.
	 * @return String
	 */
	public String getDtSearch_sndNm() {
		return dtSearch_sndNm;
	}

	/**
	 * dtSearch_sndNm attribute 값을 설정한다.
	 * @param dtSearch_sndNm String
	 */
	public void setDtSearch_sndNm(String dtSearch_sndNm) {
		this.dtSearch_sndNm = dtSearch_sndNm;
	}
}
