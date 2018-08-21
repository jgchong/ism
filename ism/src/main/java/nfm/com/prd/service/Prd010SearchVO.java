package nfm.com.prd.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Prd010SearchVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 검색조건
     */

    private String dtSearch_frCreateDt = "";
    private String dtSearch_toCreateDt = "";
    private String dfSearch_itemcat1;
    private String dfSearch_itemcrosstype;
    private String dfSearch_itemgubun;
    private String dfSearch_bycname;
    private String dfSearch_itemcode;
    private String dfSearch_itemname;
    private String dfSearch_itemopt;
    private String dfSearch_whsname;

    private Integer dfChange_whs010id;
    private Integer dfChange_orderitemid;

    private String currentItemcoed;

    public String getCurrentItemcoed() {
        return currentItemcoed;
    }

    public void setCurrentItemcoed(String currentItemcoed) {
        this.currentItemcoed = currentItemcoed;
    }

//    private String itemname;
//    private String itemcrosstype;
//    private int byc010id;
//    private String itemopt;
//    private int itemea;
//    private int itemprice;
//    private int itembuyprice;
//    private String taxtype;
//    private int itemdlvprice;
//    private int itembuydlvprice;
//    private String itemgubun;
//    private String createdate;
//    private String byccode;
//    private String itemcode;

    /**
     * 상세검색 여부.
     */
    private int search_isdetail = 0;
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

    public String getDtSearch_frCreateDt() {
        return dtSearch_frCreateDt;
    }

    public void setDtSearch_frCreateDt(String dtSearch_frCreateDt) {
        this.dtSearch_frCreateDt = dtSearch_frCreateDt;
    }

    public String getDtSearch_toCreateDt() {
        return dtSearch_toCreateDt;
    }

    public void setDtSearch_toCreateDt(String dtSearch_toCreateDt) {
        this.dtSearch_toCreateDt = dtSearch_toCreateDt;
    }

    public String getDfSearch_itemcat1() {
        return dfSearch_itemcat1;
    }

    public void setDfSearch_itemcat1(String dfSearch_itemcat1) {
        this.dfSearch_itemcat1 = dfSearch_itemcat1;
    }

    public String getDfSearch_itemcrosstype() {
        return dfSearch_itemcrosstype;
    }

    public void setDfSearch_itemcrosstype(String dfSearch_itemcrosstype) {
        this.dfSearch_itemcrosstype = dfSearch_itemcrosstype;
    }

    public String getDfSearch_itemgubun() {
        return dfSearch_itemgubun;
    }

    public void setDfSearch_itemgubun(String dfSearch_itemgubun) {
        this.dfSearch_itemgubun = dfSearch_itemgubun;
    }

    public String getDfSearch_bycname() {
        return dfSearch_bycname;
    }

    public void setDfSearch_bycname(String dfSearch_bycname) {
        this.dfSearch_bycname = dfSearch_bycname;
    }

    public String getDfSearch_itemcode() {
        return dfSearch_itemcode;
    }

    public void setDfSearch_itemcode(String dfSearch_itemcode) {
        this.dfSearch_itemcode = dfSearch_itemcode;
    }

    public String getDfSearch_itemname() {
        return dfSearch_itemname;
    }

    public void setDfSearch_itemname(String dfSearch_itemname) {
        this.dfSearch_itemname = dfSearch_itemname;
    }

    public String getDfSearch_itemopt() {
        return dfSearch_itemopt;
    }

    public void setDfSearch_itemopt(String dfSearch_itemopt) {
        this.dfSearch_itemopt = dfSearch_itemopt;
    }

    public String getDfSearch_whsname() {
        return dfSearch_whsname;
    }

    public void setDfSearch_whsname(String dfSearch_whsname) {
        this.dfSearch_whsname = dfSearch_whsname;
    }


    public int getSearch_isdetail() {
        return search_isdetail;
    }


    public void setSearch_isdetail(int search_isdetail) {
        this.search_isdetail = search_isdetail;
    }

    public Integer getDfChange_whs010id() {
        return dfChange_whs010id;
    }

    public void setDfChange_whs010id(Integer dfChange_whs010id) {
        this.dfChange_whs010id = dfChange_whs010id;
    }

    public Integer getDfChange_orderitemid() {
        return dfChange_orderitemid;
    }

    public void setDfChange_orderitemid(Integer dfChange_orderitemid) {
        this.dfChange_orderitemid = dfChange_orderitemid;
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
