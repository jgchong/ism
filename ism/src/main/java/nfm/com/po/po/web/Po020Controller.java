package nfm.com.po.web;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import nfm.com.po.service.Po020Service;
import nfm.com.byc.service.Ismbyc020VO;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    	model.addAttribute("bycNmList", po020Service.selectByc020List(ismpomsearch020VO.getDtSearch_bycNm()));

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
	
	/**
	 * 매입처 담당자 가져요기
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/po/po020SelectSndNm.do")
	public String po020SelectSndNm(@RequestParam("bycId") String bycId,
								  ModelMap model,
								  HttpSession session) throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		
    	//매입처 담당자 목록 정보 가져옴.[s]
		List<Ismbyc020VO> listIsmbyc020VO = (List<Ismbyc020VO>) po020Service.selectByc020List(bycId); //bycId로 조회

		JSONArray jsonArray020 = new JSONArray();

	    for(Ismbyc020VO vo : listIsmbyc020VO){
	    	JSONObject jsonObject020 = new JSONObject();
	    	jsonObject020.put("byc010id", vo.getByc010id());
	    	jsonObject020.put("byc020id", vo.getByc020id());

	    	if (vo.getBycusername() == null) {
	    		jsonObject020.put("username", "");
	    	}else{
	    		jsonObject020.put("username", URLEncoder.encode(vo.getBycusername(), "UTF-8"));
	    	}

	    	if (vo.getBycusertel() == null) {
	    		jsonObject020.put("byc020id", "");
	    	}else{
	    		jsonObject020.put("byc020id", vo.getByc020id());
	    	}

	    	jsonArray020.add(jsonObject020);
	    }
	    jsonObject.put("userlist", jsonArray020);
	    //발주처 담당자 목록 정보 가져옴.[e]
		
		
		return jsonObject.toString();
	}
}
