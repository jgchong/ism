package nfm.com.po.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismpol010VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	private int pol010id = 0;
	private int poo010id = 0;
	private String pocotype = "";
	private String uploaddate = "";

	public int getPol010id() {
		return pol010id;
	}

	public void setPol010id(int pol010id) {
		this.pol010id = pol010id;
	}

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

	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
