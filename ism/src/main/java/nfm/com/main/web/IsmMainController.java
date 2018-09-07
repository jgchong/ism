package nfm.com.main.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import nfm.com.main.service.Ismadj090VO;
import nfm.com.main.service.Ismdbo010VO;
import nfm.com.main.service.MainSearchVO;
import nfm.com.main.service.MainService;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.LoginVO;

@Controller
public class IsmMainController {
	
	/** mainService */
	@Resource(name = "mainService")
	private MainService mainService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ism/main/mainPage.do", produces="text/plain;charset=UTF-8")
	public String ismMainPage(ModelMap model, HttpSession session) throws Exception {
		
		LoginVO loginVO = (LoginVO) session.getAttribute("LoginVO");

		//기존 조회 내용 유지위해 session 사용
		//String search_day = (String) session.getAttribute("search_day");
		//String search_period = (String) session.getAttribute("search_period");
		//String search_type = (String) session.getAttribute("search_type");
		//String search_dayBar= (String) session.getAttribute("search_dayBar");
		//String search_periodBar = (String) session.getAttribute("search_periodBar");
		//String search_typeBar = (String) session.getAttribute("search_typeBar");

		String search_day = "";
		String search_period = "";
		String search_type = "";
		String search_dayBar= "";
		String search_periodBar = "";
		String search_typeBar = "";
		
		MainSearchVO mainSearchVO = new MainSearchVO();

		//각 조건에 값이 없을 경우 default 값 처리 line [s]
		Date today = new Date();
		
		if ( (search_day == null) || ("".equals(search_day)) ) {
			SimpleDateFormat todayFm1 = new SimpleDateFormat("yyyy-MM-dd"); //화면 용
			SimpleDateFormat todayFm2 = new SimpleDateFormat("yyyyMMdd"); //DB 조회 용
			mainSearchVO.setSearch_day(todayFm2.format(today));
			session.setAttribute("search_day", todayFm1.format(today));
		}else{
			mainSearchVO.setSearch_day(search_day.replaceAll("-", ""));
		}
		
		if ( (search_period == null) || ("".equals(search_period)) ) {
			mainSearchVO.setSearch_period("D");
			session.setAttribute("search_period", "D");
		}else{
			mainSearchVO.setSearch_period(search_period);
		}
		
		if ( (search_type == null) || ("".equals(search_type)) ) {
			mainSearchVO.setSearch_type("A");
			session.setAttribute("search_type", "A");
		}else{
			mainSearchVO.setSearch_type(search_type);
		}

		JSONObject jsonObject = mainService.selectChart(mainSearchVO);
		//각 조건에 값이 없을 경우 default 값 처리 line [e]


		//각 조건에 값이 없을 경우 default 값 처리 bar [s]
		if ( (search_dayBar == null) || ("".equals(search_dayBar)) ) {
			SimpleDateFormat todayFm1 = new SimpleDateFormat("yyyy-MM-dd"); //화면 용
			SimpleDateFormat todayFm2 = new SimpleDateFormat("yyyyMMdd"); //DB 조회 용
			mainSearchVO.setSearch_day(todayFm2.format(today));
			session.setAttribute("search_dayBar", todayFm1.format(today));
		}else{
			mainSearchVO.setSearch_day(search_dayBar.replaceAll("-", ""));
		}
		
		if ( (search_periodBar == null) || ("".equals(search_periodBar)) ) {
			mainSearchVO.setSearch_period("D");
			session.setAttribute("search_periodBar", "D");
		}else{
			mainSearchVO.setSearch_period(search_periodBar);
		}
		
		if ( (search_typeBar == null) || ("".equals(search_typeBar)) ) {
			mainSearchVO.setSearch_type("A");
			session.setAttribute("search_typeBar", "A");
		}else{
			mainSearchVO.setSearch_type(search_typeBar);
		}

		JSONObject jsonObjectBar = mainService.selectChart(mainSearchVO);
		//각 조건에 값이 없을 경우 default 값 처리 bar [e]

		model.addAttribute("graphVal", jsonObject); //라인그래프의 표시값(매출액)
		model.addAttribute("listYear", (List) mainService.selectYear());   //라인그래프의 년별 기준일자 select 값
		model.addAttribute("mapMonth", (HashMap<String, String>) mainService.selectMonth());   //라인그래프의 월별 기준일자 select 값
		model.addAttribute("graphValBar", jsonObjectBar); //막대그래프의 표시값(매출액)
		model.addAttribute("listYearBar", (List) mainService.selectYear());   //막대그래프의 년별 기준일자 select 값
		model.addAttribute("mapMonthBar", (HashMap<String, String>) mainService.selectMonth());   //막대그래프의 월별 기준일자 select 값
		model.addAttribute("countAndTime", (HashMap<String, String>) mainService.selectCountAndTime());   //메인화면의 건수 및 데이터 반영시점 값 select
		model.addAttribute("dashBoardSetting", mainService.selectDashBoardSetting(loginVO.getId()));

		return "ism/main/ismMain";
	}

