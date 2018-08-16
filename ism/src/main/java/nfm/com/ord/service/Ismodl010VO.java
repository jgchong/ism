package nfm.com.ord.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismodl010VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건*/
    private int odl010id = 0;
    private int cum010id = 0;
    private int cum030id = 0;
    private String uploaddate = "";
    private String uploadviewkey = "";
    

	public int getOdl010id() {
		return odl010id;
	}


	public void setOdl010id(int odl010id) {
		this.odl010id = odl010id;
	}


	public int getCum010id() {
		return cum010id;
	}


	public void setCum010id(int cum010id) {
		this.cum010id = cum010id;
	}


	public int getCum030id() {
		return cum030id;
	}


	public void setCum030id(int cum030id) {
		this.cum030id = cum030id;
	}


	public String getUploaddate() {
		return uploaddate;
	}


	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}


	public String getUploadviewkey() {
		return uploadviewkey;
	}


	public void setUploadviewkey(String uploadviewkey) {
		this.uploadviewkey = uploadviewkey;
	}


	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
