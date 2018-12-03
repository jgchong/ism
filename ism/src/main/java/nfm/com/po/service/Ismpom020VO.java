package nfm.com.po.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismpom020VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int sortNo;
	private String processdate;
	private int byc010id;
	private String bycname;
	private String rcvuser;
	private String orderuser;
	private String uploadfilename;
	private String status;
	private String rcvuseremail;
	private String receiveType;

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public String getProcessdate() {
		return processdate;
	}

	public void setProcessdate(String processdate) {
		this.processdate = processdate;
	}
	
	public int getByc010id() {
		return byc010id;
	}

	public void setByc010id(int byc010id) {
		this.byc010id = byc010id;
	}

	public String getBycname() {
		return bycname;
	}

	public void setBycname(String bycname) {
		this.bycname = bycname;
	}

	public String getRcvuser() {
		return rcvuser;
	}

	public void setRcvuser(String rcvuser) {
		this.rcvuser = rcvuser;
	}

	public String getOrderuser() {
		return orderuser;
	}

	public void setOrderuser(String orderuser) {
		this.orderuser = orderuser;
	}

	public String getUploadfilename() {
		return uploadfilename;
	}

	public void setUploadfilename(String uploadfilename) {
		this.uploadfilename = uploadfilename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 

	public String getRcvuseremail() {
		return rcvuseremail;
	}

	public void setRcvuseremail(String rcvuseremail) {
		this.rcvuseremail = rcvuseremail;
	}  
	
	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}
	
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
    
}
