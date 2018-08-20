package nfm.com.cmm.service.impl;

import java.util.List;

import nfm.com.cmm.service.IsmCmm010VO;
import nfm.com.cmm.service.IsmCmm020VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("cmm010DAO")
public class Cmm010DAO extends EgovAbstractDAO {

	public List<?> selectListMemo(IsmCmm010VO ismCmm010VO) {
		return list("cmm010DAO.selectListMemo", ismCmm010VO);
	}

	public void insertMemo(IsmCmm010VO ismCmm010VO) {
		insert("cmm010DAO.insertMemo", ismCmm010VO);
	}

	public IsmCmm020VO selectCmm020(int cmm020id) {
		return (IsmCmm020VO) select("cmm010DAO.selectCmm020", cmm020id);
	}
}
