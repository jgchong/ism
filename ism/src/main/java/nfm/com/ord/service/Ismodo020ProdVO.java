package nfm.com.ord.service;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismodo020ProdVO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건*/
	private String listNo = "";
    private int orderitemid;
    private String itemcat1;
    private String itemname;
    private String itemcrosstype;
    private int byc010id;
    private String itemopt;
    private Integer itemea;
    private Integer itemprice;
    private Integer itembuyprice;
    private String taxtype;
    private Integer itemdlvprice;
    private Integer itembuydlvprice;
    private String itemgubun;
    private Date createdate;
    private String byccode;
    private String itemcode;
    private Integer pristock;
    private String itemsize;
    private String cartonqty;
    private String palletqty;
    private String crossitemcode;
    private String bycname;
    private String whsname;
    private String childItemea;
    private String salecode;
    
	public String getListNo() {
		return listNo;
	}

	public void setListNo(String listNo) {
		this.listNo = listNo;
	}

	public int getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(int orderitemid) {
		this.orderitemid = orderitemid;
	}

	public String getItemcat1() {
		return itemcat1;
	}

	public void setItemcat1(String itemcat1) {
		this.itemcat1 = itemcat1;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemcrosstype() {
		return itemcrosstype;
	}

	public void setItemcrosstype(String itemcrosstype) {
		this.itemcrosstype = itemcrosstype;
	}

	public int getByc010id() {
		return byc010id;
	}

	public void setByc010id(int byc010id) {
		this.byc010id = byc010id;
	}

	public String getItemopt() {
		return itemopt;
	}

	public void setItemopt(String itemopt) {
		this.itemopt = itemopt;
	}

	public Integer getItemea() {
		return itemea;
	}

	public void setItemea(Integer itemea) {
		this.itemea = itemea;
	}

	public Integer getItemprice() {
		return itemprice;
	}

	public void setItemprice(Integer itemprice) {
		this.itemprice = itemprice;
	}

	public Integer getItembuyprice() {
		return itembuyprice;
	}

	public void setItembuyprice(Integer itembuyprice) {
		this.itembuyprice = itembuyprice;
	}

	public String getTaxtype() {
		return taxtype;
	}

	public void setTaxtype(String taxtype) {
		this.taxtype = taxtype;
	}

	public Integer getItemdlvprice() {
		return itemdlvprice;
	}

	public void setItemdlvprice(Integer itemdlvprice) {
		this.itemdlvprice = itemdlvprice;
	}

	public Integer getItembuydlvprice() {
		return itembuydlvprice;
	}

	public void setItembuydlvprice(Integer itembuydlvprice) {
		this.itembuydlvprice = itembuydlvprice;
	}

	public String getItemgubun() {
		return itemgubun;
	}

	public void setItemgubun(String itemgubun) {
		this.itemgubun = itemgubun;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getByccode() {
		return byccode;
	}

	public void setByccode(String byccode) {
		this.byccode = byccode;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public Integer getPristock() {
		return pristock;
	}

	public void setPristock(Integer pristock) {
		this.pristock = pristock;
	}

	public String getItemsize() {
		return itemsize;
	}

	public void setItemsize(String itemsize) {
		this.itemsize = itemsize;
	}

	public String getCartonqty() {
		return cartonqty;
	}

	public void setCartonqty(String cartonqty) {
		this.cartonqty = cartonqty;
	}

	public String getPalletqty() {
		return palletqty;
	}

	public void setPalletqty(String palletqty) {
		this.palletqty = palletqty;
	}

	public String getCrossitemcode() {
		return crossitemcode;
	}

	public void setCrossitemcode(String crossitemcode) {
		this.crossitemcode = crossitemcode;
	}

	public String getBycname() {
		return bycname;
	}

	public void setBycname(String bycname) {
		this.bycname = bycname;
	}

	public String getWhsname() {
		return whsname;
	}

	public void setWhsname(String whsname) {
		this.whsname = whsname;
	}

	public String getChildItemea() {
		return childItemea;
	}

	public void setChildItemea(String childItemea) {
		this.childItemea = childItemea;
	}

	public String getSalecode() {
		return salecode;
	}

	public void setSalecode(String salecode) {
		this.salecode = salecode;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
