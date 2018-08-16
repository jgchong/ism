package nfm.com.byc.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismbyc020VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int byc020id = 0;
	private int byc010id = 0;
	private String bycusername = "";
	private String bycusertel = "";
	private String bycuseremail = "";
	private String bycmemo = "";

	public int getByc020id() {
		return byc020id;
	}
	public void setByc020id(int byc020id) {
		this.byc020id = byc020id;
	}
	public int getByc010id() {
		return byc010id;
	}
	public void setByc010id(int byc010id) {
		this.byc010id = byc010id;
	}
	public String getBycusername() {
		return bycusername;
	}
	public void setBycusername(String bycusername) {
		this.bycusername = bycusername;
	}
	public String getBycusertel() {
		return bycusertel;
	}
	public void setBycusertel(String bycusertel) {
		this.bycusertel = bycusertel;
	}
	public String getBycuseremail() {
		return bycuseremail;
	}
	public void setBycuseremail(String bycuseremail) {
		this.bycuseremail = bycuseremail;
	}
	public String getBycmemo() {
		return bycmemo;
	}
	public void setBycmemo(String bycmemo) {
		this.bycmemo = bycmemo;
	}
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
