package nfm.com.skd.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.skd.service.Skd010SearchVO;
import nfm.com.skd.service.Skd010Service;
import nfm.com.skd.service.Skd010VO;
import nfm.com.skd.service.Skd020VO;
import nfm.com.whs.service.Ismwhs010VO;
import nfm.com.whs.service.impl.Whs010DAO;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("skd010Service")
public class Skd010ServiceImpl extends EgovAbstractServiceImpl implements Skd010Service {

    /**
     * ord010DAO
     */
    @Resource(name = "skd010DAO")
    private Skd010DAO skd010DAO;

    @Resource(name = "whs010DAO")
    private Whs010DAO ismwhs010DAO;

    public int itemeaSum01 = 0;
    public int itemeaSum02 = 0;
    public int itemeaSum03 = 0;
    public int itemeaSum04 = 0;

    @Override
    public int getSumItemea(int i) {
        if (i == 0) {
            return itemeaSum01;

        } else if (i == 1) {
            return itemeaSum02;

        } else if (i == 2) {
            return itemeaSum03;

        } else if (i == 3) {
            return itemeaSum04;
        }
        return 0;
    }

    @Override
    public String selectWithSkd010id(String currentId) {
        Skd010VO skd010VO = (Skd010VO) skd010DAO.selectWithSkd010id(currentId);
        JSONObject jsonObject = new JSONObject();
        if (skd010VO == null || StringUtils.isBlank(skd010VO.getItemcode())) {
            jsonObject.put("itemname", "");
            jsonObject.put("itemea", "");
            jsonObject.put("createdate", "");
            jsonObject.put("expirationdate", "");
            jsonObject.put("itemdlprice", "");
        } else {
            jsonObject.put("itemname", getResult(skd010VO.getItemname()));
            jsonObject.put("itemea", getResult(skd010VO.getItemea()));
            jsonObject.put("createdate", getResult(skd010VO.getCreatedate()));
            jsonObject.put("expirationdate", getResult(skd010VO.getExpirationdate()));
            jsonObject.put("itemdlprice", getResult(skd010VO.getItemdlprice()));
        }
        List<Skd020VO> skd020VOList = (List<Skd020VO>) skd010DAO.selectWhsNameList(skd010VO.getSkd010id());
        JSONArray jsonArray = new JSONArray();
        for (Skd020VO skd020VO : skd020VOList) {
            JSONObject jsonTempObject = new JSONObject();
            jsonTempObject.put("whsname", getResult(skd020VO.getWhsname()));
            jsonTempObject.put("itemea", getResult(skd020VO.getItemea()));
            jsonTempObject.put("createdate", getResult(skd020VO.getCreatedate()));
            jsonTempObject.put("itemdlprice", getResult(skd020VO.getItemdlprice()));
            jsonArray.add(jsonTempObject);
        }
        jsonObject.put("skd020", jsonArray);
        String result = jsonObject.toJSONString();
        return result;
    }

    private String getResult(Object result) {
        if (result instanceof String) {
            if (StringUtils.isBlank(String.valueOf(result))) {
                return "";
            } else {
                return String.valueOf(result);
            }
        } else {
            if (result == null) {
                return "";
            } else {
                return String.valueOf(result);
            }
        }


    }


