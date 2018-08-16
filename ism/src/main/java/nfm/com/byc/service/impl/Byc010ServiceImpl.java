package nfm.com.byc.service.impl;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.byc.service.Byc010SearchVO;
import nfm.com.byc.service.Byc010Service;
import nfm.com.byc.service.BycAllVO;
import nfm.com.byc.service.Ismbyc010VO;
import nfm.com.byc.service.Ismbyc020VO;
import nfm.com.byc.service.Ismbyc030VO;
import nfm.com.cmm.service.Cmm010Service;
import nfm.com.cmm.service.IsmCmm010VO;
import nfm.com.cmm.service.IsmCmm020VO;
import nfm.com.cmm.service.impl.Cmm020DAO;
import nfm.com.cmm.util.TempKey;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("byc010Service")
public class Byc010ServiceImpl extends EgovAbstractServiceImpl implements Byc010Service {

	/** byc010DAO */
	@Resource(name="byc010DAO")
	private Byc010DAO byc010DAO;
	
	/** cmm020DAO */
	@Resource(name="cmm020DAO")
	private Cmm020DAO cmm020DAO;

	/** cmm010Service */
	@Resource(name = "cmm010Service")
	private Cmm010Service cmm010Service;

	@Override
	public List<?> selectBuyerList(Byc010SearchVO byc010SearchVO) throws Exception {

		return byc010DAO.selectByc010List(byc010SearchVO);

	}

	@SuppressWarnings("unchecked")
	@Override
	public String selectDetail(Byc010SearchVO byc010SearchVO) throws Exception {
		
		List<Ismbyc010VO> listIsmbyc010VO = (List<Ismbyc010VO>) byc010DAO.selectByc010List(byc010SearchVO);
		
    	JSONObject jsonObject = new JSONObject();
    	
		if (listIsmbyc010VO.size() == 0) { //데이터 없는 경우 초기화해서 웹화면 신규등록에 사용
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
		}
		
	    for(Ismbyc010VO vo : listIsmbyc010VO){
	    	jsonObject.put("byc010id", vo.getByc010id());
	    	
	    	if (vo.getBycname() == null) {
	    		jsonObject.put("bycname", "");
	    	}else{
	    		jsonObject.put("bycname", URLEncoder.encode(vo.getBycname(), "UTF-8"));
	    	}

	    	jsonObject.put("byctype", vo.getByctype());
	    	jsonObject.put("cogubun", vo.getCogubun());
	    	jsonObject.put("cono", vo.getCono());
	    	jsonObject.put("lawcono", vo.getLawcono());

	    	if (vo.getCoaddr() == null) {
	    		jsonObject.put("coaddr", "");
	    	}else{
	    		jsonObject.put("coaddr", URLEncoder.encode(vo.getCoaddr(), "UTF-8"));
	    	}

	    	if (vo.getCobustype() == null) {
	    		jsonObject.put("cobustype", "");
	    	}else{
	    		jsonObject.put("cobustype", URLEncoder.encode(vo.getCobustype(), "UTF-8"));
	    	}

	    	if (vo.getAccount() == null) {
	    		jsonObject.put("account", "");
	    	}else{
	    		jsonObject.put("account", URLEncoder.encode(vo.getAccount(), "UTF-8"));
	    	}

	    	jsonObject.put("accountno", vo.getAccountno());
	    	jsonObject.put("useyn", vo.getUseyn());
	    	
	    	if (vo.getAttachname() == null) {
	    		jsonObject.put("attachname", "");
	    	}else{
	    		jsonObject.put("attachname", URLEncoder.encode(vo.getAttachname(), "UTF-8"));
	    	}
	    	
	    	jsonObject.put("byccode", vo.getByccode());
	    	jsonObject.put("uploadgubun", vo.getUploadgubun());

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
	    }
	    


	    //매입처 메모 정보 가져옴.[s]
	    IsmCmm010VO ismCmm010VO = new IsmCmm010VO();
	    ismCmm010VO.setBuss_key(""+byc010SearchVO.getByc010id());
	    ismCmm010VO.setBuss_type("BY");
	    String strMemo = (String) cmm010Service.selectListMemo(ismCmm010VO);
    	jsonObject.put("cumMemo", URLEncoder.encode(strMemo, "UTF-8"));
    	//매입처 메모 정보 가져옴.[e]
    	
    	//매입처 담당자 목록 정보 가져옴.[s]
		List<Ismbyc020VO> listIsmbyc020VO = (List<Ismbyc020VO>) byc010DAO.selectByc020List(byc010SearchVO); //byc010id로 조회

		JSONArray jsonArray020 = new JSONArray();

	    for(Ismbyc020VO vo : listIsmbyc020VO){
	    	JSONObject jsonObject020 = new JSONObject();
	    	jsonObject020.put("byc010id", vo.getByc010id());
	    	jsonObject020.put("byc020id", vo.getByc020id());

	    	if (vo.getBycusername() == null) {
	    		jsonObject020.put("username", "");
	    	}else{
	    		jsonObject020.put("username", URLEncoder.encode(vo.getBycusername(), "UTF-8"));
	    	}

	    	if (vo.getBycusertel() == null) {
	    		jsonObject020.put("usertel", "");
	    	}else{
	    		jsonObject020.put("usertel", URLEncoder.encode(vo.getBycusertel(), "UTF-8"));
	    	}

	    	if (vo.getBycuseremail() == null) {
	    		jsonObject020.put("useremail", "");
	    	}else{
	    		jsonObject020.put("useremail", URLEncoder.encode(vo.getBycuseremail(), "UTF-8"));
	    	}

	    	if (vo.getBycmemo() == null) {
	    		jsonObject020.put("memo", "");
	    	}else{
	    		jsonObject020.put("memo", URLEncoder.encode(vo.getBycmemo(), "UTF-8"));
	    	}

	    	jsonArray020.add(jsonObject020);
	    }
	    jsonObject.put("userlist", jsonArray020);
	    //발주처 담당자 목록 정보 가져옴.[e]
    	
    	//발주처 쇼핑몰 목록 정보 가져옴.[s]
		List<Ismbyc030VO> listIsmbyc030VO = (List<Ismbyc030VO>) byc010DAO.selectByc030List(byc010SearchVO); //byc010id로 조회
    	JSONArray jsonArray030 = new JSONArray();
	    for(Ismbyc030VO vo : listIsmbyc030VO){
	    	JSONObject jsonObject030 = new JSONObject();
	    	jsonObject030.put("byc010id", vo.getByc010id());
	    	jsonObject030.put("byc030id", vo.getByc030id());

	    	if (vo.getBycurl() == null) {
	    		jsonObject030.put("bycurl", "");
	    	}else{
	    		jsonObject030.put("bycurl", URLEncoder.encode(vo.getBycurl(), "UTF-8"));
	    	}
	    	
	    	if (vo.getBycuid() == null) {
	    		jsonObject030.put("bycuid", "");
	    	}else{
	    		jsonObject030.put("bycuid", URLEncoder.encode(vo.getBycuid(), "UTF-8"));
	    	}

			jsonObject030.put("bycpwd", vo.getBycpwd());
			
			jsonArray030.add(jsonObject030);
	    }
	    jsonObject.put("byc030", jsonArray030);
	    //발주처 쇼핑몰 목록 정보 가져옴.[e]

	    return jsonObject.toString();
	}

