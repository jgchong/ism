package nfm.com.main.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismdbo010VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String emplyr_id = "";
    private String linegraph = "N";
    private String bargraph = "N";
    private String ipStatus = "N";
    private String otStatus = "N";
    private String prodStatus = "N";

	public String getEmplyr_id() {
		return emplyr_id;
	}

	public void setEmplyr_id(String emplyr_id) {
		this.emplyr_id = emplyr_id;
	}

	public String getLinegraph() {
		return linegraph;
	}

	public void setLinegraph(String linegraph) {
		this.linegraph = linegraph;
	}

	public String getBargraph() {
		return bargraph;
	}

	public void setBargraph(String bargraph) {
		this.bargraph = bargraph;
	}

	public String getIpStatus() {
		return ipStatus;
	}

	public void setIpStatus(String ipStatus) {
		this.ipStatus = ipStatus;
	}

	public String getOtStatus() {
		return otStatus;
	}

	public void setOtStatus(String otStatus) {
		this.otStatus = otStatus;
	}

	public String getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
