package nfm.com.ord.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.cmm.service.Cmm010Service;
import nfm.com.cmm.service.IsmCmm010VO;
import nfm.com.cmm.service.impl.Cmm010DAO;
import nfm.com.exl.util.ExcelManager;
import nfm.com.ord.service.Ismodm010VO;
import nfm.com.ord.service.Ismodo020ProdVO;
import nfm.com.ord.service.Ord020SearchVO;
import nfm.com.ord.service.Ord020Service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service("ord020Service")
public class Ord020ServiceImpl extends EgovAbstractServiceImpl implements Ord020Service {

	/** cmm010Service */
	@Resource(name = "cmm010Service")
	private Cmm010Service cmm010Service;
	
	/** cmm010DAO */
	@Resource(name="cmm010DAO")
	private Cmm010DAO cmm010DAO;

	/** ord020DAO */
	@Resource(name="ord020DAO")
	private Ord020DAO ord020DAO;
	
	@Override
	public int selectListTotCnt(Ord020SearchVO ord020SearchVO) throws Exception {
		return ord020DAO.selectListTotCnt(ord020SearchVO);
	}
	
	@Override
	public List<?> selectList(Ord020SearchVO ord020SearchVO) throws Exception {
		return ord020DAO.selectList(ord020SearchVO);
	}

