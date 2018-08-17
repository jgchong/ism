package nfm.com.whs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismwhs030VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int whs030id = 0;
	private int whs010id = 0;
	private String whsurl = "";
	private String whsuid = "";
	private String whspwd = "";
	
	public int getWhs030id() {
		return whs030id;
	}

	public void setWhs030id(int whs030id) {
		this.whs030id = whs030id;
	}

	public int getWhs010id() {
		return whs010id;
	}

	public void setWhs010id(int whs010id) {
		this.whs010id = whs010id;
	}

	public String getWhsurl() {
		return whsurl;
	}

	public void setWhsurl(String whsurl) {
		this.whsurl = whsurl;
	}

	public String getWhsuid() {
		return whsuid;
	}

	public void setWhsuid(String whsuid) {
		this.whsuid = whsuid;
	}

	public String getWhspwd() {
		return whspwd;
	}

	public void setWhspwd(String whspwd) {
		this.whspwd = whspwd;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
