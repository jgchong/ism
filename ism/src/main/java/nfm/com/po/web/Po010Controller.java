package nfm.com.po.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import nfm.com.po.service.Po010SaveVO;
import nfm.com.po.service.Po010Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

@Controller
public class Po010Controller {

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService egovCmmUseService;

	/** po010Service */
	@Resource(name = "po010Service")
	private Po010Service po010Service;
	
	/**
	 * 발주 메인
	 */
	@RequestMapping(value = "/ism/po/po010.do")
	public String mainList(ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

    	model.addAttribute("resultListWhs", po010Service.selectWhsList());
    	model.addAttribute("resultListByc", po010Service.selectBycList());
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

		return "ism/po/po010";
	}

	@ResponseBody
	@RequestMapping(value = "/ism/po/po010SelectUserListJson.do", produces="text/plain;charset=UTF-8")
	public String po010SelectUserListJson(@RequestParam("keyid") int keyid, @RequestParam("pocotype") String pocotype) throws Exception {

		return po010Service.selectUserListJson(keyid, pocotype);
	}

	@ResponseBody
	@RequestMapping(value = "/ism/po/po010SelectPoo010ListJson.do", produces="text/plain;charset=UTF-8")
	public String po010SelectPoo010ListJson(@RequestParam("poo010id") int poo010id, @RequestParam("pocotype") String pocotype) throws Exception {

		return po010Service.selectPoo010ListJson(poo010id, pocotype);
	}

	@ResponseBody
	@RequestMapping(value = "/ism/po/po010SelectOrm010ListJson.do", produces="text/plain;charset=UTF-8")
	public String po010SelectOrm010ListJson(@RequestParam("poo010id") int poo010id, @RequestParam("pocotype") String pocotype) throws Exception {
		
		return po010Service.selectOrm010ListJson(poo010id, pocotype);
	}

	/**
	 * 발주 수동 환경 설정 저장
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/po/po010SavePoo010.do")
	public String po010SavePoo010(@RequestParam("poo010id") int poo010id,
								  @RequestParam("pocotype") String pocotype,
								  @RequestParam("savedata") String savedata) throws Exception {
		
		return po010Service.savePoo010(poo010id, pocotype, savedata);
	}

	@ResponseBody
	@RequestMapping(value = "/ism/po/po010SaveApiDetail.do")
	public String po010SaveApiDetail(@RequestParam("byc010id") int byc010id,
								  @RequestParam("apiurl") String apiurl,
								  @RequestParam("apicontext") String apicontext,
								  @RequestParam("apiversion") String apiversion) throws Exception {
		
		po010Service.savePoo020(byc010id, apiurl, apicontext, apiversion);

		return "SUCCESS";
	}

	@ResponseBody
	@RequestMapping(value = "/ism/po/po010SelectApiDetail.do")
	public String po010SelectApiDetail(@RequestParam("byc010id") int byc010id) throws Exception {
		
		return po010Service.selectPoo020Json(byc010id);
	}

	@ResponseBody
	@RequestMapping(value = "/ism/po/po010SavePoList.do")
	public String po010SavePoList(@ModelAttribute("po010SaveVO") Po010SaveVO po010SaveVO,
			@RequestParam("odm010id") String[] odm010idArr, HttpSession session) throws Exception {

		LoginVO loginVO = (LoginVO) session.getAttribute("LoginVO");

		return po010Service.savePoList(odm010idArr, po010SaveVO, loginVO);
	}
	
	/**
	 * 송장 데이터 일괄 업로드
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/po/po010batchup.do")
	public String po010batchup(@RequestParam("files")List<MultipartFile> fileList) throws Exception {
		return po010Service.batchupExcel(fileList);
	}
}
