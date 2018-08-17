package nfm.com.whs.service;

import org.springframework.web.multipart.MultipartFile;

public interface Whs010Service  {
	
	public String selectDetail(Whs010SearchVO whs010SearchVO) throws Exception;

	public Object selectList(Whs010SearchVO whs010SearchVO) throws Exception;

	public int saveWhsAll(MultipartFile mf, String path, WhsAllVO whsAllVO) throws Exception;

}