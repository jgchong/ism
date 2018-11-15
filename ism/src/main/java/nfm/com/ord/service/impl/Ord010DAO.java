package nfm.com.ord.service.impl;

import java.util.List;

import nfm.com.ord.service.Ismodl010VO;
import nfm.com.ord.service.Ismodm010VO;
import nfm.com.ord.service.Ismodo010VO;
import nfm.com.ord.service.Ismodo020VO;
import nfm.com.ord.service.Ord010SearchVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("ord010DAO")
public class Ord010DAO extends EgovAbstractDAO {
	public int selectListTotCnt(Ord010SearchVO ord010SearchVO) {
		return (Integer)select("ord010DAO.selectListTotCnt", ord010SearchVO);
	}

	public List<?> selectList(Ord010SearchVO ord010SearchVO) {
		return list("ord010DAO.selectList", ord010SearchVO);
	}

	public List<?> selectApiDataDetail(String cum030id) {
		return list("ord010DAO.selectApiDataDetail", cum030id);
	}

	public void updateApiDetailData(Ismodo020VO ismodo020vo) {
    	update("ord010DAO.updateApiDetailData", ismodo020vo);
	}

	public void insertApiDetailData(Ismodo020VO ismodo020vo) {
    	insert("ord010DAO.insertApiDetailData", ismodo020vo);
	}

	public void deleteManualDetailData(String cum030id) {
    	delete("ord010DAO.deleteManualDetailData", cum030id);
	}

	public void insertManualDetailData(Ismodo010VO ismodo010vo) {
    	insert("ord010DAO.insertManualDetailData", ismodo010vo);
	}

	public List<?> selectManualDataDetail(String cum030id) {
		return list("ord010DAO.selectManualDataDetail", cum030id);
	}

	public void insertOrderMainData(Ismodm010VO ismodm010vo) {
    	insert("ord010DAO.insertOrderMainData", ismodm010vo);		
	}

	public void insertOrderLogData(List<Ismodl010VO> listIsmodl010VO) {
    	insert("ord010DAO.insertOrderLogData", listIsmodl010VO);	
	}

	public int selectCntNoItem(String orderTempKey) {
		return (Integer)select("ord010DAO.selectCntNoItem", orderTempKey);
	}
}
