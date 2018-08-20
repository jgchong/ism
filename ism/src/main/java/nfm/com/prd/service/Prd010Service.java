package nfm.com.prd.service;

import nfm.com.ord.service.Ismodm010VO;
import nfm.com.ord.service.Ord020SearchVO;

public interface Prd010Service {
	public Object selectWhsAll() throws Exception;
	public Object selectBycAll() throws Exception;
	public Object selectList(Prd010SearchVO prd010SearchVO) throws Exception;
	public int selectListTotCnt(Prd010SearchVO prd010SearchVO) throws Exception;
	public void updateItemWhs(Integer itemId, Integer whsId) throws Exception;
	public String selectAll() throws Exception;
}