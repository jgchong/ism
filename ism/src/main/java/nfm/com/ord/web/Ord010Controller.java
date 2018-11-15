package nfm.com.ord.web;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import nfm.com.ord.service.Ismodo020VO;
import nfm.com.ord.service.Ord010SearchVO;
import nfm.com.ord.service.Ord010Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class Ord010Controller {

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService egovCmmUseService;
	
	/** ord010Service */
	@Resource(name = "ord010Service")
	private Ord010Service ord010Service;

	/**
	 * 주문수집 목록
	 * @param ord010SearchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ism/ord/ord010.do")
	public String mainList(@ModelAttribute("ord010SearchVO") Ord010SearchVO ord010SearchVO, ModelMap model) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "uat/uia/EgovLoginUsr";
    	}

		/** EgovPropertyService */
    	ord010SearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	ord010SearchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing [s]*/
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ord010SearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ord010SearchVO.getPageUnit());
		paginationInfo.setPageSize(ord010SearchVO.getPageSize());

		ord010SearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ord010SearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ord010SearchVO.setRecordCountPerPage(10000);
		//ord010SearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = ord010Service.selectListTotCnt(ord010SearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		/** pageing [e]*/
		
		model.addAttribute("resultList", ord010Service.selectList(ord010SearchVO));
		//공통코드 전달
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("ISM010");	//주문필드
		model.addAttribute("orderField", egovCmmUseService.selectCmmCodeDetail(vo));
				
		return "ism/ord/ord010";
	}

	/**
	 * 수동 수집 환경설정 쇼핑몰 선택시 데이터 조회
	 * @param cum030id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/ord/odo010.do")
	public String selectManualDataDetail(@RequestParam("cum030id") String cum030id) throws Exception {

		return ord010Service.selectManualDataDetail(cum030id);
	}
	
	/**
	 * 수동 수집 환경설정 저장
	 * @param cum030id
	 * @param userTitleNames
	 * @param sysmTitleNames
	 * @param assgTitleNames
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/ord/odo010Save.do")
	public String saveManualDetailData(
			@RequestParam("cum030id") String cum030id,
			@RequestParam("userTitleNames") String userTitleNames,
			@RequestParam("sysmTitleNames") String sysmTitleNames,
			@RequestParam("assgTitleNames") String assgTitleNames
			) throws Exception {
		ord010Service.saveManualDetailData(cum030id, userTitleNames, sysmTitleNames, assgTitleNames);
		return "SUCCESS";
	}

	/**
	 * 타이틀 정보 조회를 위해 엑셀파일 선택하여 처리 부분
	 * @param mtfrequest
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/ord/odo010upfile.do")
	public String uploadfile(MultipartHttpServletRequest mtfrequest) throws Exception {

		MultipartFile mf = mtfrequest.getFile("file");

		return URLEncoder.encode(ord010Service.readExcelFile(mf), "UTF-8");
	}

	/**
	 * 주문 데이터 일괄 업로드
	 * @param fileList
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/ord/ord010batchup.do", produces="text/plain;charset=UTF-8")
	public String upload(@RequestParam("files")List<MultipartFile> fileList) throws Exception {
		return ord010Service.readExcelFile(fileList);
	}
	
	/**
	 * 각 쇼핑몰 엑셀파일 개별업로드 처리 부분
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/ord/odo010orderupfile.do", produces="text/plain;charset=UTF-8")
	public String orderuploadfile(MultipartHttpServletRequest mtfrequest,
			@RequestParam("filecum010id") int filecum010id, @RequestParam("filecum030id") int filecum030id) throws Exception {

		MultipartFile mf = mtfrequest.getFile("file1");

		return ord010Service.readOrderExcelFile(mf, filecum010id, filecum030id);
	}
	
	/**
	 * api 환경설정 쇼핑몰 select box 변경시 정보 조회
	 * @param cum030id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/ord/odo020.do", produces="text/plain;charset=UTF-8")
	public String selectApiDataDetail(@RequestParam("cum030id") String cum030id) throws Exception {
		return ord010Service.selectApiDataDetail(cum030id);
	    //return retVal;
	}
	
	/**
	 * api 환경설정 저장
	 * @param ismodo020VO
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/ord/odo020Save.do")
	public String saveApiDetailData(@ModelAttribute("ismodo020VO") Ismodo020VO ismodo020VO) throws Exception {
		ord010Service.saveApiDetailData(ismodo020VO);
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/m1.do")
	public String m1() throws Exception {
		return "ism/m1";
	}
}
