package nfm.com.skd.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.skd.service.Skd010SearchVO;
import nfm.com.skd.service.Skd010Service;
import nfm.com.skd.service.Skd010VO;
import nfm.com.skd.service.Skd020VO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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


    @Override
    public List<Skd010VO> selectList(Skd010SearchVO skd010SearchVO) throws Exception {
        List<Skd010VO> skd010VOList = (List<Skd010VO>) skd010DAO.selectList(skd010SearchVO);
        for (Skd010VO skd010VO : skd010VOList) {
            List<Skd020VO> skd020VOList = (List<Skd020VO>) skd010DAO.selectWhsNameList(skd010VO.getSkd010id());
            int i = 0;
            for (Skd020VO skd020VO : skd020VOList) {
                if (i == 0) {
                    skd010VO.setWhs1itemea(skd020VO.getItemea());
                    skd010VO.setWhs1itemname(skd020VO.getWhsname());
                } else if (i == 1) {
                    skd010VO.setWhs2itemea(skd020VO.getItemea());
                    skd010VO.setWhs2itemname(skd020VO.getWhsname());
                } else if (i == 2) {
                    skd010VO.setWhs3itemea(skd020VO.getItemea());
                    skd010VO.setWhs3itemname(skd020VO.getWhsname());
                } else if (i == 3) {
                    skd010VO.setWhs4itemea(skd020VO.getItemea());
                    skd010VO.setWhs4itemname(skd020VO.getWhsname());
                } else {
                    String tempNamuge = skd010VO.getWhsNamuge();
                    tempNamuge = tempNamuge + "창고명 : " + skd020VO.getWhsname() + "재고 : " + skd020VO.getItemea() + "\n";
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
                long allPrice = (allBuyPrice * 10) / 9;
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

