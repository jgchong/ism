package nfm.com.skd.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Skd010SearchVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 현재페이지
     */
    private int pageIndex = 1;
    /**
     * 페이지갯수
     */
    private int pageUnit = 50;
    /**
     * 페이지사이즈
     */
    private int pageSize = 10;
    /**
     * firstIndex
     */
    private int firstIndex = 0;
    /**
     * lastIndex
     */
    private int lastIndex = 1;
    /**
     * recordCountPerPage
     */
    private int recordCountPerPage = 10;


    //search 조건
    private String dfSearch_itemname;

    public String getDtSearch_frCreateDt() {
        return dtSearch_frCreateDt;
    }

    public void setDtSearch_frCreateDt(String dtSearch_frCreateDt) {
        this.dtSearch_frCreateDt = dtSearch_frCreateDt;
    }

    private String dtSearch_frCreateDt;


    public String getDfSearch_itemname() {
        return dfSearch_itemname;
    }

    public void setDfSearch_itemname(String dfSearch_itemname) {
        this.dfSearch_itemname = dfSearch_itemname;
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
