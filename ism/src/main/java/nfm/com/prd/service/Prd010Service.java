package nfm.com.prd.service;

import java.util.Map;

public interface Prd010Service {
    public Object selectWhsAll() throws Exception;

    public Object selectBycAll() throws Exception;

    public Object selectList(Prd010SearchVO prd010SearchVO) throws Exception;

    public int selectListTotCnt(Prd010SearchVO prd010SearchVO) throws Exception;

    public void updateItemWhs(Integer itemId, Integer whsId) throws Exception;

    public String selectAll() throws Exception;

    public String selectGubun2() throws Exception;

    public String selectWithItemcode(String itemcode) throws Exception;

    public String insertItem(Map param) throws Exception;

    public String updateItem(Map param) throws Exception;

    public String updateItemForBatchUp(Map param) throws Exception;

    public String updateCross(String itemcode, String targetItemcodes) throws Exception;

    public void prd010SelectDel(String chgodm010ids) throws Exception;
}