package nfm.com.cum.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismcum030VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int cum030id = 0;
	private int cum010id = 0;
	private String shopmallname = "";
	private String shopurl = "";
	private String shopuid = "";
	private String shoppwd = "";
	private String uploadtype = "";
	private String uploadgubun = "";
	private String useyn = "";

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

	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
