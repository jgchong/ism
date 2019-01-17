package nfm.com.whs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismwhs010VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int whs010id = 0;
	private String whsgubun = "";
	private String whsname = "";
	private String whscotype = "";
	private String whscono = "";
	private String whslawcono = "";
	private String whsadress = "";
	private String whsbustype = "";
	private String accontdate = "";
	private int cmm020id = 0;
	private String whsgubunnm = "";
	private String whscotypenm = "";
	private String orgfilename = "";
	private String savefilename = "";
	private String useyn = "";
	private String receivetype = "";
	private String uploaddate = "";
	private int pocnt = 0;

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

	public int getCmm020id() {
		return cmm020id;
	}

	public void setCmm020id(int cmm020id) {
		this.cmm020id = cmm020id;
	}

	public String getWhsgubunnm() {
		return whsgubunnm;
	}

	public void setWhsgubunnm(String whsgubunnm) {
		this.whsgubunnm = whsgubunnm;
	}

	public String getWhscotypenm() {
		return whscotypenm;
	}

	public void setWhscotypenm(String whscotypenm) {
		this.whscotypenm = whscotypenm;
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

	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}

	public String getReceivetype() {
		return receivetype;
	}

	public void setReceivetype(String receivetype) {
		this.receivetype = receivetype;
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
