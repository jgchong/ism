package nfm.com.main.service;

import org.json.simple.JSONObject;

public interface MainService  {

	public MainGraphRetVO selectChartAllYear(String selectVal) throws Exception;

	public JSONObject selectChart(MainSearchVO mainSearchVO) throws Exception;
	
}