	@Override
	public int saveBycAll(MultipartFile mf, String path, BycAllVO bycAllVO) throws Exception {
		
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
		
		Ismbyc010VO ismbyc010VO = new Ismbyc010VO();
		ismbyc010VO.setByc010id(bycAllVO.getByc010id());
		ismbyc010VO.setBycname(bycAllVO.getBycname());
		ismbyc010VO.setByctype(bycAllVO.getByctype());
		ismbyc010VO.setCogubun(bycAllVO.getCogubun());
		ismbyc010VO.setCono(bycAllVO.getCono());
		ismbyc010VO.setLawcono(bycAllVO.getLawcono());
		ismbyc010VO.setCoaddr(bycAllVO.getCoaddr());
		ismbyc010VO.setCobustype(bycAllVO.getCobustype());
		ismbyc010VO.setAccount(bycAllVO.getAccount());
		ismbyc010VO.setAccountno(bycAllVO.getAccountno());
		ismbyc010VO.setUseyn(bycAllVO.getUseyn());
		ismbyc010VO.setUploadgubun(bycAllVO.getUploadgubun());
		ismbyc010VO.setByccode(bycAllVO.getByccode());
		ismbyc010VO.setCmm020id(cmm020id);

		int byc010id = 0;
		if (bycAllVO.getByc010id() <= 0) {
			byc010id = byc010DAO.insertByc010(ismbyc010VO);
		}else{
			byc010id = bycAllVO.getByc010id();
			byc010DAO.updateByc010(ismbyc010VO);
			byc010DAO.deleteByc020(ismbyc010VO);
			byc010DAO.deleteByc030(ismbyc010VO);
		}

		String[] bycusername = bycAllVO.getBycusername();
		String[] bycusertel = bycAllVO.getBycusertel();
		String[] bycuseremail = bycAllVO.getBycuseremail();
		String[] bycmemo = bycAllVO.getBycmemo();
		for(int i=0;i < bycusername.length; i++) {
			Ismbyc020VO ismbyc020VO = new Ismbyc020VO();
			ismbyc020VO.setByc010id(byc010id);
			ismbyc020VO.setBycusername(bycusername[i]);
			if (bycusertel.length > 0) ismbyc020VO.setBycusertel(bycusertel[i]);
			if (bycuseremail.length > 0) ismbyc020VO.setBycuseremail(bycuseremail[i]);
			if (bycmemo.length > 0) ismbyc020VO.setBycmemo(bycmemo[i]);
			
			byc010DAO.insertByc020(ismbyc020VO);
		}
		
		String[] bycurl = bycAllVO.getBycurl();
		String[] bycuid = bycAllVO.getBycuid();
		String[] bycpwd = bycAllVO.getBycpwd();

		for(int i=0;i < bycurl.length; i++) {
			Ismbyc030VO ismbyc030VO = new Ismbyc030VO();
			ismbyc030VO.setByc010id(byc010id);
			if (bycurl.length > 0) ismbyc030VO.setBycurl(bycurl[i]);
			if (bycuid.length > 0) ismbyc030VO.setBycuid(bycuid[i]);
			if (bycpwd.length > 0) ismbyc030VO.setBycpwd(bycpwd[i]);

			byc010DAO.insertByc030(ismbyc030VO);
		}
		
		return byc010id;
	}
}
