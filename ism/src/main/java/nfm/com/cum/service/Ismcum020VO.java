package nfm.com.cum.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismcum020VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int cum020id = 0;
	private int cum010id = 0;
	private String cumusername = "";
	private String cumusertel = "";
	private String cumuseremail = "";
	private String cummemo = "";


	public int getCum020id() {
		return cum020id;
	}


	public void setCum020id(int cum020id) {
		this.cum020id = cum020id;
	}


	public int getCum010id() {
		return cum010id;
	}


	public void setCum010id(int cum010id) {
		this.cum010id = cum010id;
	}


	public String getCumusername() {
		return cumusername;
	}


	public void setCumusername(String cumusername) {
		this.cumusername = cumusername;
	}


	public String getCumusertel() {
		return cumusertel;
	}


	public void setCumusertel(String cumusertel) {
		this.cumusertel = cumusertel;
	}


	public String getCumuseremail() {
		return cumuseremail;
	}


	public void setCumuseremail(String cumuseremail) {
		this.cumuseremail = cumuseremail;
	}


	public String getCummemo() {
		return cummemo;
	}


	public void setCummemo(String cummemo) {
		this.cummemo = cummemo;
	}


	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
