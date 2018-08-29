package nfm.com.main.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
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
}
