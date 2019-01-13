package nfm.com.cum.service.impl;

import java.util.List;

import nfm.com.cum.service.Cum010SearchVO;
import nfm.com.cum.service.Ismcum010VO;
import nfm.com.cum.service.Ismcum020VO;
import nfm.com.cum.service.Ismcum030VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("cum010DAO")
public class Cum010DAO extends EgovAbstractDAO {
	public int selectListTotCnt(Cum010SearchVO cum010SearchVO) {
		return (Integer)select("cum010DAO.selectListTotCnt", cum010SearchVO);
	} 

	public List<?> selectList(Cum010SearchVO cum010SearchVO) {
		return list("cum010DAO.selectList", cum010SearchVO);
	}

	public List<?> selectCum020List(Cum010SearchVO cum010SearchVO) {
		return list("cum010DAO.selectCum020List", cum010SearchVO);
	}

	public List<?> selectCum030List(Cum010SearchVO cum010SearchVO) {
		return list("cum010DAO.selectCum030List", cum010SearchVO);
	}

	public String insertCum010(Ismcum010VO ismcum010VO) {
		return (String) insert("cum010DAO.insertCum010", ismcum010VO);
	}

	public void updateCum010(Ismcum010VO ismcum010VO) {
		update("cum010DAO.updateCum010", ismcum010VO);
	}

	public void deleteCum020(Ismcum010VO ismcum010VO) {
		delete("cum010DAO.deleteCum020", ismcum010VO);
	}

	public void deleteCum030(Ismcum010VO ismcum010VO) {
		delete("cum010DAO.deleteCum030", ismcum010VO);
	}

	public void updateAllCum030(Ismcum010VO ismcum010VO) {
		delete("cum010DAO.updateAllCum030", ismcum010VO);
	}

	public void insertCum020(Ismcum020VO ismcum020VO) {
		insert("cum010DAO.insertCum020", ismcum020VO);
	}

	public void insertCum030(Ismcum030VO ismcum030VO) {
		insert("cum010DAO.insertCum030", ismcum030VO);
	}

	public void updateCum030(Ismcum030VO ismcum030VO) {
		insert("cum010DAO.updateCum030", ismcum030VO);
	}
}
