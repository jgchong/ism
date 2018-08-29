package nfm.com.main.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import nfm.com.main.service.MainSearchVO;
import nfm.com.main.service.MainService;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IsmMainController {
	
	/** mainService */
	@Resource(name = "mainService")
	private MainService mainService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ism/main/mainPage.do", produces="text/plain;charset=UTF-8")
	public String ismMainPage(ModelMap model, HttpSession session) throws Exception {
		
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
		
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat yearFm = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFm1 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat monthFm2 = new SimpleDateFormat("yyyyMM");

		List listYear = new ArrayList();
		
		int yearVal = Integer.parseInt(yearFm.format(cal.getTime()));

		listYear.add(yearVal);
		listYear.add(yearVal-1);
		listYear.add(yearVal-2);
		
		HashMap<String, String> mapMonth = new HashMap<String, String>();
		
		mapMonth.put(monthFm2.format(cal.getTime()), monthFm1.format(cal.getTime()));
		
		cal.add(Calendar.MONTH, -1);
		mapMonth.put(monthFm2.format(cal.getTime()), monthFm1.format(cal.getTime()));

		cal.add(Calendar.MONTH, -1);
		mapMonth.put(monthFm2.format(cal.getTime()), monthFm1.format(cal.getTime()));
		
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
		
		Calendar calBar = Calendar.getInstance();

		SimpleDateFormat yearFmBar = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFm1Bar = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat monthFm2Bar = new SimpleDateFormat("yyyyMM");

		List listYearBar = new ArrayList();
		
		int yearValBar = Integer.parseInt(yearFmBar.format(calBar.getTime()));

		listYearBar.add(yearValBar);
		listYearBar.add(yearValBar-1);
		listYearBar.add(yearValBar-2);
		
		HashMap<String, String> mapMonthBar = new HashMap<String, String>();
		
		mapMonthBar.put(monthFm2Bar.format(calBar.getTime()), monthFm1Bar.format(calBar.getTime()));
		
		calBar.add(Calendar.MONTH, -1);
		mapMonthBar.put(monthFm2Bar.format(calBar.getTime()), monthFm1Bar.format(calBar.getTime()));

		calBar.add(Calendar.MONTH, -1);
		mapMonthBar.put(monthFm2Bar.format(calBar.getTime()), monthFm1Bar.format(calBar.getTime()));
		
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

		model.addAttribute("graphVal", jsonObject);
		model.addAttribute("listYear", listYear);
		model.addAttribute("mapMonth", mapMonth);
		model.addAttribute("graphValBar", jsonObjectBar);
		model.addAttribute("listYearBar", listYearBar);
		model.addAttribute("mapMonthBar", mapMonthBar);
		
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

}
