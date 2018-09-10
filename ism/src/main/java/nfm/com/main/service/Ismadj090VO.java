package nfm.com.main.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismadj090VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String closemonth = "";
	private String cumclosedate = "";
	private String bycclosedate = "";
	private int cumclosestatus = 1;
	private int bycclosestatus = 1;
	private int orgcumclosestatus = 1;
	private int orgbycclosestatus = 1;

	public String getClosemonth() {
		return closemonth;
	}

	public void setClosemonth(String closemonth) {
		this.closemonth = closemonth;
	}

	public String getCumclosedate() {
		return cumclosedate;
	}

	public void setCumclosedate(String cumclosedate) {
		this.cumclosedate = cumclosedate;
	}

	public String getBycclosedate() {
		return bycclosedate;
	}

	public void setBycclosedate(String bycclosedate) {
		this.bycclosedate = bycclosedate;
	}

	public int getCumclosestatus() {
		return cumclosestatus;
	}

	public void setCumclosestatus(int cumclosestatus) {
		this.cumclosestatus = cumclosestatus;
	}

	public int getBycclosestatus() {
		return bycclosestatus;
	}

	public void setBycclosestatus(int bycclosestatus) {
		this.bycclosestatus = bycclosestatus;
	}

	public int getOrgcumclosestatus() {
		return orgcumclosestatus;
	}

	public void setOrgcumclosestatus(int orgcumclosestatus) {
		this.orgcumclosestatus = orgcumclosestatus;
	}

	public int getOrgbycclosestatus() {
		return orgbycclosestatus;
	}

	public void setOrgbycclosestatus(int orgbycclosestatus) {
		this.orgbycclosestatus = orgbycclosestatus;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
