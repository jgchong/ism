package nfm.com.byc.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BycAllVO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

    private int byc010id = -1;
    private String bycname = "";
    private String byctype = "";
    private String cogubun = "";
    private String cono = "";
    private String lawcono = "";
    private String cobustype = "";
    private String coaddr = "";
    private String account = "";
    private String accountno = "";
    private String useyn = "";
    private String uploadgubun = "";
    private String attachname = "";
    private String byccode = "";
    private String[] bycusername;
    private String[] bycusertel;
    private String[] bycuseremail;
    private String[] bycmemo;
    private String[] bycurl;
    private String[] bycuid;
    private String[] bycpwd;
    
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
	public String getByctype() {
		return byctype;
	}
	public void setByctype(String byctype) {
		this.byctype = byctype;
	}
	public String getCogubun() {
		return cogubun;
	}
	public void setCogubun(String cogubun) {
		this.cogubun = cogubun;
	}
	public String getCono() {
		return cono;
	}
	public void setCono(String cono) {
		this.cono = cono;
	}
	public String getLawcono() {
		return lawcono;
	}
	public void setLawcono(String lawcono) {
		this.lawcono = lawcono;
	}
	public String getCobustype() {
		return cobustype;
	}
	public void setCobustype(String cobustype) {
		this.cobustype = cobustype;
	}
	public String getCoaddr() {
		return coaddr;
	}
	public void setCoaddr(String coaddr) {
		this.coaddr = coaddr;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	public String getUploadgubun() {
		return uploadgubun;
	}
	public void setUploadgubun(String uploadgubun) {
		this.uploadgubun = uploadgubun;
	}
	public String getAttachname() {
		return attachname;
	}
	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}
	public String getByccode() {
		return byccode;
	}
	public void setByccode(String byccode) {
		this.byccode = byccode;
	}
	public String[] getBycusername() {
		return bycusername;
	}
	public void setBycusername(String[] bycusername) {
		this.bycusername = bycusername;
	}
	public String[] getBycusertel() {
		return bycusertel;
	}
	public void setBycusertel(String[] bycusertel) {
		this.bycusertel = bycusertel;
	}
	public String[] getBycuseremail() {
		return bycuseremail;
	}
	public void setBycuseremail(String[] bycuseremail) {
		this.bycuseremail = bycuseremail;
	}
	public String[] getBycmemo() {
		return bycmemo;
	}
	public void setBycmemo(String[] bycmemo) {
		this.bycmemo = bycmemo;
	}
	public String[] getBycurl() {
		return bycurl;
	}
	public void setBycurl(String[] bycurl) {
		this.bycurl = bycurl;
	}
	public String[] getBycuid() {
		return bycuid;
	}
	public void setBycuid(String[] bycuid) {
		this.bycuid = bycuid;
	}
	public String[] getBycpwd() {
		return bycpwd;
	}
	public void setBycpwd(String[] bycpwd) {
		this.bycpwd = bycpwd;
	}
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
