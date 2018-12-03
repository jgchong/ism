package nfm.com.po.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import nfm.com.po.service.Po020Service;
import nfm.com.cmm.util.MailHandler;
import nfm.com.ord.service.Ord020SearchVO;
import nfm.com.po.service.Ismpomsearch020VO;
import nfm.com.prd.service.Prd010Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.let.sym.mnu.mcm.service.EgovMenuCreateManageService;
import egovframework.let.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class Po020Controller {

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService egovCmmUseService;

	/** po020Service */
	@Resource(name = "po020Service")
	private Po020Service po020Service;
	
    @Resource(name = "prd010Service")
    private Prd010Service prd010Service;
	
	/** EgovMenuManageService */
	@Resource(name = "meunCreateManageService")
	private EgovMenuCreateManageService menuCreateManageService;
	
	/** jgc add id:mailv1 메일 send */
	@Autowired
    private JavaMailSender mailSender;
	
	/**
	 * 발주 메인
	 */
	@RequestMapping(value = "/ism/po/po020.do")
	public String mainList(@ModelAttribute("ismpomsearch020VO") Ismpomsearch020VO ismpomsearch020VO, ModelMap model) throws Exception {
		String resultMsg = "";
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}
    	
    	int pageUnit = ismpomsearch020VO.getPageUnit();
    	
		/** EgovPropertyService.sample */
    	//ismpomsearch020VO.setPageUnit(propertiesService.getInt("pageUnit"));
    	ismpomsearch020VO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ismpomsearch020VO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pageUnit);
		paginationInfo.setPageSize(ismpomsearch020VO.getPageSize());

		ismpomsearch020VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ismpomsearch020VO.setLastIndex(paginationInfo.getLastRecordIndex());
		ismpomsearch020VO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		String path = propertiesService.getString("Globals.fileStorePath");
		
		int totCnt = po020Service.selectPoListTotCnt(ismpomsearch020VO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("path", path);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("resultMsg", resultMsg);
    	model.addAttribute("resultPoList", po020Service.selectPoList(ismpomsearch020VO));
    	model.addAttribute("bycList", prd010Service.selectBycAll());

		return "ism/po/po020";
	}
	
	/**
	 * 발주 수동 환경 설정 저장
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/po/po020ReSendMail020.do")
	public String po020ReSendMail(@RequestParam("rcvuseremail") String rcvuseremail,
								  @RequestParam("uploadfilename") String uploadfilename,
								  @RequestParam("receiveType") String receiveType,
								  HttpSession session) throws Exception {
		
		LoginVO loginVO = (LoginVO) session.getAttribute("LoginVO");
		String filefullname = "";
		String retVal = "SUCCESS";
    	String path = propertiesService.getString("Globals.fileStorePath");
    	filefullname = path + uploadfilename;
    	
		if ("X".equals(receiveType)) {
	    	retVal = filefullname;
	    }else{
	    //메일전송[s]
	    	MailHandler sendMail = new MailHandler(mailSender);
	        sendMail.setSubject("[ISM]발주정보 파일을 재전송합니다.");
	        sendMail.setText("[ISM]발주정보 파일을 재전송합니다.");
	        sendMail.setFrom(loginVO.getEmail(), loginVO.getName());
	        sendMail.setTo(rcvuseremail);
	        sendMail.addAttachFile(uploadfilename, filefullname);
	        sendMail.send();
		//메일전송[e]
	    }
		
		return retVal;
	}
}
