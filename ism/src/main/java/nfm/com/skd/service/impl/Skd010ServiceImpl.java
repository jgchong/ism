package nfm.com.skd.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.skd.service.Skd010SearchVO;
import nfm.com.skd.service.Skd010Service;
import nfm.com.skd.service.Skd020VO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("skd010Service")
public class Skd010ServiceImpl extends EgovAbstractServiceImpl implements Skd010Service {

    /**
     * ord010DAO
     */
    @Resource(name = "skd010DAO")
    private Skd010DAO skd010DAO;


    @Override
    public Object selectList(Skd010SearchVO skd010SearchVO) throws Exception {
        return null;
    }

    @Override
    public int selectListTotCnt(Skd010SearchVO skd010SearchVO) throws Exception {
        return 0;
    }

    @Override
    public int insertSkd010(Map param) throws Exception {
        return skd010DAO.insertSkd010(param);
    }

    @Override
    public int insertSkd020(Map param) throws Exception {
        return skd010DAO.insertSkd020(param);
    }

    @Override
    public String prd020seletWhsitem(String whs010id) throws Exception {
        List<Skd020VO> skd020VOList = (List<Skd020VO>) skd010DAO.prd020seletWhsitem(whs010id);
        JSONArray jsonArray = new JSONArray();
        for (Skd020VO skd020VO : skd020VOList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("skd010id", skd020VO.getSkd010id());
            jsonObject.put("label", skd020VO.getItemname() + " (" + skd020VO.getCreatedate() + ")");
            jsonObject.put("itemea", skd020VO.getItemea());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}

