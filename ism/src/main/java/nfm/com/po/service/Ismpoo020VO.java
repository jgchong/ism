package nfm.com.po.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismpoo020VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int byc010id = 0;
	private String apiurl = "";
	private String apicontext = "";
	private String apiversion = "";

	public int getByc010id() {
		return byc010id;
	}

	public void setByc010id(int byc010id) {
		this.byc010id = byc010id;
	}

	public String getApiurl() {
		return apiurl;
	}

	public void setApiurl(String apiurl) {
		this.apiurl = apiurl;
	}

	public String getApicontext() {
		return apicontext;
	}

	public void setApicontext(String apicontext) {
		this.apicontext = apicontext;
	}

	public String getApiversion() {
		return apiversion;
	}

	public void setApiversion(String apiversion) {
		this.apiversion = apiversion;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
