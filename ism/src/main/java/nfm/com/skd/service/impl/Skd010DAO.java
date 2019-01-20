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
    public Integer selectSkd030type3(String itemcode, String dtSearch_frCreateDt) {
        Map<String, String> param = new HashMap<>();
        param.put("itemcode", itemcode);
        param.put("dtSearch_frCreateDt", dtSearch_frCreateDt);
        return (Integer) select("skd010DAO.selectSkd030type3", param);
    }

    public int selectSkd030itemeaAtWhs010id(String itemcode, int whs010id) {
        Map<String, String> param = new HashMap<>();
        param.put("itemcode", itemcode);
        param.put("whs010id", String.valueOf(whs010id));
        return (Integer) select("skd010DAO.skd030itemeaAtWhs010id", param);
    }

    public List<?> selectList(Skd010SearchVO skd010SearchVO) {
        return list("skd010DAO.selectList", skd010SearchVO);
    }

    public List<?> selectskd030VOForList() {
        return list("skd010DAO.selectskd030VOForList");
    }
    public List<?> selectskd030VOForAdj(String lastMonth) {
        return list("skd010DAO.selectskd030VOForAdj", lastMonth);
    }
    public List<?> selectskd030VOForAdjDestination(String lastMonth) {
        return list("skd010DAO.selectskd030VOForAdjDestination", lastMonth);
    }
    public List<?> selectskd030VOForAdjSource(String lastMonth) {
        return list("skd010DAO.selectskd030VOForAdjSource", lastMonth);
    }


    public List<?> selectskd030VOForDetail(String itemcode) {
        return list("skd010DAO.selectskd030VOForDetail", itemcode);
    }

    public String selectSkd010Itemcode(String skd010id) {
        return (String) select("skd010DAO.selectSkd010Itemcode", skd010id);
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

    public void insertSkd030(Map param) throws Exception {
        insert("skd010DAO.insertSkd030", param);
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

    @SuppressWarnings("rawtypes")
    public void skd020zeroDelete() {
        delete("skd010DAO.skd020zeroDelete");
    }


}

