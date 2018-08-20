package nfm.com.prd.web;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nfm.com.prd.service.Prd010DetailVO;
import nfm.com.prd.service.Prd010SearchVO;
import nfm.com.prd.service.Prd010Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class Prd010Controller {
    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** egovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService egovCmmUseService;

    /** ord020Service */
    @Resource(name = "prd010Service")
    private Prd010Service prd010Service;

    /**
     * 주문수집 목록
     * @param prd010SearchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ism/prd/prd010.do")
    public String mainList(@ModelAttribute("prd010SearchVO") Prd010SearchVO prd010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        prd010SearchVOInit(prd010SearchVO);

        /** EgovPropertyService */
        //ord020SearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        prd010SearchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing [s] */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(prd010SearchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(prd010SearchVO.getPageUnit());
        paginationInfo.setPageSize(prd010SearchVO.getPageSize());

        prd010SearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        prd010SearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        prd010SearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        int totCnt = prd010Service.selectListTotCnt(prd010SearchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        /** pageing [e]*/



        model.addAttribute("resultList", prd010Service.selectList(prd010SearchVO));

        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("ISM090");	//주문상태필드
        model.addAttribute("ISM090", egovCmmUseService.selectCmmCodeDetail(vo));

        model.addAttribute("bycList", prd010Service.selectBycAll());
        model.addAttribute("whsList", prd010Service.selectWhsAll());

        // id있으면 수정, 없으면 입력
        // 등록 및 수정을 여는 기준으로 결정하기. (id가 있는경우 등록으로 열면 무조건 초기화하기.) (id가 없는경우 그대로 열기)


        //나와있는 모든 액셀데이터 출력하기

        // 일괄등록 기능은 단품만 적용하기

        // 선택 삭제 기능 구현

       return "/ism/prd/prd010";
    }

    /**
     * 주문수집 목록
     * @param prd010DetailVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ism/prd/prd010Detail.do")
    public String mainDetail(@ModelAttribute("prd010DetailVO") Prd010DetailVO prd010DetailVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        return "/ism/prd/prd010";
    }

    /**
     * 모든 상품 조회
     */
    @ResponseBody
    @RequestMapping(value = "/ism/cum/prd010selectAll.do", produces = "application/json; charset=utf8")
    public String cum010SelectDetail(ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        String test = prd010Service.selectAll();
        return test;
    }



    private void prd010SearchVOInit(@ModelAttribute("prd010SearchVO") Prd010SearchVO prd010SearchVO) throws Exception {
        /** 조회 기준 날짜, 다른 검색파라미터 초기화 */
        if (StringUtils.isBlank(prd010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, -30);
            prd010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        }
        if (StringUtils.isBlank (prd010SearchVO.getDtSearch_toCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calender = Calendar.getInstance();
            prd010SearchVO.setDtSearch_toCreateDt(formatter.format(calender.getTime()));
        }
        if (StringUtils.isBlank(prd010SearchVO.getDfSearch_itemcat1())) {
            prd010SearchVO.setDfSearch_itemcat1(null);
        }
        if (StringUtils.isBlank(prd010SearchVO.getDfSearch_itemcrosstype())) {
            prd010SearchVO.setDfSearch_itemcrosstype(null);
        }
        if (StringUtils.isBlank(prd010SearchVO.getDfSearch_itemgubun())) {
            prd010SearchVO.setDfSearch_itemgubun(null);
        }
        if (StringUtils.isBlank(prd010SearchVO.getDfSearch_bycname())) {
            prd010SearchVO.setDfSearch_bycname(null);
        }
        if (StringUtils.isBlank(prd010SearchVO.getDfSearch_itemcode())) {
            prd010SearchVO.setDfSearch_itemcode(null);
        }
        if (StringUtils.isBlank(prd010SearchVO.getDfSearch_itemname())) {
            prd010SearchVO.setDfSearch_itemname(null);
        }
        if (StringUtils.isBlank(prd010SearchVO.getDfSearch_itemopt())) {
            prd010SearchVO.setDfSearch_itemopt(null);
        }
        if (StringUtils.isBlank(prd010SearchVO.getDfSearch_whsname())) {
            prd010SearchVO.setDfSearch_whsname(null);
        }

        if (prd010SearchVO.getDfChange_whs010id() != null) {
            if (prd010SearchVO.getDfChange_orderitemid() != null) {
                prd010Service.updateItemWhs(prd010SearchVO.getDfChange_orderitemid(), prd010SearchVO.getDfChange_whs010id());
            }
        }
        prd010SearchVO.setDfChange_orderitemid(null);
        prd010SearchVO.setDfChange_whs010id(null);
    }
}
