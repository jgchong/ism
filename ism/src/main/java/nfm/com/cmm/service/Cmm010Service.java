package nfm.com.cmm.service;

public interface Cmm010Service  {
	public String selectListMemo(IsmCmm010VO ismCmm010VO) throws Exception;

	public void insertMemo(IsmCmm010VO ismCmm010VO) throws Exception;
}