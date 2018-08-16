package nfm.com.ord.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismodo010VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건*/
    private int cum030id = 0;
    private String additem = "";
    private String isassign = "";
    private String orderfield = "";

	public int getCum030id() {
		return cum030id;
	}

	public void setCum030id(int cum030id) {
		this.cum030id = cum030id;
	}

	public String getAdditem() {
		return additem;
	}

	public void setAdditem(String additem) {
		this.additem = additem;
	}

	public String getIsassign() {
		return isassign;
	}

	public void setIsassign(String isassign) {
		this.isassign = isassign;
	}

	public String getOrderfield() {
		return orderfield;
	}

	public void setOrderfield(String orderfield) {
		this.orderfield = orderfield;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
