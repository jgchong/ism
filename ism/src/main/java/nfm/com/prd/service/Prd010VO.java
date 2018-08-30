package nfm.com.prd.service;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class Prd010VO {

    private String listNo = "ㄴ";
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
    private Integer cartonqty;
    private Integer palletqty;
    private String crossitemcode;
    private String bycname;
    private String whsname;

    public String getTaxfree() {
        return taxfree;
    }

    public void setTaxfree(String taxfree) {
        this.taxfree = taxfree;
    }

    private String taxfree;

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
        if (StringUtils.isBlank(itemname)) {
            return "-";
        }
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

    public Integer getCartonqty() {
        return cartonqty;
    }

    public void setCartonqty(Integer cartonqty) {
        this.cartonqty = cartonqty;
    }

    public Integer getPalletqty() {
        return palletqty;
    }

    public void setPalletqty(Integer palletqty) {
        this.palletqty = palletqty;
    }

    public String getCrossitemcode() {
        return crossitemcode;
    }

    public void setCrossitemcode(String crossitemcode) {
        this.crossitemcode = crossitemcode;
    }

    public String getBycname() {
        if (StringUtils.isBlank(bycname)) {
            return "-";
        }
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

}
