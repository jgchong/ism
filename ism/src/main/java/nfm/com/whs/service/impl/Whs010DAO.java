package nfm.com.whs.service.impl;

import java.util.List;

import nfm.com.whs.service.Whs010SearchVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("whs010DAO")
public class Whs010DAO extends EgovAbstractDAO {
	
	public List<?> selectWhs020List(Whs010SearchVO whs010SearchVO) {
		return list("whs010DAO.selectWhs020List", whs010SearchVO);
	}

}
