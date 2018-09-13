package nfm.com.adj.dao;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nfm.com.ord.service.Ord010SearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Adj060DAO extends EgovAbstractDAO {
    public List<?> selectList(String yyyymm) {
        return list("adj060DAO.selectList", yyyymm);
    }
    public void insertInit(String yyyymm) {
        try {
            insert("adj060DAO.insertInit", yyyymm);
        } catch (Exception ignored) {
        }
    }

    public void updateItem(Map param) {
        update("adj060DAO.updateItem", param);
    }
}

