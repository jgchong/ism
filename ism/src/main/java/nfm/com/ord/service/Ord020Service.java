package nfm.com.ord.service;

public interface Ord020Service  {

	public int selectListTotCnt(Ord020SearchVO ord020SearchVO) throws Exception;

	public Object selectList(Ord020SearchVO ord020SearchVO) throws Exception;

	public String selectListJson(Ord020SearchVO ord020SearchVO) throws Exception;

	public void ord020SelectChgOrderStatus(String selectoptionval, String chgodm010ids) throws Exception;

	public void ord020SelectDel(String chgodm010ids) throws Exception;

	public void updateOrderDetailData(Ismodm010VO ismodm010vo) throws Exception;

	public byte[] ord020ExcelDownload(Ord020SearchVO ord020SearchVO) throws Exception;

	public String selectListMemo(Ord020SearchVO ord020SearchVO) throws Exception;

	public Object selectStattList(Ord020SearchVO ord020SearchVO) throws Exception;

	public Object selectCompList(Ord020SearchVO ord020SearchVO) throws Exception;

	public String selectProdList(Ord020SearchVO ord020SearchVO) throws Exception;

	public void ord020InsertProd(Ismodm010VO ismodm010vo) ;
}