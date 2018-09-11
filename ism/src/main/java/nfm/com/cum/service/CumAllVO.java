package nfm.com.cum.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CumAllVO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

    private int cum010id = -1;
    private String coname = "";
    private String cotype1 = "";
    private String cotype2 = "";
    private String cotype3 = "";
    private String cogubun = "";
    private String cono = "";
    private String lawcono = "";
    private String cobustype = "";
    private String coaddr = "";
    private String account = "";
    private String account2 = "";
    private String accountamt = "";
    private String accountamtdate = "";
    private String useyn = "";
    private String attachname = "";
    private int cmm020id = 0;
    private String[] cumusername;
    private String[] cumusertel;
    private String[] cumuseremail;
    private String[] cummemo;
    private String[] shopmallname;
    private String[] shopurl;
    private String[] shopuid;
    private String[] shoppwd;
    private String[] uploadtype;
    private String[] uploadgubun;
    private String[] shopUseYn;


	public int getCum010id() {
		return cum010id;
	}


	public void setCum010id(int cum010id) {
		this.cum010id = cum010id;
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


	public String getAccount2() {
		return account2;
	}


	public void setAccount2(String account2) {
		this.account2 = account2;
	}


	public String getAccountamt() {
		return accountamt;
	}


	public void setAccountamt(String accountamt) {
		this.accountamt = accountamt;
	}


	public String getAccountamtdate() {
		return accountamtdate;
	}


	public void setAccountamtdate(String accountamtdate) {
		this.accountamtdate = accountamtdate;
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


	public int getCmm020id() {
		return cmm020id;
	}


	public void setCmm020id(int cmm020id) {
		this.cmm020id = cmm020id;
	}


	public String[] getCumusername() {
		return cumusername;
	}


	public void setCumusername(String[] cumusername) {
		this.cumusername = cumusername;
	}


	public String[] getCumusertel() {
		return cumusertel;
	}


	public void setCumusertel(String[] cumusertel) {
		this.cumusertel = cumusertel;
	}


	public String[] getCumuseremail() {
		return cumuseremail;
	}


	public void setCumuseremail(String[] cumuseremail) {
		this.cumuseremail = cumuseremail;
	}


	public String[] getCummemo() {
		return cummemo;
	}


	public void setCummemo(String[] cummemo) {
		this.cummemo = cummemo;
	}


	public String[] getShopmallname() {
		return shopmallname;
	}


	public void setShopmallname(String[] shopmallname) {
		this.shopmallname = shopmallname;
	}


	public String[] getShopurl() {
		return shopurl;
	}


	public void setShopurl(String[] shopurl) {
		this.shopurl = shopurl;
	}


	public String[] getShopuid() {
		return shopuid;
	}


	public void setShopuid(String[] shopuid) {
		this.shopuid = shopuid;
	}


	public String[] getShoppwd() {
		return shoppwd;
	}


	public void setShoppwd(String[] shoppwd) {
		this.shoppwd = shoppwd;
	}


	public String[] getUploadtype() {
		return uploadtype;
	}


	public void setUploadtype(String[] uploadtype) {
		this.uploadtype = uploadtype;
	}


	public String[] getUploadgubun() {
		return uploadgubun;
	}


	public void setUploadgubun(String[] uploadgubun) {
		this.uploadgubun = uploadgubun;
	}


	public String[] getShopUseYn() {
		return shopUseYn;
	}


	public void setShopUseYn(String[] shopUseYn) {
		this.shopUseYn = shopUseYn;
	}


	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
