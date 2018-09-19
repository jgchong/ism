package nfm.com.adj.model;

public class Adj040Result {
    public int getCum010id() {
        return cum010id;
    }

    public void setCum010id(int cum010id) {
        this.cum010id = cum010id;
    }

    public String getClosedt() {
        return closedt;
    }

    public void setClosedt(String closedt) {
        this.closedt = closedt;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTaxprice() {
        return taxprice;
    }

    public void setTaxprice(String taxprice) {
        this.taxprice = taxprice;
    }

    public String getTaxfreeprice() {
        return taxfreeprice;
    }

    public void setTaxfreeprice(String taxfreeprice) {
        this.taxfreeprice = taxfreeprice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSusuprice() {
        return susuprice;
    }

    public void setSusuprice(String susuprice) {
        this.susuprice = susuprice;
    }

    public String getNamuge() {
        return namuge;
    }

    public void setNamuge(String namuge) {
        this.namuge = namuge;
    }

    public String getSugumdate() {
        return sugumdate;
    }

    public void setSugumdate(String sugumdate) {
        this.sugumdate = sugumdate;
    }

    public String getSugumprice() {
        return sugumprice;
    }

    public void setSugumprice(String sugumprice) {
        this.sugumprice = sugumprice;
    }

    public Long getGivesusuprice() {
        if (givesusuprice == null) {
            givesusuprice = 0L;
        }
        return givesusuprice;
    }

    public void setGivesusuprice(Long givesusuprice) {
        this.givesusuprice = givesusuprice;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public Long getExprice() {
        if (exprice == null) {
            exprice = 0L;
        }
        return exprice;
    }

    public void setExprice(Long exprice) {
        this.exprice = exprice;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private int cum010id;

    public String getCum010name() {
        return cum010name;
    }

    public void setCum010name(String cum010name) {
        this.cum010name = cum010name;
    }

    private String cum010name;
    private String closedt;
    private String account;
    private String taxprice;
    private String taxfreeprice;
    private Integer price;
    private String susuprice;
    private String namuge;
    private String sugumdate;
    private String sugumprice;

    public Long getSaleprice() {
        if (saleprice == null) {
            saleprice = 0L;
        }
        return saleprice;
    }

    public void setSaleprice(Long saleprice) {
        this.saleprice = saleprice;
    }

    private Long saleprice;
    private Long givesusuprice;
    private String policy;
    private Long exprice;
    private String memo;
}