	@ResponseBody
	@RequestMapping(value = "/ism/main/mainPageChart.do", produces="text/plain;charset=UTF-8")
	public String ismMainPageChart(@ModelAttribute("mainSearchVO") MainSearchVO mainSearchVO, HttpSession session) throws Exception {

		if ("".equals(mainSearchVO.getIsBar())) {
			session.setAttribute("search_day", mainSearchVO.getSearch_day());
			session.setAttribute("search_period", mainSearchVO.getSearch_period());
			session.setAttribute("search_type", mainSearchVO.getSearch_type());
		}else{
			session.setAttribute("search_dayBar", mainSearchVO.getSearch_day());
			session.setAttribute("search_periodBar", mainSearchVO.getSearch_period());
			session.setAttribute("search_typeBar", mainSearchVO.getSearch_type());
		}
		
		mainSearchVO.setSearch_day(mainSearchVO.getSearch_day().replaceAll("-", ""));
		
		return mainService.selectChart(mainSearchVO).toString();
	}

	/**
	 * 대쉬보드 환경 설정 저장
	 */
	@ResponseBody
	@RequestMapping(value = "/ism/main/mainSaveDashBoardSetting.do")
	public String mainSaveDashBoardSetting(@ModelAttribute("ismdbo010VO") Ismdbo010VO ismdbo010VO, HttpSession session) throws Exception {
		
		LoginVO loginVO = (LoginVO) session.getAttribute("LoginVO");

		ismdbo010VO.setEmplyr_id(loginVO.getId());

		return mainService.saveDashBoardSetting(ismdbo010VO);
	}

	/*
	 * 정산 마감용 sample
	 */
	@RequestMapping(value = "/ism/main/samPage.do", produces="text/plain;charset=UTF-8")
	public String ismSamPage(ModelMap model, HttpSession session) throws Exception {
		return "ism/main/ismSam";
	}
	
	/**
	 * 정산 마감 팝업
	 */
	@RequestMapping(value = "/ism/main/mainAccountClose.do")
	public String mainAccountClose(ModelMap model, HttpSession session) throws Exception {

		SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMM");
		Calendar calender = Calendar.getInstance();

		model.addAttribute("today", formatter.format(calender.getTime()));

		calender.add(Calendar.MONTH, -1);

		model.addAttribute("closeAccountInfo", mainService.selectAccountClose(formatter1.format(calender.getTime())));

		return "ism/main/ismAccountClose";
	}
	
	/**
	 * 정산 마감 처리
	 */
	@RequestMapping(value = "/ism/main/mainAccountCloseAct.do")
	public String mainAccountCloseAct(@ModelAttribute("ismadj090VO") Ismadj090VO ismadj090VO, ModelMap model, HttpSession session) throws Exception {

		mainService.accountCloseAct(ismadj090VO);
		
		model.addAttribute("resultMsg", "마감처리되었습니다.");
		
		return "forward:/ism/main/mainAccountClose.do";
	}
}
