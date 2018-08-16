package nfm.com.main.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MainGraphYearVO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

    private String[] prod1;
    private String[] prod2;
    private String[] prod3;
    private String[] prod4;
    private String[] prod5;
    private String[] amt;

	public String[] getProd1() {
		return prod1;
	}

	public void setProd1(String[] prod1) {
		this.prod1 = prod1;
	}

	public String[] getProd2() {
		return prod2;
	}

	public void setProd2(String[] prod2) {
		this.prod2 = prod2;
	}

	public String[] getProd3() {
		return prod3;
	}

	public void setProd3(String[] prod3) {
		this.prod3 = prod3;
	}

	public String[] getProd4() {
		return prod4;
	}

	public void setProd4(String[] prod4) {
		this.prod4 = prod4;
	}

	public String[] getProd5() {
		return prod5;
	}

	public void setProd5(String[] prod5) {
		this.prod5 = prod5;
	}

	public String[] getAmt() {
		return amt;
	}

	public void setAmt(String[] amt) {
		this.amt = amt;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
