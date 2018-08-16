package nfm.com.ord.service.impl;

import java.util.HashMap;
import java.util.List;

import nfm.com.ord.service.Ismodm010VO;
import nfm.com.ord.service.Ord020SearchVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("ord020DAO")
public class Ord020DAO extends EgovAbstractDAO {
	public int selectListTotCnt(Ord020SearchVO ord020SearchVO) {
		return (Integer)select("ord020DAO.selectListTotCnt", ord020SearchVO);
	}

	public List<?> selectList(Ord020SearchVO ord020SearchVO) {
		return list("ord020DAO.selectList", ord020SearchVO);
	}

	@SuppressWarnings("rawtypes")
	public void ord020SelectChgOrderStatus(HashMap hm) {
		update("ord020DAO.ord020SelectChgOrderStatus", hm);
	}

	@SuppressWarnings("rawtypes")
	public void ord020SelectDel(HashMap hm) {
		delete("ord020DAO.ord020SelectDel", hm);
	}

	public void updateOrderDetailData(Ismodm010VO ismodm010vo) {
		update("ord020DAO.updateOrderDetailData", ismodm010vo);
	}
}
