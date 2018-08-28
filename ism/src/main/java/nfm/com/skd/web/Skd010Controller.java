package nfm.com.skd.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nfm.com.prd.service.Prd010Service;
import nfm.com.skd.service.Skd010SearchVO;
import nfm.com.skd.service.Skd010Service;
import nfm.com.skd.service.Skd010VO;
import nfm.com.skd.service.impl.Skd010DAO;
import nfm.com.whs.service.Ismwhs010VO;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        int totCnt = skd010VOList.size();
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        /** pageing [e]*/
//
//
//
        model.addAttribute("resultList", skd010VOList);
//
//        ComDefaultCodeVO vo = new ComDefaultCodeVO();
//        vo.setCodeId("ISM090");	//주문상태필드
//        model.addAttribute("ISM090", egovCmmUseService.selectCmmCodeDetail(vo));
        model.addAttribute("whsList", prd010Service.selectWhsAll());
        List <Ismwhs010VO> ismwhs010VOList = (List<Ismwhs010VO>) prd010Service.selectWhsAll();
        for (int i = 0; i < 4; i ++) {
            if (ismwhs010VOList.size() < 4) {
                Ismwhs010VO ismwhs010VO = new Ismwhs010VO();
                ismwhs010VO.setWhsname("창고없음");
                ismwhs010VOList.add(ismwhs010VO);
            }
        }
        model.addAttribute("whsListForTop", ismwhs010VOList);

        //입고등록 만들기 (save)만 (입고할 경우, 최초 창고 이관하기)

        //삭제의 경우 입고, 이관 삭제

        //

        return "/ism/skd/skd010";
    }


    /**
     * 상품 상세 저장
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

//        if (StringUtils.isBlank(result)) {
//            return "등록을 실패하였습니다. 다시 시도하여주세요.";
//        }

//        //detail_itemcrosstype == "F"인경우, currentItemcoed을 부모로가지는 아이들의 부모코드를 전부 null로 수정 detail_childItemcode 들의 부모를 다시 설정
//        if ("F".equals(detail_itemcrosstype)) {
//            if (!StringUtils.isBlank(detail_childItemcode)) {
//                prd010Service.updateCross(result, detail_childItemcode);
//            }
//        }

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }

    @Resource(name = "skd010DAO")
    private Skd010DAO skd010DAO;

    /**
     * 상품 상세 저장
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
        Map<String, Integer> itemOrginEaMap = new HashMap<>();
        for (int i = 0; i < skd020save_skd010idsArr.length; i++) {
            if (skd020save_whs010id.equals(skd020save_whs010id_updatesArr[i])) {
                continue;
            }
            //A창고 A상품을 B창고에 옮긴다.

            itemOrginEaMap.put(skd020save_skd010idsArr[i], Integer.parseInt(skd020save_itemeasArr[i]));
        }

        boolean isPass = true;
        List<String> updateSkdids = new ArrayList<>();
        List<String> updateWhsids = new ArrayList<>();
        List<Integer> updateItemea = new ArrayList<>();

        for (int i = 0; i < skd020save_skd010idsArr.length; i++) {
            if (skd020save_whs010id.equals(skd020save_whs010id_updatesArr[i])) {
                continue;
            }
            updateSkdids.add(skd020save_skd010idsArr[i]);
            updateWhsids.add(skd020save_whs010id_updatesArr[i]);
            updateItemea.add(Integer.parseInt(skd020save_itemea_updatesArr[i]));

            Integer currentEa = itemOrginEaMap.get(skd020save_skd010idsArr[i]);
            Integer newEa = currentEa - Integer.parseInt(skd020save_itemea_updatesArr[i]);
            if (newEa < 0) {
                isPass = false;
            }
            itemOrginEaMap.put(skd020save_skd010idsArr[i], newEa);
        }
        if (!isPass) {
            return "재고가 남아있지 않습니다. 다시입력해주세요.";
        }

        for (String originIds : itemOrginEaMap.keySet()) {
            Map<String, Object> param = new HashMap<>();
            param.put("skd010id", originIds);
                param.put("whs010id", skd020save_whs010id);
                param.put("itemea", itemOrginEaMap.get(originIds));
                param.put("createdate", skd020save_createdate);
            param.put("itemdlprice", skd020save_itemdlprice);
            skd010DAO.updateOriginSkd020(param);
        }


        for (int i = 0; i < updateSkdids.size(); i ++) {
            Map<String, Object> param = new HashMap<>();
            param.put("skt010id", updateSkdids.get(i));
            param.put("skd010save_whs010id", updateWhsids.get(i));
            param.put("skd010save_itemea", updateItemea.get(i));
            param.put("skd010save_createdate", skd020save_createdate);
            param.put("skd010save_itemdlprice", skd020save_itemdlprice);
            int result = skd010DAO.insertSkd020(param);
            if (result == 0) {
                skd010DAO.updateSkd020(param);
            }
        }

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



}
