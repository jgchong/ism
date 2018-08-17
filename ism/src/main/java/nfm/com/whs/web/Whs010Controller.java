package nfm.com.whs.web;

import javax.annotation.Resource;

import nfm.com.whs.service.Whs010SearchVO;
import nfm.com.whs.service.Whs010Service;
import nfm.com.whs.service.WhsAllVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

@Controller
public class Whs010Controller {

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** whs010Service */
	@Resource(name = "whs010Service")
	private Whs010Service whs010Service;

	/**
	 * 창고 목록
	 */
	@RequestMapping(value = "/ism/whs/whs010.do")
	public String mainList(@ModelAttribute("whs010SearchVO") Whs010SearchVO whs010SearchVO, ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		model.addAttribute("resultList", whs010Service.selectList(whs010SearchVO));
				
		return "ism/whs/whs010";
	}
	
	/**
	 * 창고 상세 조회
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/whs/whs010SelectDetail.do")
	public String whs010SelectDetail(@ModelAttribute("whs010SearchVO") Whs010SearchVO whs010SearchVO, ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

    	return (String) whs010Service.selectDetail(whs010SearchVO);
	}
	
	/**
	 * 창고 전체 저장
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/whs/whs010SaveWhsAll.do")
	public String saveWhsAll(MultipartHttpServletRequest mtfrequest, @ModelAttribute("whsAllVO") WhsAllVO whsAllVO) throws Exception {

		MultipartFile mf = mtfrequest.getFile("attachfile");
		String path = propertiesService.getString("Globals.fileStorePath");
		int whs010id = whs010Service.saveWhsAll(mf, path, whsAllVO);

    	return "SUCCESS,"+whs010id;
	}
}
