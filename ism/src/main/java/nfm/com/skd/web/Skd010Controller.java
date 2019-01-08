package nfm.com.skd.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nfm.com.exl.util.ExcelManager;
import nfm.com.prd.service.Prd010Service;
import nfm.com.prd.service.Prd010VO;
import nfm.com.prd.service.impl.Prd010DAO;
import nfm.com.skd.service.*;
import nfm.com.skd.service.impl.Skd010DAO;
import nfm.com.whs.service.Ismwhs010VO;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Skd010Controller {
    /**
     * EgovMessageSource
     */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * egovCmmUseService
     */
    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService egovCmmUseService;

    /**
     * skd010Service
     */
    @Resource(name = "skd010Service")
    private Skd010Service skd010Service;

    @Resource(name = "prd010Service")
    private Prd010Service prd010Service;

    @RequestMapping(value = "/ism/skd/skd010.do")
    public String mainList(@ModelAttribute("skd010SearchVO") Skd010SearchVO skd010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(skd010SearchVO.getDfSearch_itemname())) {
            skd010SearchVO.setDfSearch_itemname(null);
        }

        /** EgovPropertyService */
        skd010SearchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing [s] */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(skd010SearchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(skd010SearchVO.getPageUnit());
        paginationInfo.setPageSize(skd010SearchVO.getPageSize());

        skd010SearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        skd010SearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        skd010SearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        List<Skd010VO> skd010VOList = skd010Service.selectList(skd010SearchVO);


        List<Skd010VO> skd010VOListReal = new ArrayList<>();

        //기존의 입고날짜별 재고들을 묶어준다.
        if (skd010VOList.size() > 0) {
            String tempItemcode = "";
            Skd010VO tempSkd010VO = null;
            for (Skd010VO skd010VOIterate : skd010VOList) {
                if (!tempItemcode.equals(skd010VOIterate.getItemcode())) {
                    tempItemcode = skd010VOIterate.getItemcode();
                    tempSkd010VO = new Skd010VO();
                    skd010VOListReal.add(tempSkd010VO);
                    tempSkd010VO.setItemcode(tempItemcode);
                    tempSkd010VO.setItemname(skd010VOIterate.getItemname());
                    tempSkd010VO.setResultType("P");
                }

                long tempitemea = getLongValue(tempSkd010VO.getItemea());
                long tempitemAllprice = getLongValue(tempSkd010VO.getItemAllprice());
                long tempitemAllbuyprice = getLongValue(tempSkd010VO.getItemAllbuyprice());
                long tempwhs1itemea1 = getLongValue(tempSkd010VO.getWhs1itemea());
                long tempwhs1itemea2 = getLongValue(tempSkd010VO.getWhs2itemea());
                long tempwhs1itemea3 = getLongValue(tempSkd010VO.getWhs3itemea());
                long tempwhs1itemea4 = getLongValue(tempSkd010VO.getWhs4itemea());

                long itemea = getLongValue(skd010VOIterate.getItemea());
                long itemAllprice = getLongValue(skd010VOIterate.getItemAllprice());
                long itemAllbuyprice = getLongValue(skd010VOIterate.getItemAllbuyprice());
                long whs1itemea1 = getLongValue(skd010VOIterate.getWhs1itemea());
                long whs1itemea2 = getLongValue(skd010VOIterate.getWhs2itemea());
                long whs1itemea3 = getLongValue(skd010VOIterate.getWhs3itemea());
                long whs1itemea4 = getLongValue(skd010VOIterate.getWhs4itemea());

                tempSkd010VO.setItembuyprice(skd010VOIterate.getItembuyprice());
                tempSkd010VO.setItemea(String.valueOf(tempitemea + itemea));
                tempSkd010VO.setItemAllprice(String.valueOf(tempitemAllprice + itemAllprice));
                tempSkd010VO.setItemAllbuyprice(String.valueOf(tempitemAllbuyprice + itemAllbuyprice));
                if (skd010VOIterate.getWhs1id() != 0) {
                    tempSkd010VO.setWhs1id(skd010VOIterate.getWhs1id());
                }

                if (skd010VOIterate.getWhs2id() != 0) {
                    tempSkd010VO.setWhs2id(skd010VOIterate.getWhs2id());
                }

                if (skd010VOIterate.getWhs3id() != 0) {
                    tempSkd010VO.setWhs3id(skd010VOIterate.getWhs3id());
                }

                if (skd010VOIterate.getWhs4id() != 0) {
                    tempSkd010VO.setWhs4id(skd010VOIterate.getWhs4id());
                }
                tempSkd010VO.setWhs1itemea(String.valueOf(tempwhs1itemea1 + whs1itemea1));
                tempSkd010VO.setWhs2itemea(String.valueOf(tempwhs1itemea2 + whs1itemea2));
                tempSkd010VO.setWhs3itemea(String.valueOf(tempwhs1itemea3 + whs1itemea3));
                tempSkd010VO.setWhs4itemea(String.valueOf(tempwhs1itemea4 + whs1itemea4));

                for (String stringKeyset : skd010VOIterate.getNamugeMap().keySet()) {
                    int skd010Count = skd010VOIterate.getNamugeMap().containsKey(stringKeyset) ? skd010VOIterate.getNamugeMap().get(stringKeyset) : 0;
                    int tempSkd010Count = tempSkd010VO.getNamugeMap().containsKey(stringKeyset) ? tempSkd010VO.getNamugeMap().get(stringKeyset) : 0;
                    tempSkd010VO.getNamugeMap().put(stringKeyset, tempSkd010Count + skd010Count);
                    tempSkd010VO.getNamugeWhsNameMap().put(stringKeyset, skd010VOIterate.getNamugeWhsNameMap().get(stringKeyset));
                }
            }
        }

        //아이템코드, 창고아이디 int map 만들기
        List<Skd030VO> skd030VOList = (List<Skd030VO>) skd010DAO.selectskd030VOForList();
        Map<String, Integer> skd030VOMap = new HashMap<>();

        for (Skd030VO skd030VO : skd030VOList) {
            skd030VOMap.put(skd030VO.getItemcode() + skd030VO.getSourcewhs010id(), skd030VO.getItemea());
        }

        long[] temp = new long[8];

        //출고된 것들을 차감한다.
        for (Skd010VO skd010VOIterate : skd010VOListReal) {
            long itemea = getLongValue(skd010VOIterate.getItemea());
            Integer buyPrice = skd010VOIterate.getItembuyprice();
            if (buyPrice == null) {
                buyPrice = 0;
            }

            long whs1itemea1 = getLongValue(skd010VOIterate.getWhs1itemea());
            long whs1itemea2 = getLongValue(skd010VOIterate.getWhs2itemea());
            long whs1itemea3 = getLongValue(skd010VOIterate.getWhs3itemea());
            long whs1itemea4 = getLongValue(skd010VOIterate.getWhs4itemea());

            itemea = itemea - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs1id()));
            whs1itemea1 = whs1itemea1 - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs1id()));

            itemea = itemea - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs2id()));
            whs1itemea2 = whs1itemea2 - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs2id()));

            itemea = itemea - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs3id()));
            whs1itemea3 = whs1itemea3 - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs3id()));

            itemea = itemea - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs4id()));
            whs1itemea4 = whs1itemea4 - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs4id()));

            long buyPriceToLong = buyPrice;
            skd010VOIterate.setItemea(String.valueOf(itemea));
            skd010VOIterate.setWhs1itemea(String.valueOf(whs1itemea1));
            skd010VOIterate.setWhs2itemea(String.valueOf(whs1itemea2));
            skd010VOIterate.setWhs3itemea(String.valueOf(whs1itemea3));
            skd010VOIterate.setWhs4itemea(String.valueOf(whs1itemea4));
            temp[5] = temp[5] + itemea;
            temp[0] = temp[0] + whs1itemea1;
            temp[1] = temp[1] + whs1itemea2;
            temp[2] = temp[2] + whs1itemea3;
            temp[3] = temp[3] + whs1itemea4;
            temp[4] = temp[5] - temp[0] - temp[1] - temp[2] - temp[3];
            try {
                long allBuyPrice = buyPriceToLong * itemea;
                long allPrice = (allBuyPrice * 10) / 11;
                skd010VOIterate.setItemAllbuyprice(String.format("%,3d", allBuyPrice));
                skd010VOIterate.setItemAllprice(String.format("%,3d", allPrice));
                temp[6] = temp[6] + allPrice;
                temp[7] = temp[7] + allBuyPrice;
            } catch (Exception e) {
                skd010VOIterate.setItemAllbuyprice("액수가 너무 큽니다.");
                skd010VOIterate.setItemAllprice("액수가 너무 큽니다.");
            }

            for (String stringKeyset : skd010VOIterate.getNamugeMap().keySet()) {
                int skd010Count = skd010VOIterate.getNamugeMap().containsKey(stringKeyset) ? skd010VOIterate.getNamugeMap().get(stringKeyset) : 0;
                int minusCount = getIntValue(skd030VOMap.get(stringKeyset));
                skd010VOIterate.getNamugeMap().put(stringKeyset, skd010Count - minusCount);
            }


        }

        int totCnt = skd010VOList.size();
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultList", skd010VOListReal);
        model.addAttribute("whsList", prd010Service.selectWhsAll());
        List <Ismwhs010VO> whsListForTop = (List<Ismwhs010VO>) prd010Service.selectWhsAll();
        for (int i = 0; i < 4; i ++) {
            if (whsListForTop.size() < 4) {
                Ismwhs010VO ismwhs010VO = new Ismwhs010VO();
                ismwhs010VO.setWhsname("창고없음");
                whsListForTop.add(ismwhs010VO);
            }
        }
        for (int i = 0; i < 4; i ++) {
            whsListForTop.get(i).setCmm020id(Integer.parseInt(String.valueOf(temp[i])));
        }
        for (int i = 4; i < 6; i ++) {
            Ismwhs010VO ismwhs010VO = new Ismwhs010VO();
            ismwhs010VO.setCmm020id(Integer.parseInt(String.valueOf(temp[i])));
            whsListForTop.add(ismwhs010VO);
        }

        for (int i = 6; i < 8; i ++) {
            Ismwhs010VO ismwhs010VO = new Ismwhs010VO();
            ismwhs010VO.setWhsname(String.valueOf(temp[i]));
            whsListForTop.add(ismwhs010VO);
        }
        model.addAttribute("whsListForTop", whsListForTop);


        return "/ism/skd/skd010";
    }

    private long getLongValue(String stringValue) {
        long itemea;
        if (StringUtils.isBlank(stringValue)) {
            itemea = 0;
        } else {
            itemea = Long.parseLong(stringValue.replaceAll(",", ""));
        }
        return itemea;
    }

    private long getLongValue(Integer intValue) {
        long itemea;
        if (intValue == null) {
            itemea = 0;
        } else {
            itemea = intValue;
        }
        return itemea;
    }

    private int getIntValue(Integer intValue) {
        int itemea;
        if (intValue == null) {
            itemea = 0;
        } else {
            itemea = intValue;
        }
        return itemea;
    }


    /**
     * 상품 입고
     *
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ism/skd/skd010Save.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String detailSave(ModelMap model, String skd010save_itemcode, String skd010save_itemea, String skd010save_createdate, String skd010save_expirationdate, String skd010save_itemdlprice, String skd010save_whs010id) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        if (StringUtils.isBlank(skd010save_itemcode) || StringUtils.isBlank(skd010save_itemea) || StringUtils.isBlank(skd010save_createdate) || StringUtils.isBlank(skd010save_whs010id)) {
            return "정상적으로 값을 입력해주세요.";
        }
        Map<String, String> param = new HashMap<>();
        param.put("skd010save_itemcode", skd010save_itemcode);
        param.put("skd010save_itemea", skd010save_itemea);
        param.put("skd010save_createdate", skd010save_createdate);
        param.put("skd010save_whs010id", skd010save_whs010id);

        if (StringUtils.isBlank(skd010save_expirationdate)) {
            skd010save_expirationdate = null;
        }

        if (StringUtils.isBlank(skd010save_itemdlprice)) {
            skd010save_itemdlprice = null;
        }
        param.put("skd010save_expirationdate", skd010save_expirationdate);
        param.put("skd010save_itemdlprice", skd010save_itemdlprice);

        int skt010id = skd010Service.insertSkd010(param);
        if (skt010id == 0) {
            return "등록을 실패하였습니다. 다시 시도하여주세요.";
        }
        param.put("skt010id", String.valueOf(skt010id));

        int skt020id = skd010Service.insertSkd020(param);
        if (skt020id == 0) {
            return "등록을 실패하였습니다. 다시 시도하여주세요.";
        }

        Map<String, Object> param2 = new HashMap<>();
        param2.put("itemcode", skd010save_itemcode);
        param2.put("skd010type", 1);
        param2.put("sourcewhs010id", -1);
        param2.put("destinationwhs010id", skd010save_whs010id);
        param2.put("itemea", skd010save_itemea);
        param2.put("createdate", skd010save_createdate);
        param2.put("itemdlprice", skd010save_itemdlprice);
        param2.put("expirationdate", skd010save_expirationdate);
        skd010DAO.insertSkd030(param2);

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }

    @Resource(name = "skd010DAO")
    private Skd010DAO skd010DAO;

    /**
     * 이관처리
     *
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ism/skd/skd020Save.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String detailSave2(ModelMap model, String skd020save_whs010id, String skd020save_createdate, String skd020save_itemdlprice, String skd020save_skd010ids, String skd020save_itemeas, String skd020save_itemea_updates
            , String skd020save_whs010id_updates) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        if (StringUtils.isBlank(skd020save_whs010id) || StringUtils.isBlank(skd020save_createdate) || StringUtils.isBlank(skd020save_skd010ids) || StringUtils.isBlank(skd020save_itemeas)  || StringUtils.isBlank(skd020save_itemea_updates)
                || StringUtils.isBlank(skd020save_whs010id_updates)) {
            return "정상적으로 값을 입력해주세요.";
        }

        //로직 생각
        //1. 각각의 키쌍에 맞는 창고에서 차감을 한다. (만약 0보다 작으면 빠꾸한다)
        //2. 옮겨지는 창고가 있다면 업데이트 없다면 인서트 한다.

        if (StringUtils.isBlank(skd020save_itemdlprice)) {
            skd020save_itemdlprice = null;
        }
        String[] skd020save_skd010idsArr = skd020save_skd010ids.split(",");
        String[] skd020save_itemeasArr = skd020save_itemeas.split(",");
        String[] skd020save_itemea_updatesArr = skd020save_itemea_updates.split(",");
        String[] skd020save_whs010id_updatesArr = skd020save_whs010id_updates.split(",");
        if (skd020save_skd010idsArr.length != skd020save_itemeasArr.length && skd020save_skd010idsArr.length != skd020save_itemea_updatesArr.length  && skd020save_skd010idsArr.length != skd020save_whs010id_updatesArr.length) {
            return "제대로 된 이관서식을 입력해주세요.";
        }

        //옮김 당하는 재고 수량
        List<Integer> skd020save_itemeaList = new ArrayList<>();

        //옮기는 재고 수량 (해당 정수값만큼 이관이 되어진다)
        List<Integer> skd020save_itemea_updateList = new ArrayList<>();

        try {
            for(int i = 0; i < skd020save_itemeasArr.length; i ++) {
                Integer itemea = Integer.parseInt(skd020save_itemeasArr[i]);
                Integer itemea_update = Integer.parseInt(skd020save_itemea_updatesArr[i]);
                if (itemea - itemea_update < 0) {
//                    return "재고가 없습니다.";
                }
                itemea = itemea - itemea_update;
                skd020save_itemeaList.add(itemea);
                skd020save_itemea_updateList.add(itemea_update);

                //재고 찾기
                int myItemEa = skd010DAO.selectSkd030itemeaAtWhs010id(skd010DAO.selectSkd010Itemcode(skd020save_skd010idsArr[i]), Integer.parseInt(skd020save_whs010id_updatesArr[i]));
                if (myItemEa - itemea_update < 0) {
                    return "재고가 없습니다.";
                }
            }
        }
        catch (Exception e) {
            return "수량을 제대로 입력해주세요.";
        }

        for (int i = 0; i < skd020save_itemeaList.size(); i ++) {
            Map<String, Object> param = new HashMap<>();
            param.put("skd010id", skd020save_skd010idsArr[i]);
            param.put("whs010id", skd020save_whs010id_updatesArr[i]);
            param.put("itemea", skd020save_itemeaList.get(i));
            skd010DAO.updateOriginSkd020(param);
        }

        for (int i = 0; i < skd020save_itemeaList.size(); i ++) {
            Map<String, Object> param = new HashMap<>();
            param.put("skt010id", skd020save_skd010idsArr[i]);
            param.put("skd010save_whs010id", skd020save_whs010id);
            param.put("skd010save_itemea", skd020save_itemea_updateList.get(i));
            param.put("skd010save_createdate", skd020save_createdate);
            param.put("skd010save_itemdlprice", skd020save_itemdlprice);
            int result = skd010DAO.insertSkd020(param);
            if (result == 0) {
                skd010DAO.updateSkd020(param);
            }

            Map<String, Object> param2 = new HashMap<>();
            param2.put("itemcode", skd010DAO.selectSkd010Itemcode(skd020save_skd010idsArr[i]));
            param2.put("skd010type", 2);
            param2.put("sourcewhs010id", skd020save_whs010id_updatesArr[i]);
            param2.put("destinationwhs010id",  skd020save_whs010id);
            param2.put("itemea", skd020save_itemea_updateList.get(i));
            param2.put("createdate", skd020save_createdate);
            param2.put("itemdlprice", skd020save_itemdlprice);
            skd010DAO.insertSkd030(param2);
        }



//        skd010DAO.skd020zeroDelete();
        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/ism/skd/skd020seletWhsitem.do", produces = "application/json; charset=utf8")
    public String detailAutoSearch2(ModelMap model , String whs010id) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        if (StringUtils.isBlank(whs010id)) {
            return "창고가 설정되지 않았습니다.";
        }
        return skd010Service.skd020seletWhsitem(whs010id);
    }

    @ResponseBody
    @RequestMapping(value = "/ism/skd/skd010SelectDel.do")
    public String skd010SelectDel(@RequestParam("skd010ids") String skd010ids, ModelMap model) throws Exception {
        skd010Service.skd010SelectDel(skd010ids);
        return "SUCCESS";
    }

    /**
     * 상품 상세
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ism/skd/skd010Detail.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String detailSelect(ModelMap model, String currentId) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        return skd010Service.selectWithSkd010id(currentId);
    }

    @RequestMapping(value="/ism/skd/skd010ExcelDownload.do")
    public @ResponseBody byte[] ord020ExcelDownload(@ModelAttribute("skd010SearchVO") Skd010SearchVO skd010SearchVO, ModelMap model, HttpServletRequest request,
                                                    HttpServletResponse response, HttpSession session ) throws Exception {


        skd010SearchVO.setFirstIndex(0);
        skd010SearchVO.setRecordCountPerPage(100000);
        List<Skd010VO> skd010VOList = skd010Service.selectList(skd010SearchVO);

        List<Skd010VO> skd010VOListReal = new ArrayList<>();

        //기존의 입고날짜별 재고들을 묶어준다.
        if (skd010VOList.size() > 0) {
            String tempItemcode = "";
            Skd010VO tempSkd010VO = null;
            for (Skd010VO skd010VOIterate : skd010VOList) {
                if (!tempItemcode.equals(skd010VOIterate.getItemcode())) {
                    tempItemcode = skd010VOIterate.getItemcode();
                    tempSkd010VO = new Skd010VO();
                    skd010VOListReal.add(tempSkd010VO);
                    tempSkd010VO.setItemcode(tempItemcode);
                    tempSkd010VO.setItemname(skd010VOIterate.getItemname());
                    tempSkd010VO.setResultType("P");
                }

                long tempitemea = getLongValue(tempSkd010VO.getItemea());
                long tempitemAllprice = getLongValue(tempSkd010VO.getItemAllprice());
                long tempitemAllbuyprice = getLongValue(tempSkd010VO.getItemAllbuyprice());
                long tempwhs1itemea1 = getLongValue(tempSkd010VO.getWhs1itemea());
                long tempwhs1itemea2 = getLongValue(tempSkd010VO.getWhs2itemea());
                long tempwhs1itemea3 = getLongValue(tempSkd010VO.getWhs3itemea());
                long tempwhs1itemea4 = getLongValue(tempSkd010VO.getWhs4itemea());

                long itemea = getLongValue(skd010VOIterate.getItemea());
                long itemAllprice = getLongValue(skd010VOIterate.getItemAllprice());
                long itemAllbuyprice = getLongValue(skd010VOIterate.getItemAllbuyprice());
                long whs1itemea1 = getLongValue(skd010VOIterate.getWhs1itemea());
                long whs1itemea2 = getLongValue(skd010VOIterate.getWhs2itemea());
                long whs1itemea3 = getLongValue(skd010VOIterate.getWhs3itemea());
                long whs1itemea4 = getLongValue(skd010VOIterate.getWhs4itemea());

                tempSkd010VO.setItembuyprice(skd010VOIterate.getItembuyprice());
                tempSkd010VO.setItemea(String.valueOf(tempitemea + itemea));
                tempSkd010VO.setItemAllprice(String.valueOf(tempitemAllprice + itemAllprice));
                tempSkd010VO.setItemAllbuyprice(String.valueOf(tempitemAllbuyprice + itemAllbuyprice));
                if (skd010VOIterate.getWhs1id() != 0) {
                    tempSkd010VO.setWhs1id(skd010VOIterate.getWhs1id());
                }

                if (skd010VOIterate.getWhs2id() != 0) {
                    tempSkd010VO.setWhs2id(skd010VOIterate.getWhs2id());
                }

                if (skd010VOIterate.getWhs3id() != 0) {
                    tempSkd010VO.setWhs3id(skd010VOIterate.getWhs3id());
                }

                if (skd010VOIterate.getWhs4id() != 0) {
                    tempSkd010VO.setWhs4id(skd010VOIterate.getWhs4id());
                }
                tempSkd010VO.setWhs1itemea(String.valueOf(tempwhs1itemea1 + whs1itemea1));
                tempSkd010VO.setWhs2itemea(String.valueOf(tempwhs1itemea2 + whs1itemea2));
                tempSkd010VO.setWhs3itemea(String.valueOf(tempwhs1itemea3 + whs1itemea3));
                tempSkd010VO.setWhs4itemea(String.valueOf(tempwhs1itemea4 + whs1itemea4));

                for (String stringKeyset : skd010VOIterate.getNamugeMap().keySet()) {
                    int skd010Count = skd010VOIterate.getNamugeMap().containsKey(stringKeyset) ? skd010VOIterate.getNamugeMap().get(stringKeyset) : 0;
                    int tempSkd010Count = tempSkd010VO.getNamugeMap().containsKey(stringKeyset) ? tempSkd010VO.getNamugeMap().get(stringKeyset) : 0;
                    tempSkd010VO.getNamugeMap().put(stringKeyset, tempSkd010Count + skd010Count);
                    tempSkd010VO.getNamugeWhsNameMap().put(stringKeyset, skd010VOIterate.getNamugeWhsNameMap().get(stringKeyset));
                }
            }
        }

        //아이템코드, 창고아이디 int map 만들기
        List<Skd030VO> skd030VOList = (List<Skd030VO>) skd010DAO.selectskd030VOForList();
        Map<String, Integer> skd030VOMap = new HashMap<>();

        for (Skd030VO skd030VO : skd030VOList) {
            skd030VOMap.put(skd030VO.getItemcode() + skd030VO.getSourcewhs010id(), skd030VO.getItemea());
        }

        long[] temp = new long[8];

        //출고된 것들을 차감한다.
        for (Skd010VO skd010VOIterate : skd010VOListReal) {
            long itemea = getLongValue(skd010VOIterate.getItemea());
            Integer buyPrice = skd010VOIterate.getItembuyprice();
            if (buyPrice == null) {
                buyPrice = 0;
            }

            long whs1itemea1 = getLongValue(skd010VOIterate.getWhs1itemea());
            long whs1itemea2 = getLongValue(skd010VOIterate.getWhs2itemea());
            long whs1itemea3 = getLongValue(skd010VOIterate.getWhs3itemea());
            long whs1itemea4 = getLongValue(skd010VOIterate.getWhs4itemea());

            itemea = itemea - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs1id()));
            whs1itemea1 = whs1itemea1 - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs1id()));

            itemea = itemea - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs2id()));
            whs1itemea2 = whs1itemea2 - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs2id()));

            itemea = itemea - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs3id()));
            whs1itemea3 = whs1itemea3 - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs3id()));

            itemea = itemea - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs4id()));
            whs1itemea4 = whs1itemea4 - getLongValue(skd030VOMap.get(skd010VOIterate.getItemcode() + skd010VOIterate.getWhs4id()));

            long buyPriceToLong = buyPrice;
            skd010VOIterate.setItemea(String.valueOf(itemea));
            skd010VOIterate.setWhs1itemea(String.valueOf(whs1itemea1));
            skd010VOIterate.setWhs2itemea(String.valueOf(whs1itemea2));
            skd010VOIterate.setWhs3itemea(String.valueOf(whs1itemea3));
            skd010VOIterate.setWhs4itemea(String.valueOf(whs1itemea4));
            temp[5] = temp[5] + itemea;
            temp[0] = temp[0] + whs1itemea1;
            temp[1] = temp[1] + whs1itemea2;
            temp[2] = temp[2] + whs1itemea3;
            temp[3] = temp[3] + whs1itemea4;
            temp[4] = temp[5] - temp[0] - temp[1] - temp[2] - temp[3];
            try {
                long allBuyPrice = buyPriceToLong * itemea;
                long allPrice = (allBuyPrice * 10) / 11;
                skd010VOIterate.setItemAllbuyprice(String.format("%,3d", allBuyPrice));
                skd010VOIterate.setItemAllprice(String.format("%,3d", allPrice));
                temp[6] = temp[6] + allPrice;
                temp[7] = temp[7] + allBuyPrice;
            } catch (Exception e) {
                skd010VOIterate.setItemAllbuyprice("액수가 너무 큽니다.");
                skd010VOIterate.setItemAllprice("액수가 너무 큽니다.");
            }

            for (String stringKeyset : skd010VOIterate.getNamugeMap().keySet()) {
                int skd010Count = skd010VOIterate.getNamugeMap().containsKey(stringKeyset) ? skd010VOIterate.getNamugeMap().get(stringKeyset) : 0;
                int minusCount = getIntValue(skd030VOMap.get(stringKeyset));
                skd010VOIterate.getNamugeMap().put(stringKeyset, skd010Count - minusCount);
            }


        }


        List <Ismwhs010VO> whsListForTop = (List<Ismwhs010VO>) prd010Service.selectWhsAll();
        for (int i = 0; i < 4; i ++) {
            if (whsListForTop.size() < 4) {
                Ismwhs010VO ismwhs010VO = new Ismwhs010VO();
                ismwhs010VO.setWhsname("창고없음");
                whsListForTop.add(ismwhs010VO);
            }
        }



        List<Object> header = new ArrayList<Object>();
        List<List<Object>> data = new ArrayList<List<Object>>();
        header.add("상품코드");
        header.add("상품명");
        for (Ismwhs010VO ismwhs010VO : whsListForTop) {
            header.add(ismwhs010VO.getWhsname());
        }
        header.add("기타");
        header.add("합계");
        header.add("총재고금액(VAT불포함)");
        header.add("총재고금액(VAT포함)");

        for (Skd010VO skd010VO : skd010VOListReal) {
            List<Object> obj = new ArrayList<Object>();
            obj.add(skd010VO.getItemcode());
            obj.add(skd010VO.getItemname());
            obj.add(skd010VO.getWhs1itemea());
            obj.add(skd010VO.getWhs2itemea());
            obj.add(skd010VO.getWhs3itemea());
            obj.add(skd010VO.getWhs4itemea());
            obj.add(skd010VO.getWhsNamuge());
            obj.add(skd010VO.getItemea());
            obj.add(skd010VO.getItemAllprice());
            obj.add(skd010VO.getItemAllbuyprice());
            data.add(obj);
        }

        String[] excelCellType = {"S","S","S","S","S","S","S","S","S","S"};

        ExcelManager excelManager = new ExcelManager(header, data);
        excelManager.setSheetName("재고관리");
        excelManager.setWidth(6000);
        excelManager.setCellDataType(excelCellType);
        excelManager.setStartRow(0);
        excelManager.setStartCol(0);
        excelManager.setExcelType("xls");

        byte[] bytes = excelManager.makeExcel();

        response.setHeader("Content-Disposition", "attachment; filename=InventoryManagementExcel.xls");
        response.setContentLength(bytes.length);
        response.setContentType("application/vnd.ms-excel");

        return bytes;
    }


    @Resource(name = "prd010DAO")
    private Prd010DAO prd010DAO;

    /**
     * 상품 출고를 담당합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ism/skd/skd010Add.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String detailSave(ModelMap model, String itemcode, String whs010id, String itemea) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

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
