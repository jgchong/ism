package nfm.com.skd.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

import nfm.com.prd.service.Prd010Service;
import nfm.com.skd.service.Skd010SearchVO;
import nfm.com.skd.service.Skd010Service;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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
    public String mainList(@ModelAttribute("prd010SearchVO") Skd010SearchVO prd010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

//        /** EgovPropertyService */
//        //ord020SearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
//        prd010SearchVO.setPageSize(propertiesService.getInt("pageSize"));
//
//        /** pageing [s] */
//        PaginationInfo paginationInfo = new PaginationInfo();
//        paginationInfo.setCurrentPageNo(prd010SearchVO.getPageIndex());
//        paginationInfo.setRecordCountPerPage(prd010SearchVO.getPageUnit());
//        paginationInfo.setPageSize(prd010SearchVO.getPageSize());
//
//        prd010SearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//        prd010SearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
//        prd010SearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//
//        int totCnt = skd010Service.selectListTotCnt(prd010SearchVO);
//        paginationInfo.setTotalRecordCount(totCnt);
//        model.addAttribute("paginationInfo", paginationInfo);
//        /** pageing [e]*/
//
//
//
//        model.addAttribute("resultList", skd010Service.selectList(prd010SearchVO));
//
//        ComDefaultCodeVO vo = new ComDefaultCodeVO();
//        vo.setCodeId("ISM090");	//주문상태필드
//        model.addAttribute("ISM090", egovCmmUseService.selectCmmCodeDetail(vo));
        model.addAttribute("whsList", prd010Service.selectWhsAll());

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

    @ResponseBody
    @RequestMapping(value = "/ism/skd/prd020seletWhsitem.do", produces = "application/json; charset=utf8")
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
        return skd010Service.prd020seletWhsitem(whs010id);
    }



}
