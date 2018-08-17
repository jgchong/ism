package nfm.com.byc.service.impl;

import java.util.List;

import nfm.com.byc.service.Byc010SearchVO;
import nfm.com.byc.service.Ismbyc010VO;
import nfm.com.byc.service.Ismbyc020VO;
import nfm.com.byc.service.Ismbyc030VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("byc010DAO")
public class Byc010DAO extends EgovAbstractDAO {
	public List<?> selectAll() {
		return list("ismbyc010DAO.selectAll");
	}

	public List<?> selectByc010List(Byc010SearchVO byc010SearchVO) {
		return list("byc010DAO.selectByc010List", byc010SearchVO);
	}

	public List<?> selectByc020List(Byc010SearchVO byc010SearchVO) {
		return list("byc010DAO.selectByc020List", byc010SearchVO);
	}

	public List<?> selectByc030List(Byc010SearchVO byc010SearchVO) {
		return list("byc010DAO.selectByc030List", byc010SearchVO);
	}

	public int insertByc010(Ismbyc010VO ismbyc010vo) {
		return (int) insert("byc010DAO.insertByc010", ismbyc010vo);
	}

	public void updateByc010(Ismbyc010VO ismbyc010VO) {
		update("byc010DAO.updateByc010", ismbyc010VO);
	}

	public void deleteByc020(Ismbyc010VO ismbyc010VO) {
		delete("byc010DAO.deleteByc020", ismbyc010VO);
	}

	public void deleteByc030(Ismbyc010VO ismbyc010VO) {
		delete("byc010DAO.deleteByc030", ismbyc010VO);
	}

	public void insertByc020(Ismbyc020VO ismbyc020VO) {
		insert("byc010DAO.insertByc020", ismbyc020VO);
	}

	public void insertByc030(Ismbyc030VO ismbyc030VO) {
		insert("byc010DAO.insertByc030", ismbyc030VO);
	}
}
