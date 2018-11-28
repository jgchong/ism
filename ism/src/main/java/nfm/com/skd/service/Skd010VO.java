package nfm.com.skd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skd010VO {
    private int skd010id;
    private String itemcode;
    private String itemname;

    private String itemea;
    private String itemAllprice;
    private String itemAllbuyprice;

    private Integer itembuyprice;
    private String createdate;
    private String expirationdate;
    private String itemdlprice;


    private int whs1id = 0;
    private String whs1itemea = "0";
    private String whs1itemname = "-";
    private int whs2id = 0;
    private String whs2itemea = "0";
    private String whs2itemname = "-";
    private int whs3id = 0;
    private String whs3itemea = "0";
    private String whs3itemname = "-";
    private int whs4id = 0;
    private String whs4itemea = "0";
    private String whs4itemname = "-";
    private String resultType;
    List<Skd020VO> namugeList = new ArrayList<>();
    Map<String, Integer> namugeMap = new HashMap<>();
    Map<String, String> namugeWhsNameMap = new HashMap<>();

    public Map<String, String> getNamugeWhsNameMap() {
        return namugeWhsNameMap;
    }

    public void setNamugeWhsNameMap(Map<String, String> namugeWhsNameMap) {
        this.namugeWhsNameMap = namugeWhsNameMap;
    }

    public Map<String, Integer> getNamugeMap() {
        return namugeMap;
    }

    public void setNamugeMap(Map<String, Integer> namugeMap) {
        this.namugeMap = namugeMap;
    }

    public List<Skd020VO> getNamugeList() {
        return namugeList;
    }

    public void setNamugeList(List<Skd020VO> namugeList) {
        this.namugeList = namugeList;
    }


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
        String namugeInfo = "";
        for (String stringKeyset : getNamugeMap().keySet()) {
            int skd010Count = getNamugeMap().containsKey(stringKeyset) ? getNamugeMap().get(stringKeyset) : 0;
            namugeInfo = namugeInfo + "창고명 : " + getNamugeWhsNameMap().get(stringKeyset) + " | 재고 : " + skd010Count + " | ";
        }
        return namugeInfo;
    }

    public int getWhs1id() {
        return whs1id;
    }

    public void setWhs1id(int whs1id) {
        this.whs1id = whs1id;
    }

    public int getWhs2id() {
        return whs2id;
    }

    public void setWhs2id(int whs2id) {
        this.whs2id = whs2id;
    }

    public int getWhs3id() {
        return whs3id;
    }

    public void setWhs3id(int whs3id) {
        this.whs3id = whs3id;
    }

    public int getWhs4id() {
        return whs4id;
    }

    public void setWhs4id(int whs4id) {
        this.whs4id = whs4id;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultType() {
        return resultType;
    }
}
