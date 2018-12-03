package nfm.com.po.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismpoo010VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int poo010id = 0;
	private String pocotype = "";
	private String isassign = "";
	private String orderfield = "";
	private String orderfieldnm = "";
	private int fieldorder = 0;
	private String fieldvalue = "";
	private String fieldname = "";

	public int getPoo010id() {
		return poo010id;
	}

	public void setPoo010id(int poo010id) {
		this.poo010id = poo010id;
	}

	public String getPocotype() {
		return pocotype;
	}

	public void setPocotype(String pocotype) {
		this.pocotype = pocotype;
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

	public String getOrderfieldnm() {
		return orderfieldnm;
	}

	public void setOrderfieldnm(String orderfieldnm) {
		this.orderfieldnm = orderfieldnm;
	}

	public int getFieldorder() {
		return fieldorder;
	}

	public void setFieldorder(int fieldorder) {
		this.fieldorder = fieldorder;
	}
	
	public String getFieldvalue() {
		return fieldvalue;
	}

	public void setFieldvalue(String fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	
	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
