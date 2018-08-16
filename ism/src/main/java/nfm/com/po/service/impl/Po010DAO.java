package nfm.com.po.service.impl;

import java.util.HashMap;
import java.util.List;

import nfm.com.po.service.Ismpol010VO;
import nfm.com.po.service.Ismpom010VO;
import nfm.com.po.service.Ismpoo010VO;
import nfm.com.po.service.Ismpoo020VO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("po010DAO")
public class Po010DAO extends EgovAbstractDAO {
	public List<?> selectWhsList() {
		return list("po010DAO.selectWhsList");
	}

	public List<?> selectBycList() {
		return list("po010DAO.selectBycList");
	}

	public List<?> selectPoo010List(Ismpoo010VO poo010SearchVO) {
		return list("po010DAO.selectPoo010List", poo010SearchVO);
	}

	public void deletePoo010(Ismpoo010VO ismpoo010vo) {
		delete("po010DAO.deletePoo010", ismpoo010vo);
	}

	public void insertPoo010(Ismpoo010VO ismpoo010vo) {
		insert("po010DAO.insertPoo010", ismpoo010vo);
	}

	public void insertPoo020(Ismpoo020VO ismpoo020vo) {
		insert("po010DAO.insertPoo020", ismpoo020vo);
	}

	public List<?> selectPoo020(int byc010id) {
		return list("po010DAO.selectPoo020", byc010id);
	}

	public void updatePoo020(Ismpoo020VO ismpoo020vo) {
		update("po010DAO.updatePoo020", ismpoo020vo);
	}

	@SuppressWarnings("rawtypes")
	public void insertPom010Arr(HashMap hm) {
		insert("po010DAO.insertPom010Arr", hm);
	}

	public List<?> selectPom010List(Ismpom010VO ismpom010VO) {
		return list("po010DAO.selectPom010List", ismpom010VO);
	}

	public void updateDlv010(Ismpom010VO ismpom010VO) {
		update("po010DAO.updateDlv010", ismpom010VO);
	}

	public void insertPol010(Ismpol010VO ismpol010vo) {
		insert("po010DAO.insertPol010", ismpol010vo);
	}
}
