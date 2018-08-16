package nfm.com.cmm.service.impl;

import nfm.com.cmm.service.IsmCmm020VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("cmm020DAO")
public class Cmm020DAO extends EgovAbstractDAO {

	public int insertCmm020(IsmCmm020VO ismCmm020VO) {
		return (int) insert("cmm020DAO.insertCmm020", ismCmm020VO);
	}
}
