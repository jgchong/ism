package nfm.com.skd.service.impl;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nfm.com.skd.service.Skd010SearchVO;
import nfm.com.skd.service.Skd010VO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("skd010DAO")
public class Skd010DAO extends EgovAbstractDAO {
    public int selectListTotCnt(Skd010SearchVO skd010SearchVO) {
        return (Integer) select("skd010DAO.selectListTotCnt", skd010SearchVO);
    }

    public List<?> selectList(Skd010SearchVO skd010SearchVO) {
        return list("skd010DAO.selectList", skd010SearchVO);
    }

    public Object selectWithSkd010id(String currentId) {
        return select("skd010DAO.selectWithSkd010id", currentId);
    }

    public List<?> selectWhsNameList(int skd010id) {
        return list("skd010DAO.selectWhsNameList", skd010id);
    }

    public int insertSkd010(Map param) throws Exception {
        int result = 0;
        try {
            result = (int) insert("skd010DAO.insertSkd010", param);
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    public int insertSkd020(Map param) throws Exception {
        int result = 0;
        try {
            result = (int) insert("skd010DAO.insertSkd020", param);
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    public void updateOriginSkd020(Map param) throws Exception {
        update("skd010DAO.updateOriginSkd020", param);
    }

    public void updateSkd020(Map param) throws Exception {
        update("skd010DAO.updateSkd020", param);
    }

    public Object skd020seletWhsitem(String whs010id) {
        return list("skd010DAO.skd020seletWhsitem", whs010id);
    }


    @SuppressWarnings("rawtypes")
    public void skd010SelectDel(HashMap hm) {
        delete("skd010DAO.skd010SelectDel", hm);
    }

    @SuppressWarnings("rawtypes")
    public void skd020SelectDel(HashMap hm) {
        delete("skd010DAO.skd020SelectDel", hm);
    }


}

