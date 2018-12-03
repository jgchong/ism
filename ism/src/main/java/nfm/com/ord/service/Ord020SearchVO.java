package nfm.com.ord.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ord020SearchVO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건*/
    private String search_key1 = ""; //uploadviewkey 조건
    private String search_status = "ALL";
    private String search_cstype = "0";
    private int odm010id = 0;
    
    private String dtSearch_frOrderDt = "";
    private String dtSearch_toOrderDt = "";
    private String dtSearch_cumType1 = "0";
    private String dtSearch_bycNm = "";
    private String dtSearch_cumNm = "";
    private String dtSearch_dlvNo = "";
    private String dtSearch_dlvCo = "";
    private String dtSearch_orderno = "";
    private String dtSearch_orderItemid = "";
    private String dtSearch_orderItemName = "";
    private String dtSearch_rcvUser = "";
    private String dtSearch_rcvuserContact = "";
    private String dtSearch_orderUser = "";
    private String dtSearch_status = "0";
    private String dtSearch_crtype = "0";
    private String dtSearch_all = "";
    private int search_isdetail = 0;
    //발주를 위해 추가
    private int poSearch_byc010id = 0;
    private int poSearch_pristock = 0;
    //private String orderbyclause = "a.shopmallname";
    
    // LDC 추가
    private String search_tempdiv = "";
    private String search_uploadviewkey = "";
    private String search_cum010id = "";
    private String search_cum030id = "";
    
    public String getSearch_uploadviewkey() {
		return search_uploadviewkey;
	}

	public void setSearch_uploadviewkey(String search_uploadviewkey) {
		this.search_uploadviewkey = search_uploadviewkey;
	}

	public String getSearch_cum010id() {
		return search_cum010id;
	}

	public void setSearch_cum010id(String search_cum010id) {
		this.search_cum010id = search_cum010id;
	}

	public String getSearch_cum030id() {
		return search_cum030id;
	}

	public void setSearch_cum030id(String search_cum030id) {
		this.search_cum030id = search_cum030id;
	}

	public String getSearch_tempdiv() {
		return search_tempdiv;
	}

	public void setSearch_tempdiv(String search_tempdiv) {
		this.search_tempdiv = search_tempdiv;
	}



	/** 현재페이지 */
    private int pageIndex = 1;
    /** 페이지갯수 */
    private int pageUnit = 50;
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


/*
	public String getOrderbyclause() {
		return orderbyclause;
	}



	public void setOrderbyclause(String orderbyclause) {
		this.orderbyclause = orderbyclause;
	}
*/


	public String getSearch_status() {
		return search_status;
	}



	public void setSearch_status(String search_status) {
		this.search_status = search_status;
	}



	public int getOdm010id() {
		return odm010id;
	}



	public void setOdm010id(int odm010id) {
		this.odm010id = odm010id;
	}



	public String getSearch_cstype() {
		return search_cstype;
	}



	public void setSearch_cstype(String search_cstype) {
		this.search_cstype = search_cstype;
	}



	public String getDtSearch_frOrderDt() {
		return dtSearch_frOrderDt;
	}



	public void setDtSearch_frOrderDt(String dtSearch_frOrderDt) {
		this.dtSearch_frOrderDt = dtSearch_frOrderDt;
	}



	public String getDtSearch_toOrderDt() {
		return dtSearch_toOrderDt;
	}



	public void setDtSearch_toOrderDt(String dtSearch_toOrderDt) {
		this.dtSearch_toOrderDt = dtSearch_toOrderDt;
	}



	public String getDtSearch_cumType1() {
		return dtSearch_cumType1;
	}



	public void setDtSearch_cumType1(String dtSearch_cumType1) {
		this.dtSearch_cumType1 = dtSearch_cumType1;
	}



	public String getDtSearch_bycNm() {
		return dtSearch_bycNm;
	}



	public void setDtSearch_bycNm(String dtSearch_bycNm) {
		this.dtSearch_bycNm = dtSearch_bycNm;
	}



	public String getDtSearch_cumNm() {
		return dtSearch_cumNm;
	}



	public void setDtSearch_cumNm(String dtSearch_cumNm) {
		this.dtSearch_cumNm = dtSearch_cumNm;
	}



	public String getDtSearch_dlvNo() {
		return dtSearch_dlvNo;
	}



	public void setDtSearch_dlvNo(String dtSearch_dlvNo) {
		this.dtSearch_dlvNo = dtSearch_dlvNo;
	}



	public String getDtSearch_dlvCo() {
		return dtSearch_dlvCo;
	}



	public void setDtSearch_dlvCo(String dtSearch_dlvCo) {
		this.dtSearch_dlvCo = dtSearch_dlvCo;
	}



	public String getDtSearch_orderno() {
		return dtSearch_orderno;
	}



	public void setDtSearch_orderno(String dtSearch_orderno) {
		this.dtSearch_orderno = dtSearch_orderno;
	}



	public String getDtSearch_orderItemid() {
		return dtSearch_orderItemid;
	}



	public void setDtSearch_orderItemid(String dtSearch_orderItemid) {
		this.dtSearch_orderItemid = dtSearch_orderItemid;
	}



	public String getDtSearch_orderItemName() {
		return dtSearch_orderItemName;
	}



	public void setDtSearch_orderItemName(String dtSearch_orderItemName) {
		this.dtSearch_orderItemName = dtSearch_orderItemName;
	}



	public String getDtSearch_rcvUser() {
		return dtSearch_rcvUser;
	}



	public void setDtSearch_rcvUser(String dtSearch_rcvUser) {
		this.dtSearch_rcvUser = dtSearch_rcvUser;
	}



	public String getDtSearch_rcvuserContact() {
		return dtSearch_rcvuserContact;
	}



	public void setDtSearch_rcvuserContact(String dtSearch_rcvuserContact) {
		this.dtSearch_rcvuserContact = dtSearch_rcvuserContact;
	}



	public String getDtSearch_orderUser() {
		return dtSearch_orderUser;
	}



	public void setDtSearch_orderUser(String dtSearch_orderUser) {
		this.dtSearch_orderUser = dtSearch_orderUser;
	}



	public String getDtSearch_status() {
		return dtSearch_status;
	}



	public void setDtSearch_status(String dtSearch_status) {
		this.dtSearch_status = dtSearch_status;
	}



	public String getDtSearch_crtype() {
		return dtSearch_crtype;
	}



	public void setDtSearch_crtype(String dtSearch_crtype) {
		this.dtSearch_crtype = dtSearch_crtype;
	}



	public String getDtSearch_all() {
		return dtSearch_all;
	}



	public void setDtSearch_all(String dtSearch_all) {
		this.dtSearch_all = dtSearch_all;
	}



	public int getSearch_isdetail() {
		return search_isdetail;
	}



	public void setSearch_isdetail(int search_isdetail) {
		this.search_isdetail = search_isdetail;
	}



	public int getPoSearch_byc010id() {
		return poSearch_byc010id;
	}



	public void setPoSearch_byc010id(int poSearch_byc010id) {
		this.poSearch_byc010id = poSearch_byc010id;
	}



	public int getPoSearch_pristock() {
		return poSearch_pristock;
	}



	public void setPoSearch_pristock(int poSearch_pristock) {
		this.poSearch_pristock = poSearch_pristock;
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
