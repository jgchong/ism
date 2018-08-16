package nfm.com.cmm.web;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import nfm.com.cmm.service.Cmm010Service;
import nfm.com.cmm.service.IsmCmm010VO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

@Controller
public class Cmm010Controller {

	/** cmm010Service */
	@Resource(name = "cmm010Service")
	private Cmm010Service cmm010Service;
	
	/**
	 * 메모 목록 조회
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/cmm/selectListMemo.do")
	public String selectListMemo(@RequestParam("buss_key") String buss_key, @RequestParam("buss_type") String buss_type) throws Exception {
		
		IsmCmm010VO ismCmm010VO = new IsmCmm010VO();
		ismCmm010VO.setBuss_key(buss_key);
		ismCmm010VO.setBuss_type(buss_type);

	    String retStr = URLEncoder.encode(cmm010Service.selectListMemo(ismCmm010VO), "UTF-8");
	    
		return retStr;
	}

	@ResponseBody
	@RequestMapping(value = "/ism/cmm/insertMemo.do")
	public String insertMemo(@RequestParam("buss_key") String buss_key, @RequestParam("inputmemo") String inputmemo, @RequestParam("buss_type") String buss_type,
				HttpSession session) throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		return "LFAIL";
    	}
    	
		LoginVO loginVO = (LoginVO) session.getAttribute("LoginVO");
		
		IsmCmm010VO ismCmm010VO = new IsmCmm010VO();
		ismCmm010VO.setBuss_key(buss_key);
		ismCmm010VO.setBuss_type(buss_type);
		ismCmm010VO.setMemotext(inputmemo);
		ismCmm010VO.setCreateuserid(loginVO.getId());
		
		cmm010Service.insertMemo(ismCmm010VO);
		
		return "SUCCESS";
	}
}
