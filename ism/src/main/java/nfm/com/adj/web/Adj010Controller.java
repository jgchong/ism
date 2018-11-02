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
import nfm.com.byc.service.Ismbyc010VO;
import nfm.com.exl.util.ExcelManager;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
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
            calender.add(Calendar.MONTH, -1);
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

        Adj020Result adj020ResultCUM0 = (Adj020Result) adj010Service.adj020selectListCumAll(yyyy00);
        Adj020Result adj020ResultCUM1 = (Adj020Result) adj010Service.adj020selectListCumAll(yyyy01);

        List<Adj020Result> adj020ResultBYCList = new ArrayList<>();
        List<Adj020Result> adj020ResultCUMList = new ArrayList<>();
        List<Adj010Result> adj010ResultList = new ArrayList<>();
        for (String tempyyyymm : yyyymmList) {
            Adj020Result adj020Result = (Adj020Result) adj010Service.adj020selectListBycAll(tempyyyymm);
            adj020ResultBYCList.add(adj020Result);

            Adj020Result adj020ResultCUM = (Adj020Result) adj010Service.adj020selectListCumAll(tempyyyymm);
            adj020ResultCUMList.add(adj020ResultCUM);

            Adj010Result adj010Result00 = new Adj010Result();

            Adj040Result adj040Result00 = (Adj040Result) adj040DAO.selectListSum(tempyyyymm);
            Adj070Result adj070Result00 = (Adj070Result) adj070DAO.selectObject(tempyyyymm);

            adj010Result00.price3_1 = adj020ResultCUM.getPriceAll() - adj020Result.getPriceAll();
            try {
                adj010Result00.price3_2 = adj010Result00.price3_1 / adj020ResultCUM.getPriceAll();
            } catch (Exception e) {
                adj010Result00.price3_2 = 9999999L;
            }

            adj010Result00.price4_1 = adj040Result00.getExprice();
            adj010Result00.price4_2 = adj040Result00.getGivesusuprice();
            adj010Result00.price4_3 = 0L;
            adj010Result00.price4_4 = adj070Result00.getPrice1();
            adj010Result00.price4_5 = adj070Result00.getPrice2();
            adj010Result00.price4_6 = adj040Result00.getSaleprice();
            adj010Result00.price4_7 = adj070Result00.getPrice3();
            adj010Result00.price4_sum = adj010Result00.price4_1 + adj010Result00.price4_2 + adj010Result00.price4_3 + adj010Result00.price4_4 + adj010Result00.price4_5 + adj010Result00.price4_6 + adj010Result00.price4_7;
            adj010Result00.price5_1 = adj010Result00.price3_1 - adj010Result00.price4_sum;
            try {
                adj010Result00.price5_2 = adj010Result00.price5_1 / adj020ResultCUM.getPriceAll();
            } catch (Exception e) {
                adj010Result00.price5_2 = 9999999L;
            }
            adj010Result00.price6 = adj040Result00.getExprice();
            adj010Result00.price7 = adj070Result00.getPrice33();
            adj010Result00.price8 = adj010Result00.price5_1 + adj010Result00.price6 - adj010Result00.price7;
            try {
                adj010Result00.price9 = adj010Result00.price8 / adj020ResultCUM.getPriceAll();
            } catch (Exception e) {
                adj010Result00.price9 = 9999999L;
            }
            adj010ResultList.add(adj010Result00);
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


        Adj010Result adj010Result00 = new Adj010Result();
        Adj010Result adj010Result01 = new Adj010Result();


        //전년도 ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

        adj010Result00.price3_1 = adj020ResultCUM0.getPriceAll() - adj020ResultBYC0.getPriceAll();
        try {
            adj010Result00.price3_2 = adj010Result00.price3_1 / adj020ResultCUM0.priceAll;
        } catch (Exception e) {
            adj010Result00.price3_2 = 9999999L;
        }

        Adj040Result adj040Result00 = (Adj040Result) adj040DAO.selectListSum(yyyy00);
        Adj070Result adj070Result00 = (Adj070Result) adj070DAO.selectObject(yyyy00);

        adj010Result00.price4_1 = adj040Result00.getExprice();
        adj010Result00.price4_2 = adj040Result00.getGivesusuprice();
        adj010Result00.price4_3 = 0L;
        adj010Result00.price4_4 = adj070Result00.getPrice1();
        adj010Result00.price4_5 = adj070Result00.getPrice2();
        adj010Result00.price4_6 = adj040Result00.getSaleprice();
        adj010Result00.price4_7 = adj070Result00.getPrice3();
        adj010Result00.price4_sum = adj010Result00.price4_1 + adj010Result00.price4_2 + adj010Result00.price4_3 + adj010Result00.price4_4 + adj010Result00.price4_5 + adj010Result00.price4_6 + adj010Result00.price4_7;
        adj010Result00.price5_1 = adj010Result00.price3_1 - adj010Result00.price4_sum;
        try {
            adj010Result00.price5_2 = adj010Result00.price5_1 / adj020ResultCUM0.priceAll;
        } catch (Exception e) {
            adj010Result00.price5_2 = 9999999L;
        }
        adj010Result00.price6 = adj040Result00.getExprice();
        adj010Result00.price7 = adj070Result00.getPrice33();
        adj010Result00.price8 = adj010Result00.price5_1 + adj010Result00.price6 - adj010Result00.price7;
        try {
            adj010Result00.price9 = adj010Result00.price8 / adj020ResultCUM0.priceAll;
        } catch (Exception e) {
            adj010Result00.price9 = 9999999L;
        }


        // 전년도 끝

        adj010Result01.price3_1 = adj020ResultCUM1.getPriceAll() - adj020ResultBYC1.getPriceAll();
        try {
            adj010Result01.price3_2 = adj010Result01.price3_1 / adj020ResultCUM1.priceAll;
        } catch (Exception e) {
            adj010Result01.price3_2 = 9999999L;
        }

        Adj040Result adj040Result01 = (Adj040Result) adj040DAO.selectListSum(yyyy01);
        Adj070Result adj070Result01 = (Adj070Result) adj070DAO.selectObject(yyyy01);

        adj010Result01.price4_1 = adj040Result01.getExprice();
        adj010Result01.price4_2 = adj040Result01.getGivesusuprice();
        adj010Result01.price4_3 = 0L;
        adj010Result01.price4_4 = adj070Result01.getPrice1();
        adj010Result01.price4_5 = adj070Result01.getPrice2();
        adj010Result01.price4_6 = adj040Result01.getSaleprice();
        adj010Result01.price4_7 = adj070Result01.getPrice3();
        adj010Result01.price4_sum = adj010Result01.price4_1 + adj010Result01.price4_2 + adj010Result01.price4_3 + adj010Result01.price4_4 + adj010Result01.price4_5 + adj010Result01.price4_6 + adj010Result01.price4_7;
        adj010Result01.price5_1 = adj010Result01.price3_1 - adj010Result01.price4_sum;
        try {
            adj010Result01.price5_2 = adj010Result01.price5_1 / adj020ResultCUM1.priceAll;
        } catch (Exception e) {
            adj010Result01.price5_2 = 9999999L;
        }
        adj010Result01.price6 = adj040Result01.getExprice();
        adj010Result01.price7 = adj070Result01.getPrice33();
        adj010Result01.price8 = adj010Result01.price5_1 + adj010Result01.price6 - adj010Result01.price7;
        try {
            adj010Result01.price9 = adj010Result01.price8 / adj020ResultCUM1.priceAll;
        } catch (Exception e) {
            adj010Result01.price9 = 9999999L;
        }

        model.addAttribute("adj010Result00", adj010Result00);
        model.addAttribute("adj010Result01", adj010Result01);
        model.addAttribute("adj010ResultList", adj010ResultList);


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
            calender.add(Calendar.MONTH, -1);
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
            calender.add(Calendar.MONTH, -1);
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
            calender.add(Calendar.MONTH, -1);
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
            calender.add(Calendar.MONTH, -1);
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
            calender.add(Calendar.MONTH, -1);
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
        model.addAttribute("resultList4", adj070DAO.selectAdj0702List(yyyymm));

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
        adj070DAO.updateItem0702(param);

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
    @RequestMapping(value = "/ism/adj/adj070insert4.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String adj070insert4(ModelMap model, String closedt, String in1, String in2, String ln3) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        if (StringUtils.isBlank(closedt) || StringUtils.isBlank(in1)) {
            return "정상적으로 값을 입력해주세요.";
        } else {
            closedt = closedt.replaceAll("-", "");
        }
        in1 = setStringToNull(in1);
        in2 = setStringToNull(in2);
        ln3 = setStringToNull(ln3);


        Map<String, String> param = new HashMap<>();
        param.put("yyyymm", closedt);
        param.put("in1", in1);
        param.put("in2", in2);
        param.put("ln3", ln3);
        adj070DAO.insertAdj0702(param);

        JSONObject resultMessage = new JSONObject();
        resultMessage.put("success", "success");
        return resultMessage.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/ism/adj/adj070update4.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String mainList7_update4(ModelMap model, int adj060id, String in0, String in1, String in2) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        in0 = setStringToNull(in0);
        in1 = setStringToNull(in1);
        in2 = setStringToNull(in2);


        Map<String, String> param = new HashMap<>();
        param.put("adj0702id", String.valueOf(adj060id));
        param.put("in0", in0);
        param.put("in1", in1);
        param.put("in2", in2);
        adj070DAO.updateItem0702(param);

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


    @RequestMapping(value = "/ism/adj/adj010ExcelDownload.do")
    public @ResponseBody
    byte[] adj010ExcelDownload(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) throws Exception {
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

        Adj020Result adj020ResultCUM0 = (Adj020Result) adj010Service.adj020selectListCumAll(yyyy00);
        Adj020Result adj020ResultCUM1 = (Adj020Result) adj010Service.adj020selectListCumAll(yyyy01);

        List<Adj020Result> adj020ResultBYCList = new ArrayList<>();
        List<Adj020Result> adj020ResultCUMList = new ArrayList<>();
        List<Adj010Result> adj010ResultList = new ArrayList<>();
        for (String tempyyyymm : yyyymmList) {
            Adj020Result adj020Result = (Adj020Result) adj010Service.adj020selectListBycAll(tempyyyymm);
            adj020ResultBYCList.add(adj020Result);

            Adj020Result adj020ResultCUM = (Adj020Result) adj010Service.adj020selectListCumAll(tempyyyymm);
            adj020ResultCUMList.add(adj020ResultCUM);

            Adj010Result adj010Result00 = new Adj010Result();

            Adj040Result adj040Result00 = (Adj040Result) adj040DAO.selectListSum(tempyyyymm);
            Adj070Result adj070Result00 = (Adj070Result) adj070DAO.selectObject(tempyyyymm);

            adj010Result00.price3_1 = adj020ResultCUM.getPriceAll() - adj020Result.getPriceAll();
            try {
                adj010Result00.price3_2 = adj010Result00.price3_1 / adj020ResultCUM.getPriceAll();
            } catch (Exception e) {
                adj010Result00.price3_2 = 9999999L;
            }

            adj010Result00.price4_1 = adj040Result00.getExprice();
            adj010Result00.price4_2 = adj040Result00.getGivesusuprice();
            adj010Result00.price4_3 = 0L;
            adj010Result00.price4_4 = adj070Result00.getPrice1();
            adj010Result00.price4_5 = adj070Result00.getPrice2();
            adj010Result00.price4_6 = adj040Result00.getSaleprice();
            adj010Result00.price4_7 = adj070Result00.getPrice3();
            adj010Result00.price4_sum = adj010Result00.price4_1 + adj010Result00.price4_2 + adj010Result00.price4_3 + adj010Result00.price4_4 + adj010Result00.price4_5 + adj010Result00.price4_6 + adj010Result00.price4_7;
            adj010Result00.price5_1 = adj010Result00.price3_1 - adj010Result00.price4_sum;
            try {
                adj010Result00.price5_2 = adj010Result00.price5_1 / adj020ResultCUM.getPriceAll();
            } catch (Exception e) {
                adj010Result00.price5_2 = 9999999L;
            }
            adj010Result00.price6 = adj040Result00.getExprice();
            adj010Result00.price7 = adj070Result00.getPrice33();
            adj010Result00.price8 = adj010Result00.price5_1 + adj010Result00.price6 - adj010Result00.price7;
            try {
                adj010Result00.price9 = adj010Result00.price8 / adj020ResultCUM.getPriceAll();
            } catch (Exception e) {
                adj010Result00.price9 = 9999999L;
            }
            adj010ResultList.add(adj010Result00);
        }


        List<Adj020VO> top10bycList = (List<Adj020VO>) ord020DAO.adj020selectTop10List(adj010SearchVO.getDtSearch_frCreateDt());
        initData(top10bycList);


        Adj010Result adj010Result00 = new Adj010Result();
        Adj010Result adj010Result01 = new Adj010Result();


        //전년도 ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

        adj010Result00.price3_1 = adj020ResultCUM0.getPriceAll() - adj020ResultBYC0.getPriceAll();
        try {
            adj010Result00.price3_2 = adj010Result00.price3_1 / adj020ResultCUM0.priceAll;
        } catch (Exception e) {
            adj010Result00.price3_2 = 9999999L;
        }

        Adj040Result adj040Result00 = (Adj040Result) adj040DAO.selectListSum(yyyy00);
        Adj070Result adj070Result00 = (Adj070Result) adj070DAO.selectObject(yyyy00);

        adj010Result00.price4_1 = adj040Result00.getExprice();
        adj010Result00.price4_2 = adj040Result00.getGivesusuprice();
        adj010Result00.price4_3 = 0L;
        adj010Result00.price4_4 = adj070Result00.getPrice1();
        adj010Result00.price4_5 = adj070Result00.getPrice2();
        adj010Result00.price4_6 = adj040Result00.getSaleprice();
        adj010Result00.price4_7 = adj070Result00.getPrice3();
        adj010Result00.price4_sum = adj010Result00.price4_1 + adj010Result00.price4_2 + adj010Result00.price4_3 + adj010Result00.price4_4 + adj010Result00.price4_5 + adj010Result00.price4_6 + adj010Result00.price4_7;
        adj010Result00.price5_1 = adj010Result00.price3_1 - adj010Result00.price4_sum;
        try {
            adj010Result00.price5_2 = adj010Result00.price5_1 / adj020ResultCUM0.priceAll;
        } catch (Exception e) {
            adj010Result00.price5_2 = 9999999L;
        }
        adj010Result00.price6 = adj040Result00.getExprice();
        adj010Result00.price7 = adj070Result00.getPrice33();
        adj010Result00.price8 = adj010Result00.price5_1 + adj010Result00.price6 - adj010Result00.price7;
        try {
            adj010Result00.price9 = adj010Result00.price8 / adj020ResultCUM0.priceAll;
        } catch (Exception e) {
            adj010Result00.price9 = 9999999L;
        }


        // 전년도 끝

        adj010Result01.price3_1 = adj020ResultCUM1.getPriceAll() - adj020ResultBYC1.getPriceAll();
        try {
            adj010Result01.price3_2 = adj010Result01.price3_1 / adj020ResultCUM1.priceAll;
        } catch (Exception e) {
            adj010Result01.price3_2 = 9999999L;
        }

        Adj040Result adj040Result01 = (Adj040Result) adj040DAO.selectListSum(yyyy01);
        Adj070Result adj070Result01 = (Adj070Result) adj070DAO.selectObject(yyyy01);

        adj010Result01.price4_1 = adj040Result01.getExprice();
        adj010Result01.price4_2 = adj040Result01.getGivesusuprice();
        adj010Result01.price4_3 = 0L;
        adj010Result01.price4_4 = adj070Result01.getPrice1();
        adj010Result01.price4_5 = adj070Result01.getPrice2();
        adj010Result01.price4_6 = adj040Result01.getSaleprice();
        adj010Result01.price4_7 = adj070Result01.getPrice3();
        adj010Result01.price4_sum = adj010Result01.price4_1 + adj010Result01.price4_2 + adj010Result01.price4_3 + adj010Result01.price4_4 + adj010Result01.price4_5 + adj010Result01.price4_6 + adj010Result01.price4_7;
        adj010Result01.price5_1 = adj010Result01.price3_1 - adj010Result01.price4_sum;
        try {
            adj010Result01.price5_2 = adj010Result01.price5_1 / adj020ResultCUM1.priceAll;
        } catch (Exception e) {
            adj010Result01.price5_2 = 9999999L;
        }
        adj010Result01.price6 = adj040Result01.getExprice();
        adj010Result01.price7 = adj070Result01.getPrice33();
        adj010Result01.price8 = adj010Result01.price5_1 + adj010Result01.price6 - adj010Result01.price7;
        try {
            adj010Result01.price9 = adj010Result01.price8 / adj020ResultCUM1.priceAll;
        } catch (Exception e) {
            adj010Result01.price9 = 9999999L;
        }


        adj010SearchVO.setDtSearch_frCreateDt(new StringBuilder(yyyymm).insert(4, "-").toString());


        List<Object> header = new ArrayList<Object>();
        List<List<Object>> data = new ArrayList<List<Object>>();
        header.add("구분");
        header.add("분류");
        header.add(yyyy00 + "년도");
        header.add(yyyy01 + "년도");
        for (int i = 0; i < yyyymmList.size(); i++) {
            header.add(yyyy01 + "년" + (i + 1) + "월");
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add("매출총액");
            obj01.add(decimalFormat.format(adj020ResultCUM0.priceAll));
            obj01.add(decimalFormat.format(adj020ResultCUM1.priceAll));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).priceAll));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add(top10bycList.get(0).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultCUM0.price01));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price01));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price01));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add(top10bycList.get(1).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultCUM0.price02));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price02));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price02));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add(top10bycList.get(2).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultCUM0.price03));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price03));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price03));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add(top10bycList.get(3).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultCUM0.price04));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price04));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price04));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add(top10bycList.get(4).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultCUM0.price05));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price05));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price05));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add(top10bycList.get(5).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultCUM0.price06));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price06));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price06));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add(top10bycList.get(6).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultCUM0.price07));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price07));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price07));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add(top10bycList.get(7).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultCUM0.price08));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price08));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price08));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add(top10bycList.get(8).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultCUM0.price09));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price09));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price09));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add(top10bycList.get(9).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultCUM0.price10));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price10));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price10));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("1. 매출액");
            obj01.add("기타");
            obj01.add(decimalFormat.format(adj020ResultCUM0.price11));
            obj01.add(decimalFormat.format(adj020ResultCUM1.price11));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultCUMList.get(i).price11));
            }
            data.add(obj01);
        }

        //MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add("매입총액");
            obj01.add(decimalFormat.format(adj020ResultBYC0.priceAll));
            obj01.add(decimalFormat.format(adj020ResultBYC1.priceAll));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).priceAll));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add(top10bycList.get(0).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultBYC0.price01));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price01));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price01));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add(top10bycList.get(1).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultBYC0.price02));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price02));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price02));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add(top10bycList.get(2).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultBYC0.price03));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price03));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price03));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add(top10bycList.get(3).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultBYC0.price04));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price04));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price04));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add(top10bycList.get(4).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultBYC0.price05));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price05));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price05));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add(top10bycList.get(5).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultBYC0.price06));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price06));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price06));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add(top10bycList.get(6).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultBYC0.price07));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price07));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price07));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add(top10bycList.get(7).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultBYC0.price08));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price08));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price08));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add(top10bycList.get(8).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultBYC0.price09));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price09));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price09));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add(top10bycList.get(9).getByc010name());
            obj01.add(decimalFormat.format(adj020ResultBYC0.price10));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price10));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price10));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("2. 매입액");
            obj01.add("기타");
            obj01.add(decimalFormat.format(adj020ResultBYC0.price11));
            obj01.add(decimalFormat.format(adj020ResultBYC1.price11));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj020ResultBYCList.get(i).price11));
            }
            data.add(obj01);
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
        model.addAttribute("top10bycList", top10bycList);

        model.addAttribute("adj010Result00", adj010Result00);
        model.addAttribute("adj010Result01", adj010Result01);
        model.addAttribute("adj010ResultList", adj010ResultList);

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("3.1 매출총이익");
            obj01.add("(1 - 2)");
            obj01.add(decimalFormat.format(adj010Result00.price3_1));
            obj01.add(decimalFormat.format(adj010Result01.price3_1));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price3_1));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("3.2 매출총이익률");
            obj01.add("3 / 1");
            obj01.add(adj010Result00.price3_2 + "%");
            obj01.add(adj010Result01.price3_2 + "%");
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(adj010ResultList.get(i).price3_2 + "%");
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("4. 판관비");
            obj01.add("판관비 총액");
            obj01.add(decimalFormat.format(adj010Result00.price4_sum));
            obj01.add(decimalFormat.format(adj010Result01.price4_sum));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price4_sum));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("4. 판관비");
            obj01.add("지급수수료(고객사 선공제)");
            obj01.add(decimalFormat.format(adj010Result00.price4_1));
            obj01.add(decimalFormat.format(adj010Result01.price4_1));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price4_1));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("4. 판관비");
            obj01.add("지급수수료(영업사)");
            obj01.add(decimalFormat.format(adj010Result00.price4_2));
            obj01.add(decimalFormat.format(adj010Result01.price4_2));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price4_2));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("4. 판관비");
            obj01.add("지급수수료(PG 외)");
            obj01.add(decimalFormat.format(adj010Result00.price4_3));
            obj01.add(decimalFormat.format(adj010Result01.price4_3));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price4_3));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("4. 판관비");
            obj01.add("광고선전비(사은품, 협찬)");
            obj01.add(decimalFormat.format(adj010Result00.price4_4));
            obj01.add(decimalFormat.format(adj010Result01.price4_4));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price4_4));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("4. 판관비");
            obj01.add("광고선전비(샘플)");
            obj01.add(decimalFormat.format(adj010Result00.price4_5));
            obj01.add(decimalFormat.format(adj010Result01.price4_5));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price4_5));
            }
            data.add(obj01);
        }
        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("4. 판관비");
            obj01.add("운반보관비");
            obj01.add(decimalFormat.format(adj010Result00.price4_6));
            obj01.add(decimalFormat.format(adj010Result01.price4_6));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price4_6));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("4. 판관비");
            obj01.add("기타");
            obj01.add(decimalFormat.format(adj010Result00.price4_7));
            obj01.add(decimalFormat.format(adj010Result01.price4_7));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price4_7));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("5.1 영업손익");
            obj01.add("(3 - 4)");
            obj01.add(decimalFormat.format(adj010Result00.price5_1));
            obj01.add(decimalFormat.format(adj010Result01.price5_1));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price5_1));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("5.2 매출총이익률");
            obj01.add("(5 / 1)");
            obj01.add(adj010Result00.price5_2 + "%");
            obj01.add(adj010Result01.price5_2 + "%");
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(adj010ResultList.get(i).price5_2 + "%");
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("6 영업외 수익");
            obj01.add("판매장려금 외");
            obj01.add(decimalFormat.format(adj010Result00.price6));
            obj01.add(decimalFormat.format(adj010Result01.price6));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price6));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("7 영업외 수익");
            obj01.add("파손, 망실, 분실 외");
            obj01.add(decimalFormat.format(adj010Result00.price7));
            obj01.add(decimalFormat.format(adj010Result01.price7));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price7));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("8.1 순손익");
            obj01.add("(5 + 6 - 7)");
            obj01.add(decimalFormat.format(adj010Result00.price8));
            obj01.add(decimalFormat.format(adj010Result01.price8));
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(decimalFormat.format(adj010ResultList.get(i).price8));
            }
            data.add(obj01);
        }

        {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("8.2 순손익률");
            obj01.add("(8 / 1)");
            obj01.add(adj010Result00.price9 + "%");
            obj01.add(adj010Result01.price9 + "%");
            for (int i = 0; i < yyyymmList.size(); i++) {
                obj01.add(adj010ResultList.get(i).price9 + "%");
            }
            data.add(obj01);
        }

        ExcelManager excelManager = new ExcelManager(header, data);
        excelManager.setSheetName("종합판매정산" + adj010SearchVO.getDtSearch_frCreateDt());
        excelManager.setWidth(6000);
        excelManager.setStartRow(0);
        excelManager.setStartCol(0);
        excelManager.setExcelType("xlsx");
        excelManager.addRowColor(1, 31);
        excelManager.addRowColor(13, 31);
        excelManager.addRowColor(25, 29);
        excelManager.addRowColor(26, 29);
        excelManager.addRowColor(27, 31);
        excelManager.addRowColor(35, 29);
        excelManager.addRowColor(36, 29);
        excelManager.addRowColor(39, 29);
        excelManager.addRowColor(40, 29);


        byte[] bytes = excelManager.makeExcel();

        response.setHeader("Content-Disposition", "attachment; filename=ManagementExcel01.xlsx");
        response.setContentLength(bytes.length);
        response.setContentType("application/vnd.ms-excel");

        return bytes;
    }


    @RequestMapping(value = "/ism/adj/adj020ExcelDownload.do")
    public @ResponseBody
    byte[] adj020ExcelDownload(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) throws Exception {
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


        List<Adj020Result> resultList;


        List<Adj020VO> top10bycList = (List<Adj020VO>) ord020DAO.adj020selectTop10List(adj010SearchVO.getDtSearch_frCreateDt());
        initData(top10bycList);
        model.addAttribute("top10bycList", top10bycList);



        List<Object> header = new ArrayList<Object>();
        List<List<Object>> data = new ArrayList<List<Object>>();
        header.add("구분");
        header.add("매출처");
        header.add("쇼핑몰");
        header.add("합계");

        for (Adj020VO adj020VO : top10bycList) {
            header.add(adj020VO.getByc010name());
        }
        header.add("기타");

        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        resultList = adj010Service.adj020selectListCum(adj010SearchVO.getDtSearch_frCreateDt());
        Collections.sort(resultList, new Ascending());

        for (Adj020Result adj020Result : resultList) {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("매출액");
            obj01.add(adj020Result.cum010name);
            obj01.add(adj020Result.name);
            obj01.add(decimalFormat.format(adj020Result.priceAll));
            obj01.add(decimalFormat.format(adj020Result.price01));
            obj01.add(decimalFormat.format(adj020Result.price02));
            obj01.add(decimalFormat.format(adj020Result.price03));
            obj01.add(decimalFormat.format(adj020Result.price04));
            obj01.add(decimalFormat.format(adj020Result.price05));
            obj01.add(decimalFormat.format(adj020Result.price06));
            obj01.add(decimalFormat.format(adj020Result.price07));
            obj01.add(decimalFormat.format(adj020Result.price08));
            obj01.add(decimalFormat.format(adj020Result.price09));
            obj01.add(decimalFormat.format(adj020Result.price10));
            obj01.add(decimalFormat.format(adj020Result.price11));
            data.add(obj01);
        }

        resultList = adj010Service.adj020selectListByc(adj010SearchVO.getDtSearch_frCreateDt());
        Collections.sort(resultList, new Ascending());
        int temp = data.size();
        for (Adj020Result adj020Result : resultList) {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add("매입액");
            obj01.add(adj020Result.cum010name);
            obj01.add(adj020Result.name);
            obj01.add(decimalFormat.format(adj020Result.priceAll));
            obj01.add(decimalFormat.format(adj020Result.price01));
            obj01.add(decimalFormat.format(adj020Result.price02));
            obj01.add(decimalFormat.format(adj020Result.price03));
            obj01.add(decimalFormat.format(adj020Result.price04));
            obj01.add(decimalFormat.format(adj020Result.price05));
            obj01.add(decimalFormat.format(adj020Result.price06));
            obj01.add(decimalFormat.format(adj020Result.price07));
            obj01.add(decimalFormat.format(adj020Result.price08));
            obj01.add(decimalFormat.format(adj020Result.price09));
            obj01.add(decimalFormat.format(adj020Result.price10));
            obj01.add(decimalFormat.format(adj020Result.price11));
            data.add(obj01);
        }



        ExcelManager excelManager = new ExcelManager(header, data);
        excelManager.setSheetName("상품별정산" + adj010SearchVO.getDtSearch_frCreateDt());
        excelManager.setWidth(6000);
        excelManager.setStartRow(0);
        excelManager.setStartCol(0);
        excelManager.setExcelType("xlsx");
        excelManager.addRowColor(temp+1, 31);

        byte[] bytes = excelManager.makeExcel();

        response.setHeader("Content-Disposition", "attachment; filename=ManagementExcel02.xlsx");
        response.setContentLength(bytes.length);
        response.setContentType("application/vnd.ms-excel");

        return bytes;
    }

    @RequestMapping(value = "/ism/adj/adj030ExcelDownload.do")
    public @ResponseBody
    byte[] adj030ExcelDownload(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) throws Exception {
        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        }

        adj010SearchVO.setFirstIndex(0);
        adj010SearchVO.setRecordCountPerPage(100000);


