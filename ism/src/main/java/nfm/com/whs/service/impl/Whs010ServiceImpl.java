package nfm.com.whs.service.impl;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.whs.service.Ismwhs020VO;
import nfm.com.whs.service.Whs010SearchVO;
import nfm.com.whs.service.Whs010Service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service("whs010Service")
public class Whs010ServiceImpl extends EgovAbstractServiceImpl implements Whs010Service {

	/** byc010DAO */
	@Resource(name="whs010DAO")
	private Whs010DAO whs010DAO;

	@SuppressWarnings("unchecked")
	@Override
	public String selectDetail(Whs010SearchVO whs010SearchVO) throws Exception {
		
    	JSONObject jsonObject = new JSONObject();

    	jsonObject.put("byc010id", "");
    	jsonObject.put("bycname", "");
    	jsonObject.put("byctype", "");
    	jsonObject.put("cogubun", "");
    	jsonObject.put("cono", "");
    	jsonObject.put("lawcono", "");
    	jsonObject.put("coaddr", "");
    	jsonObject.put("cobustype", "");
    	jsonObject.put("account", "");
    	jsonObject.put("accountno", "");
    	jsonObject.put("useyn", "");
    	jsonObject.put("purchaseform", "");
    	jsonObject.put("attachname", "");
    	jsonObject.put("byccode", "");
    	jsonObject.put("uploadgubun", "");
    	jsonObject.put("orgfilename", "");
    	jsonObject.put("savefilename", "");

    	jsonObject.put("cumMemo", "");
    	
    	//창고 담당자 목록 정보 가져옴.[s]
		List<Ismwhs020VO> listIsmwhs020VO = (List<Ismwhs020VO>) whs010DAO.selectWhs020List(whs010SearchVO); //whs010id로 조회

		JSONArray jsonArray020 = new JSONArray();
    	
	    for(Ismwhs020VO vo : listIsmwhs020VO){
	    	JSONObject jsonObject020 = new JSONObject();
	    	jsonObject020.put("whs010id", vo.getWhs010id());
	    	jsonObject020.put("whs020id", vo.getWhs020id());

	    	if (vo.getWhsusername() == null) {
	    		jsonObject020.put("username", "");
	    	}else{
	    		jsonObject020.put("username", URLEncoder.encode(vo.getWhsusername(), "UTF-8"));
	    	}

	    	if (vo.getWhsusertel() == null) {
	    		jsonObject020.put("usertel", "");
	    	}else{
	    		jsonObject020.put("usertel", URLEncoder.encode(vo.getWhsusertel(), "UTF-8"));
	    	}

	    	if (vo.getWhsuseremail() == null) {
	    		jsonObject020.put("useremail", "");
	    	}else{
	    		jsonObject020.put("useremail", URLEncoder.encode(vo.getWhsuseremail(), "UTF-8"));
	    	}

	    	if (vo.getWhsmemo() == null) {
	    		jsonObject020.put("memo", "");
	    	}else{
	    		jsonObject020.put("memo", URLEncoder.encode(vo.getWhsmemo(), "UTF-8"));
	    	}

	    	jsonArray020.add(jsonObject020);
	    }
	    jsonObject.put("userlist", jsonArray020);
	    //창고 담당자 목록 정보 가져옴.[e]

	    return jsonObject.toString();
	}
}
