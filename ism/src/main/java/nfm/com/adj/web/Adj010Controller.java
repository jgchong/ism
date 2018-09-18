package nfm.com.adj.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nfm.com.adj.dao.Adj040DAO;
import nfm.com.adj.dao.Adj060DAO;
import nfm.com.adj.dao.Adj070DAO;
import nfm.com.adj.model.*;
import nfm.com.adj.service.*;
import nfm.com.ord.service.Adj020VO;
import nfm.com.ord.service.impl.Ord020DAO;
import nfm.com.prd.service.Prd010Service;
import nfm.com.skd.service.Skd010SearchVO;
import nfm.com.skd.service.Skd010Service;
import nfm.com.skd.service.Skd010VO;
import nfm.com.whs.service.Ismwhs010VO;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Adj010Controller {
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

    @Autowired
    Adj010Service adj010Service;

    @Resource(name = "ord020DAO")
    private Ord020DAO ord020DAO;

    /**
     * 주문수집 목록
     *
     * @param adj010SearchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ism/adj/adj010.do")
    public String mainList(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        } else {
            adj010SearchVO.setDtSearch_frCreateDt(adj010SearchVO.getDtSearch_frCreateDt().replaceAll("-", ""));
        }

        String yyyymm = adj010SearchVO.getDtSearch_frCreateDt();
        String yyyy01 = yyyymm.substring(0, 4);
        String yyyy00 = String.valueOf(Integer.valueOf(yyyy01) - 1);
        List<String> yyyymmList = getAllyyyymmList(yyyymm);

        Adj020Result adj020ResultBYC0 = (Adj020Result) adj010Service.adj020selectListBycAll(yyyy00);
        Adj020Result adj020ResultBYC1 = (Adj020Result) adj010Service.adj020selectListBycAll(yyyy01);
        List<Adj020Result> adj020ResultBYCList = new ArrayList<>();
        for (String tempyyyymm : yyyymmList) {
            Adj020Result adj020Result = (Adj020Result) adj010Service.adj020selectListBycAll(tempyyyymm);
            adj020ResultBYCList.add(adj020Result);
        }

        Adj020Result adj020ResultCUM0 = (Adj020Result) adj010Service.adj020selectListCumAll(yyyy00);
        Adj020Result adj020ResultCUM1 = (Adj020Result) adj010Service.adj020selectListCumAll(yyyy01);
        List<Adj020Result> adj020ResultCUMList = new ArrayList<>();
        for (String tempyyyymm : yyyymmList) {
            Adj020Result adj020Result = (Adj020Result) adj010Service.adj020selectListCumAll(tempyyyymm);
            adj020ResultCUMList.add(adj020Result);
        }
        model.addAttribute("yyyy00", yyyy00);
        model.addAttribute("yyyy01", yyyy01);
        model.addAttribute("yyyymmList", yyyymmList);
        model.addAttribute("adj020ResultBYC0", adj020ResultBYC0);
        model.addAttribute("adj020ResultBYC1", adj020ResultBYC1);
        model.addAttribute("adj020ResultBYCList", adj020ResultBYCList);
        model.addAttribute("adj020ResultCUM0", adj020ResultCUM0);
        model.addAttribute("adj020ResultCUM1", adj020ResultCUM1);
        model.addAttribute("adj020ResultCUMList", adj020ResultCUMList);
        List<Adj020VO> top10bycList = (List<Adj020VO>) ord020DAO.adj020selectTop10List(adj010SearchVO.getDtSearch_frCreateDt());
        initData(top10bycList);
        model.addAttribute("top10bycList", top10bycList);

        adj010SearchVO.setDtSearch_frCreateDt(new StringBuilder(yyyymm).insert(4, "-").toString());
        return "/ism/adj/adj010";
    }

    private List<String> getAllyyyymmList(String yyyymm) {
        Integer today = Integer.valueOf(yyyymm);
        List<String> yyyymmList = new ArrayList<>();
        while (true) {
            String endString = String.valueOf(today).substring(4, 6);
            if (endString.equals("00")) {
                break;
            } else {
                yyyymmList.add(String.valueOf(today));
                today = today - 1;
            }
        }
        Collections.reverse(yyyymmList);

        return yyyymmList;
    }


    @RequestMapping(value = "/ism/adj/adj020.do")
    public String mainList2(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        } else {
            adj010SearchVO.setDtSearch_frCreateDt(adj010SearchVO.getDtSearch_frCreateDt().replaceAll("-", ""));
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
            resultList = adj010Service.adj020selectListByc(adj010SearchVO.getDtSearch_frCreateDt());
        } else {
            resultList = adj010Service.adj020selectListCum(adj010SearchVO.getDtSearch_frCreateDt());
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
        for (int i = 0; i < 10; i++) {
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
        if (!isAuthenticated) {
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


    @Autowired
    Adj040DAO adj040DAO;

    @RequestMapping(value = "/ism/adj/adj040.do")
    public String mainList4(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        } else {
            adj010SearchVO.setDtSearch_frCreateDt(adj010SearchVO.getDtSearch_frCreateDt().replaceAll("-", ""));
        }
        String yyyymm = adj010SearchVO.getDtSearch_frCreateDt();

        adj040DAO.insertInit(yyyymm);

        model.addAttribute("resultList", adj040DAO.selectList(yyyymm));

        adj010SearchVO.setDtSearch_frCreateDt(new StringBuilder(yyyymm).insert(4, "-").toString());
        return "/ism/adj/adj040";
    }

    @ResponseBody
    @RequestMapping(value = "/ism/adj/adj040update.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String mainList4_update(ModelMap model, int adj060id, String closedt, String in1, String in2, String in3, String in4) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(closedt)) {
            return "정상적으로 값을 입력해주세요.";
        } else {
            closedt = closedt.replaceAll("-", "");
        }
        in1 = setStringToNull(in1);
        in2 = setStringToNull(in2);
        in3 = setStringToNull(in3);
        in4 = setStringToNull(in4);


        Map<String, String> param = new HashMap<>();
        param.put("adj060id", String.valueOf(adj060id));
        param.put("closedt", closedt);
        param.put("in1", in1);
        param.put("in2", in2);
        param.put("in3", in3);
        param.put("in4", in4);
        adj040DAO.updateItem(param);

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }


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
        List<Ismwhs010VO> whsListForTop = (List<Ismwhs010VO>) prd010Service.selectWhsAll();
        for (int i = 0; i < 4; i++) {
            if (whsListForTop.size() < 4) {
                Ismwhs010VO ismwhs010VO = new Ismwhs010VO();
                ismwhs010VO.setWhsname("창고없음");
                whsListForTop.add(ismwhs010VO);
            }
        }
        for (int i = 0; i < 4; i++) {
            whsListForTop.get(i).setCmm020id(skd010Service.getSumItemea(i));
        }
        for (int i = 4; i < 8; i++) {
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

    @Autowired
    Adj060DAO adj060DAO;

    @RequestMapping(value = "/ism/adj/adj060.do")
    public String mainList6(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        } else {
            adj010SearchVO.setDtSearch_frCreateDt(adj010SearchVO.getDtSearch_frCreateDt().replaceAll("-", ""));
        }
        String yyyymm = adj010SearchVO.getDtSearch_frCreateDt();
        adj060DAO.insertInit(yyyymm);
        List<Adj060Result> adj060ResultList = (List<Adj060Result>) adj060DAO.selectList(yyyymm);
        Adj060ResultSum adj060ResultSum = new Adj060ResultSum();
        for (Adj060Result adj060Result : adj060ResultList) {
            try {
                adj060ResultSum.a1 = adj060ResultSum.a1 + adj060Result.getDlprice();
            } catch (Exception ignored) {
            }
            try {
                adj060ResultSum.a2 = adj060ResultSum.a2 + adj060Result.getRetprice();
            } catch (Exception ignored) {
            }
            try {
                adj060ResultSum.a3 = adj060ResultSum.a3 + adj060Result.getAirprice();
            } catch (Exception ignored) {
            }
            try {
                adj060ResultSum.a4 = adj060ResultSum.a4 + adj060Result.getDoprice();
            } catch (Exception ignored) {
            }
            try {
                adj060ResultSum.a5 = adj060ResultSum.a5 + adj060Result.getMissprice();
            } catch (Exception ignored) {
            }
            try {
                adj060ResultSum.a6 = adj060ResultSum.a6 + adj060Result.getSaveprice();
            } catch (Exception ignored) {
            }
            try {
                adj060ResultSum.a7 = adj060ResultSum.a7 + adj060Result.getMoveprice();
            } catch (Exception ignored) {
            }
            try {
                adj060ResultSum.a8 = adj060ResultSum.a8 + adj060Result.getWorkprice();
            } catch (Exception ignored) {
            }
            try {
                adj060ResultSum.a9 = adj060ResultSum.a9 + adj060Result.getSubprice();
            } catch (Exception ignored) {
            }
            try {
                adj060ResultSum.a10 = adj060ResultSum.a10 + adj060Result.getClaim();
            } catch (Exception ignored) {
            }
        }


        model.addAttribute("resultList", adj060ResultList);

        model.addAttribute("adj060ResultSum", adj060ResultSum);


        adj010SearchVO.setDtSearch_frCreateDt(new StringBuilder(yyyymm).insert(4, "-").toString());
        return "/ism/adj/adj060";
    }

    @ResponseBody
    @RequestMapping(value = "/ism/adj/adj060update.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String mainList6_update(ModelMap model, int adj060id, String closedt, String in1, String in2, String in3, String in4, String in5,
                                   String in6, String in7, String in8, String in9, String in10, String in11, String in12) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(closedt)) {
            return "정상적으로 값을 입력해주세요.";
        } else {
            closedt = closedt.replaceAll("-", "");
        }
        in1 = setStringToNull(in1);
        in2 = setStringToNull(in2);
        in3 = setStringToNull(in3);
        in4 = setStringToNull(in4);
        in5 = setStringToNull(in5);
        in6 = setStringToNull(in6);
        in7 = setStringToNull(in7);
        in8 = setStringToNull(in8);
        in9 = setStringToNull(in9);
        in10 = setStringToNull(in10);
        in11 = setStringToNull(in11);
        in12 = setStringToNull(in12);


        Map<String, String> param = new HashMap<>();
        param.put("adj060id", String.valueOf(adj060id));
        param.put("closedt", closedt);
        param.put("in1", in1);
        param.put("in2", in2);
        param.put("in3", in3);
        param.put("in4", in4);
        param.put("in5", in5);
        param.put("in6", in6);
        param.put("in7", in7);
        param.put("in8", in8);
        param.put("in9", in9);
        param.put("in10", in10);
        param.put("in11", in11);
        param.put("in12", in12);
        adj060DAO.updateItem(param);

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }

    private String setStringToNull(String in1) {
        if (StringUtils.isBlank(in1)) {
            in1 = null;
        }
        return in1;
    }

    @Autowired
    Adj070DAO adj070DAO;


    @RequestMapping(value = "/ism/adj/adj070.do")
    public String mainList7(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        } else {
            adj010SearchVO.setDtSearch_frCreateDt(adj010SearchVO.getDtSearch_frCreateDt().replaceAll("-", ""));
        }
        String yyyymm = adj010SearchVO.getDtSearch_frCreateDt();

        adj070DAO.insertInit(yyyymm);
        adj040DAO.insertInit(yyyymm);
        List<Adj040Result> adj040Results = (List<Adj040Result>) adj040DAO.selectList(yyyymm);
        model.addAttribute("resultObject", adj070DAO.selectObject(yyyymm));
        model.addAttribute("resultList3", adj040Results);
        model.addAttribute("resultList4", adj040Results);

        adj010SearchVO.setDtSearch_frCreateDt(new StringBuilder(yyyymm).insert(4, "-").toString());
        return "/ism/adj/adj070";
    }

    @ResponseBody
    @RequestMapping(value = "/ism/adj/adj070update1.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String mainList7_update1(ModelMap model, String closedt, String in1, String in2, String in3, String in4) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(closedt)) {
            return "정상적으로 값을 입력해주세요.";
        } else {
            closedt = closedt.replaceAll("-", "");
        }
        in1 = setStringToNull(in1);
        in2 = setStringToNull(in2);
        in3 = setStringToNull(in3);
        in4 = setStringToNull(in4);


        Map<String, String> param = new HashMap<>();
        param.put("closedt", closedt);
        param.put("in1", in1);
        param.put("in2", in2);
        param.put("in3", in3);
        param.put("in4", in4);
        adj070DAO.updateItem07_01(param);

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/ism/adj/adj070update2.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String mainList7_update2(ModelMap model, String closedt, String in1, String in2, String in3) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(closedt)) {
            return "정상적으로 값을 입력해주세요.";
        } else {
            closedt = closedt.replaceAll("-", "");
        }
        in1 = setStringToNull(in1);
        in2 = setStringToNull(in2);
        in3 = setStringToNull(in3);


        Map<String, String> param = new HashMap<>();
        param.put("closedt", closedt);
        param.put("in1", in1);
        param.put("in2", in2);
        param.put("in3", in3);
        adj070DAO.updateItem07_02(param);

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }


    @ResponseBody
    @RequestMapping(value = "/ism/adj/adj070update3.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String mainList7_update3(ModelMap model, int adj060id, String closedt, String in0, String in1, String in2) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(closedt)) {
            return "정상적으로 값을 입력해주세요.";
        } else {
            closedt = closedt.replaceAll("-", "");
        }
        in0 = setStringToNull(in0);
        in1 = setStringToNull(in1);
        in2 = setStringToNull(in2);


        Map<String, String> param = new HashMap<>();
        param.put("adj060id", String.valueOf(adj060id));
        param.put("closedt", closedt);
        param.put("in0", in0);
        param.put("in1", in1);
        param.put("in2", in2);
        adj040DAO.updateItem07_03(param);

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/ism/adj/adj070update4.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String mainList7_update4(ModelMap model, int adj060id, String closedt, String in1, String in2) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(closedt)) {
            return "정상적으로 값을 입력해주세요.";
        } else {
            closedt = closedt.replaceAll("-", "");
        }
        in1 = setStringToNull(in1);
        in2 = setStringToNull(in2);


        Map<String, String> param = new HashMap<>();
        param.put("adj060id", String.valueOf(adj060id));
        param.put("closedt", closedt);
        param.put("in1", in1);
        param.put("in2", in2);
        adj040DAO.updateItem07_04(param);

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }

    @RequestMapping(value = "/ism/adj/adj080.do")
    public String mainList8(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }


        return "/ism/adj/adj080";
    }


}
