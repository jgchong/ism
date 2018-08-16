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
	private int accontdate = 0;
	private int cmm020id = 0;

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

	public int getAccontdate() {
		return accontdate;
	}

	public void setAccontdate(int accontdate) {
		this.accontdate = accontdate;
	}

	public int getCmm020id() {
		return cmm020id;
	}

	public void setCmm020id(int cmm020id) {
		this.cmm020id = cmm020id;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
