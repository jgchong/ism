package nfm.com.adj.service;

public class Adj030Result {


    private String no;
    private String itemcode;
    private String itemname;
    private Integer itemeaEx = 0;
    private Integer itemeaAll;
    private Integer itemeaOut;
    private Integer itemeaBroken;
    private Integer itemeaNamuge;

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Integer getItemeaEx() {
        return itemeaEx;
    }

    public void setItemeaEx(Integer itemeaEx) {
        this.itemeaEx = itemeaEx;
    }

    public Integer getItemeaAll() {
        return itemeaAll;
    }

    public void setItemeaAll(Integer itemeaAll) {
        this.itemeaAll = itemeaAll;
    }

    public Integer getItemeaOut() {
        return itemeaOut;
    }

    public void setItemeaOut(Integer itemeaOut) {
        this.itemeaOut = itemeaOut;
    }

    public Integer getItemeaBroken() {
        return itemeaBroken;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setItemeaBroken(Integer itemeaBroken) {
        this.itemeaBroken = itemeaBroken;
    }

    public Integer getItemeaNamuge() {
        return itemeaNamuge;
    }

    public void setItemeaNamuge(Integer itemeaNamuge) {
        this.itemeaNamuge = itemeaNamuge;
    }
}
