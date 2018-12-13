package nfm.com.prd.web;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import nfm.com.byc.service.Ismbyc010VO;
import nfm.com.exl.util.ExcelManager;
import nfm.com.prd.service.Prd010SearchVO;
import nfm.com.prd.service.Prd010Service;
import nfm.com.prd.service.Prd010VO;
import nfm.com.prd.service.impl.Prd010DAO;
import nfm.com.whs.service.Ismwhs010VO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Prd010Controller {
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
     * ord020Service
     */
    @Resource(name = "prd010Service")
    private Prd010Service prd010Service;

    /**
     * 주문수집 목록
     *
     * @param prd010SearchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ism/prd/prd010.do")
    public String mainList(@ModelAttribute("prd010SearchVO") Prd010SearchVO prd010SearchVO, ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
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
        vo.setCodeId("ISM090");    //주문상태필드
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
     *
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ism/prd/prd010Detail.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String detailSelect(ModelMap model, String currentItemcoed) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }

        return prd010Service.selectWithItemcode(currentItemcoed);
    }

    /**
     * 상품 상세 저장
     *
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ism/prd/prd010DetailSave.do", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public String detailSave(ModelMap model, String currentItemcoed, String detail_category, String detail_itemcrosstype, String detail_byc, String detail_itemname, String detail_itemopt, String detail_itemea, String detail_itembuyprice,
                             String detail_itembuydlvprice, String detail_itemgubun, String detail_pristock, String detail_itemsize, String detail_cartonqty, String detail_palletqty, String detail_childItemcode, String detail_childItemea, String detail_taxfree, String detail_salecode
    ) throws Exception {

        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        if ("F".equals(detail_itemcrosstype)) {
            List<Ismbyc010VO> ismbyc010VOList = (List<Ismbyc010VO>) prd010Service.selectBycAll();
            detail_byc = ""+ismbyc010VOList.get(0).getByc010id();
        }
        if (StringUtils.isBlank(detail_category) || StringUtils.isBlank(detail_itemcrosstype) || StringUtils.isBlank(detail_taxfree) || StringUtils.isBlank(detail_byc) || StringUtils.isBlank(detail_itemname) || StringUtils.isBlank(detail_itemgubun)
                || ("2".equals(detail_itemgubun) && StringUtils.isBlank(detail_pristock)) || ("3".equals(detail_itemgubun) && StringUtils.isBlank(detail_pristock))) {
            return "정상적으로 값을 입력해주세요.";
        }
        Map<String, String> param = new HashMap<>();
        param.put("detail_category", detail_category);
        param.put("detail_itemcrosstype", detail_itemcrosstype);
        param.put("detail_byc", detail_byc);
        param.put("detail_itemname", detail_itemname);
        param.put("detail_taxfree", detail_taxfree);

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

        if (StringUtils.isBlank(detail_salecode)) {
            detail_salecode = null;
        }
        param.put("detail_salecode", detail_salecode);

        //만약 "1".equals(detail_itemgubun)인경우 null을 넣을것
        param.put("detail_itemgubun", detail_itemgubun);
        if ("1".equals(detail_itemgubun)) {
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
            if (!StringUtils.isBlank(detail_childItemcode) && !StringUtils.isBlank(detail_childItemea)) {
                prd010Service.updateCross(result, detail_childItemcode, detail_childItemea);
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
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        return prd010Service.selectAll();
    }

    @ResponseBody
    @RequestMapping(value = "/ism/prd/prd010selectGubun2.do", produces = "application/json; charset=utf8")
    public String detailAutoSearch2(ModelMap model) throws Exception {
        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "uat/uia/EgovLoginUsr";
        }
        return prd010Service.selectGubun2();
    }


    private void prd010SearchVOInit(@ModelAttribute("prd010SearchVO") Prd010SearchVO prd010SearchVO) throws Exception {
        /** 조회 기준 날짜, 다른 검색파라미터 초기화 */
        if (StringUtils.isBlank(prd010SearchVO.getDtSearch_frCreateDt())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DATE, -30);
            prd010SearchVO.setDtSearch_frCreateDt("2015-01-01");
        }
        if (StringUtils.isBlank(prd010SearchVO.getDtSearch_toCreateDt())) {
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


    @Resource(name = "prd010DAO")
    private Prd010DAO prd010DAO;

    @RequestMapping(value = "/ism/prd/prd010ExcelDownload.do")
    public @ResponseBody
    byte[] ord020ExcelDownload(@ModelAttribute("prd010SearchVO") Prd010SearchVO prd010SearchVO, ModelMap model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) throws Exception {


        prd010SearchVO.setFirstIndex(0);
        prd010SearchVO.setRecordCountPerPage(100000);
        List<Prd010VO> prd010VOList = (List<Prd010VO>) prd010DAO.selectList(prd010SearchVO);

        List<Ismbyc010VO> ismbyc010VOList = (List<Ismbyc010VO>) prd010Service.selectBycAll();
        List<Ismwhs010VO> ismwhs010VOList = (List<Ismwhs010VO>) prd010Service.selectWhsAll();

        List<Object> header = new ArrayList<Object>();
        List<List<Object>> data = new ArrayList<List<Object>>();
        header.add("NO");
        header.add("결합여부");
        header.add("매입처");
        header.add("상품코드");
        header.add("상품명");
        header.add("상품카테고리");
        header.add("면세여부");
        header.add("옵션");
        header.add("단위수량");
        header.add("매입단가");
        header.add("매입배송비");
        header.add("구분");
        header.add("우선창고");
        header.add("상품크기");
        header.add("카톤수량");
        header.add("피렛트수량");

        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("ISM090");    //주문상태필드
        List<CmmnDetailCode> cmmnDetailCodeList = egovCmmUseService.selectCmmCodeDetail(vo);


        int index = 1;
        for (Prd010VO prd010VO : prd010VOList) {
            List<Object> obj = new ArrayList<Object>();
            obj.add("" + index);
            if ("S".equals(prd010VO.getItemcrosstype())) {
                obj.add("단품");
            } else {
                obj.add("결합");
            }

            for (Ismbyc010VO ismbyc010VO : ismbyc010VOList) {
                if (ismbyc010VO.getByc010id() == prd010VO.getByc010id()) {
                    obj.add(ismbyc010VO.getBycname());
                }
            }
            obj.add(prd010VO.getItemcode());
            obj.add(prd010VO.getItemname());

            for (CmmnDetailCode cmmnDetailCode : cmmnDetailCodeList) {
                if (cmmnDetailCode.getCode().equals(String.valueOf(prd010VO.getItemcat1()))) {
                    obj.add(cmmnDetailCode.getCodeNm());
                }
            }

            if ("0".equals(prd010VO.getTaxfree())) {
                obj.add("과세");
            } else {
                obj.add("비과세");
            }


            obj.add(prd010VO.getItemopt());
            obj.add(prd010VO.getItemea());
            obj.add(prd010VO.getItembuyprice());
            obj.add(prd010VO.getItembuydlvprice());

            if ("1".equals(prd010VO.getItemgubun())) {
                obj.add("제조사출고");
            } else if ("2".equals(prd010VO.getItemgubun())) {
                obj.add("당사재고출고");
            } else {
                obj.add("사은품");
            }
            obj.add(prd010VO.getWhsname());
            obj.add(prd010VO.getItemsize());
            obj.add(prd010VO.getCartonqty());
            obj.add(prd010VO.getPalletqty());
            data.add(obj);
            index++;
        }

        String[] excelCellType = {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"};

        List<String> explainText = new ArrayList<>();
        explainText.add("1. 상품명과 매입처를 Key값으로 하여 동시에 중복된 경우, 해당 열은 등록되지 않습니다.");
        explainText.add("2. 숫자가 들어가야 하는 자리에 문자가 들어가는 경우, 해당 열은 등록되지 않습니다.");
        explainText.add("3. 매입처가 없거나, 등록된 명칭의 매입처가 아닌경우, 해당 열은 등록되지 않습니다.");
        explainText.add("4. 상품카테고리가 없거나, 등록된 명칭의 상품카테고리가 아닌경우, 해당 열은 등록되지 않습니다.");
        explainText.add("5. 면세여부가 없거나, 과세 또는 비과세로 등록된 단어가 아닐경우, 해당 열은 등록되지 않습니다.");
        explainText.add("6. 구분이 없거나, 제조사출고 또는 당사재고출고 또는 사은품 으로 등록된 단어가 아닐경우, 해당 열은 등록되지 않습니다.");
        explainText.add("7. 당사재고출고 또는 사은품의 경우, 우선창고가 없거나, 등록된 명칭의 창고가 아닌경우, 해당 열은 등록되지 않습니다.");
        explainText.add("8. 상품코드가 존재하는 경우, 해당 매입단가가 수정되도록 등록이 됩니다.");
        explainText.add("EX) 예를들어 매입단가에 null이라는 값이 들어간 경우 해당 열은 등록되지 않습니다.");
        explainText.add("EX) 예를들어 면세여부에 면세라는 값이 들어간경우 해당 열은 등록되지 않고 비과세라는 값이 들어가야 해당 열은 등록이 됩니다.");
        explainText.add("해당 규칙을 잘 지켜서 값을 입력해야 제대로 상품이 등록됩니다.");


        ExcelManager excelManager = new ExcelManager(header, data);
        excelManager.setSheetName("운영상품관리");
        excelManager.setWidth(6000);
        excelManager.setCellDataType(excelCellType);
        excelManager.setStartRow(0);
        excelManager.setStartCol(0);
        excelManager.setExcelType("xls");
        excelManager.makeExpailnSheet("주의사항");
        excelManager.setExplainTextList(explainText);

        byte[] bytes = excelManager.makeExcel();

        response.setHeader("Content-Disposition", "attachment; filename=ItemManagementExcel.xls");
        response.setContentLength(bytes.length);
        response.setContentType("application/vnd.ms-excel");

        return bytes;
    }

    /**
     * 운영상품 데이터 일괄 업로드
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ism/prd/prd010batchup.do", produces = "text/plain; charset=utf8")
    public String upload(MultipartHttpServletRequest mtfrequest) throws Exception {
        List<Ismbyc010VO> ismbyc010VOList = (List<Ismbyc010VO>) prd010Service.selectBycAll();
        List<Ismwhs010VO> ismwhs010VOList = (List<Ismwhs010VO>) prd010Service.selectWhsAll();
        List<Prd010VO> prd010VOList = (List<Prd010VO>) prd010DAO.selectAll(); //단품 리스트
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("ISM090");    //주문상태필드
        List<CmmnDetailCode> cmmnDetailCodeList = egovCmmUseService.selectCmmCodeDetail(vo);


        Map<String, Integer> bycMap = new HashMap<>();
        for (Ismbyc010VO ismbyc010VO : ismbyc010VOList) {
            bycMap.put(ismbyc010VO.getBycname(), ismbyc010VO.getByc010id());
        }

        Map<String, Integer> whsMap = new HashMap<>();
        for (Ismwhs010VO ismwhs010VO : ismwhs010VOList) {
            whsMap.put(ismwhs010VO.getWhsname(), ismwhs010VO.getWhs010id());
        }

        Map<String, Integer> prdMap = new HashMap<>();
        Map<String, Integer> prdItemcodeMap = new HashMap<>();
        for (Prd010VO prd010VO : prd010VOList) {
            prdMap.put("" + prd010VO.getByc010id() + prd010VO.getItemname(), prd010VO.getByc010id());
            prdItemcodeMap.put("" + prd010VO.getItemcode(), 0);
        }

        MultipartFile mf = mtfrequest.getFile("file1");
        String result = "";
        try {
            HSSFWorkbook wb = new HSSFWorkbook(mf.getInputStream());
            for (Row row : wb.getSheetAt(0)) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                try {
                    Map<String, String> param = new HashMap<>();
                    String itemname;
                    String byc010name;
                    String itemcrossType;

                    boolean isModify = false;

                    itemcrossType = getRowStringValue(row, 1);
                    if (StringUtils.isBlank(itemcrossType) || "결합".equals(itemcrossType)) {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <결합여부 없음>\n";
                        continue;
                    } else if ("단품".equals(itemcrossType)) {
                        param.put("detail_itemcrosstype", "S");
                    } else {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 결합여부 값(단품만 입력가능)>\n";
                        continue;
                    }

                    byc010name = getRowStringValue(row, 2);
                    if (StringUtils.isBlank(byc010name)) {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <매입처 없음>\n";
                        continue;
                    }

                    if (bycMap.get(byc010name) == null) {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 매입처 이름>\n";
                        continue;
                    } else {
                        param.put("detail_byc", "" + bycMap.get(byc010name));
                    }


                    itemname = getRowStringValue(row, 4);
                    if (StringUtils.isBlank(itemname)) {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <상품이름 없음>\n";
                        continue;
                    }

                    if (prdMap.get("" + bycMap.get(byc010name) + itemname) == null) {
                        param.put("detail_itemname", itemname);
                    } else {
                        if (prdItemcodeMap.containsKey("" + getRowStringValue(row, 3))) {
                            param.put("itemcode", getRowStringValue(row, 3));
                            isModify = true;
                        } else {
                            result = result + row.getRowNum() + "번째 열 수정을 실패하였습니다. <잘못된 상품코드>";
                            continue;
                        }
                    }
                    //상품명, 매입처, 결합여부 등록완료
                    if (prdItemcodeMap.containsKey("" + getRowStringValue(row, 3))) {
                        param.put("itemcode", getRowStringValue(row, 3));
                        isModify = true;
                    }


                    String itemCat1;

                    itemCat1 = getRowStringValue(row, 5);
                    if (StringUtils.isBlank(itemCat1)) {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <카테고리 값 없음>\n";
                        continue;
                    }

                    boolean isPass = false;
                    for (CmmnDetailCode cmmnDetailCode : cmmnDetailCodeList) {
                        if (cmmnDetailCode.getCodeNm().equals(String.valueOf(itemCat1))) {
                            param.put("detail_category", cmmnDetailCode.getCode());
                            isPass = true;
                            break;
                        }
                    }

                    if (isPass == false) {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다.  <잘못된 카테고리 이름>\n";
                        continue;
                    }
                    isPass = false;


                    String taxfree;

                    taxfree = getRowStringValue(row, 6);
                    if (StringUtils.isBlank(taxfree)) {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <면세 값 없음>\n";
                        continue;
                    } else if ("과세".equals(taxfree)) {
                        param.put("detail_taxfree", "0");
                    } else if ("비과세".equals(taxfree)) {
                        param.put("detail_taxfree", "1");
                    } else {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 면세 값 입력(과세 와 비과세 중 하나 입력)>\n";
                        continue;
                    }

                    param.put("detail_itemopt", emptyStringToNull(getRowStringValue(row, 7)));
                    param.put("detail_itemea", emptyStringToNull(getRowStringValue(row, 8)));
                    param.put("detail_itembuyprice", emptyStringToNull(getRowStringValue(row, 9)));
                    param.put("detail_itembuydlvprice", emptyStringToNull(getRowStringValue(row, 10)));
                    String itemgubun;

                    itemgubun = getRowStringValue(row, 11);
                    if (StringUtils.isBlank(itemgubun)) {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 구분 값 없음>\n";
                        continue;
                    } else if ("제조사출고".equals(itemgubun)) {
                        param.put("detail_itemgubun", "1");
                        param.put("detail_pristock", null);
                        param.put("detail_itemsize", null);
                        param.put("detail_cartonqty", null);
                        param.put("detail_palletqty", null);
                    } else if ("제조사 출고".equals(itemgubun)) {
                        param.put("detail_itemgubun", "1");
                        param.put("detail_pristock", null);
                        param.put("detail_itemsize", null);
                        param.put("detail_cartonqty", null);
                        param.put("detail_palletqty", null);
                    } else if ("제조사 출고상품".equals(itemgubun)) {
                        param.put("detail_itemgubun", "1");
                        param.put("detail_pristock", null);
                        param.put("detail_itemsize", null);
                        param.put("detail_cartonqty", null);
                        param.put("detail_palletqty", null);
                    } else if ("제조사 출고 상품".equals(itemgubun)) {
                        param.put("detail_itemgubun", "1");
                        param.put("detail_pristock", null);
                        param.put("detail_itemsize", null);
                        param.put("detail_cartonqty", null);
                        param.put("detail_palletqty", null);
                    } else if ("당사재고출고".equals(itemgubun)) {
                        param.put("detail_itemgubun", "2");
                        String whsname;
                        whsname = getRowStringValue(row, 12);
                        if (StringUtils.isBlank(whsname)) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <우선창고 없음>\n";
                            continue;
                        }

                        if (whsMap.get(whsname) == null) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 창고이름(해당 이름으로 창고등록해주세요)>\n";
                            continue;
                        } else {
                            param.put("detail_pristock", "" + whsMap.get(whsname));
                        }

                        param.put("detail_itemsize", emptyStringToNull(getRowStringValue(row, 13)));
                        param.put("detail_cartonqty", emptyStringToNull(getRowStringValue(row, 14)));
                        param.put("detail_palletqty", emptyStringToNull(getRowStringValue(row, 15)));
                    } else if ("당사 재고출고".equals(itemgubun)) {
                        param.put("detail_itemgubun", "2");
                        String whsname;
                        whsname = getRowStringValue(row, 12);
                        if (StringUtils.isBlank(whsname)) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <우선창고 없음>\n";
                            continue;
                        }

                        if (whsMap.get(whsname) == null) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 창고이름(해당 이름으로 창고등록해주세요)>\n";
                            continue;
                        } else {
                            param.put("detail_pristock", "" + whsMap.get(whsname));
                        }

                        param.put("detail_itemsize", emptyStringToNull(getRowStringValue(row, 13)));
                        param.put("detail_cartonqty", emptyStringToNull(getRowStringValue(row, 14)));
                        param.put("detail_palletqty", emptyStringToNull(getRowStringValue(row, 15)));
                    } else if ("당사재고 출고".equals(itemgubun)) {
                        param.put("detail_itemgubun", "2");
                        String whsname;
                        whsname = getRowStringValue(row, 12);
                        if (StringUtils.isBlank(whsname)) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <우선창고 없음>\n";
                            continue;
                        }

                        if (whsMap.get(whsname) == null) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 창고이름(해당 이름으로 창고등록해주세요)>\n";
                            continue;
                        } else {
                            param.put("detail_pristock", "" + whsMap.get(whsname));
                        }

                        param.put("detail_itemsize", emptyStringToNull(getRowStringValue(row, 13)));
                        param.put("detail_cartonqty", emptyStringToNull(getRowStringValue(row, 14)));
                        param.put("detail_palletqty", emptyStringToNull(getRowStringValue(row, 15)));
                    } else if ("당사 재고 출고".equals(itemgubun)) {
                        param.put("detail_itemgubun", "2");
                        String whsname;
                        whsname = getRowStringValue(row, 12);
                        if (StringUtils.isBlank(whsname)) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <우선창고 없음>\n";
                            continue;
                        }

                        if (whsMap.get(whsname) == null) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 창고이름(해당 이름으로 창고등록해주세요)>\n";
                            continue;
                        } else {
                            param.put("detail_pristock", "" + whsMap.get(whsname));
                        }

                        param.put("detail_itemsize", emptyStringToNull(getRowStringValue(row, 13)));
                        param.put("detail_cartonqty", emptyStringToNull(getRowStringValue(row, 14)));
                        param.put("detail_palletqty", emptyStringToNull(getRowStringValue(row, 15)));
                    } else if ("재고관리상품".equals(itemgubun)) {
                        param.put("detail_itemgubun", "2");
                        String whsname;
                        whsname = getRowStringValue(row, 12);
                        if (StringUtils.isBlank(whsname)) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <우선창고 없음>\n";
                            continue;
                        }

                        if (whsMap.get(whsname) == null) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 창고이름(해당 이름으로 창고등록해주세요)>\n";
                            continue;
                        } else {
                            param.put("detail_pristock", "" + whsMap.get(whsname));
                        }

                        param.put("detail_itemsize", emptyStringToNull(getRowStringValue(row, 13)));
                        param.put("detail_cartonqty", emptyStringToNull(getRowStringValue(row, 14)));
                        param.put("detail_palletqty", emptyStringToNull(getRowStringValue(row, 15)));
                    }else if ("사은품".equals(itemgubun)) {
                        param.put("detail_itemgubun", "3");
                        String whsname;
                        whsname = row.getCell(12).getStringCellValue();
                        if (StringUtils.isBlank(whsname)) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <우선창고 없음>\n";
                            continue;
                        }

                        if (whsMap.get(whsname) == null) {
                            result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 창고이름(해당 이름으로 창고등록해주세요)>\n";
                            continue;
                        } else {
                            param.put("detail_pristock", "" + whsMap.get(whsname));
                        }

                        param.put("detail_itemsize", emptyStringToNull(getRowStringValue(row, 13)));
                        param.put("detail_cartonqty", emptyStringToNull(getRowStringValue(row, 14)));
                        param.put("detail_palletqty", emptyStringToNull(getRowStringValue(row, 15)));
                    } else {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 구분 값 입력>\n";
                        continue;
                    }
                    String resultInsert = null;
                    if (isModify) {
                        resultInsert = prd010Service.updateItemForBatchUp(param);
                    } else {
                        resultInsert = prd010Service.insertItem(param);
                    }
                    if (StringUtils.isBlank(resultInsert)) {
                        result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <잘못된 형식의 값 입력>\n";
                    }
                } catch (Exception e) {
                    result = result + row.getRowNum() + "번째 열 입력을 실패하였습니다. <알수없는 이유 : " + e.toString() + ">\n";
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException fe) {
            return "파일을 찾을 수 없습니다.";
        } catch (IOException ie) {
            return "잘못된 형식입니다.";
        }

        return result;
    }

    public String getRowStringValue(Row row, int i) {
        String result = null;
        try {
            result = String.valueOf(row.getCell(i).getNumericCellValue());
        } catch (Exception e) {

        }
        if (StringUtils.isBlank(result)) {
            try {
                result = String.valueOf(row.getCell(i).getStringCellValue());
            } catch (Exception e) {

            }
        }
        if (4 != i && !StringUtils.isBlank(result)) {
            result = result.replaceAll(",", "");
        }
        return result;
    }


    private String emptyStringToNull(String getString) {
        if (StringUtils.isBlank(getString)) {
            return null;
        }
        if ("null".equals(getString)) {
            return null;
        }
        return getString;
    }
}
