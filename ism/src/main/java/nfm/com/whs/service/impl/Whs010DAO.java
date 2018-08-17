package nfm.com.whs.service.impl;

import java.util.List;

import nfm.com.whs.service.Ismwhs010VO;
import nfm.com.whs.service.Ismwhs020VO;
import nfm.com.whs.service.Ismwhs030VO;
import nfm.com.whs.service.Whs010SearchVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("whs010DAO")
public class Whs010DAO extends EgovAbstractDAO {
	public List<?> selectAll() {
		return list("ismwhs010DAO.selectAll");
	}

	public List<?> selectWhs020List(Whs010SearchVO whs010SearchVO) {
		return list("whs010DAO.selectWhs020List", whs010SearchVO);
	}

	public List<?> selectWhs010List(Whs010SearchVO whs010SearchVO) {
		return list("whs010DAO.selectWhs010List", whs010SearchVO);
	}

	public List<?> selectWhs030List(Whs010SearchVO whs010SearchVO) {
		return list("whs010DAO.selectWhs030List", whs010SearchVO);
	}

	public int insertWhs010(Ismwhs010VO ismwhs010vo) {
		return (int) insert("whs010DAO.insertWhs010", ismwhs010vo);
	}

	public void updateWhs010(Ismwhs010VO ismwhs010vo) {
		update("whs010DAO.updateWhs010", ismwhs010vo);
	}

	public void deleteWhs020(Ismwhs010VO ismwhs010vo) {
		delete("whs010DAO.deleteWhs020", ismwhs010vo);
	}

	public void deleteWhs030(Ismwhs010VO ismwhs010vo) {
		delete("whs010DAO.deleteWhs030", ismwhs010vo);
	}

	public void insertWhs020(Ismwhs020VO ismwhs020vo) {
		insert("whs010DAO.insertWhs020", ismwhs020vo);
	}

	public void insertWhs030(Ismwhs030VO ismwhs030vo) {
		insert("whs010DAO.insertWhs030", ismwhs030vo);
	}

}
