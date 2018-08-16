package nfm.com.cum.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismcum010VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

    private String cum010id = "";
    private String cono = "";
    private String lawcono = "";
    private String coname = "";
    private String cotype1 = "";
    private String cotype2 = "";
    private String cotype3 = "";
    private String cotype1nm = "";
    private String cotype2nm = "";
    private String cotype3nm = "";
    private String cogubun = "";
    private String coaddr = "";
    private String cobustype = "";
    private String account = "";
    private String accountamt = "";
    private String useyn = "";
    private String attachname = "";
    private String shopmallname = "";
    private String uploadtype = "";
    private String shopuseyn = "";
    private String account2 = "";
    private String cmm020id = "";
    private String orgfilename = "";
    private String savefilename = "";
    private String cogubunnm = "";

	public String getCum010id() {
		return cum010id;
	}


	public void setCum010id(String cum010id) {
		this.cum010id = cum010id;
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


	public String getConame() {
		return coname;
	}


	public void setConame(String coname) {
		this.coname = coname;
	}


	public String getCotype1() {
		return cotype1;
	}


	public void setCotype1(String cotype1) {
		this.cotype1 = cotype1;
	}


	public String getCotype2() {
		return cotype2;
	}


	public void setCotype2(String cotype2) {
		this.cotype2 = cotype2;
	}


	public String getCotype3() {
		return cotype3;
	}


	public void setCotype3(String cotype3) {
		this.cotype3 = cotype3;
	}


	public String getCotype1nm() {
		return cotype1nm;
	}


	public void setCotype1nm(String cotype1nm) {
		this.cotype1nm = cotype1nm;
	}


	public String getCotype2nm() {
		return cotype2nm;
	}


	public void setCotype2nm(String cotype2nm) {
		this.cotype2nm = cotype2nm;
	}


	public String getCotype3nm() {
		return cotype3nm;
	}


	public void setCotype3nm(String cotype3nm) {
		this.cotype3nm = cotype3nm;
	}


	public String getCogubun() {
		return cogubun;
	}


	public void setCogubun(String cogubun) {
		this.cogubun = cogubun;
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


	public String getAccountamt() {
		return accountamt;
	}


	public void setAccountamt(String accountamt) {
		this.accountamt = accountamt;
	}


	public String getUseyn() {
		return useyn;
	}


	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}


	public String getAttachname() {
		return attachname;
	}


	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}


	public String getShopmallname() {
		return shopmallname;
	}


	public void setShopmallname(String shopmallname) {
		this.shopmallname = shopmallname;
	}


	public String getUploadtype() {
		return uploadtype;
	}


	public void setUploadtype(String uploadtype) {
		this.uploadtype = uploadtype;
	}


	public String getShopuseyn() {
		return shopuseyn;
	}


	public void setShopuseyn(String shopuseyn) {
		this.shopuseyn = shopuseyn;
	}


	public String getAccount2() {
		return account2;
	}


	public void setAccount2(String account2) {
		this.account2 = account2;
	}


	public String getCmm020id() {
		return cmm020id;
	}


	public void setCmm020id(String cmm020id) {
		this.cmm020id = cmm020id;
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


	public String getCogubunnm() {
		return cogubunnm;
	}


	public void setCogubunnm(String cogubunnm) {
		this.cogubunnm = cogubunnm;
	}


	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
