package nfm.com.ord.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismodo020VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건*/
    private int cum030id = 0;
    private String apiurl = "";
    private String apicontext = "";
    private String apiversion = "";

	public int getCum030id() {
		return cum030id;
	}

	public void setCum030id(int cum030id) {
		this.cum030id = cum030id;
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
