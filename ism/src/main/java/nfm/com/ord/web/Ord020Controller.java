package nfm.com.ord.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nfm.com.cmm.service.Cmm010Service;
import nfm.com.ord.service.Ismodm010VO;
import nfm.com.ord.service.Ord020SearchVO;
import nfm.com.ord.service.Ord020Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class Ord020Controller {

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
	@Resource(name = "ord020Service")
	private Ord020Service ord020Service;
	
	/** cmm010Service */
	@Resource(name = "cmm010Service")
	private Cmm010Service cmm010Service;
	
	/**
	 * 주문현황 목록
	 * @param ord020SearchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ism/ord/ord020.do")
	public String mainList(@ModelAttribute("ord020SearchVO") Ord020SearchVO ord020SearchVO, ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

    	int pageUnit = ord020SearchVO.getPageUnit();
		if (!"TEMP".equals(ord020SearchVO.getSearch_status())) {
			if ( (ord020SearchVO.getDtSearch_frOrderDt() == null) || ("".equals(ord020SearchVO.getDtSearch_frOrderDt()) ) ) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calender = Calendar.getInstance();
				calender.add(Calendar.DATE, -180);
				ord020SearchVO.setDtSearch_frOrderDt(formatter.format(calender.getTime()));
			}
			if ( (ord020SearchVO.getDtSearch_toOrderDt() == null) || ("".equals(ord020SearchVO.getDtSearch_toOrderDt()) ) ) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calender = Calendar.getInstance();
				ord020SearchVO.setDtSearch_toOrderDt(formatter.format(calender.getTime()));
			}
		}else{
			if (!"".equals(ord020SearchVO.getSearch_key1())) {
				pageUnit = 100000;
			}
		}
		
		/** EgovPropertyService */
    	//ord020SearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	ord020SearchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
		/** pageing [s] */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ord020SearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pageUnit);
		paginationInfo.setPageSize(ord020SearchVO.getPageSize());

		ord020SearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ord020SearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ord020SearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = ord020Service.selectListTotCnt(ord020SearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		/** pageing [e]*/
		
		model.addAttribute("resultList", ord020Service.selectList(ord020SearchVO));
		//공통코드 전달
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("ISM050");	//주문상태필드
		model.addAttribute("ISM050", egovCmmUseService.selectCmmCodeDetail(vo));
		vo.setCodeId("ISM020");	//업체대분류
		model.addAttribute("ISM020", egovCmmUseService.selectCmmCodeDetail(vo));
		vo.setCodeId("ISM060");	//클레임상태필드
		model.addAttribute("ISM060", egovCmmUseService.selectCmmCodeDetail(vo));
		vo.setCodeId("ISM070");	//클레임 사유 필드
		model.addAttribute("ISM070", egovCmmUseService.selectCmmCodeDetail(vo));
		vo.setCodeId("ISM080");	//반품상태필드
		model.addAttribute("ISM080", egovCmmUseService.selectCmmCodeDetail(vo));
	
		return "ism/ord/ord020";
	}
	
	/**
	 * 주문현황 상세 조회
	 * @param ord020SearchVO
	 * @param model
	 * @return
	 * @throws Exception
	 * 팝업으로 변경하면서 미사용
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/ord/odo020SelectOrderOne.do", produces="text/plain;charset=UTF-8")
	public String orderDetail(@ModelAttribute("ord020SearchVO") Ord020SearchVO ord020SearchVO, ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

	    return ord020Service.selectListJson(ord020SearchVO);
	}
	

	/**
	 * 주문현황 상세 조회 팝업
	 * @param ord020SearchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ism/ord/odo020SelectOrderDetailPopUp.do")
	public String selectOrderDetailPopUp(@ModelAttribute("ord020SearchVO") Ord020SearchVO ord020SearchVO, ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		//공통코드 전달
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("ISM050");	//주문상태필드
		model.addAttribute("ISM050", egovCmmUseService.selectCmmCodeDetail(vo));
		vo.setCodeId("ISM060");	//클레임상태필드
		model.addAttribute("ISM060", egovCmmUseService.selectCmmCodeDetail(vo));
		vo.setCodeId("ISM070");	//클레임 사유 필드
		model.addAttribute("ISM070", egovCmmUseService.selectCmmCodeDetail(vo));
		vo.setCodeId("ISM080");	//반품상태필드
		model.addAttribute("ISM080", egovCmmUseService.selectCmmCodeDetail(vo));
		
    	model.addAttribute("result", ord020Service.selectList(ord020SearchVO));
    	model.addAttribute("memoListHtml", ord020Service.selectListMemo(ord020SearchVO));

	    return "ism/ord/ord020PopUp";
	}

	@ResponseBody
	@RequestMapping(value = "/ism/ord/ord020SelectChgOrderStatus.do")
	public String ord020SelectChgOrderStatus(@RequestParam("selectoptionval") String selectoptionval,
			@RequestParam("chgodm010ids") String chgodm010ids,
			ModelMap model) throws Exception {
		
		ord020Service.ord020SelectChgOrderStatus(selectoptionval, chgodm010ids);
		
		return "SUCCESS";
	}

	@ResponseBody
	@RequestMapping(value = "/ism/ord/ord020SelectDel.do")
	public String ord020SelectDel(@RequestParam("chgodm010ids") String chgodm010ids, ModelMap model) throws Exception {
		
		ord020Service.ord020SelectDel(chgodm010ids);
		
		return "SUCCESS";
	}

	@ResponseBody
	@RequestMapping(value = "/ism/ord/updateOrderDetailData.do")
	public String updateOrderDetailData(@ModelAttribute("ismodm010VO") Ismodm010VO ismodm010VO) throws Exception {
		
		ord020Service.updateOrderDetailData(ismodm010VO);
		
		return "SUCCESS";
	}
	@RequestMapping(value="/ism/ord/ord020ExcelDownload.do")
	public @ResponseBody byte[] ord020ExcelDownload(@ModelAttribute("ord020SearchVO") Ord020SearchVO ord020SearchVO, ModelMap model, HttpServletRequest request,
	         HttpServletResponse response, HttpSession session ) throws Exception {

		ord020SearchVO.setFirstIndex(0);
		ord020SearchVO.setRecordCountPerPage(100000);
		
		byte[] bytes = ord020Service.ord020ExcelDownload(ord020SearchVO);

	    response.setHeader("Content-Disposition", "attachment; filename=orderExcel.xls");
	    response.setContentLength(bytes.length);
	    response.setContentType("application/vnd.ms-excel");

		return bytes;
	}
}
