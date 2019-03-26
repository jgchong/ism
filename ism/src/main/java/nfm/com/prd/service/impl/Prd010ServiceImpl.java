package nfm.com.prd.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.byc.service.Ismbyc010VO;
import nfm.com.byc.service.impl.Byc010DAO;
import nfm.com.prd.service.Prd010SearchVO;
import nfm.com.prd.service.Prd010Service;
import nfm.com.prd.service.Prd010VO;
import nfm.com.whs.service.impl.Whs010DAO;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("prd010Service")
public class Prd010ServiceImpl extends EgovAbstractServiceImpl implements Prd010Service {

    /**
     * prd010DAO
     */
    @Resource(name = "prd010DAO")
    private Prd010DAO prd010DAO;

    /**
     * ord010DAO
     */
    @Resource(name = "whs010DAO")
    private Whs010DAO ismwhs010DAO;

    /**
     * ord010DAO
     */
    @Resource(name = "byc010DAO")
    private Byc010DAO ismbyc010DAO;

    @Override
    public Object selectList(Prd010SearchVO prd010SearchVO) throws Exception {
        List<Prd010VO> prd010VOList = (List<Prd010VO>) prd010DAO.selectList(prd010SearchVO);
        List<Prd010VO> prd010VOListTemp = new ArrayList<>();
        if (!StringUtils.isBlank(prd010SearchVO.getDfSearch_bycname())) {
            for (Prd010VO prd010VO : prd010VOList) {
                if ("S".equals(prd010VO.getItemcrosstype())) {
                    prd010VOListTemp.add(prd010VO);
                }
            }
        }
        prd010VOList = prd010VOListTemp;

        for (int i = 0; i < prd010VOList.size(); i++) {
            prd010VOList.get(i).setListNo("" + (i + 1 + prd010SearchVO.getFirstIndex()));
        }
        for (int i = 0; i < prd010VOList.size(); i++) {
            Prd010VO prd010VO = prd010VOList.get(i);
            if ("F".equals(prd010VO.getItemcrosstype())) {
                List<Prd010VO> prd010VOFusionList = (List<Prd010VO>) prd010DAO.selectFusionList(prd010VO.getItemcode());
                if (prd010VOFusionList != null && prd010VOFusionList.size() > 0) {
                    for (Prd010VO prd010VO1 : prd010VOFusionList) {
                        prd010VO1.setItemcrosstype("C");
                        prd010VOList.add(i + 1, prd010VO1);
                    }
                }
            }
        }
        return prd010VOList;
    }

    @Override
    public int selectListTotCnt(Prd010SearchVO prd010SearchVO) throws Exception {
        return prd010DAO.selectListTotCnt(prd010SearchVO);
    }

