package nfm.com.cum.service.impl;

import java.util.HashMap;
import java.util.List;

import nfm.com.cum.service.Ismcum040VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("cum020DAO")
public class Cum020DAO extends EgovAbstractDAO {
	public List<?> selectListCumProdList(int search_cum030id) {
		return list("cum020DAO.selectListCumProdList", search_cum030id);
	}

	public void insertCum040(Ismcum040VO ismcum040vo) {
		insert("cum020DAO.insertCum040", ismcum040vo);
	}
	
	@SuppressWarnings("rawtypes")
	public List<?> selectList040(HashMap hm) {
		return list("cum020DAO.selectList040", hm);
	}

	public void deleteCum040(int cum030id) {
		delete("cum020DAO.deleteCum040", cum030id);
	}
}
