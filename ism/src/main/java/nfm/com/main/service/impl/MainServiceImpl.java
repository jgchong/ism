package nfm.com.main.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.main.service.Ismdbo010VO;
import nfm.com.main.service.MainGraphRetVO;
import nfm.com.main.service.MainSearchVO;
import nfm.com.main.service.MainService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service("mainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService {

	/** mainDAO */
	@Resource(name="mainDAO")
	private MainDAO mainDAO;

	@Override
	public MainGraphRetVO selectChartAllYear(String selectVal) throws Exception {
		return (MainGraphRetVO) mainDAO.selectChartAllYear(selectVal);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject selectChart(MainSearchVO mainSearchVO) throws Exception {

		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		List<MainGraphRetVO> listMainGraphRetVO = null;
		
		if ("D".equals(mainSearchVO.getSearch_period())) { //일자별
			if ("A".equals(mainSearchVO.getSearch_type())) { //총매출액
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartAllDay(mainSearchVO.getSearch_day());
			}else if ("P".equals(mainSearchVO.getSearch_type())) { //상품별
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartProdDay(mainSearchVO.getSearch_day());
			}else if ("C".equals(mainSearchVO.getSearch_type())) { //매출처별
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartCustDay(mainSearchVO.getSearch_day());
			}else if ("B".equals(mainSearchVO.getSearch_type())) { //매입처별
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartBuyDay(mainSearchVO.getSearch_day());
			}
			jsonObject.put("labels", getLabelsDate(mainSearchVO.getSearch_day(), 14));
		}else if ("M".equals(mainSearchVO.getSearch_period())) { //월별
			if ("A".equals(mainSearchVO.getSearch_type())) { //총매출액
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartAllMon(mainSearchVO.getSearch_day());
			}else if ("P".equals(mainSearchVO.getSearch_type())) { //상품별
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartProdMon(mainSearchVO.getSearch_day());
			}else if ("C".equals(mainSearchVO.getSearch_type())) { //매출처별
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartCustMon(mainSearchVO.getSearch_day());
			}else if ("B".equals(mainSearchVO.getSearch_type())) { //매입처별
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartBuyMon(mainSearchVO.getSearch_day());
			}
			jsonObject.put("labels", getLabelsMonth(mainSearchVO.getSearch_day()+"01", 3));
		}else if ("Y".equals(mainSearchVO.getSearch_period())) { //년별
			if ("A".equals(mainSearchVO.getSearch_type())) { //총매출액
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartAllYear(mainSearchVO.getSearch_day());
			}else if ("P".equals(mainSearchVO.getSearch_type())) { //상품별
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartProdYear(mainSearchVO.getSearch_day());
			}else if ("C".equals(mainSearchVO.getSearch_type())) { //매출처별
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartCustYear(mainSearchVO.getSearch_day());
			}else if ("B".equals(mainSearchVO.getSearch_type())) { //매입처별
				listMainGraphRetVO = (List<MainGraphRetVO>) mainDAO.selectChartBuyYear(mainSearchVO.getSearch_day());
			}
			jsonObject.put("labels", "1월,2월,3월,4월,5월,6월,7월,8월,9월,10월,11월,12월");
		}
		
		
		if (listMainGraphRetVO != null) {
			for(MainGraphRetVO mainGraphRetVO : listMainGraphRetVO){
				JSONObject jsonObjectSub = new JSONObject();
				jsonObjectSub.put("titles", mainGraphRetVO.getCharTitle());
				String valStr = mainGraphRetVO.getChartValues1() + "," +
								mainGraphRetVO.getChartValues2() + "," +
								mainGraphRetVO.getChartValues3() + "," +
								mainGraphRetVO.getChartValues4() + "," +
								mainGraphRetVO.getChartValues5() + "," +
								mainGraphRetVO.getChartValues6() + "," +
								mainGraphRetVO.getChartValues7() + "," +
								mainGraphRetVO.getChartValues8() + "," +
								mainGraphRetVO.getChartValues9() + "," +
								mainGraphRetVO.getChartValues10() + "," +
								mainGraphRetVO.getChartValues11() + "," +
								mainGraphRetVO.getChartValues12() + "," +
								mainGraphRetVO.getChartValues13() + "," +
								mainGraphRetVO.getChartValues14();

				jsonObjectSub.put("values", valStr);
				jsonArray.add(jsonObjectSub);
			}
		}else{
			JSONObject jsonObjectSub = new JSONObject();
			jsonObjectSub.put("titles", "");
			jsonObjectSub.put("values", "");
			jsonArray.add(jsonObjectSub);
		}

		jsonObject.put("datas", jsonArray);

		return jsonObject;
	}

	private  String getLabelsDate(String dateStr, int cnt) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		String strRet = "";

		try {
			Date date = formatter.parse(dateStr);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			cal.add(Calendar.DAY_OF_YEAR, (cnt-1)*-1);
			
			int imonth = cal.get(Calendar.MONTH) + 1;
			int iDay = cal.get(Calendar.DAY_OF_MONTH);
			
			strRet = imonth + "월" + iDay + "일,"; 

			for(int i=1;i<cnt;i++) {
				cal.add(Calendar.DAY_OF_YEAR, 1);
				imonth = cal.get(Calendar.MONTH) + 1;
				iDay = cal.get(Calendar.DAY_OF_MONTH);

				strRet = strRet + imonth + "월" + iDay + "일,";
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		strRet = strRet.substring(0,strRet.length()-1);

		return strRet;
	}

	private static String getLabelsMonth(String dateStr, int cnt) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		String strRet = "";

		try {
			Date date = formatter.parse(dateStr);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			cal.add(Calendar.MONTH, (cnt-1)*-1);
			
			int iyear = cal.get(Calendar.YEAR);
			int imonth = cal.get(Calendar.MONTH) + 1;
			
			strRet = iyear + "년" + imonth + "월,"; 

			for(int i=1;i<cnt;i++) {
				cal.add(Calendar.MONTH, 1);
				
				iyear = cal.get(Calendar.YEAR);
				imonth = cal.get(Calendar.MONTH) + 1;

				strRet = strRet + iyear + "년" + imonth + "월,";
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		strRet = strRet.substring(0,strRet.length()-1);

		return strRet;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object selectYear() throws Exception {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat yearFm = new SimpleDateFormat("yyyy");

		List listYear = new ArrayList();
		
		int yearVal = Integer.parseInt(yearFm.format(cal.getTime()));

		listYear.add(yearVal);
		listYear.add(yearVal-1);
		listYear.add(yearVal-2);
		
		return listYear;
	}

	@Override
	public Object selectMonth() throws Exception {
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat monthFm1 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat monthFm2 = new SimpleDateFormat("yyyyMM");
		
		//기준일자 기준으로 월의 select값 저장[s]
		HashMap<String, String> mapMonth = new HashMap<String, String>();
		
		mapMonth.put(monthFm2.format(cal.getTime()), monthFm1.format(cal.getTime()));
		
		cal.add(Calendar.MONTH, -1);
		mapMonth.put(monthFm2.format(cal.getTime()), monthFm1.format(cal.getTime()));

		cal.add(Calendar.MONTH, -1);
		mapMonth.put(monthFm2.format(cal.getTime()), monthFm1.format(cal.getTime()));

		return mapMonth;
	}

	@Override
	public Object selectCountAndTime() throws Exception {
		HashMap<String, String> countAndTime = new HashMap<String, String>();
		
		countAndTime.put("ipDayCnt",   mainDAO.selectipDayCnt());   //재고상품입고현황 일단위 count
		countAndTime.put("ipWeekCnt",  mainDAO.selectipWeekCnt());  //재고상품입고현황 주단위 count
		countAndTime.put("ipMonthCnt", mainDAO.selectipMonthCnt()); //재고상품입고현황 월단위 count
		countAndTime.put("ipTime",     mainDAO.selectipTime());     //재고상품입고현황 데이터반영시점

		countAndTime.put("otDayCnt",   mainDAO.selectotDayCnt());   //출고대기현황 일단위 count
		countAndTime.put("otWeekCnt",  mainDAO.selectotWeekCnt());  //출고대기현황 주단위 count
		countAndTime.put("otMonthCnt", mainDAO.selectotMonthCnt()); //출고대기현황 월단위 count
		countAndTime.put("otTime",     mainDAO.selectotTime());     //출고대기현황 데이터반영시점

		countAndTime.put("prodMakerCnt", mainDAO.selectprodMakerCnt()); //현재운영상품수 제조사 출고상품 count
		countAndTime.put("prodStockCnt", mainDAO.selectprodStockCnt()); //현재운영상품수 재고상품 count
		countAndTime.put("prodTime",     mainDAO.selectprodTime());     //현재운영상품수 데이터반영시점

		return countAndTime;
	}

	@Override
	public Object selectDashBoardSetting(String emplyr_id) throws Exception {
		
		Ismdbo010VO ismdbo010VO = (Ismdbo010VO) mainDAO.selectDbo010(emplyr_id);
		
		if (ismdbo010VO == null) {
			Ismdbo010VO ismdbo010VONew = new Ismdbo010VO();
			ismdbo010VONew.setBargraph("Y");
			ismdbo010VONew.setIpStatus("Y");
			ismdbo010VONew.setLinegraph("Y");
			ismdbo010VONew.setOtStatus("Y");
			ismdbo010VONew.setProdStatus("Y");
			
			ismdbo010VO = ismdbo010VONew;
		}

		return ismdbo010VO;
	}

	@Override
	public String saveDashBoardSetting(Ismdbo010VO ismdbo010VO) throws Exception {
		mainDAO.insertOrUpdateDbo010(ismdbo010VO);
		return "SUCCESS";
	}
}
