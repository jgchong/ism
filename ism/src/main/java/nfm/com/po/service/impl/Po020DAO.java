package nfm.com.po.service.impl;

import java.util.HashMap;
import java.util.List;

import nfm.com.po.service.Ismpomsearch020VO;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("po020DAO")
public class Po020DAO extends EgovAbstractDAO {
	public List<?> selectPoList(Ismpomsearch020VO ismpomsearch020VO) {
		return list("po020DAO.selectPoList", ismpomsearch020VO);
	}
	
	public int selectPoListTotCnt(Ismpomsearch020VO ismpomsearch020VO) {
        return (Integer)select("po020DAO.selectPoListTotCnt", ismpomsearch020VO);
    }
}
