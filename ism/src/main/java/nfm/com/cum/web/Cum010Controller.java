package nfm.com.cum.web;

import javax.annotation.Resource;

import nfm.com.cmm.service.Cmm010Service;
import nfm.com.cmm.util.CmmUtil;
import nfm.com.cum.service.Cum010SearchVO;
import nfm.com.cum.service.Cum010Service;
import nfm.com.cum.service.CumAllVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class Cum010Controller {

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService egovCmmUseService;

	/** cum010Service */
	@Resource(name = "cum010Service")
	private Cum010Service cum010Service;

	/** cmm010Service */
	@Resource(name = "cmm010Service")
	private Cmm010Service cmm010Service;
	
	/**
	 * 매출처 목록
	 */
	@RequestMapping(value = "/ism/cum/cum010.do")
	public String mainList(@ModelAttribute("cum010SearchVO") Cum010SearchVO cum010SearchVO, ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		/** EgovPropertyService */
    	cum010SearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	cum010SearchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing [s]*/
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(cum010SearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cum010SearchVO.getPageUnit());
		paginationInfo.setPageSize(cum010SearchVO.getPageSize());

		cum010SearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cum010SearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cum010SearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = cum010Service.selectListTotCnt(cum010SearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		/** pageing [e]*/
		model.addAttribute("resultList", cum010Service.selectList(cum010SearchVO));
		//공통코드 전달
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("ISM020");	//업체대분류
		model.addAttribute("ISM020", CmmUtil.cmmCodeOptionConvert(egovCmmUseService.selectCmmCodeDetail(vo), ""));
		vo.setCodeId("ISM031");	//업체중분류1
		model.addAttribute("ISM031", CmmUtil.cmmCodeOptionConvert(egovCmmUseService.selectCmmCodeDetail(vo), ""));
		vo.setCodeId("ISM032");	//업체중분류2
		model.addAttribute("ISM032", CmmUtil.cmmCodeOptionConvert(egovCmmUseService.selectCmmCodeDetail(vo), ""));
		vo.setCodeId("ISM041");	//업체소분류1
		model.addAttribute("ISM041", CmmUtil.cmmCodeOptionConvert(egovCmmUseService.selectCmmCodeDetail(vo), ""));
		vo.setCodeId("ISM042");	//업체소분류2
		model.addAttribute("ISM042", CmmUtil.cmmCodeOptionConvert(egovCmmUseService.selectCmmCodeDetail(vo), ""));
		vo.setCodeId("ISM043");	//업체소분류3
		model.addAttribute("ISM043", CmmUtil.cmmCodeOptionConvert(egovCmmUseService.selectCmmCodeDetail(vo), ""));
		vo.setCodeId("ISM044");	//업체소분류4
		model.addAttribute("ISM044", CmmUtil.cmmCodeOptionConvert(egovCmmUseService.selectCmmCodeDetail(vo), ""));
		vo.setCodeId("ISM0A0");	//매출처 대금정산
		model.addAttribute("ISM0A0", CmmUtil.cmmCodeOptionConvert(egovCmmUseService.selectCmmCodeDetail(vo), ""));
				
		return "ism/cum/cum010";
	}
	
	/**
	 * 매출처 상세 조회
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/cum/cum010SelectDetail.do", produces="text/plain;charset=UTF-8")
	public String cum010SelectDetail(@ModelAttribute("cum010SearchVO") Cum010SearchVO cum010SearchVO, ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

    	return (String) cum010Service.selectDetail(cum010SearchVO);
	}
	
	/**
	 * 매출처 전체 저장
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/cum/saveCumAll.do")
	public String insertCumAllData(MultipartHttpServletRequest mtfrequest, @ModelAttribute("cumAllVO") CumAllVO cumAllVO) throws Exception {

		MultipartFile mf = mtfrequest.getFile("attachfile");
		String path = propertiesService.getString("Globals.fileStorePath");
		System.out.println("jgc debug getAccountamt = "+cumAllVO.getAccountamt());
		System.out.println("jgc debug getAccountamtdate = ["+cumAllVO.getAccountamtdate()+"]");
		int cum010id = cum010Service.insertCumAllData(mf, path, cumAllVO);

    	return "SUCCESS,"+cum010id;
	}
}
