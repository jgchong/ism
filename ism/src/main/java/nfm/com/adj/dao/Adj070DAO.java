package nfm.com.adj.dao;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Adj070DAO extends EgovAbstractDAO {
    public Object selectObject(String yyyymm) {
        return select("adj070DAO.selectList", yyyymm);
    }
    public void insertInit(String yyyymm) {
        try {
            insert("adj070DAO.insertInit", yyyymm);
        } catch (Exception ignored) {
        }
    }

    public void updateItem07_01(Map param) {
        update("adj070DAO.updateItem07_01", param);
    }

    public List<?> selectAdj0702List(String yyyymm) {
        return list("adj0702DAO.selectList", yyyymm);
    }

    public void insertAdj0702(Map param) {
        insert("adj0702DAO.insert", param);
    }

    public void updateItem0702(Map param) {
        update("adj0702DAO.update", param);
    }
}

