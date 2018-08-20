package nfm.com.whs.service.impl;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.cmm.service.Cmm010Service;
import nfm.com.cmm.service.IsmCmm010VO;
import nfm.com.cmm.service.IsmCmm020VO;
import nfm.com.cmm.service.impl.Cmm020DAO;
import nfm.com.cmm.util.TempKey;
import nfm.com.whs.service.Ismwhs010VO;
import nfm.com.whs.service.Ismwhs020VO;
import nfm.com.whs.service.Ismwhs030VO;
import nfm.com.whs.service.Whs010SearchVO;
import nfm.com.whs.service.Whs010Service;
import nfm.com.whs.service.WhsAllVO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("whs010Service")
public class Whs010ServiceImpl extends EgovAbstractServiceImpl implements Whs010Service {

	/** byc010DAO */
	@Resource(name="whs010DAO")
	private Whs010DAO whs010DAO;
	
	/** cmm020DAO */
	@Resource(name="cmm020DAO")
	private Cmm020DAO cmm020DAO;

	/** cmm010Service */
	@Resource(name = "cmm010Service")
	private Cmm010Service cmm010Service;

	@Override
	public List<?> selectList(Whs010SearchVO whs010SearchVO) throws Exception {
		List<?> result = whs010DAO.selectWhs010List(whs010SearchVO);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String selectDetail(Whs010SearchVO whs010SearchVO) throws Exception {
		
		List<Ismwhs010VO> listIsmwhs010VO = (List<Ismwhs010VO>) whs010DAO.selectWhs010List(whs010SearchVO);
		
    	JSONObject jsonObject = new JSONObject();
    	
		if (listIsmwhs010VO.size() == 0) { //데이터 없는 경우 초기화해서 웹화면 신규등록에 사용
	    	jsonObject.put("whs010id", "");
	    	jsonObject.put("whsgubun", "");
	    	jsonObject.put("whsname", "");
	    	jsonObject.put("whscotype", "");
	    	jsonObject.put("whscono", "");
	    	jsonObject.put("whslawcono", "");
	    	jsonObject.put("whsadress", "");
	    	jsonObject.put("whsbustype", "");
	    	jsonObject.put("accontdate", "");
	    	jsonObject.put("orgfilename", "");
	    	jsonObject.put("savefilename", "");
	    	jsonObject.put("cmm020id", "");
		}
		
	    for(Ismwhs010VO vo : listIsmwhs010VO){
	    	jsonObject.put("whs010id", vo.getWhs010id());
	    	jsonObject.put("whsgubun", vo.getWhsgubun());
	    	
	    	if (vo.getWhsname() == null) {
	    		jsonObject.put("whsname", "");
	    	}else{
	    		jsonObject.put("whsname", URLEncoder.encode(vo.getWhsname(), "UTF-8"));
	    	}

	    	jsonObject.put("whscotype", vo.getWhscotype());
	    	jsonObject.put("whscono", vo.getWhscono());
	    	jsonObject.put("whslawcono", vo.getWhslawcono());

	    	if (vo.getWhsadress() == null) {
	    		jsonObject.put("whsadress", "");
	    	}else{
	    		jsonObject.put("whsadress", URLEncoder.encode(vo.getWhsadress(), "UTF-8"));
	    	}

	    	if (vo.getWhsbustype() == null) {
	    		jsonObject.put("whsbustype", "");
	    	}else{
	    		jsonObject.put("whsbustype", URLEncoder.encode(vo.getWhsbustype(), "UTF-8"));
	    	}

	    	jsonObject.put("accontdate", vo.getAccontdate());
	    	
	    	if (vo.getOrgfilename() == null) {
	    		jsonObject.put("orgfilename", "");
	    	}else{
	    		jsonObject.put("orgfilename", URLEncoder.encode(vo.getOrgfilename(), "UTF-8"));
	    	}

	    	if (vo.getSavefilename() == null) {
	    		jsonObject.put("savefilename", "");
	    	}else{
	    		jsonObject.put("savefilename", URLEncoder.encode(vo.getSavefilename(), "UTF-8"));
	    	}

	    	jsonObject.put("cmm020id", vo.getCmm020id());
	    }

	    //매입처 메모 정보 가져옴.[s]
	    IsmCmm010VO ismCmm010VO = new IsmCmm010VO();
	    ismCmm010VO.setBuss_key(""+whs010SearchVO.getWhs010id());
	    ismCmm010VO.setBuss_type("ST");
	    String strMemo = (String) cmm010Service.selectListMemo(ismCmm010VO);
    	jsonObject.put("whsMemo", URLEncoder.encode(strMemo, "UTF-8"));
    	//매입처 메모 정보 가져옴.[e]
    	
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
    	
    	//창고 admin 목록 정보 가져옴.[s]
		List<Ismwhs030VO> listIsmwhs030VO = (List<Ismwhs030VO>) whs010DAO.selectWhs030List(whs010SearchVO); //whs010id로 조회
    	JSONArray jsonArray030 = new JSONArray();
	    for(Ismwhs030VO vo : listIsmwhs030VO){
	    	JSONObject jsonObject030 = new JSONObject();
	    	jsonObject030.put("whs010id", vo.getWhs010id());
	    	jsonObject030.put("whs030id", vo.getWhs030id());

	    	if (vo.getWhsurl() == null) {
	    		jsonObject030.put("whsurl", "");
	    	}else{
	    		jsonObject030.put("whsurl", URLEncoder.encode(vo.getWhsurl(), "UTF-8"));
	    	}
	    	
	    	if (vo.getWhsuid() == null) {
	    		jsonObject030.put("whsuid", "");
	    	}else{
	    		jsonObject030.put("whsuid", URLEncoder.encode(vo.getWhsuid(), "UTF-8"));
	    	}

			jsonObject030.put("whspwd", vo.getWhspwd());
			
			jsonArray030.add(jsonObject030);
	    }
	    jsonObject.put("whs030", jsonArray030);
	    //창고 admin 목록 정보 가져옴.[e]

	    return jsonObject.toString();
	}

	@Override
	public int saveWhsAll(MultipartFile mf, String path, WhsAllVO whsAllVO) throws Exception {
		
		int cmm020id = 0;
		if (mf != null) {
			String orgfilename  = mf.getOriginalFilename();
	
			GregorianCalendar gc = new GregorianCalendar();
	        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
	        Date d = gc.getTime();
	        String str = sf.format(d) + new TempKey().getKey(6, false);
	        
			String savefilename = path + orgfilename + str;
			
			mf.transferTo(new File(savefilename));
			
			IsmCmm020VO ismCmm020VO = new IsmCmm020VO();
			ismCmm020VO.setOrgfilename(orgfilename);
			ismCmm020VO.setSavefilename(orgfilename + str);
			cmm020id = cmm020DAO.insertCmm020(ismCmm020VO);
		}
		
		Ismwhs010VO ismwhs010VO = new Ismwhs010VO();
		ismwhs010VO.setWhs010id(whsAllVO.getWhs010id());
		ismwhs010VO.setWhsgubun(whsAllVO.getWhsgubun());
		ismwhs010VO.setWhsname(whsAllVO.getWhsname());
		ismwhs010VO.setWhscotype(whsAllVO.getWhscotype());
		ismwhs010VO.setWhscono(whsAllVO.getWhscono());
		ismwhs010VO.setWhslawcono(whsAllVO.getWhslawcono());
		ismwhs010VO.setWhsadress(whsAllVO.getWhsadress());
		ismwhs010VO.setWhsbustype(whsAllVO.getWhsbustype());
		ismwhs010VO.setAccontdate(whsAllVO.getAccontdate());
		ismwhs010VO.setCmm020id(cmm020id);

		int whs010id = 0;
		if (whsAllVO.getWhs010id() <= 0) {
			whs010id = whs010DAO.insertWhs010(ismwhs010VO);
		}else{
			whs010id = whsAllVO.getWhs010id();
			whs010DAO.updateWhs010(ismwhs010VO);
			whs010DAO.deleteWhs020(ismwhs010VO);
			whs010DAO.deleteWhs030(ismwhs010VO);
		}

		String[] whsusername = whsAllVO.getWhsusername();
		String[] whsusertel = whsAllVO.getWhsusertel();
		String[] whsuseremail = whsAllVO.getWhsuseremail();
		String[] whsmemo = whsAllVO.getWhsmemo();
		for(int i=0;i < whsusername.length; i++) {
			Ismwhs020VO ismwhs020VO = new Ismwhs020VO();
			ismwhs020VO.setWhs010id(whs010id);
			ismwhs020VO.setWhsusername(whsusername[i]);
			if (whsusertel.length > 0) ismwhs020VO.setWhsusertel(whsusertel[i]);
			if (whsuseremail.length > 0) ismwhs020VO.setWhsuseremail(whsuseremail[i]);
			if (whsmemo.length > 0) ismwhs020VO.setWhsmemo(whsmemo[i]);
			
			whs010DAO.insertWhs020(ismwhs020VO);
		}
		
		String[] whsurl = whsAllVO.getWhsurl();
		String[] whsuid = whsAllVO.getWhsuid();
		String[] whspwd = whsAllVO.getWhspwd();

		for(int i=0;i < whsurl.length; i++) {
			Ismwhs030VO ismwhs030VO = new Ismwhs030VO();
			ismwhs030VO.setWhs010id(whs010id);
			if (whsurl.length > 0) ismwhs030VO.setWhsurl(whsurl[i]);
			if (whsuid.length > 0) ismwhs030VO.setWhsuid(whsuid[i]);
			if (whspwd.length > 0) ismwhs030VO.setWhspwd(whspwd[i]);

			whs010DAO.insertWhs030(ismwhs030VO);
		}
		
		return whs010id;
	}
}
