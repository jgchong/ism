package nfm.com.whs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class WhsAllVO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

    private int whs010id = -1;
    private String whsgubun = "";
    private String whsname = "";
    private String whscotype = "";
    private String whscono = "";
    private String whslawcono = "";
    private String whsadress = "";
    private String whsbustype = "";
    private String accontdate = "";
    private String useyn = "";
    private String[] whsusername;
    private String[] whsusertel;
    private String[] whsuseremail;
    private String[] whsmemo;
    private String[] whsurl;
    private String[] whsuid;
    private String[] whspwd;
    
	public int getWhs010id() {
		return whs010id;
	}

	public void setWhs010id(int whs010id) {
		this.whs010id = whs010id;
	}

	public String getWhsgubun() {
		return whsgubun;
	}

	public void setWhsgubun(String whsgubun) {
		this.whsgubun = whsgubun;
	}

	public String getWhsname() {
		return whsname;
	}

	public void setWhsname(String whsname) {
		this.whsname = whsname;
	}

	public String getWhscotype() {
		return whscotype;
	}

	public void setWhscotype(String whscotype) {
		this.whscotype = whscotype;
	}

	public String getWhscono() {
		return whscono;
	}

	public void setWhscono(String whscono) {
		this.whscono = whscono;
	}

	public String getWhslawcono() {
		return whslawcono;
	}

	public void setWhslawcono(String whslawcono) {
		this.whslawcono = whslawcono;
	}

	public String getWhsadress() {
		return whsadress;
	}

	public void setWhsadress(String whsadress) {
		this.whsadress = whsadress;
	}

	public String getWhsbustype() {
		return whsbustype;
	}

	public void setWhsbustype(String whsbustype) {
		this.whsbustype = whsbustype;
	}

	public String getAccontdate() {
		return accontdate;
	}

	public void setAccontdate(String accontdate) {
		this.accontdate = accontdate;
	}

	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}

	public String[] getWhsusername() {
		return whsusername;
	}

	public void setWhsusername(String[] whsusername) {
		this.whsusername = whsusername;
	}

	public String[] getWhsusertel() {
		return whsusertel;
	}

	public void setWhsusertel(String[] whsusertel) {
		this.whsusertel = whsusertel;
	}

	public String[] getWhsuseremail() {
		return whsuseremail;
	}

	public void setWhsuseremail(String[] whsuseremail) {
		this.whsuseremail = whsuseremail;
	}

	public String[] getWhsmemo() {
		return whsmemo;
	}

	public void setWhsmemo(String[] whsmemo) {
		this.whsmemo = whsmemo;
	}

	public String[] getWhsurl() {
		return whsurl;
	}

	public void setWhsurl(String[] whsurl) {
		this.whsurl = whsurl;
	}

	public String[] getWhsuid() {
		return whsuid;
	}

	public void setWhsuid(String[] whsuid) {
		this.whsuid = whsuid;
	}

	public String[] getWhspwd() {
		return whspwd;
	}

	public void setWhspwd(String[] whspwd) {
		this.whspwd = whspwd;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
