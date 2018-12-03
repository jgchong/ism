package nfm.com.cum.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismcum040VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int cum030id = 0;
	private String coname = "";
	private String shopmallname = "";
	private int orderitemid = 0;
	private String itemcode = "";
	private String itemname = "";
	private String cumprodcode = "";
	private int itembuyprice = 0;
	private int accountPrice = 0;
	private String cumprodoptnm1 = "";
	private String cumprodoptnm2 = "";

	public int getCum030id() {
		return cum030id;
	}
	public void setCum030id(int cum030id) {
		this.cum030id = cum030id;
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
	public int getOrderitemid() {
		return orderitemid;
	}
	public void setOrderitemid(int orderitemid) {
		this.orderitemid = orderitemid;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getCumprodcode() {
		return cumprodcode;
	}
	public void setCumprodcode(String cumprodcode) {
		this.cumprodcode = cumprodcode;
	}
	public int getItembuyprice() {
		return itembuyprice;
	}
	public void setItembuyprice(int itembuyprice) {
		this.itembuyprice = itembuyprice;
	}
	public int getAccountPrice() {
		return accountPrice;
	}
	public void setAccountPrice(int accountPrice) {
		this.accountPrice = accountPrice;
	}
	public String getCumprodoptnm1() {
		return cumprodoptnm1;
	}
	public void setCumprodoptnm1(String cumprodoptnm1) {
		this.cumprodoptnm1 = cumprodoptnm1;
	}
	public String getCumprodoptnm2() {
		return cumprodoptnm2;
	}
	public void setCumprodoptnm2(String cumprodoptnm2) {
		this.cumprodoptnm2 = cumprodoptnm2;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
