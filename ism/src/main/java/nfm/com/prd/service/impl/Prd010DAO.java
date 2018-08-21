package nfm.com.prd.service.impl;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nfm.com.ord.service.*;
import nfm.com.prd.service.Prd010SearchVO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("prd010DAO")
public class Prd010DAO extends EgovAbstractDAO {
	public Object selectPrd010VO(String itemcode) {
		return select("prd010DAO.selectPrd010VO", itemcode);
	}

	public List<?> selectAll() {
		return list("prd010DAO.selectAll");
	}

	public int selectListTotCnt(Prd010SearchVO prd010SearchVO) {
		return (Integer)select("prd010DAO.selectListTotCnt", prd010SearchVO);
	}

	public List<?> selectList(Prd010SearchVO prd010SearchVO) {
		return list("prd010DAO.selectList", prd010SearchVO);
	}

	public List<?> selectFusionList(String itemcode) {
		return list("prd010DAO.selectFusionList", itemcode);
	}

	public Object selectWithItemcode(String itemcode) {
		return select("prd010DAO.selectWithItemcode", itemcode);
	}

	public void updateItemWhs(Integer itemId, Integer whsId) {
		Map<String, Object> map = new HashMap<>();
		map.put("itemId", itemId);
		map.put("whsId", whsId);
		update("prd010DAO.updateItemWhs", map);
	}

	public void updateCrossitemcodeInit(String itemcode) {
		update("prd010DAO.updateCrossitemcodeInit", itemcode);
	}

	public void updateCrossitemcodes(String itemcode, String targetItemcodes) {
		String[] targetItemcodesArr = targetItemcodes.split(",");
		Map<String, Object> map = new HashMap<>();
		map.put("itemcode", itemcode);
		map.put("targetItemcodes", targetItemcodesArr);
		update("prd010DAO.updateCrossitemcodes", map);
	}

	public void insertItem(Map param) {
		insert("prd010DAO.insertItem", param);
	}

	public void updateItem(Map param) {
		update("prd010DAO.updateItem", param);
	}

	@SuppressWarnings("rawtypes")
	public void prd010SelectDel(HashMap hm) {
		delete("prd010DAO.prd010SelectDel", hm);
	}
}
