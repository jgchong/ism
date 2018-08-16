package nfm.com.cum.service;

import org.springframework.web.multipart.MultipartFile;

public interface Cum010Service  {

	public int selectListTotCnt(Cum010SearchVO cum010SearchVO) throws Exception;

	public Object selectList(Cum010SearchVO cum010SearchVO) throws Exception;

	public Object selectDetail(Cum010SearchVO cum010SearchVO) throws Exception;

	public int insertCumAllData(MultipartFile mf, String path, CumAllVO cumAllVO) throws Exception;
	
}