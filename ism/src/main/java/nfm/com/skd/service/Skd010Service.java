package nfm.com.skd.service;

import java.util.Map;

public interface Skd010Service {
	public Object selectList(Skd010SearchVO skd010SearchVO) throws Exception;
	public int selectListTotCnt(Skd010SearchVO skd010SearchVO) throws Exception;
	public int insertSkd010(Map param) throws Exception;
	public int insertSkd020(Map param) throws Exception;
	public String prd020seletWhsitem(String whs010id) throws Exception;
}