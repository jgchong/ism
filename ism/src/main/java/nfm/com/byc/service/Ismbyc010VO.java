package nfm.com.byc.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismbyc010VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int byc010id = 0;
	private String bycname = "";
	private String byctype = "";
	private String cogubun = "";
	private String cono = "";
	private String lawcono = "";
	private String coaddr = "";
	private String cobustype = "";
	private String account = "";
	private String accountno = "";
	private String useyn = "";
	private String purchaseform = "";
	private String attachname = "";
	private String byccode = "";
	private String uploadgubun = "";
	private String byctypenm = "";
	private String cogubunnm = "";
	private String orgfilename = "";
	private String savefilename = "";
	private int cmm020id = 0;
	private String uploaddate = "";
	private int pocnt = 0;

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

	public String getCoaddr() {
		return coaddr;
	}

	public void setCoaddr(String coaddr) {
		this.coaddr = coaddr;
	}

	public String getCobustype() {
		return cobustype;
	}

	public void setCobustype(String cobustype) {
		this.cobustype = cobustype;
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

	public String getPurchaseform() {
		return purchaseform;
	}

	public void setPurchaseform(String purchaseform) {
		this.purchaseform = purchaseform;
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

	public String getUploadgubun() {
		return uploadgubun;
	}

	public void setUploadgubun(String uploadgubun) {
		this.uploadgubun = uploadgubun;
	}

	public String getByctypenm() {
		return byctypenm;
	}

	public void setByctypenm(String byctypenm) {
		this.byctypenm = byctypenm;
	}

	public String getCogubunnm() {
		return cogubunnm;
	}

	public void setCogubunnm(String cogubunnm) {
		this.cogubunnm = cogubunnm;
	}

	public String getOrgfilename() {
		return orgfilename;
	}

	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}

	public String getSavefilename() {
		return savefilename;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	public int getCmm020id() {
		return cmm020id;
	}

	public void setCmm020id(int cmm020id) {
		this.cmm020id = cmm020id;
	}

	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}

	public int getPocnt() {
		return pocnt;
	}

	public void setPocnt(int pocnt) {
		this.pocnt = pocnt;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
