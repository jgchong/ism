package nfm.com.skd.service;

public class Skd010VO {
    private int skd010id;
    private String itemcode;
    private String itemname;


    private String itemAllprice;
    private String itemAllbuyprice;
    private Integer itembuyprice;


    private String itemea;
    private String createdate;
    private String expirationdate;
    private String itemdlprice;
    private String whs1itemea = "0";

    private String whs1itemname = "-";
    private String whs2itemea = "0";
    private String whs2itemname = "-";
    private String whs3itemea = "0";
    private String whs3itemname = "-";
    private String whs4itemea = "0";
    private String whs4itemname = "-";
    private String whsNamuge = "";


    public String getItemAllprice() {
        return itemAllprice;
    }

    public void setItemAllprice(String itemAllprice) {
        this.itemAllprice = itemAllprice;
    }

    public String getItemAllbuyprice() {
        return itemAllbuyprice;
    }

    public void setItemAllbuyprice(String itemAllbuyprice) {
        this.itemAllbuyprice = itemAllbuyprice;
    }

    public String getWhs1itemname() {
        return whs1itemname;
    }

    public void setWhs1itemname(String whs1itemname) {
        this.whs1itemname = whs1itemname;
    }

    public String getWhs2itemname() {
        return whs2itemname;
    }

    public void setWhs2itemname(String whs2itemname) {
        this.whs2itemname = whs2itemname;
    }

    public String getWhs3itemname() {
        return whs3itemname;
    }

    public void setWhs3itemname(String whs3itemname) {
        this.whs3itemname = whs3itemname;
    }

    public String getWhs4itemname() {
        return whs4itemname;
    }

    public void setWhs4itemname(String whs4itemname) {
        this.whs4itemname = whs4itemname;
    }


    public int getSkd010id() {
        return skd010id;
    }

    public void setSkd010id(int skd010id) {
        this.skd010id = skd010id;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemea() {
        return itemea;
    }

    public void setItemea(String itemea) {
        this.itemea = itemea;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(String expirationdate) {
        this.expirationdate = expirationdate;
    }

    public String getItemdlprice() {
        return itemdlprice;
    }

    public void setItemdlprice(String itemdlprice) {
        this.itemdlprice = itemdlprice;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Integer getItembuyprice() {
        return itembuyprice;
    }

    public void setItembuyprice(Integer itembuyprice) {
        this.itembuyprice = itembuyprice;
    }

    public String getWhs1itemea() {
        return whs1itemea;
    }

    public void setWhs1itemea(String whs1itemea) {
        this.whs1itemea = whs1itemea;
    }


    public String getWhs2itemea() {
        return whs2itemea;
    }

    public void setWhs2itemea(String whs2itemea) {
        this.whs2itemea = whs2itemea;
    }


    public String getWhs3itemea() {
        return whs3itemea;
    }

    public void setWhs3itemea(String whs3itemea) {
        this.whs3itemea = whs3itemea;
    }

    public String getWhs4itemea() {
        return whs4itemea;
    }

    public void setWhs4itemea(String whs4itemea) {
        this.whs4itemea = whs4itemea;
    }

    public String getWhsNamuge() {
        return whsNamuge;
    }

    public void setWhsNamuge(String whsNamuge) {
        this.whsNamuge = whsNamuge;
    }
}
