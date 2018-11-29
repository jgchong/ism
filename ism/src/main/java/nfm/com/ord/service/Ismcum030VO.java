package nfm.com.ord.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismcum030VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건*/
    private int cum030id = 0;
    private int cum010id = 0;
    private String coname = "";
    private String shopmallname = "";
    private String shopurl = "";
    private String shopuid = "";
    private String shoppwd = "";
    private String uploadtype = "";
    private String uploadgubun = "";
    private int uploadCnt = 0; //주문수짐목록에 업로드 횟수 추가필드
    private String lastUploadDate = ""; //주문수짐목록에 최근 반영일 추가필드
    // LDC 주문 수집 수동 관리에서 정산가 매입가 표시.
    private String account2 = "";


	public int getCum030id() {
		return cum030id;
	}



	public void setCum030id(int cum030id) {
		this.cum030id = cum030id;
	}



	public int getCum010id() {
		return cum010id;
	}



	public void setCum010id(int cum010id) {
		this.cum010id = cum010id;
	}



	public String getConame() {
		return coname;
	}



	public void setConame(String coname) {
		this.coname = coname;
	}



	public String getShopmallname() {
		return shopmallname;
	}



	public void setShopmallname(String shopmallname) {
		this.shopmallname = shopmallname;
	}



	public String getShopurl() {
		return shopurl;
	}



	public void setShopurl(String shopurl) {
		this.shopurl = shopurl;
	}



	public String getShopuid() {
		return shopuid;
	}



	public void setShopuid(String shopuid) {
		this.shopuid = shopuid;
	}



	public String getShoppwd() {
		return shoppwd;
	}



	public void setShoppwd(String shoppwd) {
		this.shoppwd = shoppwd;
	}



	public String getUploadtype() {
		return uploadtype;
	}



	public void setUploadtype(String uploadtype) {
		this.uploadtype = uploadtype;
	}



	public String getUploadgubun() {
		return uploadgubun;
	}



	public void setUploadgubun(String uploadgubun) {
		this.uploadgubun = uploadgubun;
	}



	public int getUploadCnt() {
		return uploadCnt;
	}



	public void setUploadCnt(int uploadCnt) {
		this.uploadCnt = uploadCnt;
	}



	public String getLastUploadDate() {
		return lastUploadDate;
	}



	public void setLastUploadDate(String lastUploadDate) {
		this.lastUploadDate = lastUploadDate;
	}

	
	// LDC
	public String getAccount2() {
		return account2;
	}

	public void setAccount2(String account2) {
		this.account2 = account2;
	}



	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
