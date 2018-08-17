package nfm.com.prd.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Prd010DetailVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    public String getDfDetail_itemname() {
        return dfDetail_itemname;
    }

    public void setDfDetail_itemname(String dfDetail_itemname) {
        this.dfDetail_itemname = dfDetail_itemname;
    }
    public String getDfDetail_orderitemid() {
        return dfDetail_orderitemid;
    }

    public void setDfDetail_orderitemid(String dfDetail_orderitemid) {
        this.dfDetail_orderitemid = dfDetail_orderitemid;
    }

    private String dfDetail_itemname;

    private String dfDetail_orderitemid;
//    private String itemcrosstype;
//    private int byc010id;
//    private String itemopt;
//    private int itemea;
//    private int itemprice;
//    private int itembuyprice;
//    private String taxtype;
//    private int itemdlvprice;
//    private int itembuydlvprice;
//    private String itemgubun;
//    private String createdate;
//    private String byccode;
//    private String itemcode;


    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