    @Override
    public void updateItemWhs(Integer itemId, Integer whsId) throws Exception {
        prd010DAO.updateItemWhs(itemId, whsId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String selectAll() throws Exception {
        List<Prd010VO> prd010VOList = (List<Prd010VO>) prd010DAO.selectAll();
        JSONArray jsonArray = new JSONArray();
        for (Prd010VO prd010VO : prd010VOList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemcode", prd010VO.getItemcode());
            jsonObject.put("label", prd010VO.getItemname());
            jsonObject.put("explain", "["+prd010VO.getItemea()+"개/"+prd010VO.getItemopt()+"/"+prd010VO.getItembuyprice()+"원"+"]");
            jsonObject.put("itembuyprice", prd010VO.getItembuyprice());
            //추가
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public String selectGubun2() throws Exception {
        List<Prd010VO> prd010VOList = (List<Prd010VO>) prd010DAO.selectGubun2();
        JSONArray jsonArray = new JSONArray();
        for (Prd010VO prd010VO : prd010VOList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemcode", prd010VO.getItemcode());
            jsonObject.put("label", prd010VO.getItemname());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public String selectWithItemcode(String itemcode) throws Exception {
        Prd010VO originPrdVO = (Prd010VO) prd010DAO.selectWithItemcode(itemcode);
        JSONObject jsonObject = new JSONObject();
        if (originPrdVO == null || StringUtils.isBlank(originPrdVO.getItemcode())) {
            jsonObject.put("itemcode", "");
            jsonObject.put("orderitemid", "");
            jsonObject.put("itemcat1", "");
            jsonObject.put("itemcrosstype", "");
            jsonObject.put("byc010id", "");
            jsonObject.put("itemname", "");
            jsonObject.put("itemopt", "");
            jsonObject.put("itemea", "");
            jsonObject.put("itembuyprice", "");
            jsonObject.put("itemgubun", "");
            jsonObject.put("itembuydlvprice", "");
            jsonObject.put("pristock", "");
            jsonObject.put("itemsize", "");
            jsonObject.put("cartonqty", "");
            jsonObject.put("palletqty", "");
            jsonObject.put("taxfree", "");
            jsonObject.put("salecode", "");
        } else {
            jsonObject.put("itemcode", getResult(originPrdVO.getItemcode()));
            jsonObject.put("orderitemid", getResult(String.valueOf(originPrdVO.getOrderitemid())));
            jsonObject.put("itemcat1", getResult(originPrdVO.getItemcat1()));
            jsonObject.put("itemcrosstype", getResult(originPrdVO.getItemcrosstype()));
            jsonObject.put("byc010id", getResult(String.valueOf(originPrdVO.getByc010id())));
            jsonObject.put("itemname", getResult(originPrdVO.getItemname()));
            jsonObject.put("itemopt", getResult(originPrdVO.getItemopt()));
            jsonObject.put("itemea", getResult(originPrdVO.getItemea()));
            jsonObject.put("itembuyprice", getResult(originPrdVO.getItembuyprice()));
            jsonObject.put("itemgubun", getResult(originPrdVO.getItemgubun()));
            jsonObject.put("itembuydlvprice", getResult(originPrdVO.getItembuydlvprice()));
            jsonObject.put("pristock", getResult(originPrdVO.getPristock()));
            jsonObject.put("itemsize", getResult(originPrdVO.getItemsize()));
            jsonObject.put("cartonqty", getResult(originPrdVO.getCartonqty()));
            jsonObject.put("palletqty", getResult(originPrdVO.getPalletqty()));
            jsonObject.put("taxfree", getResult(originPrdVO.getTaxfree()));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            jsonObject.put("createdate", getResult(formatter.format(originPrdVO.getCreatedate())));
            jsonObject.put("salecode", getResult(originPrdVO.getSalecode()));
        }
        if ("F".equals(originPrdVO.getItemcrosstype())) {
            List<Prd010VO> prd010VOFusionList = (List<Prd010VO>) prd010DAO.selectFusionList(originPrdVO.getItemcode());
            JSONArray jsonArray = new JSONArray();
            for (Prd010VO tempPrdVO : prd010VOFusionList) {
                JSONObject jsonTempObject = new JSONObject();
                jsonTempObject.put("itemcode", tempPrdVO.getItemcode());
                jsonTempObject.put("label", tempPrdVO.getItemname() + "["+tempPrdVO.getItemea()+"개/"+tempPrdVO.getItemopt()+"/"+tempPrdVO.getItembuyprice()+"원"+"]");
                jsonTempObject.put("itembuyprice", tempPrdVO.getItembuyprice());
                jsonTempObject.put("ea", tempPrdVO.getChildItemea());
                jsonArray.add(jsonTempObject);
            }
            jsonObject.put("childItemcode", jsonArray);
        }

        String result = jsonObject.toJSONString();


        return result;
    }

    @Override
    public String insertItem(Map param) throws Exception {
        Ismbyc010VO ismbyc010VO = (Ismbyc010VO) ismbyc010DAO.selectismbyc010VO(String.valueOf(param.get("detail_byc")));
        param.put("byccode", ismbyc010VO.getByccode().substring(0,2));
        String detail_itemcrosstype = (String) param.get("detail_itemcrosstype");
        String makingCode = "";
        if (detail_itemcrosstype.equals("F")) {
            makingCode = makingCode + "F";
        } else {
            makingCode = makingCode + "S";
        }
        makingCode = makingCode + ismbyc010VO.getByccode().substring(0,2);

        Prd010VO prd010VO = (Prd010VO) prd010DAO.selectPrd010VO(makingCode);
        Integer code = null;
        if (prd010VO != null && !StringUtils.isBlank(prd010VO.getItemcode())) {
            String tempItemCode = prd010VO.getItemcode();
            code = Integer.parseInt(tempItemCode.substring(0,7).replace(makingCode, ""));
            code++;
        } else {
            code = 0;
        }
        makingCode = makingCode + String.format("%04d", code);
        if("1".equals(String.valueOf(param.get("detail_itemgubun")))) {
            makingCode = makingCode + "I";
        } else if ("2".equals(String.valueOf(param.get("detail_itemgubun")))) {
            makingCode = makingCode + "D";
        } else if ("3".equals(String.valueOf(param.get("detail_itemgubun")))) {
            makingCode = makingCode + "P";
        }
        //나중에 기타도 상품코드 추가가 되야하면 추가하기

        param.put("itemcode", makingCode);
        try {
            prd010DAO.insertItem(param);
        } catch (Exception e) {
            return "";
        }
        return makingCode;
    }

    @Override
    public String updateItem(Map param) throws Exception {
        Ismbyc010VO ismbyc010VO = (Ismbyc010VO) ismbyc010DAO.selectismbyc010VO(String.valueOf(param.get("detail_byc")));
        param.put("byccode", ismbyc010VO.getByccode());
        try {
            prd010DAO.updateItem(param);
        } catch (Exception e) {
            return "";
        }
        return String.valueOf(param.get("itemcode"));
    }

    @Override
    public String updateItemForBatchUp(Map param) throws Exception {
        Ismbyc010VO ismbyc010VO = (Ismbyc010VO) ismbyc010DAO.selectismbyc010VO(String.valueOf(param.get("detail_byc")));
        param.put("byccode", ismbyc010VO.getByccode());
        try {
            prd010DAO.updateItemForBatchUp(param);
        } catch (Exception e) {
            return "";
        }
        return String.valueOf(param.get("itemcode"));
    }

    @Override
    public String updateCross(String itemcode, String targetItemcodes, String targetItemeas) throws Exception {
//        prd010DAO.updateCrossitemcodeInit(targetItemcodes);
        prd010DAO.deleteCrossitemcode(itemcode);
//        prd010DAO.updateCrossitemcodes(itemcode, targetItemcodes);
        prd010DAO.insertCrossitemcode(itemcode, targetItemcodes, targetItemeas);
        return null;
    }

    @Override
    public void prd010SelectDel(String orderitemids) throws Exception {
        String[] orderitemidsArr = orderitemids.split(",");
        HashMap hm = new HashMap();
        hm.put("orderitemids", orderitemidsArr);
        prd010DAO.prd010SelectDel(hm);
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
    public Object selectWhsAll() throws Exception {
        return ismwhs010DAO.selectAll();
    }

    @Override
    public Object selectBycAll() throws Exception {
        return ismbyc010DAO.selectAll();
    }
}
