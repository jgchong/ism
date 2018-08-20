package nfm.com.prd.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.byc.service.impl.Byc010DAO;
import nfm.com.prd.service.Prd010SearchVO;
import nfm.com.prd.service.Prd010Service;
import nfm.com.prd.service.Prd010VO;
import nfm.com.whs.service.impl.Whs010DAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.List;

@Service("prd010Service")
public class Prd010ServiceImpl extends EgovAbstractServiceImpl implements Prd010Service {

    /**
     * ord010DAO
     */
    @Resource(name = "prd010DAO")
    private Prd010DAO prd010DAO;

    /**
     * ord010DAO
     */
    @Resource(name = "whs010DAO")
    private Whs010DAO ismwhs010DAO;

    /**
     * ord010DAO
     */
    @Resource(name = "byc010DAO")
    private Byc010DAO ismbyc010DAO;

    @Override
    public Object selectList(Prd010SearchVO prd010SearchVO) throws Exception {
        List<Prd010VO> prd010VOList = (List<Prd010VO>) prd010DAO.selectList(prd010SearchVO);
        for (int i = 0; i < prd010VOList.size(); i++) {
            prd010VOList.get(i).setListNo("" + (i + 1));
        }
        for (int i = 0; i < prd010VOList.size(); i++) {
            Prd010VO prd010VO = prd010VOList.get(i);
            if ("P".equals(prd010VO.getItemcrosstype())) {
                List<Prd010VO> prd010VOFusionList = (List<Prd010VO>) prd010DAO.selectFusionList(prd010VO.getItemcode());
                if (prd010VOFusionList != null && prd010VOFusionList.size() > 0) {
                    for (Prd010VO prd010VO1 : prd010VOFusionList) {
                        prd010VOList.add(i + 1, prd010VO1);
                    }
                }
            }
        }
        return prd010VOList;
    }

    @Override
    public int selectListTotCnt(Prd010SearchVO prd010SearchVO) throws Exception {
        return prd010DAO.selectListTotCnt(prd010SearchVO);
    }

    @Override
    public void updateItemWhs(Integer itemId, Integer whsId) throws Exception {
        prd010DAO.updateItemWhs(itemId, whsId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String selectAll() throws Exception {
        List<Prd010VO> prd010VOList = (List<Prd010VO>) prd010DAO.selectAll();
        JSONArray jsonArray = new JSONArray();
        for (Prd010VO prd010VO : prd010VOList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemcode", prd010VO.getItemcode());
            jsonObject.put("label", prd010VO.getItemname());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public Object selectWhsAll() throws Exception {
        return ismwhs010DAO.selectAll();
    }

    @Override
    public Object selectBycAll() throws Exception {
        return ismbyc010DAO.selectAll();
    }
}
