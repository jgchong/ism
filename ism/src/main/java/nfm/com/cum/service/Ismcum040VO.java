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
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
