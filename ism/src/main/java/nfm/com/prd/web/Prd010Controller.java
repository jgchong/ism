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


        //id 있으면 수정, 없으면 운영상품 단품등록록
       //form을 통해 단품등록, 수정 만들기(수정의 경우 form을 수정할 상품id 전달, 통해 전달, 수정용 상품id가 있으면 해당 id로 셋팅해서 열어주기) 단품구분은 삭제하고, 해당 자리에 상품구분을 드랍다운으로 적용. 메모기능 추가하기.
        //매입사코드 입력시, like구문으로 조회하기 - 코드생성하기
        //결합상품 겸용으로 사용하기 (itemCode)

        //운영상품 결합 등록 http://jqueryui.com/autocomplete/#custom-data 검색어자동완성 참고 (availableTags에 상품명 전체 저장) 자동완성 통해서 추가하면 추가가 됨. 최대 5개짜지 추가가 되도록 설정. 히든에 코드가 추가되도록 설정.
        //받은 개수만큼 초과 안되도록 설정. 생성을 하면 결합상품이 생성되고, 해당 코드의 상품들은 결합상태가 되어짐.
        //결합상품 수정을 누를경우 추가가 됨.

        //나와있는 모든 액셀데이터 출력하기. (단품들만) <살짝 하지말기>

        // 일괄등록 기능은 단품만 적용하기.

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



    private void prd010SearchVOInit(@ModelAttribute("prd010SearchVO") Prd010SearchVO prd010SearchVO) throws Exception {
        /** 조회 기준 날짜, 다른 검색파라미터 초기화 */
        if (StringUtils.isBlank(prd010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, -30);
            prd010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        }
        if (StringUtils.isBlank (prd010SearchVO.getDtSearch_toCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
