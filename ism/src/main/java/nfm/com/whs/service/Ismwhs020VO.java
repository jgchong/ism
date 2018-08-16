package nfm.com.whs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismwhs020VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int whs020id = 0;
	private int whs010id = 0;
	private String whsusername = "";
	private String whsusertel = "";
	private String whsuseremail = "";
	private String whsmemo = "";
	
	public int getWhs020id() {
		return whs020id;
	}
	public void setWhs020id(int whs020id) {
		this.whs020id = whs020id;
	}
	public int getWhs010id() {
		return whs010id;
	}
	public void setWhs010id(int whs010id) {
		this.whs010id = whs010id;
	}
	public String getWhsusername() {
		return whsusername;
	}
	public void setWhsusername(String whsusername) {
		this.whsusername = whsusername;
	}
	public String getWhsusertel() {
		return whsusertel;
	}
	public void setWhsusertel(String whsusertel) {
		this.whsusertel = whsusertel;
	}
	public String getWhsuseremail() {
		return whsuseremail;
	}
	public void setWhsuseremail(String whsuseremail) {
		this.whsuseremail = whsuseremail;
	}
	public String getWhsmemo() {
		return whsmemo;
	}
	public void setWhsmemo(String whsmemo) {
		this.whsmemo = whsmemo;
	}
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
