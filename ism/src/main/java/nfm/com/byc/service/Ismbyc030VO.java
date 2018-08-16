package nfm.com.byc.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismbyc030VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int byc030id = 0;
	private int byc010id = 0;
	private String bycurl = "";
	private String bycuid = "";
	private String bycpwd = "";
	
	public int getByc030id() {
		return byc030id;
	}
	public void setByc030id(int byc030id) {
		this.byc030id = byc030id;
	}
	public int getByc010id() {
		return byc010id;
	}
	public void setByc010id(int byc010id) {
		this.byc010id = byc010id;
	}
	public String getBycurl() {
		return bycurl;
	}
	public void setBycurl(String bycurl) {
		this.bycurl = bycurl;
	}
	public String getBycuid() {
		return bycuid;
	}
	public void setBycuid(String bycuid) {
		this.bycuid = bycuid;
	}
	public String getBycpwd() {
		return bycpwd;
	}
	public void setBycpwd(String bycpwd) {
		this.bycpwd = bycpwd;
	}
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
