package nfm.com.cum.service.impl;

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
import nfm.com.cum.service.Cum010SearchVO;
import nfm.com.cum.service.Cum010Service;
import nfm.com.cum.service.CumAllVO;
import nfm.com.cum.service.Ismcum010VO;
import nfm.com.cum.service.Ismcum020VO;
import nfm.com.cum.service.Ismcum030VO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("cum010Service")
public class Cum010ServiceImpl extends EgovAbstractServiceImpl implements Cum010Service {

	/** cum010DAO */
	@Resource(name="cum010DAO")
	private Cum010DAO cum010DAO;
	
	/** cmm020DAO */
	@Resource(name="cmm020DAO")
	private Cmm020DAO cmm020DAO;

	/** cmm010Service */
	@Resource(name = "cmm010Service")
	private Cmm010Service cmm010Service;
	
	@Override
	public int selectListTotCnt(Cum010SearchVO cum010SearchVO) throws Exception {
		return cum010DAO.selectListTotCnt(cum010SearchVO);
	}

	@Override
	public List<?> selectList(Cum010SearchVO cum010SearchVO) throws Exception {
		List<?> result = cum010DAO.selectList(cum010SearchVO);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object selectDetail(Cum010SearchVO cum010SearchVO) throws Exception {
		
		List<Ismcum010VO> listIsmcum010VO = (List<Ismcum010VO>) cum010DAO.selectList(cum010SearchVO); //cum010id로 조회
		
    	JSONObject jsonObject = new JSONObject();

		if (listIsmcum010VO.size() == 0) { //데이터 없는 경우 초기화해서 웹화면 신규등록에 사용
	    	jsonObject.put("cum010id", "");
	    	jsonObject.put("cono", "");
	    	jsonObject.put("lawcono", "");
	    	jsonObject.put("coname", "");
	    	jsonObject.put("cotype1", "");
	    	jsonObject.put("cotype2", "");
	    	jsonObject.put("cotype3", "");
	    	jsonObject.put("cotype1nm", "");
	    	jsonObject.put("cotype2nm", "");
	    	jsonObject.put("cotype3nm", "");
	    	jsonObject.put("cogubun", "");
	    	jsonObject.put("coaddr", "");
	    	jsonObject.put("cobustype", "");
	    	jsonObject.put("account", "");
	    	jsonObject.put("account2", "");
	    	jsonObject.put("accountamt", "");
	    	jsonObject.put("useyn", "");
	    	jsonObject.put("attachname", "");
	    	jsonObject.put("shopmallname", "");
	    	jsonObject.put("uploadtype", "");
	    	jsonObject.put("shopuseyn", "");
	    	jsonObject.put("orgfilename", "");
	    	jsonObject.put("savefilename", "");
	    	jsonObject.put("cmm020id", "");
		}
		
	    for(Ismcum010VO vo : listIsmcum010VO){
	    	jsonObject.put("cum010id", vo.getCum010id());
	    	jsonObject.put("cono", vo.getCono());
	    	jsonObject.put("lawcono", vo.getLawcono());
	    	if (vo.getConame() == null) {
	    		jsonObject.put("coname", URLEncoder.encode("", "UTF-8"));
	    	}else{
	    		jsonObject.put("coname", URLEncoder.encode(vo.getConame(), "UTF-8"));
	    	}
	    	jsonObject.put("cotype1", vo.getCotype1());
	    	jsonObject.put("cotype2", vo.getCotype2());
	    	jsonObject.put("cotype3", vo.getCotype3());
	    	
	    	if (vo.getCotype1nm() == null) {
	    		jsonObject.put("cotype1nm", "");
	    	}else{
	    		jsonObject.put("cotype1nm", URLEncoder.encode(vo.getCotype1nm(), "UTF-8"));
	    	}
	    	
	    	if (vo.getCotype2nm() == null) {
	    		jsonObject.put("cotype2nm", "");
	    	}else{
	    		jsonObject.put("cotype2nm", URLEncoder.encode(vo.getCotype2nm(), "UTF-8"));
	    	}
	    	
	    	if (vo.getCotype3nm() == null) {
	    		jsonObject.put("cotype3nm", "");
	    	}else{
	    		jsonObject.put("cotype3nm", URLEncoder.encode(vo.getCotype3nm(), "UTF-8"));
	    	}
	    	jsonObject.put("cogubun", vo.getCogubun());

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
	    	
	    	jsonObject.put("account", vo.getAccount());
	    	jsonObject.put("account2", vo.getAccount2());

	    	if (vo.getAccountamt() == null) {
	    		jsonObject.put("accountamt", "");
	    	}else{
	    		jsonObject.put("accountamt", URLEncoder.encode(vo.getAccountamt(), "UTF-8"));
	    	}

	    	jsonObject.put("useyn", vo.getUseyn());

	    	if (vo.getAttachname() == null) {
	    		jsonObject.put("attachname", "");
	    	}else{
	    		jsonObject.put("attachname", URLEncoder.encode(vo.getAttachname(), "UTF-8"));
	    	}

	    	if (vo.getShopmallname() == null) {
	    		jsonObject.put("shopmallname", "");
	    	}else{
	    		jsonObject.put("shopmallname", URLEncoder.encode(vo.getShopmallname(), "UTF-8"));
	    	}

	    	if (vo.getUploadtype() == null) {
	    		jsonObject.put("uploadtype", "");
	    	}else{
	    		jsonObject.put("uploadtype", URLEncoder.encode(vo.getUploadtype(), "UTF-8"));
	    	}

	    	jsonObject.put("shopuseyn", vo.getShopuseyn());

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

	    //발주처 메모 정보 가져옴.[s]
	    IsmCmm010VO ismCmm010VO = new IsmCmm010VO();
	    ismCmm010VO.setBuss_key(""+cum010SearchVO.getCum010id());
	    ismCmm010VO.setBuss_type("CM");
	    String strMemo = (String) cmm010Service.selectListMemo(ismCmm010VO);
    	jsonObject.put("cumMemo", URLEncoder.encode(strMemo, "UTF-8"));
    	//발주처 메모 정보 가져옴.[e]
    	
    	//발주처 담당자 목록 정보 가져옴.[s]
		List<Ismcum020VO> listIsmcum020VO = (List<Ismcum020VO>) cum010DAO.selectCum020List(cum010SearchVO); //cum010id로 조회

		JSONArray jsonArray020 = new JSONArray();

	    for(Ismcum020VO vo : listIsmcum020VO){
	    	JSONObject jsonObject020 = new JSONObject();
	    	jsonObject020.put("cum010id", vo.getCum010id());
	    	jsonObject020.put("cum020id", vo.getCum020id());

	    	if (vo.getCumusername() == null) {
	    		jsonObject020.put("cumusername", "");
	    	}else{
	    		jsonObject020.put("cumusername", URLEncoder.encode(vo.getCumusername(), "UTF-8"));
	    	}

	    	if (vo.getCumusertel() == null) {
	    		jsonObject020.put("cumusertel", "");
	    	}else{
	    		jsonObject020.put("cumusertel", URLEncoder.encode(vo.getCumusertel(), "UTF-8"));
	    	}

	    	if (vo.getCumuseremail() == null) {
	    		jsonObject020.put("cumuseremail", "");
	    	}else{
	    		jsonObject020.put("cumuseremail", URLEncoder.encode(vo.getCumuseremail(), "UTF-8"));
	    	}

	    	if (vo.getCummemo() == null) {
	    		jsonObject020.put("cummemo", "");
	    	}else{
	    		jsonObject020.put("cummemo", URLEncoder.encode(vo.getCummemo(), "UTF-8"));
	    	}

	    	jsonArray020.add(jsonObject020);
	    }
	    jsonObject.put("cum020", jsonArray020);
	    //발주처 담당자 목록 정보 가져옴.[e]
    	
    	//발주처 쇼핑몰 목록 정보 가져옴.[s]
		List<Ismcum030VO> listIsmcum030VO = (List<Ismcum030VO>) cum010DAO.selectCum030List(cum010SearchVO); //cum010id로 조회
    	JSONArray jsonArray030 = new JSONArray();
	    for(Ismcum030VO vo : listIsmcum030VO){
	    	JSONObject jsonObject030 = new JSONObject();
	    	jsonObject030.put("cum010id", vo.getCum010id());
	    	jsonObject030.put("cum030id", vo.getCum030id());

	    	if (vo.getShopmallname() == null) {
	    		jsonObject030.put("shopmallname", "");
	    	}else{
	    		jsonObject030.put("shopmallname", URLEncoder.encode(vo.getShopmallname(), "UTF-8"));
	    	}
	    	
	    	if (vo.getShopurl() == null) {
	    		jsonObject030.put("shopurl", "");
	    	}else{
	    		jsonObject030.put("shopurl", URLEncoder.encode(vo.getShopurl(), "UTF-8"));
	    	}
	    	
	    	if (vo.getShopuid() == null) {
	    		jsonObject030.put("shopuid", "");
	    	}else{
	    		jsonObject030.put("shopuid", URLEncoder.encode(vo.getShopuid(), "UTF-8"));
	    	}

			jsonObject030.put("shoppwd", vo.getShoppwd());

	    	if (vo.getUploadtype() == null) {
	    		jsonObject030.put("uploadtype", "");
	    	}else{
	    		jsonObject030.put("uploadtype", URLEncoder.encode(vo.getUploadtype(), "UTF-8"));
	    	}

	    	jsonObject030.put("uploadgubun", vo.getUploadgubun());
			jsonObject030.put("useyn", vo.getUseyn());
			jsonArray030.add(jsonObject030);
	    }
	    jsonObject.put("cum030", jsonArray030);
	    //발주처 쇼핑몰 목록 정보 가져옴.[e]
	    
    	return jsonObject.toString();
	}

	@Override
	public int insertCumAllData(MultipartFile mf, String path, CumAllVO cumAllVO) throws Exception {
		
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
		
		Ismcum010VO ismcum010VO = new Ismcum010VO();
		ismcum010VO.setCum010id(cumAllVO.getCum010id()+"");
		ismcum010VO.setCono(cumAllVO.getCono());
		ismcum010VO.setLawcono(cumAllVO.getLawcono());
		ismcum010VO.setConame(cumAllVO.getConame());
		ismcum010VO.setCotype1(cumAllVO.getCotype1());
		ismcum010VO.setCotype2(cumAllVO.getCotype2());
		ismcum010VO.setCotype3(cumAllVO.getCotype3());
		ismcum010VO.setCogubun(cumAllVO.getCogubun());
		ismcum010VO.setCoaddr(cumAllVO.getCoaddr());
		ismcum010VO.setCobustype(cumAllVO.getCobustype());
		ismcum010VO.setAccount(cumAllVO.getAccount());
		ismcum010VO.setAccount2(cumAllVO.getAccount2());
		ismcum010VO.setAccountamt(cumAllVO.getAccountamt());
		ismcum010VO.setUseyn(cumAllVO.getUseyn());
		ismcum010VO.setCmm020id(cmm020id+"");
		
		int cum010id = 0;
		if (cumAllVO.getCum010id() <= 0) {
			cum010id = Integer.parseInt(cum010DAO.insertCum010(ismcum010VO));
		}else{
			cum010id = cumAllVO.getCum010id();
			cum010DAO.updateCum010(ismcum010VO);
			cum010DAO.deleteCum020(ismcum010VO);
			cum010DAO.deleteCum030(ismcum010VO);
		}
		
		String[] cumusername = cumAllVO.getCumusername();
		String[] cumusertel = cumAllVO.getCumusertel();
		String[] cumuseremail = cumAllVO.getCumuseremail();
		String[] cummemo = cumAllVO.getCummemo();
		for(int i=0;i < cumusername.length; i++) {
			Ismcum020VO ismcum020VO = new Ismcum020VO();
			ismcum020VO.setCum010id(cum010id);
			ismcum020VO.setCumusername(cumusername[i]);
			if (cumusertel.length > 0) ismcum020VO.setCumusertel(cumusertel[i]);
			if (cumuseremail.length > 0) ismcum020VO.setCumuseremail(cumuseremail[i]);
			if (cummemo.length > 0) ismcum020VO.setCummemo(cummemo[i]);
			
			cum010DAO.insertCum020(ismcum020VO);
		}
		
		String[] shopmallname = cumAllVO.getShopmallname();
		String[] shopurl = cumAllVO.getShopurl();
		String[] shopuid = cumAllVO.getShopuid();
		String[] shoppwd = cumAllVO.getShoppwd();
		String[] uploadtype = cumAllVO.getUploadtype();
		String[] uploadgubun = cumAllVO.getUploadgubun();
		String[] useyn = cumAllVO.getShopUseYn();

		for(int i=0;i < shopmallname.length; i++) {
			Ismcum030VO ismcum030VO = new Ismcum030VO();
			ismcum030VO.setCum010id(cum010id);
			ismcum030VO.setShopmallname(shopmallname[i]);
			if (shopurl.length > 0) ismcum030VO.setShopurl(shopurl[i]);
			if (shopuid.length > 0) ismcum030VO.setShopuid(shopuid[i]);
			if (shoppwd.length > 0) ismcum030VO.setShoppwd(shoppwd[i]);
			if (uploadtype.length > 0) ismcum030VO.setUploadtype(uploadtype[i]);
			if (uploadgubun.length > 0) ismcum030VO.setUploadgubun(uploadgubun[i]);
			if (useyn.length > 0) ismcum030VO.setUseyn(useyn[i]);

			cum010DAO.insertCum030(ismcum030VO);
		}
		
		return cum010id;
	}
}
