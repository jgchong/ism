package nfm.com.main.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("mainDAO")
public class MainDAO extends EgovAbstractDAO {

	public List<?> selectChartAllDay(String search_day) {
		return list("mainDAO.selectChartAllDay",search_day);
	}

	public List<?> selectChartProdDay(String search_day) {
		return list("mainDAO.selectChartProdDay",search_day);
	}

	public List<?> selectChartCustDay(String search_day) {
		return list("mainDAO.selectChartCustDay",search_day);
	}

	public List<?> selectChartBuyDay(String search_day) {
		return list("mainDAO.selectChartBuyDay",search_day);
	}

	public List<?> selectChartAllMon(String search_day) {
		return list("mainDAO.selectChartAllMon",search_day);
	}

	public List<?> selectChartProdMon(String search_day) {
		return list("mainDAO.selectChartProdMon",search_day);
	}

	public List<?> selectChartCustMon(String search_day) {
		return list("mainDAO.selectChartCustMon",search_day);
	}

	public List<?> selectChartBuyMon(String search_day) {
		return list("mainDAO.selectChartBuyMon",search_day);
	}

	public List<?> selectChartAllYear(String search_day) {
		return list("mainDAO.selectChartAllYear",search_day);
	}
	
	public List<?> selectChartProdYear(String search_day) {
		return list("mainDAO.selectChartProdYear",search_day);
	}

	public List<?> selectChartCustYear(String search_day) {
		return list("mainDAO.selectChartCustYear",search_day);
	}

	public List<?> selectChartBuyYear(String search_day) {
		return list("mainDAO.selectChartBuyYear",search_day);
	}
}
