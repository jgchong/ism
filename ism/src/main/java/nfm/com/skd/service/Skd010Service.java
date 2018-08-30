package nfm.com.skd.service;

import java.util.List;
import java.util.Map;

public interface Skd010Service {
	public List<Skd010VO> selectList(Skd010SearchVO skd010SearchVO) throws Exception;
	public int selectListTotCnt(Skd010SearchVO skd010SearchVO) throws Exception;
	public int insertSkd010(Map param) throws Exception;
	public int insertSkd020(Map param) throws Exception;
	public String skd020seletWhsitem(String whs010id) throws Exception;
	public void skd010SelectDel(String skd010ids) throws Exception;

	public int getSumItemea(int i);

	String selectWithSkd010id(String currentId);
}