    @Override
    public List<Skd010VO> selectList(Skd010SearchVO skd010SearchVO) throws Exception {
        itemeaSum01 = 0;
        itemeaSum02 = 0;
        itemeaSum03 = 0;
        itemeaSum04 = 0;

        List<Skd010VO> skd010VOList = (List<Skd010VO>) skd010DAO.selectList(skd010SearchVO);
        List<Ismwhs010VO> ismwhs010VOList = (List<Ismwhs010VO>) ismwhs010DAO.selectAll();
        for (int i = 0; i < 4; i ++) {
            if (ismwhs010VOList.size() < 4) {
                Ismwhs010VO ismwhs010VO = new Ismwhs010VO();
                ismwhs010VO.setWhsname("창고없음");
                ismwhs010VO.setWhs010id(-1);
                ismwhs010VOList.add(ismwhs010VO);
            }
        }

        for (Skd010VO skd010VO : skd010VOList) {
            List<Skd020VO> skd020VOList = (List<Skd020VO>) skd010DAO.selectWhsNameList(skd010VO.getSkd010id());
            int i = 0;
            for (Skd020VO skd020VO : skd020VOList) {
                if (i < 4) {
                    if (ismwhs010VOList.get(0).getWhs010id() == skd020VO.getWhs010id()) {
                        itemeaSum01 = itemeaSum01 + Integer.parseInt(skd020VO.getItemea());
                        skd010VO.setWhs1itemea(skd020VO.getItemea());
                        skd010VO.setWhs1itemname(skd020VO.getWhsname());
                    } else if (ismwhs010VOList.get(1).getWhs010id() == skd020VO.getWhs010id()) {
                        itemeaSum02 = itemeaSum02 + Integer.parseInt(skd020VO.getItemea());
                        skd010VO.setWhs2itemea(skd020VO.getItemea());
                        skd010VO.setWhs2itemname(skd020VO.getWhsname());
                    } else if (ismwhs010VOList.get(2).getWhs010id() == skd020VO.getWhs010id()) {
                        itemeaSum03 = itemeaSum03 + Integer.parseInt(skd020VO.getItemea());
                        skd010VO.setWhs3itemea(skd020VO.getItemea());
                        skd010VO.setWhs3itemname(skd020VO.getWhsname());
                    } else if (ismwhs010VOList.get(3).getWhs010id() == skd020VO.getWhs010id()) {
                        itemeaSum04 = itemeaSum04 + Integer.parseInt(skd020VO.getItemea());
                        skd010VO.setWhs4itemea(skd020VO.getItemea());
                        skd010VO.setWhs4itemname(skd020VO.getWhsname());
                    } else {
                        String tempNamuge = skd010VO.getWhsNamuge();
                        tempNamuge = tempNamuge + "창고명 : " + skd020VO.getWhsname() + " | 재고 : " + skd020VO.getItemea() + " | ";
                        skd010VO.setWhsNamuge(tempNamuge);
                    }
                } else {
                    String tempNamuge = skd010VO.getWhsNamuge();
                    tempNamuge = tempNamuge + "창고명 : " + skd020VO.getWhsname() + " | 재고 : " + skd020VO.getItemea() + " | ";
                    skd010VO.setWhsNamuge(tempNamuge);
                }
                i++;
            }
            Integer buyPrice = skd010VO.getItembuyprice();
            if (buyPrice == null) {
                buyPrice = 0;
            }

            try {
                long allBuyPrice = buyPrice * Integer.parseInt(skd010VO.getItemea());
                long allPrice = (allBuyPrice * 10) / 11;
                skd010VO.setItemAllbuyprice(String.format("%,3d", allBuyPrice));
                skd010VO.setItemAllprice(String.format("%,3d", allPrice));
            } catch (Exception e) {
                skd010VO.setItemAllbuyprice("액수가 너무 큽니다.");
                skd010VO.setItemAllprice("액수가 너무 큽니다.");
            }

        }


        return skd010VOList;
    }

    @Override
    public int selectListTotCnt(Skd010SearchVO skd010SearchVO) throws Exception {
        return 0;
    }

    @Override
    public int insertSkd010(Map param) throws Exception {
        return skd010DAO.insertSkd010(param);
    }

    @Override
    public int insertSkd020(Map param) throws Exception {
        return skd010DAO.insertSkd020(param);
    }

    @Override
    public String skd020seletWhsitem(String whs010id) throws Exception {
        List<Skd020VO> skd020VOList = (List<Skd020VO>) skd010DAO.skd020seletWhsitem(whs010id);
        JSONArray jsonArray = new JSONArray();
        for (Skd020VO skd020VO : skd020VOList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("skd010id", skd020VO.getSkd010id());
            jsonObject.put("label", skd020VO.getItemname() + " (" + skd020VO.getCreatedate() + ")");
            jsonObject.put("itemea", skd020VO.getItemea());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public void skd010SelectDel(String skd010ids) throws Exception {
        String[] skd010idsArr = skd010ids.split(",");
        HashMap hm = new HashMap();
        hm.put("skd010ids", skd010idsArr);
        skd010DAO.skd010SelectDel(hm);
        skd010DAO.skd020SelectDel(hm);
    }


}

