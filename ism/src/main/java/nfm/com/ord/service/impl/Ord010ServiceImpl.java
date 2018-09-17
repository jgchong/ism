package nfm.com.ord.service.impl;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.exl.util.ExcelRead;
import nfm.com.exl.util.ExcelReadOption;
import nfm.com.ord.service.Ismcum030VO;
import nfm.com.ord.service.Ismodl010VO;
import nfm.com.ord.service.Ismodm010VO;
import nfm.com.ord.service.Ismodo010VO;
import nfm.com.ord.service.Ismodo020VO;
import nfm.com.ord.service.Ord010SearchVO;
import nfm.com.ord.service.Ord010Service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("ord010Service")
public class Ord010ServiceImpl extends EgovAbstractServiceImpl implements Ord010Service {

	/** ord010DAO */
	@Resource(name="ord010DAO")
	private Ord010DAO ord010DAO;
	
	@Override
	public int selectListTotCnt(Ord010SearchVO ord010SearchVO) throws Exception {
		return ord010DAO.selectListTotCnt(ord010SearchVO);
	}

	@Override
	public List<?> selectList(Ord010SearchVO ord010SearchVO) throws Exception {
		List<?> result = ord010DAO.selectList(ord010SearchVO);
		return result;
	}
	
	/**
	 * 주문정보 수집을 위한 엑셀파일 읽기
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public String readExcelFile(List<MultipartFile> fileList) throws Exception {

	    List<Ismodl010VO> listIsmodl010VO = new ArrayList<Ismodl010VO>();
		//저장 후 조회를 위한 임시 key 발급
	    String orderTempKey = Ord010ServiceUtil.getOrderTempKey();

	    //업로드 일자 setting 동일일자를 위해 loop 밖에서 setting
		GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = gc.getTime();
        String uploaddate = sf.format(d);
        
		for (MultipartFile mf : fileList) {

			//파일명으로 매출처/쇼핑몰 정보 get [s]
			int cum010id = 0;
			int cum030id = 0;
			Ord010SearchVO ord010SearchVO = new Ord010SearchVO();
			ord010SearchVO.setSearch_filename(mf.getOriginalFilename());
			ord010SearchVO.setRecordCountPerPage(1);
			
			List<?> result = ord010DAO.selectList(ord010SearchVO);
			
			//파일명 패턴으로 조회 안되는 skip
			if (result.size() <= 0) {
				continue;
			}else{
				//파일명 패턴이 있는 경우 cum030id 가져온다.
				Iterator iterator = result.iterator();
				Ismcum030VO ismcum030VO = (Ismcum030VO) iterator.next();
		    	cum010id = ismcum030VO.getCum010id();
		    	cum030id = ismcum030VO.getCum030id();
			}
			//파일명으로 매출처/쇼핑몰 정보 get [e]
			
			File convFile = new File( mf.getOriginalFilename());
			mf.transferTo(convFile);

			setOrderDataFile(convFile, orderTempKey, cum010id, cum030id);
			
		    convFile.delete();
		    Ismodl010VO ismodl010VO = new Ismodl010VO();
		    ismodl010VO.setCum010id(cum010id);
		    ismodl010VO.setCum030id(cum030id);
		    ismodl010VO.setUploaddate(uploaddate);
		    ismodl010VO.setUploadviewkey(orderTempKey);
		    listIsmodl010VO.add(ismodl010VO);
		}

    	ord010DAO.insertOrderLogData(listIsmodl010VO);
    	
		return orderTempKey;
	}
	
	@Override
	public String readOrderExcelFile(MultipartFile mf, int filecum010id, int filecum030id) throws Exception {

		List<Ismodl010VO> listIsmodl010VO = new ArrayList<Ismodl010VO>();
		//저장 후 조회를 위한 임시 key 발급
	    String orderTempKey = Ord010ServiceUtil.getOrderTempKey();

	    //업로드 일자 setting 동일일자를 위해 loop 밖에서 setting
		GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = gc.getTime();
        String uploaddate = sf.format(d);
	    
		File convFile = new File( mf.getOriginalFilename());
		mf.transferTo(convFile);

		setOrderDataFile(convFile, orderTempKey, filecum010id, filecum030id);
		
	    convFile.delete();
	    Ismodl010VO ismodl010VO = new Ismodl010VO();
	    ismodl010VO.setCum010id(filecum010id);
	    ismodl010VO.setCum030id(filecum030id);
	    ismodl010VO.setUploaddate(uploaddate);
	    ismodl010VO.setUploadviewkey(orderTempKey);
	    listIsmodl010VO.add(ismodl010VO);
	    
	    ord010DAO.insertOrderLogData(listIsmodl010VO);
	    
		return orderTempKey;
	}

	/**
	 * 수동수집환경 설정을 위한 엑셀파일 읽기
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String readExcelFile(MultipartFile mf) throws Exception {
		File convFile = new File( mf.getOriginalFilename());
		mf.transferTo(convFile);

        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(convFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","AA","AB","AC","AD","AE","AF","AG","AH","AI","AJ","AK","AL","AM","AN","AO","AP","AQ","AR","AS","AT","AU","AV","AW","AX","AY","AZ","BA","BB","BC");
        excelReadOption.setStartRow(1);

        int rowCnt = 1;
        rowCnt = Ord010ServiceUtil.getRealHeadRow(excelReadOption);
        
	    excelReadOption.setStartRow(rowCnt); //위에서 찾은 유효한 행으로 다시 setting 후 읽어서 타이틀 get
        List<Map<String, String>>excelContent1 =ExcelRead.read(excelReadOption);
        Iterator excelItem1 = excelContent1.iterator();
        
        String retStr = "";
        int blankFielsCnt = 0;
		Map<String, String> excelItemInfo = (Map<String, String>) excelItem1.next();
	    for(String item : excelReadOption.getOutputColumns()){
	    	String itemval = excelItemInfo.get(item);
	    	if ( (itemval != null)&&(!"".equals(itemval)) ) {
				retStr += (itemval + "^");
			}else{
				if ("".equals(itemval) ) blankFielsCnt++;
			}
	    }
        System.out.println("aaa = " + blankFielsCnt);
        //두줄 병합된 경우 다음행의 컬럼 읽기위해서
        if (blankFielsCnt > 0) {
		    excelReadOption.setStartRow(rowCnt+1); //위에서 찾은 유효한 행으로 다시 setting 후 읽어서 타이틀 get
	        List<Map<String, String>>excelContent2 =ExcelRead.read(excelReadOption);
	        Iterator<Map<String, String>> excelItem2 = excelContent2.iterator();
	        
			Map<String, String> excelItemInfo2 = (Map<String, String>) excelItem2.next();
		    for(String item : excelReadOption.getOutputColumns()){
		    	String itemval = excelItemInfo2.get(item);
				if ( (itemval != null)&&(!"".equals(itemval)) ) {
					retStr += (itemval + "^");
				}
		    }
        }
	    
	    convFile.delete();
	    
	    return retStr;
	}

	@Override
	public void saveManualDetailData(String cum030id, String userTitleNames, String sysmTitleNames, String assgTitleNames) throws Exception {
		ord010DAO.deleteManualDetailData(cum030id);
		String[] userTitleNameArray = userTitleNames.split("\\^");
		String[] sysmTitleNameArray = sysmTitleNames.split("\\^");
		String[] assgTitleNameArray = assgTitleNames.split("\\^");


		for (String item : userTitleNameArray ){
	    	Ismodo010VO ismodo010VO = new Ismodo010VO();
	    	ismodo010VO.setCum030id(Integer.parseInt(cum030id));
	    	ismodo010VO.setAdditem(item);
	    	ismodo010VO.setIsassign("N");
	    	
	    	ord010DAO.insertManualDetailData(ismodo010VO);
	    }
	    for(int i=0;i<sysmTitleNameArray.length;i++) {
	    	Ismodo010VO ismodo010VO = new Ismodo010VO();
	    	ismodo010VO.setCum030id(Integer.parseInt(cum030id));
	    	ismodo010VO.setAdditem(assgTitleNameArray[i]);
	    	if ("NONE".equals(assgTitleNameArray[i])) {
	    		ismodo010VO.setIsassign("N");
	    	}else{
	    		ismodo010VO.setIsassign("Y");
	    	}
	    	ismodo010VO.setOrderfield(sysmTitleNameArray[i]);
	    	
	    	ord010DAO.insertManualDetailData(ismodo010VO);
	    }
	}

	@SuppressWarnings("unchecked")
	@Override
	public String selectApiDataDetail(String cum030id) throws Exception {

		List<Ismodo020VO> listIsmodo020VO = (List<Ismodo020VO>) ord010DAO.selectApiDataDetail(cum030id);
    	JSONObject jsonObject = new JSONObject();

	    for(Ismodo020VO vo : listIsmodo020VO){
	    	jsonObject.put("url", vo.getApiurl());
	    	jsonObject.put("context", vo.getApicontext());

	    	jsonObject.put("version", URLEncoder.encode(vo.getApiversion() , "UTF-8"));
	    }
		return jsonObject.toString();
	}

	@Override
	public void saveApiDetailData(Ismodo020VO ismodo020vo) throws Exception {
		List<?> result = ord010DAO.selectApiDataDetail(ismodo020vo.getCum030id()+"");
		if (result.size() > 0) {
			ord010DAO.updateApiDetailData(ismodo020vo);
		}else{
			ord010DAO.insertApiDetailData(ismodo020vo);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String selectManualDataDetail(String cum030id)
			throws Exception {

		List<Ismodo010VO> listIsmodo010VO = (List<Ismodo010VO>) ord010DAO.selectManualDataDetail(cum030id);
		
		JSONArray jsonArray = new JSONArray();

	    for(Ismodo010VO vo : listIsmodo010VO){
	    	JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("additem", URLEncoder.encode(vo.getAdditem(), "UTF-8"));
	    	jsonObject.put("isassign", vo.getIsassign());
	    	jsonObject.put("orderfield", vo.getOrderfield());

	    	jsonArray.add(jsonObject);
	    }
		return jsonArray.toString();
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int setOrderDataFile(File convFile, String orderTempKey, int cum010id, int cum030id) {
	    
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(convFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","AA","AB","AC","AD","AE","AF","AG","AH","AI","AJ","AK","AL","AM","AN","AO","AP","AQ","AR","AS","AT","AU","AV","AW","AX","AY","AZ","BA","BB","BC");
        excelReadOption.setStartRow(1);

        int rowCnt = 1;
        rowCnt = Ord010ServiceUtil.getRealHeadRow(excelReadOption); //몇번째 엑셀행이 유효한지 행번호를 얻는다.
        
        //헤더 시작점 읽을때 중간에 빈행이 있는 경우 전체행에서 그 빈행만큼 빠지므로 이부분 가지고 있다가 데이터 읽을때 넣어줘야함
        //중간에 한번 더 헤어를 읽을때 reset 되므로 여기서 가지고 있다가 나중에 넣어줘야함
        int saveLstAddrowCnt = excelReadOption.getLstRow();
        
        // 업로드 엑셀 컬럼타이틀 읽기 [s]
        int blankFielsCnt = 0;
        excelReadOption.setStartRow(rowCnt); //위에서 찾은 유효한 행으로 다시 setting 후 읽어서 타이틀 get
        List<Map<String, String>>excelContent1 =ExcelRead.read(excelReadOption);
        Iterator excelItem1 = excelContent1.iterator();
        
        Map<String, String> fileHeader=new HashMap<String, String>();
		Map<String, String> excelItemInfoHeader = (Map<String, String>) excelItem1.next();
	    for(String item : excelReadOption.getOutputColumns()){
	    	String itemval = excelItemInfoHeader.get(item);
			if ( (itemval != null)&&(!"".equals(itemval)) ) {
				fileHeader.put(itemval, item); //'엑셀 컬럼 한글명','A' 식으로 저장
			}else{
				if ("".equals(itemval) ) blankFielsCnt++;
			}
	    }
	    //두줄 병합된 경우 다음행의 컬럼 읽기위해서
        System.out.println("blankFielsCnt = " + blankFielsCnt);
        if (blankFielsCnt > 0) {
		    excelReadOption.setStartRow(rowCnt+1); //위에서 찾은 유효한 행으로 다시 setting 후 읽어서 타이틀 get
	        List<Map<String, String>>excelContentAdd1 =ExcelRead.read(excelReadOption);
	        Iterator<Map<String, String>> excelItemAdd1 = excelContentAdd1.iterator();
	
			Map<String, String> excelItemInfoAdd1 = (Map<String, String>) excelItemAdd1.next();
		    for(String item : excelReadOption.getOutputColumns()){
		    	String itemval = excelItemInfoAdd1.get(item);
				if ( (itemval != null)&&(!"".equals(itemval)) ) {
					fileHeader.put(itemval, item); //'엑셀 컬럼 한글명','A' 식으로 저장
				}
		    }
        }
	    // 업로드 엑셀 컬럼타이틀 읽기 [e]
	    
	    // system field setting 타이틀 읽기 [s]
	    Map<String, String> dbHeader=new HashMap<String, String>();
	    List<?> resultOdo010 = ord010DAO.selectManualDataDetail(cum030id+"");
	    for (Object obj : resultOdo010) {
	    	Ismodo010VO ismodo010VO = (Ismodo010VO) obj;
	    	if ("Y".equals(ismodo010VO.getIsassign())) {
	    		dbHeader.put(ismodo010VO.getOrderfield(), ismodo010VO.getAdditem());
	    	}
	    }
	    // system field setting 타이틀 읽기 [e]
	    System.out.println("dbHeader = " + dbHeader.toString());
	    // 주문 데이터 읽어 저장 [s]
	    if (blankFielsCnt > 0) {
		    excelReadOption.setStartRow(rowCnt+2); //위에서 찾은 유효한 행으로 다시 setting 후 읽어서 타이틀 get 헤더는 제외
	    }else{
		    excelReadOption.setStartRow(rowCnt+1); //위에서 찾은 유효한 행으로 다시 setting 후 읽어서 타이틀 get 헤더는 제외
	    }
	    excelReadOption.setLstRow(saveLstAddrowCnt); //위에서 헤더 읽을때 중간에 빈행이 몇개인지 확인 후 다시 set 해줌. 
        List<Map<String, String>>excelContent2 =ExcelRead.read(excelReadOption);
        Iterator excelItem2 = excelContent2.iterator();

    	String postno = dbHeader.get("postno");
    	List<String> listPostNo = new ArrayList();
    	if (postno != null) {
    		String[] postnos = postno.split("#");

    		for (String posnoSplit : postnos ){
    			listPostNo.add(fileHeader.get(posnoSplit));
            }
    	}

    	String address = dbHeader.get("address");
    	List<String> listAddress = new ArrayList();
    	if (address != null) {
    		String[] addresss = address.split("#");

    		for (String addressSplit : addresss ){
    			listAddress.add(fileHeader.get(addressSplit));
            }
    	}
    	
    	String dlvmemo = dbHeader.get("dlvmemo");
    	if (dlvmemo != null) dlvmemo = fileHeader.get(dlvmemo);
    	
    	String orderdate = dbHeader.get("orderdate");
    	if (orderdate != null) orderdate = fileHeader.get(orderdate);
    	
    	String orderitembyprice = dbHeader.get("orderitembyprice");
    	if (orderitembyprice != null) orderitembyprice = fileHeader.get(orderitembyprice);
    	
    	String orderitemid = dbHeader.get("orderitemid");
    	if (orderitemid != null) orderitemid = fileHeader.get(orderitemid);
    	
    	String orderitemname = dbHeader.get("orderitemname");
    	if (orderitemname != null) orderitemname = fileHeader.get(orderitemname);
    	
    	String orderitemopt = dbHeader.get("orderitemopt");
    	if (orderitemopt != null) orderitemopt = fileHeader.get(orderitemopt);
    	
    	String orderitemqty = dbHeader.get("orderitemqty");
    	if (orderitemqty != null) orderitemqty = fileHeader.get(orderitemqty);
    	
    	String orderno = dbHeader.get("orderno");
    	if (orderno != null) orderno = fileHeader.get(orderno);
    	
    	String orderuser = dbHeader.get("orderuser");
    	if (orderuser != null) orderuser = fileHeader.get(orderuser);
    	
    	String orderusercontact = dbHeader.get("orderusercontact");
    	if (orderusercontact != null) orderusercontact = fileHeader.get(orderusercontact);
    	
    	String rcvuser = dbHeader.get("rcvuser");
    	if (rcvuser != null) rcvuser = fileHeader.get(rcvuser);
    	
    	String rcvusercontact = dbHeader.get("rcvusercontact");
    	if (rcvusercontact != null) rcvusercontact = fileHeader.get(rcvusercontact);
    	
    	String rcvusercontacthp = dbHeader.get("rcvusercontacthp");
    	if (rcvusercontacthp != null) rcvusercontacthp = fileHeader.get(rcvusercontacthp);
    	
    	String rcvuseremail = dbHeader.get("rcvuseremail");
    	if (rcvuseremail != null) rcvuseremail = fileHeader.get(rcvuseremail);
    	
    	String orderitemprice = dbHeader.get("orderitemprice");
    	if (orderitemprice != null) orderitemprice = fileHeader.get(orderitemprice);
    	
    	String dlvprice = dbHeader.get("dlvprice");
    	if (dlvprice != null) dlvprice = fileHeader.get(dlvprice);
    	//필드 추가시 여기 추가 1/2

        while (excelItem2.hasNext()) {
        	Ismodm010VO ismodm010VO = new Ismodm010VO();
        	Map<String, String> excelItemInfo = (Map<String, String>) excelItem2.next();

        	if (listPostNo.size() > 0) {
        		String setPostNo = "";
        		for(String str : listPostNo) {
        			setPostNo += excelItemInfo.get(str) + " ";
        		}

        		System.out.println("setPostNo = ["+setPostNo+"] / ");
        		//mod-jgc 180904 우편번호가 4자리이면 맨앞무조건 0 붙이기
        		if (setPostNo.trim().length() == 4) {
        			setPostNo = "0" + setPostNo.trim();
        		}

        		ismodm010VO.setPostno(setPostNo.trim());
        	}
        	
        	if (listAddress.size() > 0) {
        		String setAddress = "";
        		for(String str : listAddress) {
            		setAddress += excelItemInfo.get(str) + " ";
        		}

        		ismodm010VO.setAddress(setAddress.trim());
        	}

        	if (dlvmemo != null) {
        		ismodm010VO.setDlvmemo(excelItemInfo.get(dlvmemo));
        	}

        	if (orderdate != null) {
        		ismodm010VO.setOrderdate(excelItemInfo.get(orderdate));
        	}

        	if (orderitembyprice != null) {
        		ismodm010VO.setOrderitembyprice(excelItemInfo.get(orderitembyprice));
        	}

        	if (orderitemid != null) {
        		ismodm010VO.setOrderitemid(excelItemInfo.get(orderitemid));
        	}

        	if (orderitemname != null) {
        		ismodm010VO.setOrderitemname(excelItemInfo.get(orderitemname));
        	}

        	if (orderitemopt != null) {
        		ismodm010VO.setOrderitemopt(excelItemInfo.get(orderitemopt));
        	}

        	if (orderitemqty != null) {
        		ismodm010VO.setOrderitemqty(excelItemInfo.get(orderitemqty));
        	}

        	if (orderno != null) {
        		ismodm010VO.setOrderno(excelItemInfo.get(orderno));
        	}

        	if (orderuser != null) {
        		ismodm010VO.setOrderuser(excelItemInfo.get(orderuser));
        	}

        	if (orderusercontact != null) {
        		//mod-jgc 180906 전화번호 맨압자리가 0이 아니면 0 추가
        		ismodm010VO.setOrderusercontact(chgContactNo(excelItemInfo.get(orderusercontact)));
        	}

        	if (rcvuser != null) {
        		ismodm010VO.setRcvuser(excelItemInfo.get(rcvuser));
        	}

        	if (rcvusercontact != null) {
        		//mod-jgc 180906 전화번호 맨압자리가 0이 아니면 0 추가
        		ismodm010VO.setRcvusercontact(chgContactNo(excelItemInfo.get(rcvusercontact)));
        	}

        	if (rcvusercontacthp != null) {
        		//mod-jgc 180906 전화번호 맨압자리가 0이 아니면 0 추가
        		ismodm010VO.setRcvusercontacthp(chgContactNo(excelItemInfo.get(rcvusercontacthp)));
        	}

        	if (rcvuseremail != null) {
        		ismodm010VO.setRcvuseremail(excelItemInfo.get(rcvuseremail));
        	}

        	if (orderitemprice != null) {
        		ismodm010VO.setOrderitemprice(excelItemInfo.get(orderitemprice));
        	}

        	if (dlvprice != null) {
        		ismodm010VO.setDlvprice(excelItemInfo.get(dlvprice));
        	}
        	//필드 추가시 여기 추가 2/2
        	
        	ismodm010VO.setUploadviewkey(orderTempKey);
        	ismodm010VO.setCum010id(cum010id);
        	ismodm010VO.setCum030id(cum030id);
        	ord010DAO.insertOrderMainData(ismodm010VO);
        }
        return 0;
    }
	
	private String chgContactNo(String contactno) {
		String retVal = contactno;
		if ( (retVal != null)&&(retVal.length() > 0) ) {
			if (!"0".equals(contactno.subSequence(0, 1))) {
				retVal = "0" + retVal;
			}
		}
		return retVal;
	}
}
