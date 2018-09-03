package nfm.com.main.service;

import org.json.simple.JSONObject;

public interface MainService  {

	public MainGraphRetVO selectChartAllYear(String selectVal) throws Exception;

	public JSONObject selectChart(MainSearchVO mainSearchVO) throws Exception;

	public Object selectYear() throws Exception;

	public Object selectMonth() throws Exception;

	public Object selectCountAndTime() throws Exception;

	public Object selectDashBoardSetting(String emplyr_id) throws Exception;

	public String saveDashBoardSetting(Ismdbo010VO ismdbo010VO) throws Exception;
	
}