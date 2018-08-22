package nfm.com.prd.web;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nfm.com.prd.service.Prd010SearchVO;
import nfm.com.prd.service.Prd010Service;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

        //결합상품 삭제시, 그냥 해당 상품id만 삭제. (딱히 조회할 필요 X)
        //단품으로 등록된 상품은 절대로 결합으로 바꿔지지 않음
        //결합으로 등록된 상품은 계속 결합으로만 유지됨.

        //나와있는 모든 액셀데이터 출력하기

        // 일괄등록 기능은 단품만 적용하기

        // 선택 삭제 기능 구현

       return "/ism/prd/prd010";
    }

    @ResponseBody
    @RequestMapping(value = "/ism/prd/prd010SelectDel.do")
    public String ord020SelectDel(@RequestParam("orderitemids") String orderitemids, ModelMap model) throws Exception {
        prd010Service.prd010SelectDel(orderitemids);
        return "SUCCESS";
    }



    /**
     * 상품 상세
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ism/prd/prd010Detail.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String detailSelect(ModelMap model, String currentItemcoed) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        return prd010Service.selectWithItemcode(currentItemcoed);
    }

    /**
     * 상품 상세 저장
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ism/prd/prd010DetailSave.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String detailSave(ModelMap model, String currentItemcoed, String detail_category, String detail_itemcrosstype, String detail_byc, String detail_itemname, String detail_itemopt, String detail_itemea, String detail_itembuyprice,
                             String detail_itembuydlvprice, String detail_itemgubun, String detail_pristock, String detail_itemsize, String detail_cartonqty, String detail_palletqty, String detail_childItemcode
    ) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        if (StringUtils.isBlank(detail_category) || StringUtils.isBlank(detail_itemcrosstype) || StringUtils.isBlank(detail_byc) || StringUtils.isBlank(detail_itemname) || StringUtils.isBlank(detail_itemgubun)
                || ("2".equals(detail_itemgubun) && StringUtils.isBlank(detail_pristock))) {
            return "정상적으로 값을 입력해주세요.";
        }
        Map<String, String> param = new HashMap<>();
        param.put("detail_category", detail_category);
        param.put("detail_itemcrosstype", detail_itemcrosstype);
        param.put("detail_byc", detail_byc);
        param.put("detail_itemname", detail_itemname);

        if (StringUtils.isBlank(detail_itemopt)) {
            detail_itemopt = null;
        }
        param.put("detail_itemopt", detail_itemopt);

        if (StringUtils.isBlank(detail_itemea)) {
            detail_itemea = null;
        }
        param.put("detail_itemea", detail_itemea);

        if (StringUtils.isBlank(detail_itembuyprice)) {
            detail_itembuyprice = null;
        }
        param.put("detail_itembuyprice", detail_itembuyprice);

        if (StringUtils.isBlank(detail_itembuydlvprice)) {
            detail_itembuydlvprice = null;
        }
        param.put("detail_itembuydlvprice", detail_itembuydlvprice);

        //만약 "2".equals(detail_itemgubun)인경우 뒤의 값들도 넣기. 아닌경우 null을 넣을것
        param.put("detail_itemgubun", detail_itemgubun);
        if (!"2".equals(detail_itemgubun)) {
            detail_pristock = null;
            detail_itemsize = null;
            detail_cartonqty = null;
            detail_palletqty = null;
        }

        if (StringUtils.isBlank(detail_pristock)) {
            detail_pristock = null;
        }
        param.put("detail_pristock", detail_pristock);
        if (StringUtils.isBlank(detail_itemsize)) {
            detail_itemsize = null;
        }
        param.put("detail_itemsize", detail_itemsize);
        if (StringUtils.isBlank(detail_cartonqty)) {
            detail_cartonqty = null;
        }
        param.put("detail_cartonqty", detail_cartonqty);
        if (StringUtils.isBlank(detail_palletqty)) {
            detail_palletqty = null;
        }
        param.put("detail_palletqty", detail_palletqty);

        String result = null;
        if (StringUtils.isBlank(currentItemcoed)) {
            //insert 진행하기.
            result = prd010Service.insertItem(param);
        } else {
            //update 진행하기.
            param.put("itemcode", currentItemcoed);
            result = prd010Service.updateItem(param);
        }

        if (StringUtils.isBlank(result)) {
            return "등록을 실패하였습니다. 다시 시도하여주세요.";
        }

        //detail_itemcrosstype == "F"인경우, currentItemcoed을 부모로가지는 아이들의 부모코드를 전부 null로 수정 detail_childItemcode 들의 부모를 다시 설정
        if ("F".equals(detail_itemcrosstype)) {
            if (!StringUtils.isBlank(detail_childItemcode)) {
                prd010Service.updateCross(result, detail_childItemcode);
            }
        }

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("itemcode", result);
        return resultMessage.toJSONString();
    }

    /**
     * 삼품 검색용 모든 상품 조회
     */
    @ResponseBody
    @RequestMapping(value = "/ism/cum/prd010selectAll.do", produces = "application/json; charset=utf8")
    public String detailAutoSearch(ModelMap model) throws Exception {
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
            calender.add(Calendar.DATE, 1);
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
