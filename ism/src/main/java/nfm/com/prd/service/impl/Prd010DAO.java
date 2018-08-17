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
	public int selectListTotCnt(Prd010SearchVO prd010SearchVO) {
		return (Integer)select("prd010DAO.selectListTotCnt", prd010SearchVO);
	}

	public List<?> selectList(Prd010SearchVO prd010SearchVO) {
		return list("prd010DAO.selectList", prd010SearchVO);
	}

	public List<?> selectFusionList(String itemcode) {
		return list("prd010DAO.selectFusionList", itemcode);
	}

//	public void ord020SelectDel(Integer itemid) {
//		delete("ord020DAO.ord020SelectDel", itemid);
//	}

	public void updateItemWhs(Integer itemId, Integer whsId) {
		Map<String, Object> map = new HashMap<>();
		map.put("itemId", itemId);
		map.put("whsId", whsId);
		update("prd010DAO.updateItemWhs", map);
	}

}
