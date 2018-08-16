package nfm.com.byc.web;

import javax.annotation.Resource;

import nfm.com.byc.service.Byc010SearchVO;
import nfm.com.byc.service.Byc010Service;
import nfm.com.byc.service.BycAllVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

@Controller
public class Byc010Controller {

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService egovCmmUseService;

	/** byc010Service */
	@Resource(name = "byc010Service")
	private Byc010Service byc010Service;
	
	/**
	 * 매입처 목록
	 */
	@RequestMapping(value = "/ism/byc/byc010.do")
	public String byc010SelectBuyerList(@ModelAttribute("byc010SearchVO") Byc010SearchVO byc010SearchVO, ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
		/** pageing [s]*/
		byc010SearchVO.setRecordCountPerPage(10000);
		/** pageing [e]*/

		model.addAttribute("resultList", byc010Service.selectBuyerList(byc010SearchVO));
		//공통코드 전달
		//ComDefaultCodeVO vo = new ComDefaultCodeVO();
		//vo.setCodeId("ISM020");	//업체대분류
		//model.addAttribute("ISM020", CmmUtil.cmmCodeOptionConvert(egovCmmUseService.selectCmmCodeDetail(vo), ""));
				
		return "ism/byc/byc010";
	}
	
	/**
	 * 매입처 상세 조회
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/cum/byc010SelectDetail.do")
	public String byc010SelectDetail(@ModelAttribute("byc010SearchVO") Byc010SearchVO byc010SearchVO, ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

    	return (String) byc010Service.selectDetail(byc010SearchVO);
	}
	
	/**
	 * 매입처 전체 저장
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/byc/byc010SaveBycAll.do")
	public String saveBycAll(MultipartHttpServletRequest mtfrequest, @ModelAttribute("bycAllVO") BycAllVO bycAllVO) throws Exception {

		MultipartFile mf = mtfrequest.getFile("attachfile");
		String path = propertiesService.getString("Globals.fileStorePath");
		int byc010id = byc010Service.saveBycAll(mf, path, bycAllVO);

    	return "SUCCESS,"+byc010id;
	}
}