//        Adj030AllResult adj030Result = adj010Service.getAdj030AllResult();

        List<Object> header = new ArrayList<Object>();
        List<List<Object>> data = new ArrayList<List<Object>>();
        header.add("No");
        header.add("매입처");
        header.add("상품코드");
        header.add("상품명");
        header.add("이월재고");
        header.add("입고");
        header.add("출고");
        header.add("파손");
        header.add("재고");

        List<Ismbyc010VO> ismbyc010VOList = (List<Ismbyc010VO>) prd010Service.selectBycAll();

        int count = 1;
        for (Ismbyc010VO ismbyc010VO : ismbyc010VOList) {
            adj010SearchVO.setDtSearch_adj030_byc(String.valueOf(ismbyc010VO.getByc010id()));
            List<Adj030Result> adj030ResultList = adj010Service.adj030selectList(adj010SearchVO);
            for (Adj030Result adj030Result : adj030ResultList) {
                List<Object> obj01 = new ArrayList<Object>();
                obj01.add(count);
                obj01.add(ismbyc010VO.getBycname());
                obj01.add(adj030Result.getItemcode());
                obj01.add(adj030Result.getItemname());
                obj01.add(adj030Result.getItemeaEx());
                obj01.add(adj030Result.getItemeaAll());
                obj01.add(adj030Result.getItemeaOut());
                obj01.add(adj030Result.getItemeaBroken());
                obj01.add(adj030Result.getItemeaNamuge());
                data.add(obj01);
                count ++;
            }
        }

        ExcelManager excelManager = new ExcelManager(header, data);
        excelManager.setSheetName("상품수불부" + adj010SearchVO.getDtSearch_frCreateDt());
        excelManager.setWidth(6000);
        excelManager.setStartRow(0);
        excelManager.setStartCol(0);
        excelManager.setExcelType("xlsx");

        byte[] bytes = excelManager.makeExcel();

        response.setHeader("Content-Disposition", "attachment; filename=ManagementExcel03.xlsx");
        response.setContentLength(bytes.length);
        response.setContentType("application/vnd.ms-excel");

        return bytes;
    }

    @RequestMapping(value = "/ism/adj/adj040ExcelDownload.do")
    public @ResponseBody
    byte[] adj040ExcelDownload(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) throws Exception {
        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        } else {
            adj010SearchVO.setDtSearch_frCreateDt(adj010SearchVO.getDtSearch_frCreateDt().replaceAll("-", ""));
        }
        String yyyymm = adj010SearchVO.getDtSearch_frCreateDt();

        List<Adj040Result> adj040ResultList = (List<Adj040Result>) adj040DAO.selectList(yyyymm);


        List<Object> header = new ArrayList<Object>();
        List<List<Object>> data = new ArrayList<List<Object>>();
        header.add("업체명");
        header.add("세금계산서발행여부");
        header.add("과세");
        header.add("면세");
        header.add("공급가합계");
        header.add("수수료");
        header.add("기타");
        header.add("수금일");
        header.add("수금액");

        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        for (Adj040Result adj020Result : adj040ResultList) {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add(adj020Result.getCum010name());
            if ("1".equals(adj020Result.getAccount())) {
                obj01.add("정발행");
            } else {
                obj01.add("역발행");
            }
            obj01.add(decimalFormat.format(Long.parseLong(adj020Result.getTaxprice())));
            obj01.add(decimalFormat.format(Long.parseLong(adj020Result.getTaxfreeprice())));
            obj01.add(decimalFormat.format(adj020Result.getPrice()));
            obj01.add(adj020Result.getSusuprice());
            obj01.add(adj020Result.getNamuge());
            obj01.add(adj020Result.getSugumdate());
            obj01.add(adj020Result.getSugumprice());
            data.add(obj01);
        }


        ExcelManager excelManager = new ExcelManager(header, data);
        excelManager.setSheetName("수금관리대장" + adj010SearchVO.getDtSearch_frCreateDt());
        excelManager.setWidth(6000);
        excelManager.setStartRow(0);
        excelManager.setStartCol(0);
        excelManager.setExcelType("xlsx");

        byte[] bytes = excelManager.makeExcel();

        response.setHeader("Content-Disposition", "attachment; filename=ManagementExcel04.xlsx");
        response.setContentLength(bytes.length);
        response.setContentType("application/vnd.ms-excel");

        return bytes;
    }

    @RequestMapping(value = "/ism/adj/adj060ExcelDownload.do")
    public @ResponseBody
    byte[] adj060ExcelDownload(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) throws Exception {
        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        } else {
            adj010SearchVO.setDtSearch_frCreateDt(adj010SearchVO.getDtSearch_frCreateDt().replaceAll("-", ""));
        }
        String yyyymm = adj010SearchVO.getDtSearch_frCreateDt();

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


        List<Object> header = new ArrayList<Object>();
        List<List<Object>> data = new ArrayList<List<Object>>();
        header.add("구분");
        header.add("정상택배비");
        header.add("반품택배비");
        header.add("항공료");
        header.add("도선료");
        header.add("오발송");
        header.add("보관료");
        header.add("상차비");
        header.add("작업비");
        header.add("부자재비");
        header.add("클레임");
        header.add("기타");
        header.add("메모");

        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        for (Adj060Result adj060Result : adj060ResultList) {
            List<Object> obj01 = new ArrayList<Object>();
            obj01.add(adj060Result.getAdj060name());
            obj01.add(adj060Result.getDlprice());
            obj01.add(adj060Result.getRetprice());
            obj01.add(adj060Result.getAirprice());
            obj01.add(adj060Result.getDoprice());
            obj01.add(adj060Result.getMissprice());
            obj01.add(adj060Result.getSaveprice());
            obj01.add(adj060Result.getMoveprice());
            obj01.add(adj060Result.getWorkprice());
            obj01.add(adj060Result.getSubprice());
            obj01.add(adj060Result.getClaim());
            obj01.add(adj060Result.getNamuge());
            obj01.add(adj060Result.getMemo());
            data.add(obj01);
        }
        List<Object> obj01 = new ArrayList<Object>();
        obj01.add("합계");
        obj01.add(decimalFormat.format(adj060ResultSum.a1));
        obj01.add(decimalFormat.format(adj060ResultSum.a2));
        obj01.add(decimalFormat.format(adj060ResultSum.a3));
        obj01.add(decimalFormat.format(adj060ResultSum.a4));
        obj01.add(decimalFormat.format(adj060ResultSum.a5));
        obj01.add(decimalFormat.format(adj060ResultSum.a6));
        obj01.add(decimalFormat.format(adj060ResultSum.a7));
        obj01.add(decimalFormat.format(adj060ResultSum.a8));
        obj01.add(decimalFormat.format(adj060ResultSum.a9));
        obj01.add(decimalFormat.format(adj060ResultSum.a10));
        obj01.add("");
        obj01.add("");
        data.add(obj01);


        ExcelManager excelManager = new ExcelManager(header, data);
        excelManager.setSheetName("운송비대장" + adj010SearchVO.getDtSearch_frCreateDt());
        excelManager.setWidth(6000);
        excelManager.setStartRow(0);
        excelManager.setStartCol(0);
        excelManager.setExcelType("xlsx");

        byte[] bytes = excelManager.makeExcel();

        response.setHeader("Content-Disposition", "attachment; filename=ManagementExcel06.xlsx");
        response.setContentLength(bytes.length);
        response.setContentType("application/vnd.ms-excel");

        return bytes;
    }

    @RequestMapping(value = "/ism/adj/adj070ExcelDownload.do")
    public @ResponseBody
    byte[] adj070ExcelDownload(@ModelAttribute("adj010SearchVO") Adj010SearchVO adj010SearchVO, ModelMap model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) throws Exception {
        if (StringUtils.isBlank(adj010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, 0);
            adj010SearchVO.setDtSearch_frCreateDt(formatter.format(calender.getTime()));
        } else {
            adj010SearchVO.setDtSearch_frCreateDt(adj010SearchVO.getDtSearch_frCreateDt().replaceAll("-", ""));
        }


        List<Object> header = new ArrayList<Object>();
        List<List<Object>> data = new ArrayList<List<Object>>();
        header.add("구분");
        header.add("매출처");
        header.add("쇼핑몰");
        header.add("합계");


        DecimalFormat decimalFormat = new DecimalFormat("###,###");

//        for (Adj020Result adj020Result : resultList) {
//            List<Object> obj01 = new ArrayList<Object>();
//            obj01.add("매출액");
//            obj01.add(adj020Result.cum010name);
//            obj01.add(adj020Result.name);
//            obj01.add(decimalFormat.format(adj020Result.priceAll));
//            obj01.add(decimalFormat.format(adj020Result.price01));
//
//            data.add(obj01);
//        }


        ExcelManager excelManager = new ExcelManager(header, data);
        excelManager.setSheetName("상품별정산" + adj010SearchVO.getDtSearch_frCreateDt());
        excelManager.setWidth(6000);
        excelManager.setStartRow(0);
        excelManager.setStartCol(0);
        excelManager.setExcelType("xlsx");

        byte[] bytes = excelManager.makeExcel();

        response.setHeader("Content-Disposition", "attachment; filename=ManagementExcel02.xlsx");
        response.setContentLength(bytes.length);
        response.setContentType("application/vnd.ms-excel");

        return bytes;
    }


}
