package nfm.com.po.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Po010SaveVO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int poo010id = 0;
	private String pocotype = "";
	private String userList = "";
	private String ccUserList = "";
	private String mailSubject = "";
	private String mailText = "";
	private String ordermemo = "";
	private String addorderuser = "N";
	private String receivetype = "";

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

	public String getUserList() {
		return userList;
	}

	public void setUserList(String userList) {
		this.userList = userList;
	}

	public String getCcUserList() {
		return ccUserList;
	}

	public void setCcUserList(String ccUserList) {
		this.ccUserList = ccUserList;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailText() {
		return mailText;
	}

	public void setMailText(String mailText) {
		this.mailText = mailText;
	}

	public String getOrdermemo() {
		return ordermemo;
	}

	public void setOrdermemo(String ordermemo) {
		this.ordermemo = ordermemo;
	}

	public String getAddorderuser() {
		return addorderuser;
	}

	public void setAddorderuser(String addorderuser) {
		this.addorderuser = addorderuser;
	}

	public String getReceivetype() {
		return receivetype;
	}

	public void setReceivetype(String receivetype) {
		this.receivetype = receivetype;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
