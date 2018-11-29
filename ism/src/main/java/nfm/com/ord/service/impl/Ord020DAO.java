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

	public List<?> adj020selectList(String yyyymm) {
		return list("adj020DAO.selectList", yyyymm);
	}

	public List<?> adj020selectListAll(String yyyymm) {
		return list("adj020DAO.selectListAll", yyyymm);
	}

	public Object adj020selectListAllNull(String yyyymm) {
		return select("adj020DAO.selectListAllNull", yyyymm);
	}

	public List<?> adj020selectListBYC(String yyyymm) {
		return list("adj020DAO.selectListBYC", yyyymm);
	}

	public List<?> adj020selectListBYCAll(String yyyymm) {
		return list("adj020DAO.selectListBYCAll", yyyymm);
	}

	public Object adj020selectListBYCAllNull(String yyyymm) {
		return select("adj020DAO.selectListBYCAllNull", yyyymm);
	}

	public List<?> adj020selectTop10List(String yyyymm) {
		return list("adj020DAO.selectTop10List", yyyymm);
	}
	
	// LDC - 임시 통계 정보 읽어오기.
	public List<?> selectStattList(Ord020SearchVO ord020SearchVO) {
		return list("ord020DAO.selectStattList", ord020SearchVO);
	}
	
	public List<?> selectCompList(Ord020SearchVO ord020SearchVO) {
		return list("ord020DAO.selectCompList", ord020SearchVO);
	}

	public List<?> selectProdList(Ord020SearchVO ord020SearchVO) {
		// TODO Auto-generated method stub
		return list("ord020DAO.selectProdList", ord020SearchVO);
	}
	@SuppressWarnings("rawtypes")
	public void ord020InsertProd(Ismodm010VO ismodm010vo) {
		insert("ord020DAO.ord020InsertProd", ismodm010vo);
	}

}
