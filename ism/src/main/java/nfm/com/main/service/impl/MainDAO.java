package nfm.com.main.service.impl;

import java.util.List;

import nfm.com.main.service.Ismadj090VO;
import nfm.com.main.service.Ismdbo010VO;

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
	
	public String selectipDayCnt() {
		return (String) select("mainDAO.selectipDayCnt");
	}
	
	public String selectipWeekCnt() {
		return (String) select("mainDAO.selectipWeekCnt");
	}
	
	public String selectipMonthCnt() {
		return (String) select("mainDAO.selectipMonthCnt");
	}
	
	public String selectipTime() {
		return (String) select("mainDAO.selectipTime");
	}
	
	public String selectotDayCnt() {
		return (String) select("mainDAO.selectotDayCnt");
	}
	
	public String selectotWeekCnt() {
		return (String) select("mainDAO.selectotWeekCnt");
	}
	
	public String selectotMonthCnt() {
		return (String) select("mainDAO.selectotMonthCnt");
	}
	
	public String selectotTime() {
		return (String) select("mainDAO.selectotTime");
	}

	public String selectprodMakerCnt() {
		return (String) select("mainDAO.selectprodMakerCnt");
	}
	
	public String selectprodStockCnt() {
		return (String) select("mainDAO.selectprodStockCnt");
	}
	
	public String selectprodTime() {
		return (String) select("mainDAO.selectprodTime");
	}
	
	public Object selectDbo010(String emplyr_id) {
		return select("mainDAO.selectDbo010", emplyr_id);
	}
	
	public void insertOrUpdateDbo010(Ismdbo010VO ismdbo010VO) {
		insert("mainDAO.insertOrUpdateDbo010", ismdbo010VO);
	}

	public void accountCloseActSP(Ismadj090VO ismadj090vo) {
		insert("mainDAO.accountCloseActSP", ismadj090vo);
	}

	public List<?> selectAdj090(String today) {
		return list("mainDAO.selectAdj090", today);
	}
}
