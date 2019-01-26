package nfm.com.ord.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ismodm010VO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 검색조건*/
    private int odm010id = 0;
    private String orderno = "";
    private String orderdate;
    private int cum010id = 0;
    private int cum030id = 0;
    private int byc010id = 0;
    private String orderuser = "";
    private String orderusercontact = "";
    private String rcvuser = "";
    private String rcvusercontact = "";
    private String orderitemid = "";
    private String orderitemopt = "";
    private String orderitemqty = "0";
    private String orderitembyprice = "";
    private String orderitemprice = "";
    private String postno = "";
    private String address = "";
    private String dlvmemo = "";
    private String dlvbyprice = "";
    private String dlvprice = "";
    private String dlvno = "";
    private String dlvco = "";
    private String dlvstatus = "";
    private String processdate = "";
    private String accountcum = "1";
    private String accountbuy = "1";
    private String status = "";
    private String cstype = "";
    private String uploadfilename = "";
    private String uploadviewkey = "";
    private String coname = "";
    private String code_nm = "";
    private String itemname = "";
    private String bycname = "";
    private String itemprice = "";
    private String itembuyprice = "";
    private String itemdlvprice = "";
    private String itembuydlvprice = "";
    private String claimstatus = "";
    private String claimreason = "";
    private String retstatus = "";
    private String retqty = "";
    private String retprice = "";
    private String orderitemname = "";
    private String ststusNm = "";
    private String shopmallname = "";
    private String cotype2nm = "";
    private String cotype3nm = "";
    private String claimstatusnm = "";
    private String claimreasonnm = "";
    private String retstatusnm = "";
    private String rcvusercontacthp = "";
    private String rcvuseremail = "";
    
    
    // LDC  없어서 추가함 & 신규 추가 
    private String chkprod = "";
    private String chkoverlap = "";
    private String updatedt = "";
    private String totcnt = "";
    private String succcnt = "";
    private String prodfailcnt = "";
    private String overlapcnt = "";
    private String itemcode = "";
    private String cumprodoptnm1 = "";
    private String cumprodoptnm2 = "";
    private String account2 = "";
    private String cumprodcode = "";
    
    private String regdate = "";
	private String byccode = "";
    
	public int getOdm010id() {
		return odm010id;
	}

	public void setOdm010id(int odm010id) {
		this.odm010id = odm010id;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public int getCum010id() {
		return cum010id;
	}

	public void setCum010id(int cum010id) {
		this.cum010id = cum010id;
	}

	public int getCum030id() {
		return cum030id;
	}

	public void setCum030id(int cum030id) {
		this.cum030id = cum030id;
	}

	public int getByc010id() {
		return byc010id;
	}

	public void setByc010id(int byc010id) {
		this.byc010id = byc010id;
	}

	public String getOrderuser() {
		return orderuser;
	}

	public void setOrderuser(String orderuser) {
		this.orderuser = orderuser;
	}

	public String getOrderusercontact() {
		return orderusercontact;
	}

	public void setOrderusercontact(String orderusercontact) {
		this.orderusercontact = orderusercontact;
	}

	public String getRcvuser() {
		return rcvuser;
	}

	public void setRcvuser(String rcvuser) {
		this.rcvuser = rcvuser;
	}

	public String getRcvusercontact() {
		return rcvusercontact;
	}

	public void setRcvusercontact(String rcvusercontact) {
		this.rcvusercontact = rcvusercontact;
	}

	public String getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(String orderitemid) {
		this.orderitemid = orderitemid;
	}

	public String getOrderitemopt() {
		return orderitemopt;
	}

	public void setOrderitemopt(String orderitemopt) {
		this.orderitemopt = orderitemopt;
	}

	public String getOrderitemqty() {
		return orderitemqty;
	}

	public void setOrderitemqty(String orderitemqty) {
		this.orderitemqty = orderitemqty;
	}

	public String getOrderitembyprice() {
		return orderitembyprice;
	}

	public void setOrderitembyprice(String orderitembyprice) {
		this.orderitembyprice = orderitembyprice;
	}

	public String getOrderitemprice() {
		return orderitemprice;
	}

	public void setOrderitemprice(String orderitemprice) {
		this.orderitemprice = orderitemprice;
	}

	public String getPostno() {
		return postno;
	}

	public void setPostno(String postno) {
		this.postno = postno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDlvmemo() {
		return dlvmemo;
	}

	public void setDlvmemo(String dlvmemo) {
		this.dlvmemo = dlvmemo;
	}

	public String getDlvbyprice() {
		return dlvbyprice;
	}

	public void setDlvbyprice(String dlvbyprice) {
		this.dlvbyprice = dlvbyprice;
	}

	public String getDlvprice() {
		return dlvprice;
	}

	public void setDlvprice(String dlvprice) {
		this.dlvprice = dlvprice;
	}

	public String getDlvno() {
		return dlvno;
	}

	public void setDlvno(String dlvno) {
		this.dlvno = dlvno;
	}

	public String getDlvco() {
		return dlvco;
	}

	public void setDlvco(String dlvco) {
		this.dlvco = dlvco;
	}

	public String getDlvstatus() {
		return dlvstatus;
	}

	public void setDlvstatus(String dlvstatus) {
		this.dlvstatus = dlvstatus;
	}

	public String getProcessdate() {
		return processdate;
	}

	public void setProcessdate(String processdate) {
		this.processdate = processdate;
	}

	public String getAccountcum() {
		return accountcum;
	}

	public void setAccountcum(String accountcum) {
		this.accountcum = accountcum;
	}

	public String getAccountbuy() {
		return accountbuy;
	}

	public void setAccountbuy(String accountbuy) {
		this.accountbuy = accountbuy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCstype() {
		return cstype;
	}

	public void setCstype(String cstype) {
		this.cstype = cstype;
	}

	public String getUploadfilename() {
		return uploadfilename;
	}

	public void setUploadfilename(String uploadfilename) {
		this.uploadfilename = uploadfilename;
	}

	public String getUploadviewkey() {
		return uploadviewkey;
	}

	public void setUploadviewkey(String uploadviewkey) {
		this.uploadviewkey = uploadviewkey;
	}

	public String getConame() {
		return coname;
	}

	public void setConame(String coname) {
		this.coname = coname;
	}

	public String getCode_nm() {
		return code_nm;
	}

	public void setCode_nm(String code_nm) {
		this.code_nm = code_nm;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getBycname() {
		return bycname;
	}

	public void setBycname(String bycname) {
		this.bycname = bycname;
	}

	public String getItemprice() {
		return itemprice;
	}

	public void setItemprice(String itemprice) {
		this.itemprice = itemprice;
	}

	public String getItembuyprice() {
		return itembuyprice;
	}

	public void setItembuyprice(String itembuyprice) {
		this.itembuyprice = itembuyprice;
	}

	public String getItemdlvprice() {
		return itemdlvprice;
	}

	public void setItemdlvprice(String itemdlvprice) {
		this.itemdlvprice = itemdlvprice;
	}

	public String getItembuydlvprice() {
		return itembuydlvprice;
	}

	public void setItembuydlvprice(String itembuydlvprice) {
		this.itembuydlvprice = itembuydlvprice;
	}

	public String getClaimstatus() {
		return claimstatus;
	}

	public void setClaimstatus(String claimstatus) {
		this.claimstatus = claimstatus;
	}

	public String getClaimreason() {
		return claimreason;
	}

	public void setClaimreason(String claimreason) {
		this.claimreason = claimreason;
	}

	public String getRetstatus() {
		return retstatus;
	}

	public void setRetstatus(String retstatus) {
		this.retstatus = retstatus;
	}

	public String getRetqty() {
		return retqty;
	}

	public void setRetqty(String retqty) {
		this.retqty = retqty;
	}

	public String getRetprice() {
		return retprice;
	}

	public void setRetprice(String retprice) {
		this.retprice = retprice;
	}

	public String getOrderitemname() {
		return orderitemname;
	}

	public void setOrderitemname(String orderitemname) {
		this.orderitemname = orderitemname;
	}

	public String getStstusNm() {
		return ststusNm;
	}

	public void setStstusNm(String ststusNm) {
		this.ststusNm = ststusNm;
	}

	public String getShopmallname() {
		return shopmallname;
	}

	public void setShopmallname(String shopmallname) {
		this.shopmallname = shopmallname;
	}

	public String getCotype2nm() {
		return cotype2nm;
	}

	public void setCotype2nm(String cotype2nm) {
		this.cotype2nm = cotype2nm;
	}

	public String getCotype3nm() {
		return cotype3nm;
	}

	public void setCotype3nm(String cotype3nm) {
		this.cotype3nm = cotype3nm;
	}

	public String getClaimstatusnm() {
		return claimstatusnm;
	}

	public void setClaimstatusnm(String claimstatusnm) {
		this.claimstatusnm = claimstatusnm;
	}

	public String getClaimreasonnm() {
		return claimreasonnm;
	}

	public void setClaimreasonnm(String claimreasonnm) {
		this.claimreasonnm = claimreasonnm;
	}

	public String getRetstatusnm() {
		return retstatusnm;
	}

	public void setRetstatusnm(String retstatusnm) {
		this.retstatusnm = retstatusnm;
	}

	public String getRcvusercontacthp() {
		return rcvusercontacthp;
	}

	public void setRcvusercontacthp(String rcvusercontacthp) {
		this.rcvusercontacthp = rcvusercontacthp;
	}

	public String getRcvuseremail() {
		return rcvuseremail;
	}

	public void setRcvuseremail(String rcvuseremail) {
		this.rcvuseremail = rcvuseremail;
	}

	public String getChkprod() {
		return chkprod;
	}

	public void setChkprod(String chkprod) {
		this.chkprod = chkprod;
	}

	public String getUpdatedt() {
		return updatedt;
	}

	public void setUpdatedt(String updatedt) {
		this.updatedt = updatedt;
	}

	public String getTotcnt() {
		return totcnt;
	}

	public void setTotcnt(String totcnt) {
		this.totcnt = totcnt;
	}

	public String getSucccnt() {
		return succcnt;
	}

	public void setSucccnt(String succcnt) {
		this.succcnt = succcnt;
	}

	public String getProdfailcnt() {
		return prodfailcnt;
	}

	public void setProdfailcnt(String prodfailcnt) {
		this.prodfailcnt = prodfailcnt;
	}

	public String getOverlapcnt() {
		return overlapcnt;
	}

	public void setOverlapcnt(String overlapcnt) {
		this.overlapcnt = overlapcnt;
	}

	public String getChkoverlap() {
		return chkoverlap;
	}

	public void setChkoverlap(String chkoverlap) {
		this.chkoverlap = chkoverlap;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	
	public String getCumprodoptnm1() {
		return cumprodoptnm1;
	}

	public void setCumprodoptnm1(String cumprodoptnm1) {
		this.cumprodoptnm1 = cumprodoptnm1;
	}

	public String getCumprodoptnm2() {
		return cumprodoptnm2;
	}

	public void setCumprodoptnm2(String cumprodoptnm2) {
		this.cumprodoptnm2 = cumprodoptnm2;
	}

	public String getAccount2() {
		return account2;
	}

	public void setAccount2(String account2) {
		this.account2 = account2;
	}
	
	public String getCumprodcode() {
		return cumprodcode;
	}

	public void setCumprodcode(String cumprodcode) {
		this.cumprodcode = cumprodcode;
	}
	
	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public String getByccode() {
		return byccode;
	}

	public void setByccode(String byccode) {
		this.byccode = byccode;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
