package nfm.com.adj.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nfm.com.adj.service.*;
import nfm.com.ord.service.Adj020VO;
import nfm.com.ord.service.impl.Ord020DAO;
import nfm.com.prd.service.Prd010Service;
import nfm.com.skd.service.Skd010SearchVO;
import nfm.com.skd.service.Skd010Service;
import nfm.com.skd.service.Skd010VO;
import nfm.com.whs.service.Ismwhs010VO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class Adj010Controller {
    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** egovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService egovCmmUseService;

    @Autowired
    Adj010Service adj010Service;

    @Resource(name="ord020DAO")
    private Ord020DAO ord020DAO;

    /**
     * 주문수집 목록
     * @param adj010SearchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ism/adj/adj010.do")
    public String mainList(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        /** EgovPropertyService */
        //ord020SearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        adj010SearchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing [s] */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(adj010SearchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(adj010SearchVO.getPageUnit());
        paginationInfo.setPageSize(adj010SearchVO.getPageSize());

        adj010SearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        adj010SearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        adj010SearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

//        int totCnt = prd010Service.selectListTotCnt(adj010SearchVO);
//        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        /** pageing [e]*/

//        model.addAttribute("resultList", prd010Service.selectList(adj010SearchVO));


       return "/ism/adj/adj010";
    }



    @RequestMapping(value = "/ism/adj/adj020.do")
    public String mainList2(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        } else {
            adj010SearchVO.setDtSearch_frCreateDt(adj010SearchVO.getDtSearch_frCreateDt().replaceAll("-",""));
        }

        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_adj020_01())) {
            adj010SearchVO.setDtSearch_adj020_01("2");
        }

        /** EgovPropertyService */
        //ord020SearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        adj010SearchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing [s] */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(adj010SearchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(adj010SearchVO.getPageUnit());
        paginationInfo.setPageSize(adj010SearchVO.getPageSize());

        adj010SearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        adj010SearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        adj010SearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        List<Adj020Result> resultList;

        if ("1".equals(adj010SearchVO.getDtSearch_adj020_01())) {
            resultList = adj010Service.adj020selectList(adj010SearchVO.getDtSearch_frCreateDt());
        } else {
            resultList = adj010Service.adj020selectList2(adj010SearchVO.getDtSearch_frCreateDt());
        }
        Collections.sort(resultList, new Ascending());
        int totCnt = resultList.size();
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        /** pageing [e]*/

        model.addAttribute("resultList", resultList);
        List<Adj020VO> top10bycList = (List<Adj020VO>) ord020DAO.adj020selectTop10List(adj010SearchVO.getDtSearch_frCreateDt());
        initData(top10bycList);
        model.addAttribute("top10bycList", top10bycList);

        adj010SearchVO.setDtSearch_frCreateDt(new StringBuilder(adj010SearchVO.getDtSearch_frCreateDt()).insert(4, "-").toString());

        return "/ism/adj/adj020";
    }

    class Ascending implements Comparator<Adj020Result> {
        @Override
        public int compare(Adj020Result o1, Adj020Result o2) {
            return o1.cum010id.compareTo(o2.cum010id);
        }
    }


    private void initData(List<Adj020VO> top10bycList) {
        for (int i = 0; i < 10; i ++) {
            if (top10bycList.size() < 10) {
                Adj020VO adj020VO = new Adj020VO();
                adj020VO.setByc010name("-");
                adj020VO.setByc010id(-1);
                top10bycList.add(adj020VO);
            }
        }
    }



    @RequestMapping(value = "/ism/adj/adj030.do")
    public String mainList3(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        }

        /** EgovPropertyService */
        //ord020SearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        adj010SearchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing [s] */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(adj010SearchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(adj010SearchVO.getPageUnit());
        paginationInfo.setPageSize(adj010SearchVO.getPageSize());

        adj010SearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        adj010SearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        adj010SearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<Adj030Result> adj030ResultList = adj010Service.adj030selectList(adj010SearchVO);

        int totCnt = adj030ResultList.size();
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        /** pageing [e]*/

        model.addAttribute("resultList", adj030ResultList);
        model.addAttribute("resultAllCount", adj010Service.getAdj030AllResult());
        model.addAttribute("bycList", prd010Service.selectBycAll());

        return "/ism/adj/adj030";
    }




    /**
     * skd010Service
     */
    @Resource(name = "skd010Service")
    private Skd010Service skd010Service;

    @Resource(name = "prd010Service")
    private Prd010Service prd010Service;

    @RequestMapping(value = "/ism/adj/adj050.do")
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
        List <Ismwhs010VO> whsListForTop = (List<Ismwhs010VO>) prd010Service.selectWhsAll();
        for (int i = 0; i < 4; i ++) {
            if (whsListForTop.size() < 4) {
                Ismwhs010VO ismwhs010VO = new Ismwhs010VO();
                ismwhs010VO.setWhsname("창고없음");
                whsListForTop.add(ismwhs010VO);
            }
        }
        for (int i = 0; i < 4; i ++) {
            whsListForTop.get(i).setCmm020id(skd010Service.getSumItemea(i));
        }
        for (int i = 4; i < 8; i ++) {
            Ismwhs010VO ismwhs010VO = new Ismwhs010VO();
            ismwhs010VO.setCmm020id(skd010Service.getSumItemea(i));
            ismwhs010VO.setWhsname(skd010Service.getResultSumB(i));
            whsListForTop.add(ismwhs010VO);
        }


        model.addAttribute("whsListForTop", whsListForTop);

        //입고등록 만들기 (save)만 (입고할 경우, 최초 창고 이관하기)

        //삭제의 경우 입고, 이관 삭제

        //

        return "/ism/adj/adj050";
    }

    @RequestMapping(value = "/ism/adj/adj060.do")
    public String mainList6(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }


        return "/ism/adj/adj060";
    }

    @RequestMapping(value = "/ism/adj/adj070.do")
    public String mainList7(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }



        return "/ism/adj/adj070";
    }

    @RequestMapping(value = "/ism/adj/adj080.do")
    public String mainList8(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }



        return "/ism/adj/adj080";
    }


}
