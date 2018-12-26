package nfm.com.po.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import nfm.com.byc.service.Byc010SearchVO;
import nfm.com.byc.service.Byc010Service;
import nfm.com.cmm.util.CmmUtil;
import nfm.com.cmm.util.MailHandler;
import nfm.com.exl.util.ExcelManager;
import nfm.com.exl.util.ExcelRead;
import nfm.com.exl.util.ExcelReadOption;
import nfm.com.ord.service.Ismodm010VO;
import nfm.com.ord.service.Ord020SearchVO;
import nfm.com.ord.service.Ord020Service;
import nfm.com.ord.service.impl.Ord020DAO;
import nfm.com.po.service.Ismpol010VO;
import nfm.com.po.service.Ismpom010VO;
import nfm.com.po.service.Ismpoo010VO;
import nfm.com.po.service.Ismpoo020VO;
import nfm.com.po.service.Po010SaveVO;
import nfm.com.po.service.Po010Service;
import nfm.com.whs.service.Whs010SearchVO;
import nfm.com.whs.service.Whs010Service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;

@Service("po010Service")
public class Po010ServiceImpl extends EgovAbstractServiceImpl implements Po010Service {

	@Resource(name="ismPoNoIdGnrService")    
	private EgovIdGnrService ismPoNoIdGnrService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name="txManager")
    PlatformTransactionManager txManager;
	
	/** jgc add id:mailv1 메일 send */
	@Autowired
    private JavaMailSender mailSender;

	/** ord020Service */
	@Resource(name = "ord020Service")
	private Ord020Service ord020Service;

	/** byc010Service */
	@Resource(name = "byc010Service")
	private Byc010Service byc010Service;

	/** whs010Service */
	@Resource(name = "whs010Service")
	private Whs010Service whs010Service;
	
	/** po010DAO */
	@Resource(name="po010DAO")
	private Po010DAO po010DAO;
	
	/** ord020DAO */
	@Resource(name="ord020DAO")
	private Ord020DAO ord020DAO;

	@Override
	public Object selectWhsList() throws Exception {
		return po010DAO.selectWhsList();
	}

	@Override
	public Object selectBycList() throws Exception {
		return po010DAO.selectBycList();
	}

	@Override
	public String selectUserListJson(int keyid, String pocotype) throws Exception {

		String retJson = "";
		if ("B".equals(pocotype)) {
			Byc010SearchVO byc010SearchVO = new Byc010SearchVO();
			byc010SearchVO.setByc010id(keyid);
			
			retJson = byc010Service.selectDetail(byc010SearchVO);
		}else if ("W".equals(pocotype)) {
			Whs010SearchVO whs010SearchVO = new Whs010SearchVO();
			whs010SearchVO.setWhs010id(keyid);
			
			retJson = whs010Service.selectDetail(whs010SearchVO);
		}
		
		return retJson;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String selectPoo010ListJson(int poo010id, String pocotype) throws Exception {
		
		Ismpoo010VO poo010SearchVO = new Ismpoo010VO();
		poo010SearchVO.setPoo010id(poo010id);
		poo010SearchVO.setPocotype(pocotype);

		List<Ismpoo010VO> listIsmpoo010VO = (List<Ismpoo010VO>) po010DAO.selectPoo010List(poo010SearchVO);

		JSONArray jsonArray = new JSONArray();

	    for(Ismpoo010VO vo : listIsmpoo010VO){
	    	JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("isassign", vo.getIsassign());
	    	jsonObject.put("orderfield", vo.getOrderfield());
	    	jsonObject.put("orderfieldnm", URLEncoder.encode(vo.getOrderfieldnm(), "UTF-8"));

	    	jsonArray.add(jsonObject);
	    }
	    
	    return jsonArray.toString();
	}

	@Override
	public String savePoo010(int poo010id, String pocotype, String savedata) throws Exception {
		
		Ismpoo010VO ismpoo010VO = new Ismpoo010VO();
		ismpoo010VO.setPoo010id(poo010id);
		ismpoo010VO.setPocotype(pocotype);
		po010DAO.deletePoo010(ismpoo010VO);

		String[] savedataArray = savedata.split("\\^");

		int setOrder = 1;
		for (String item : savedataArray ){
			int addItemTag = item.indexOf("@");
			if(addItemTag > 0) {
				String[] addItem = item.split("@");
				ismpoo010VO.setOrderfield(addItem[0]);
				ismpoo010VO.setFieldname(addItem[1]);
				if(addItem.length > 2) {
					ismpoo010VO.setFieldvalue(addItem[2]);
				} else {
					ismpoo010VO.setFieldvalue("");
				}
			} else {
				ismpoo010VO.setOrderfield(item);
				ismpoo010VO.setFieldvalue(null);
			}
			ismpoo010VO.setIsassign("Y");
			ismpoo010VO.setFieldorder(setOrder++);
	    	
			po010DAO.insertPoo010(ismpoo010VO);
	    }
		
		return "SUCCESS";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String selectOrm010ListJson(int poo010id, String pocotype) throws Exception {

		Ord020SearchVO ord020SearchVO = new Ord020SearchVO();
		ord020SearchVO.setSearch_status("1"); //주문조건 중 출고전
		if ("W".equals(pocotype)) {
			ord020SearchVO.setPoSearch_pristock(poo010id);
		}else {
			ord020SearchVO.setPoSearch_byc010id(poo010id);
		}
		ord020SearchVO.setRecordCountPerPage(1000000);
		List<Ismodm010VO> listIsmodm010VO = (List<Ismodm010VO>) ord020DAO.selectList(ord020SearchVO);

		JSONArray jsonArray = new JSONArray();

	    for(Ismodm010VO vo : listIsmodm010VO){
	    	JSONObject jsonObject = new JSONObject();

	    	jsonObject.put("odm010id",          vo.getOdm010id());
	    	jsonObject.put("orderno",           vo.getOrderno());
	    	jsonObject.put("orderdate",         vo.getOrderdate());
	    	jsonObject.put("cum010id",          vo.getCum010id());
	    	jsonObject.put("cum030id",          vo.getCum030id());
	    	jsonObject.put("byc010id",          vo.getByc010id());
	    	jsonObject.put("orderuser",         vo.getOrderuser());
	    	jsonObject.put("orderusercontact",  vo.getOrderusercontact());
	    	jsonObject.put("rcvuser",           vo.getRcvuser());
	    	jsonObject.put("rcvusercontact",    vo.getRcvusercontact());  
	    	jsonObject.put("rcvusercontacthp",  vo.getRcvusercontacthp());  
	    	jsonObject.put("rcvuseremail",      vo.getRcvuseremail());  
	    	jsonObject.put("orderitemid",       vo.getOrderitemid());     
	    	jsonObject.put("orderitemopt",      vo.getOrderitemopt());
	    	jsonObject.put("orderitemqty",      vo.getOrderitemqty());    
	    	jsonObject.put("orderitembyprice",  vo.getOrderitembyprice());
	    	jsonObject.put("postno",            vo.getPostno());          
	    	jsonObject.put("address",           vo.getAddress());
	    	jsonObject.put("dlvmemo",           vo.getDlvmemo());
	    	jsonObject.put("dlvbyprice",        vo.getDlvbyprice());      
	    	jsonObject.put("dlvno",             vo.getDlvno());           
	    	jsonObject.put("dlvco",             vo.getDlvco());
	    	jsonObject.put("dlvstatus",         vo.getDlvstatus());
	    	jsonObject.put("processdate",       vo.getProcessdate());     
	    	jsonObject.put("accountcum",        vo.getAccountcum());      
	    	jsonObject.put("accountbuy",        vo.getAccountbuy());      
	    	jsonObject.put("status",            vo.getStatus());          
	    	jsonObject.put("cstype",            vo.getCstype());   
	    	jsonObject.put("claimstatus",       vo.getClaimstatus());     
	    	jsonObject.put("claimreason",       vo.getClaimreason());     
	    	jsonObject.put("retstatus",         vo.getRetstatus());       
	    	jsonObject.put("retqty",            vo.getRetqty());          
	    	jsonObject.put("retprice",          vo.getRetprice());        
	    	jsonObject.put("orderitemname",     vo.getOrderitemname());
	    	jsonObject.put("orderitemprice",    vo.getOrderitemprice());  
	    	jsonObject.put("dlvprice",          vo.getDlvprice());        
	    	jsonObject.put("coname",            vo.getConame());
	    	jsonObject.put("code_nm",           vo.getCode_nm());
	    	jsonObject.put("itemname",          vo.getItemname());
	    	jsonObject.put("itemprice",         vo.getItemprice());       
	    	jsonObject.put("itembuyprice",      vo.getItembuyprice());    
	    	jsonObject.put("itemdlvprice",      vo.getItemdlvprice());    
	    	jsonObject.put("itembuydlvprice",   vo.getItembuydlvprice()); 
	    	jsonObject.put("bycname",           vo.getBycname());
	    	jsonObject.put("ststusNm",          vo.getStstusNm());
	    	jsonObject.put("shopmallname",      vo.getShopmallname());
	    	jsonObject.put("cotype2nm",         vo.getCotype2nm());
	    	jsonObject.put("cotype3nm",         vo.getCotype3nm());
	    	jsonObject.put("claimstatusnm",     vo.getClaimstatusnm());
	    	jsonObject.put("claimreasonnm",     vo.getClaimreasonnm());
	    	jsonObject.put("retstatusnm",       vo.getRetstatusnm());

	    	jsonArray.add(jsonObject);
	    }
	    
	    return jsonArray.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void savePoo020(int byc010id, String apiurl, String apicontext, String apiversion) throws Exception {

		List<Ismpoo020VO> listIsmpoo020VO = (List<Ismpoo020VO>) po010DAO.selectPoo020(byc010id);

		Ismpoo020VO ismpoo020VO = new Ismpoo020VO();
		ismpoo020VO.setByc010id(byc010id);
		ismpoo020VO.setApiurl(apiurl);
		ismpoo020VO.setApicontext(apicontext);
		ismpoo020VO.setApiversion(apiversion);

		if (listIsmpoo020VO.size() > 0) {
			po010DAO.updatePoo020(ismpoo020VO);
		}else{
			po010DAO.insertPoo020(ismpoo020VO);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String selectPoo020Json(int byc010id) throws Exception {

		List<Ismpoo020VO> listIsmpoo020VO = (List<Ismpoo020VO>) po010DAO.selectPoo020(byc010id);
    	JSONObject jsonObject = new JSONObject();
    	//String retVal = "";
	    for(Ismpoo020VO vo : listIsmpoo020VO){
	    	jsonObject.put("url", vo.getApiurl());
	    	jsonObject.put("context", vo.getApicontext());
	    	jsonObject.put("version", URLEncoder.encode(vo.getApiversion() , "UTF-8"));
	    }
		return jsonObject.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String savePoList(String[] odm010idArr, Po010SaveVO po010SaveVO, LoginVO loginVO) throws Exception {
		
		int poo010id = po010SaveVO.getPoo010id();
		String pocotype = po010SaveVO.getPocotype();
		String userList = po010SaveVO.getUserList();
		String ccUserList = po010SaveVO.getCcUserList();
		String mailSubject = po010SaveVO.getMailSubject();
		String mailText = po010SaveVO.getMailText();
		String receiveType = po010SaveVO.getReceivetype();
		if (receiveType == null) {
			receiveType = "E";
		}
		
		String retVal = "SUCCESS";
		//트랜잭션 처리
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		
		try {
			//발주 정보 저장[s]
			String poNum = CmmUtil.getNumbering("PO-", ismPoNoIdGnrService.getNextStringId(), false);
			
			HashMap hm = new HashMap();
			hm.put("poNum", poNum);
			hm.put("odm010idArr", odm010idArr);
			hm.put("ordermemo", po010SaveVO.getOrdermemo()); //발주시 사용자가 입력한 발주메모로 현재 발주에 모든 주문에 적용
			hm.put("addorderuser", po010SaveVO.getAddorderuser()); //발주시 사용자가 선택으로 수령자에 주문자명 포함여부 Y인 경우 포함
			hm.put("byc010id", Integer.valueOf(poo010id)); //매입처ID
			//hm.put("bycusername", userList); //매입처ID
			hm.put("regid", loginVO.getName()); //매입처ID
			
		    po010DAO.insertPom010Arr(hm);
		    //발주 정보 저장[e]
		    
			// 발주된 주문 정보 출고대기로 상태 변경[s]
		    String odm010idArrStr = "";
		    for(String vo : odm010idArr){
		    	odm010idArrStr += (vo + ",");
		    }
	
		    ord020Service.ord020SelectChgOrderStatus("2",odm010idArrStr);
			// 발주된 주문 정보 출고대기로 상태 변경[e]
	
		    // 발주 정보 엑셀 저장[s]
			Ismpoo010VO poo010SearchVO = new Ismpoo010VO();
			poo010SearchVO.setPoo010id(poo010id);
			poo010SearchVO.setPocotype(pocotype);
	
			List<Ismpoo010VO> listIsmpoo010VO = (List<Ismpoo010VO>) po010DAO.selectPoo010List(poo010SearchVO);
	
			List<Object> header = new ArrayList<Object>();
		    List<List<Object>> data = new ArrayList<List<Object>>();
		    
		    String[] fVal = new String[50];
		    int fixValueIdx = 0;
	
			header.add("key Value");
			header.add("송장번호");
			
		    for(Ismpoo010VO vo : listIsmpoo010VO){
		    	if ("Y".equals(vo.getIsassign())) {
		    		header.add(vo.getOrderfieldnm());
			    	if(vo.getOrderfield().indexOf("additem") >= 0) {
			    		fVal[fixValueIdx++] = vo.getOrderfield();
			    	}
		    	}
		    }
		    
			header.add("주문메모");
		    
		    Ismpom010VO ismpom010VO = new Ismpom010VO();
		    ismpom010VO.setPom010id(poNum);
	
		    List<Ismpom010VO> listIsmpom010VO = (List<Ismpom010VO>) po010DAO.selectPom010List(ismpom010VO);
		    
		    for(Ismpom010VO vo : listIsmpom010VO){
		    	
		    	HashMap listKetValueHm = new HashMap();
		    	
		    	listKetValueHm.put("pom010id", vo.getPom010id());
		    	listKetValueHm.put("odm010id", vo.getOdm010id());
		    	listKetValueHm.put("orderno", vo.getOrderno());
		    	listKetValueHm.put("orderdate", vo.getOrderdate());
		    	listKetValueHm.put("cum010id", vo.getCum010id());
		    	listKetValueHm.put("cum030id", vo.getCum030id());
		    	listKetValueHm.put("byc010id", vo.getByc010id());
		    	listKetValueHm.put("orderuser", vo.getOrderuser());
		    	listKetValueHm.put("orderusercontact", vo.getOrderusercontact());
		    	listKetValueHm.put("rcvuser", vo.getRcvuser());
		    	listKetValueHm.put("rcvusercontact", vo.getRcvusercontact());
		    	listKetValueHm.put("rcvusercontacthp", vo.getRcvusercontacthp());
		    	listKetValueHm.put("rcvuseremail", vo.getRcvuseremail());
		    	listKetValueHm.put("orderitemid", vo.getOrderitemid());
		    	listKetValueHm.put("orderitemopt", vo.getOrderitemopt());
		    	listKetValueHm.put("orderitemqty", vo.getOrderitemqty());
		    	listKetValueHm.put("orderitemprice", vo.getOrderitemprice());
		    	listKetValueHm.put("postno", vo.getPostno());
		    	listKetValueHm.put("address", vo.getAddress());
		    	listKetValueHm.put("dlvmemo", vo.getDlvmemo());
		    	listKetValueHm.put("dlvprice", vo.getDlvprice());
		    	listKetValueHm.put("dlvno", vo.getDlvno());
		    	listKetValueHm.put("dlvco", vo.getDlvco());
		    	listKetValueHm.put("dlvstatus", vo.getDlvstatus());
		    	listKetValueHm.put("processdate", vo.getProcessdate());
		    	listKetValueHm.put("accountcum", vo.getAccountcum());
		    	listKetValueHm.put("accountbuy", vo.getAccountbuy());
		    	listKetValueHm.put("status", vo.getStatus());
		    	listKetValueHm.put("cstype", vo.getCstype());
		    	listKetValueHm.put("uploadfilename", vo.getUploadfilename());
		    	listKetValueHm.put("claimstatus", vo.getClaimstatus());
		    	listKetValueHm.put("claimreason", vo.getClaimreason());
		    	listKetValueHm.put("retstatus", vo.getRetstatus());
		    	listKetValueHm.put("retqty", vo.getRetqty());
		    	listKetValueHm.put("retprice", vo.getRetprice());
		    	listKetValueHm.put("orderitemname", vo.getOrderitemname());
		    	
	    		int fValCnt = fVal.length;
		    	if(fValCnt > 0) {
			    	for(int j=0;j<fixValueIdx;j++) {
			    		String[] fValTemp = fVal[j].split("@");
			    		if(fValTemp.length > 2) {
			    			listKetValueHm.put(""+fValTemp[0]+"", fValTemp[2]);
			    		} else if(fValTemp.length >= 1 && fValTemp.length <= 2 ) {
			    			listKetValueHm.put(""+fValTemp[0]+"", " ");
			    		}
			    	}
		    	}
	
		    	List<Object> obj = new ArrayList<Object>();
			    
		    	obj.add(vo.getOdm010id()); //key value
	    		obj.add("");               //송장번호
	    		
	    		for(Ismpoo010VO vosub : listIsmpoo010VO){
			    	if ("Y".equals(vosub.getIsassign())) {
			    		if(vosub.getOrderfield().indexOf("additem") >= 0) {
			    			String[] fValTemp = vosub.getOrderfield().split("@");
			    			obj.add(listKetValueHm.get(fValTemp[0]));
				    	} else {
				    		obj.add(listKetValueHm.get(vosub.getOrderfield()));
				    	}
			    	}
			    }
	    		
	    		obj.add(vo.getOrdermemo()); //주문메모
			    data.add(obj);
		    }
	
		    String[] excelCellType = {"S","S","S","S","S","S","S","S","S","S",
		    		                  "S","S","S","S","S","S","S","S","S","S",
		    		                  "S","S","S","S","S","S","S","S","S","S",
		    		                  "S","S","S"};
			
		    ExcelManager excelManager = new ExcelManager(header, data);
		    excelManager.setSheetName("발주List");
		    excelManager.setWidth(6000);
		    excelManager.setCellDataType(excelCellType);
		    excelManager.setStartRow(0);
		    excelManager.setStartCol(0);
		    excelManager.setExcelType("xlsx");
		    
		    byte[] bytes = excelManager.makeExcel();
	
		    String filefullname = "";
	    	String path = propertiesService.getString("Globals.fileStorePath");

	    	filefullname = path + poNum + ".xlsx";

	        File lOutFile = new File(filefullname);
		    
		    if(bytes != null){
		        FileOutputStream lFileOutputStream = new FileOutputStream(lOutFile);
		        lFileOutputStream.write(bytes);
		        lFileOutputStream.close();
		    }
		    // 발주 정보 엑셀 저장[e]
		    
		    //발주정보 테이블에 파일명 업데이트
		    hm.put("uploadfilename", poNum + ".xlsx");
		    hm.put("rcvuseremail", userList);
		    hm.put("receiveType", receiveType);
		    hm.put("byc010id", poo010id);
		    po010DAO.updatePom010(hm);
		    
		    if ("X".equals(receiveType)) {
		    	retVal = filefullname;
		    }else{
		    //메일전송[s]
			    String content = new StringBuffer().
			    		append(mailText).
			    		toString();
	
		    	MailHandler sendMail = new MailHandler(mailSender);
		        sendMail.setSubject(mailSubject);
		        sendMail.setText(content);
		        sendMail.setFrom(loginVO.getEmail(), loginVO.getName());
		        sendMail.setTo(userList);
		        if ( (ccUserList != null) && (!"".equals(ccUserList)) ) {
		        	sendMail.setToCC(ccUserList);
		        }
		        sendMail.addAttachFile(poNum + ".xlsx", filefullname);
		        sendMail.send();
			//메일전송[e]
		    }
	        //로그생성[s]
	        Ismpol010VO ismpol010VO = new Ismpol010VO();
	        ismpol010VO.setPoo010id(poo010id);
	        ismpol010VO.setPocotype(pocotype);
	        
	        po010DAO.insertPol010(ismpol010VO);
	        //로그생성[e]

	    	txManager.commit(status); //commit
	    } catch (Exception ex) {
	        txManager.rollback(status);
	        retVal = "FAIL";
	        ex.printStackTrace();
	    }

	    return retVal;
	}

	@Override
	public String batchupExcel(List<MultipartFile> fileList) throws Exception {
		
		for (MultipartFile mf : fileList) {
			
			File convFile = new File( mf.getOriginalFilename());
			mf.transferTo(convFile);
			//엑셀읽어서 송장정보 저장
			setOrderDataFile(convFile);
			convFile.delete();			
		}
		
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int setOrderDataFile(File convFile) {

        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(convFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B");
        excelReadOption.setStartRow(2);

	    // 송장 데이터 읽어 저장 
	    List<Map<String, String>>excelContent2 =ExcelRead.read(excelReadOption);
        Iterator excelItem2 = excelContent2.iterator();

        while (excelItem2.hasNext()) {
        	Ismodm010VO ismodm010VO = new Ismodm010VO();
        	Map<String, String> excelItemInfo = (Map<String, String>) excelItem2.next();

        	ismodm010VO.setOdm010id(Integer.parseInt(excelItemInfo.get("A")));
        	ismodm010VO.setDlvno(excelItemInfo.get("B"));
        	ismodm010VO.setStatus("3");
        	
        	ord020DAO.updateOrderDetailData(ismodm010VO);
        }
        return 0;
    }
}