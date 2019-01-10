package nfm.com.skd.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.prd.service.Prd010VO;
import nfm.com.prd.service.impl.Prd010DAO;
import nfm.com.skd.service.*;
import nfm.com.whs.service.Ismwhs010VO;
import nfm.com.whs.service.impl.Whs010DAO;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("skd010Service")
public class Skd010ServiceImpl extends EgovAbstractServiceImpl implements Skd010Service {

    @Resource(name = "skd010DAO")
    private Skd010DAO skd010DAO;

    @Resource(name = "whs010DAO")
    private Whs010DAO ismwhs010DAO;

    @Resource(name = "prd010DAO")
    private Prd010DAO prd010DAO;

    @Override
    public String selectWithSkd010id(String currentId) {
        List<Skd030VO> skd030VOList = (List<Skd030VO>) skd010DAO.selectskd030VOForDetail(currentId);

        JSONObject jsonObject = new JSONObject();
        if (skd030VOList == null || skd030VOList.size() == 0 || StringUtils.isBlank(skd030VOList.get(0).getItemname())) {
            jsonObject.put("itemname", "");
        } else {
            jsonObject.put("itemname", getResult(skd030VOList.get(0).getItemname()));
        }

        JSONArray jsonArray = new JSONArray();
        for (Skd030VO skd030VO : skd030VOList) {
            JSONObject jsonTempObject = new JSONObject();
            jsonTempObject.put("itembuyprice", "");
            jsonTempObject.put("itembuyAllprice", "");
            jsonTempObject.put("expirationdate", "");
            if (skd030VO.getSkd010type() == 1) {
                jsonTempObject.put("gubun", "입고");
                jsonTempObject.put("itembuyprice", getResult(skd030VO.getItembuyprice()));
                jsonTempObject.put("itembuyAllprice", getResult(skd030VO.getItembuyprice() * skd030VO.getItemea()));
                jsonTempObject.put("expirationdate", getResult(skd030VO.getItembuyprice()));
            } else if (skd030VO.getSkd010type() == 2) {
                jsonTempObject.put("gubun", "이관");
            } else if (skd030VO.getSkd010type() == 3) {
                int tempea = skd030VO.getItemea();
                if (skd030VO.getItemea() < 0) {
                    skd030VO.setItemea(-tempea);
                    jsonTempObject.put("gubun", "반품");
                } else {
                    jsonTempObject.put("gubun", "출고");
                }
            }



            jsonTempObject.put("createdate", getResult(skd030VO.getCreatedate()));
            jsonTempObject.put("sourcewhsname", getResult(skd030VO.getSourcewhsname()));
            jsonTempObject.put("destinationwhsname", getResult(skd030VO.getDestinationwhsname()));
            jsonTempObject.put("itemea", getResult(skd030VO.getItemea()));
            jsonTempObject.put("itemdlprice", getResult(skd030VO.getItemdlprice()));
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
        List<Skd010VO> skd010VOList = (List<Skd010VO>) skd010DAO.selectList(skd010SearchVO);
        // 상품코드로 이관된 수량 불러오기. (현재 날짜까지)
        // 해당 아이템의 총 재고 수량을 제외하기.

        List<Ismwhs010VO> ismwhs010VOList = (List<Ismwhs010VO>) ismwhs010DAO.selectAll();
        for (int i = 0; i < 4; i++) {
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
                        skd010VO.setWhs1itemea(skd020VO.getItemea());
                        skd010VO.setWhs1itemname(skd020VO.getWhsname());
                        skd010VO.setWhs1id(skd020VO.getWhs010id());
                    } else if (ismwhs010VOList.get(1).getWhs010id() == skd020VO.getWhs010id()) {
                        skd010VO.setWhs2itemea(skd020VO.getItemea());
                        skd010VO.setWhs2itemname(skd020VO.getWhsname());
                        skd010VO.setWhs2id(skd020VO.getWhs010id());
                    } else if (ismwhs010VOList.get(2).getWhs010id() == skd020VO.getWhs010id()) {
                        skd010VO.setWhs3itemea(skd020VO.getItemea());
                        skd010VO.setWhs3itemname(skd020VO.getWhsname());
                        skd010VO.setWhs3id(skd020VO.getWhs010id());
                    } else if (ismwhs010VOList.get(3).getWhs010id() == skd020VO.getWhs010id()) {
                        skd010VO.setWhs4itemea(skd020VO.getItemea());
                        skd010VO.setWhs4itemname(skd020VO.getWhsname());
                        skd010VO.setWhs4id(skd020VO.getWhs010id());
                    } else {
                        skd010VO.getNamugeMap().put(skd010VO.getItemcode() + skd020VO.getWhs010id(), Integer.parseInt(skd020VO.getItemea()));
                        skd010VO.getNamugeList().add(skd020VO);
                        skd010VO.getNamugeWhsNameMap().put(skd010VO.getItemcode() + skd020VO.getWhs010id(), skd020VO.getWhsname());
                    }
                } else {
                    skd010VO.getNamugeMap().put(skd010VO.getItemcode() + skd020VO.getWhs010id(), Integer.parseInt(skd020VO.getItemea()));
                    skd010VO.getNamugeList().add(skd020VO);
                    skd010VO.getNamugeWhsNameMap().put(skd010VO.getItemcode() + skd020VO.getWhs010id(), skd020VO.getWhsname());
                }
                i++;
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
            int myItemEa = skd010DAO.selectSkd030itemeaAtWhs010id(skd010DAO.selectSkd010Itemcode(String.valueOf(skd020VO.getSkd010id())), Integer.parseInt(whs010id));
            jsonObject.put("realItemea", myItemEa);
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

    /**
     * 상품 출고를 담당합니다.
     *
     * whs010id가 따로 정해지지않았으면 "" or NULL 을 넣어주세요.
     *
     * @return
     * @throws Exception
     */
    @Override
    public String skd010Add(String itemcode, String whs010id, String itemea) throws Exception {
        Integer itemeaParent = 0;
        // 숫자가 아닌 경우, 전부 실패처리한다.
        try {
            itemeaParent = Integer.parseInt(itemea);
        } catch (Exception e) {
            JSONObject resultMessage = new JSONObject();
            resultMessage.put("failed", "failed");
            return resultMessage.toJSONString();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.MONTH, 0);
        String today = formatter.format(calender.getTime());

        if ('F' == (itemcode.charAt(0))) {
            //결합상품의 경우 우선창고에 있는 창고에서 빠지게 된다.
            List<Prd010VO> prd010VOFusionList = (List<Prd010VO>) prd010DAO.selectFusionList(itemcode);
            for (Prd010VO prd010VO : prd010VOFusionList) {
                if (prd010VO.getPristock() == null) {
                    continue;
                }
                Map<String, Object> param = new HashMap<>();
                param.put("itemcode", prd010VO.getItemcode());
                param.put("skd010type", 3);
                param.put("sourcewhs010id", prd010VO.getPristock());
                param.put("destinationwhs010id", -1);
                Integer itemeaChild = Integer.valueOf(prd010VO.getChildItemea());
                param.put("itemea", itemeaChild * itemeaParent);
                param.put("createdate", today);
                skd010DAO.insertSkd030(param);
            }
        } else {

            if (StringUtils.isBlank(whs010id)) {
                Prd010VO prd010VO = (Prd010VO) prd010DAO.selectPrd010VO(itemcode);
                Integer pristock = prd010VO.getPristock();
                if (pristock == null) {
                    JSONObject resultMessage = new JSONObject();
                    resultMessage.put("failed", "failed");
                    return resultMessage.toJSONString();
                }
                whs010id = String.valueOf(pristock);
            }
            //일반 상품은 다음과 같이 빠지게 된다.
            Map<String, Object> param = new HashMap<>();
            param.put("itemcode", itemcode);
            param.put("skd010type", 3);
            param.put("sourcewhs010id", whs010id);
            param.put("destinationwhs010id", -1);
            param.put("itemea", itemea);
            param.put("createdate", today);
            skd010DAO.insertSkd030(param);
        }

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }


}

