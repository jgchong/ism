package nfm.com.cmm.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class IsmCmm010VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건*/
    private int cmm010id = 0;	    
    private String buss_key = "";    
    private String buss_type = "";    
    private String memotext = "";
    private String createdt = "";
    private String createuserid = "";
    private String createusername = "";
    private String userphotosrc = "";

	public int getCmm010id() {
		return cmm010id;
	}

	public void setCmm010id(int cmm010id) {
		this.cmm010id = cmm010id;
	}

	public String getBuss_key() {
		return buss_key;
	}

	public void setBuss_key(String buss_key) {
		this.buss_key = buss_key;
	}

	public String getBuss_type() {
		return buss_type;
	}

	public void setBuss_type(String buss_type) {
		this.buss_type = buss_type;
	}

	public String getMemotext() {
		return memotext;
	}

	public void setMemotext(String memotext) {
		this.memotext = memotext;
	}

	public String getCreatedt() {
		return createdt;
	}

	public void setCreatedt(String createdt) {
		this.createdt = createdt;
	}

	public String getCreateuserid() {
		return createuserid;
	}

	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}

	public String getCreateusername() {
		return createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public String getUserphotosrc() {
		return userphotosrc;
	}

	public void setUserphotosrc(String userphotosrc) {
		this.userphotosrc = userphotosrc;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
