package nfm.com.adj.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Adj010SearchVO implements Serializable {

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




    private String dtSearch_frCreateDt;
    private String dtSearch_adj020_01;
    private String dtSearch_adj030_byc;

    public String getDtSearch_frCreateDt() {
        return dtSearch_frCreateDt;
    }

    public void setDtSearch_frCreateDt(String dtSearch_frCreateDt) {
        this.dtSearch_frCreateDt = dtSearch_frCreateDt;
    }

    public String getDtSearch_adj020_01() {
        return dtSearch_adj020_01;
    }

    public void setDtSearch_adj020_01(String dtSearch_adj020_01) {
        this.dtSearch_adj020_01 = dtSearch_adj020_01;
    }

    public String getDtSearch_adj030_byc() {
        return dtSearch_adj030_byc;
    }

    public void setDtSearch_adj030_byc(String dtSearch_adj030_byc) {
        this.dtSearch_adj030_byc = dtSearch_adj030_byc;
    }
    //-------------------------------------------------------------------------------------------


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