	/**
	 * 팝업으로 변경하면서 미사용
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String selectListJson(Ord020SearchVO ord020SearchVO) throws Exception {

    	List<Ismodm010VO> listIsmodm010VO = (List<Ismodm010VO>) ord020DAO.selectList(ord020SearchVO); //odm010id로 조회
	
    	JSONObject jsonObject = new JSONObject();
	    for(Ismodm010VO vo : listIsmodm010VO){
	    	
	    	jsonObject.put("orderno", vo.getOrderno());
	    	jsonObject.put("orderdate", vo.getOrderdate());
	    	jsonObject.put("orderuser", vo.getOrderuser());
	    	jsonObject.put("orderusercontact", vo.getOrderusercontact());
	    	jsonObject.put("rcvuser", vo.getRcvuser());
	    	jsonObject.put("rcvusercontact", vo.getRcvusercontact());
	    	jsonObject.put("orderitemid", vo.getOrderitemid());
	    	jsonObject.put("orderitemopt", vo.getOrderitemopt());
	    	jsonObject.put("orderitemqty", vo.getOrderitemqty());
	    	jsonObject.put("orderitembyprice", vo.getOrderitembyprice());
	    	jsonObject.put("orderitemprice", vo.getOrderitemprice());
	    	jsonObject.put("postno", vo.getPostno());
	    	jsonObject.put("address", vo.getAddress());
	    	jsonObject.put("dlvmemo", vo.getDlvmemo());
	    	jsonObject.put("dlvbyprice", vo.getDlvbyprice());
	    	jsonObject.put("dlvprice", vo.getDlvprice());
	    	jsonObject.put("dlvno", vo.getDlvno());
	    	jsonObject.put("dlvco", vo.getDlvco());
	    	jsonObject.put("dlvstatus", vo.getDlvstatus());
	    	jsonObject.put("processdate", vo.getProcessdate());
	    	jsonObject.put("accountcum", vo.getAccountcum());
	    	jsonObject.put("accountbuy", vo.getAccountbuy());
	    	jsonObject.put("status", vo.getStatus());
	    	jsonObject.put("cstype", vo.getCstype());
	    	jsonObject.put("claimstatus", vo.getClaimstatus());
   			jsonObject.put("claimreason", vo.getClaimreason());
			jsonObject.put("retstatus", vo.getRetstatus());
			jsonObject.put("retqty", vo.getRetqty());
			jsonObject.put("retprice", vo.getRetprice());
	    	jsonObject.put("coname", vo.getConame());
	    	jsonObject.put("code_nm", vo.getCode_nm());
	    	jsonObject.put("itemname", vo.getItemname());
	    	jsonObject.put("bycname", vo.getBycname());
	    	jsonObject.put("itemprice", vo.getItemprice());
	    	jsonObject.put("itembuyprice", vo.getItembuyprice());
	    	jsonObject.put("itemdlvprice", vo.getItemdlvprice());
	    	jsonObject.put("itembuydlvprice", vo.getItembuydlvprice());
	    	jsonObject.put("itembuydlvprice", vo.getItembuydlvprice());
	    	jsonObject.put("orderitemname", vo.getOrderitemname());
	    	jsonObject.put("shopmallname", vo.getShopmallname());
	    	jsonObject.put("cotype2nm", vo.getCotype2nm());
	    	jsonObject.put("cotype3nm", vo.getCotype3nm());
	    	jsonObject.put("rcvusercontacthp", vo.getRcvusercontacthp());
	    	jsonObject.put("rcvuseremail", vo.getRcvuseremail());
	    }

	    //주문 메모 정보 가져옴.[s]
	    IsmCmm010VO ismCmm010VO = new IsmCmm010VO();
	    ismCmm010VO.setBuss_key(""+ord020SearchVO.getOdm010id());
	    ismCmm010VO.setBuss_type("OD");
	    String strMemo = (String) cmm010Service.selectListMemo(ismCmm010VO);
    	jsonObject.put("orderMemo", strMemo);
    	//주문 메모 정보 가져옴.[e]
	    
	    return jsonObject.toString();
	}

	@Override
	public String selectListMemo(Ord020SearchVO ord020SearchVO) throws Exception {
	    //주문 메모 정보 가져옴.[s]
	    IsmCmm010VO ismCmm010VO = new IsmCmm010VO();
	    ismCmm010VO.setBuss_key(""+ord020SearchVO.getOdm010id());
	    ismCmm010VO.setBuss_type("OD");
	    String strMemo = (String) cmm010Service.selectListMemo(ismCmm010VO);
    	//주문 메모 정보 가져옴.[e]
	    
	    return strMemo;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void ord020SelectChgOrderStatus(String selectoptionval, String chgodm010ids)
			throws Exception {

		String[] chgodm010idArray = chgodm010ids.split(",");
		HashMap hm = new HashMap();
		hm.put("selectoptionval", selectoptionval);
		hm.put("chgodm010ids", chgodm010idArray);

		ord020DAO.ord020SelectChgOrderStatus(hm);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void ord020SelectDel(String chgodm010ids) throws Exception {

		String[] chgodm010idArray = chgodm010ids.split(",");
		HashMap hm = new HashMap();
		hm.put("chgodm010ids", chgodm010idArray);
		
		ord020DAO.ord020SelectDel(hm);
	}

	@Override
	public void updateOrderDetailData(Ismodm010VO ismodm010vo) throws Exception {
		
		ord020DAO.updateOrderDetailData(ismodm010vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public byte[] ord020ExcelDownload(Ord020SearchVO ord020SearchVO) throws Exception {
		
		List<Ismodm010VO> listData = (List<Ismodm010VO>) ord020DAO.selectList(ord020SearchVO);
		
		List<Object> header = new ArrayList<Object>();
	    List<List<Object>> data = new ArrayList<List<Object>>();

	    header.add("사업자구분");
	    header.add("매출처");
	    header.add("쇼핑몰명");
	    header.add("매입처");
	    header.add("주문번호");
	    header.add("주문일자");
	    header.add("상품코드");
	    header.add("상품명");
	    header.add("상품옵션");
	    header.add("수량");
	    
	    header.add("매입배송비");
	    header.add("공급배송비");
	    header.add("매입가");
	    header.add("공급가");
	    header.add("주문자");
	    header.add("주문자연락처");
	    header.add("수령자");
	    header.add("수령자연락처");
	    header.add("수령자핸드폰");
	    header.add("수령자이메일");
	    header.add("우편번호");
	    header.add("주소");
	    
	    header.add("배송메모");
	    header.add("송장번호");
	    header.add("택배사");
	    header.add("처리일자");
	    header.add("정산(매출처)");
	    header.add("정산(매입처)");
	    header.add("상태");
	    header.add("CS구분");
	    header.add("클레임상태");
	    header.add("클레임사유");
	    
	    header.add("반품상태");
	    header.add("반품수량");
	    header.add("반품비");
		
	    for(Ismodm010VO vo : listData){
	    	List<Object> obj = new ArrayList<Object>();
	    	
	    	obj.add(vo.getCode_nm() + "/" + vo.getCotype2nm() + "/" + vo.getCotype3nm());
	    	obj.add(vo.getConame());
	    	obj.add(vo.getShopmallname());
	    	obj.add(vo.getBycname());
	    	obj.add(vo.getOrderno());
	    	obj.add(vo.getOrderdate());
	    	obj.add(vo.getOrderitemid());
	    	obj.add(vo.getOrderitemname());
	    	obj.add(vo.getOrderitemopt());
	    	obj.add(vo.getOrderitemqty());

	    	obj.add(vo.getItembuydlvprice());
	    	obj.add(vo.getDlvprice());
	    	obj.add(vo.getItembuyprice());
	    	obj.add(vo.getOrderitemprice());
	    	obj.add(vo.getOrderuser());
	    	obj.add(vo.getOrderusercontact());
	    	obj.add(vo.getRcvuser());
	    	obj.add(vo.getRcvusercontact());
	    	obj.add(vo.getRcvusercontacthp());
	    	obj.add(vo.getRcvuseremail());
	    	obj.add(vo.getPostno());
	    	obj.add(vo.getAddress());
	    	
	    	obj.add(vo.getDlvmemo());
	    	obj.add(vo.getDlvno());
	    	obj.add(vo.getDlvco());
	    	obj.add(vo.getProcessdate());
	    	if ("1".equals(vo.getAccountcum())) {
	    		obj.add("정산확정대기");
	    	}else{
	    		obj.add("매출이월");
	    	}
	    	if ("1".equals(vo.getAccountbuy())) {
	    		obj.add("정산확정대기");
	    	}else{
	    		obj.add("매입이월");
	    	}
	    	obj.add(vo.getStstusNm());
	    	if ("C".equals(vo.getAccountcum())) {
	    		obj.add("클레임접수");
	    	}else if ("R".equals(vo.getAccountcum())){
	    		obj.add("반품접수");
	    	}else{
	    		obj.add("");
	    	}
	    	obj.add(vo.getClaimstatusnm());
	    	obj.add(vo.getClaimreasonnm());
	    	
	    	obj.add(vo.getRetstatusnm());
	    	obj.add(vo.getRetqty());
	    	obj.add(vo.getRetprice());

	    	data.add(obj);
	    }
		
	    String[] excelCellType = {"S","S","S","S","S","S","S","S","S","N",
	    		                  "N","N","N","N","S","S","S","S","S","S","S","S",
	    		                  "S","S","S","S","S","S","S","S","S","S",
	    		                  "S","S","S"};
		
	    ExcelManager excelManager = new ExcelManager(header, data);
	    excelManager.setSheetName("주문List");
	    excelManager.setWidth(6000);
	    excelManager.setCellDataType(excelCellType);
	    excelManager.setStartRow(0);
	    excelManager.setStartCol(0);
	    excelManager.setExcelType("xls");
	    
		byte[] bytes = excelManager.makeExcel();
		
		return bytes;
	}

	/**
	 * 통계 정보 읽어오기
	 */
	@Override
	public List<?> selectStattList(Ord020SearchVO ord020SearchVO) throws Exception {
		// TODO Auto-generated method stub
		return ord020DAO.selectStattList(ord020SearchVO);
	}
	
