package nfm.com.skd.service;

public class Skd030VO {
    private String itemcode;
    private int skd010type;
    private int sourcewhs010id;
    private int destinationwhs010id;
    private int itemea;
    private String createdate;
    private String expirationdate;
    private String sourcewhsname;
    private String destinationwhsname;
    private String itemname;
    private Integer itembuyprice;
    private Integer itemdlprice;

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public int getSkd010type() {
        return skd010type;
    }

    public void setSkd010type(int skd010type) {
        this.skd010type = skd010type;
    }

    public int getSourcewhs010id() {
        return sourcewhs010id;
    }

    public void setSourcewhs010id(int sourcewhs010id) {
        this.sourcewhs010id = sourcewhs010id;
    }

    public int getDestinationwhs010id() {
        return destinationwhs010id;
    }

    public void setDestinationwhs010id(int destinationwhs010id) {
        this.destinationwhs010id = destinationwhs010id;
    }

    public int getItemea() {
        return itemea;
    }

    public void setItemea(int itemea) {
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

    public String getSourcewhsname() {
        return sourcewhsname;
    }

    public void setSourcewhsname(String sourcewhsname) {
        this.sourcewhsname = sourcewhsname;
    }

    public String getDestinationwhsname() {
        return destinationwhsname;
    }

    public void setDestinationwhsname(String destinationwhsname) {
        this.destinationwhsname = destinationwhsname;
    }

    public Integer getItembuyprice() {
        return itembuyprice;
    }

    public void setItembuyprice(Integer itembuyprice) {
        this.itembuyprice = itembuyprice;
    }

    public Integer getItemdlprice() {
        return itemdlprice;
    }

    public void setItemdlprice(Integer itemdlprice) {
        this.itemdlprice = itemdlprice;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
