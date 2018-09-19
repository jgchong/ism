package nfm.com.adj.dao;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nfm.com.adj.model.Adj040Result;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Adj040DAO extends EgovAbstractDAO {
    public List<?> selectList(String yyyymm) {
        return list("adj040DAO.selectList", yyyymm);
    }

    public Object selectListSum(String yyyymm) {
        return select("adj040DAO.selectListSum", yyyymm);
    }

    public void insertInit(String yyyymm) {
        try {
            insert("adj040DAO.insertInit", yyyymm);
        } catch (Exception ignored) {
        }
    }

    public void updateItem(Map param) {
        update("adj040DAO.updateItem", param);
    }

    public void updateItem07_03(Map param) {
        update("adj040DAO.updateItem07_03", param);
    }

    public void updateItem07_04(Map param) {
        update("adj040DAO.updateItem07_04", param);
    }
}

