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

    public void updateItem07_02(Map param) {
        update("adj070DAO.updateItem07_02", param);
    }
}