	@Override
	public List<?> selectCompList(Ord020SearchVO ord020SearchVO) throws Exception {
		return ord020DAO.selectCompList(ord020SearchVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String selectProdList(Ord020SearchVO ord020SearchVO) throws Exception {
		// TODO Auto-generated method stub
		
		List<Ismodo020ProdVO> resultList = (List<Ismodo020ProdVO>)ord020DAO.selectProdList(ord020SearchVO);
		JSONArray dataList = new JSONArray();
		for(Ismodo020ProdVO vo : resultList){
			JSONObject dataInfo = new JSONObject();
			dataInfo.put("orderitemid", vo.getByc010id());
			dataInfo.put("itemname", vo.getItemname());
			dataInfo.put("bycname", vo.getBycname());
			dataInfo.put("itemcode", vo.getItemcode());
			dataInfo.put("itemopt", (vo.getItemopt() ==null ? "" : vo.getItemopt()));
			dataList.add(dataInfo);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("prodList", dataList);
		return jsonObject.toJSONString();
	}

	@Override
	public void ord020InsertProd(Ismodm010VO ismodm010vo) {
		// TODO Auto-generated method stub
		ord020DAO.ord020InsertProd(ismodm010vo);
	}

	@Override
	public  List<?> selectBycList() throws Exception {
		// TODO Auto-generated method stub
		return ord020DAO.selectBycList();
	}

	@Override
	public  List<?> selectCumList() throws Exception {
		// TODO Auto-generated method stub
		return ord020DAO.selectCumList();
	}
}
