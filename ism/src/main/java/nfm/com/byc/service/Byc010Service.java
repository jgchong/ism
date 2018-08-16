package nfm.com.byc.service;

import org.springframework.web.multipart.MultipartFile;

public interface Byc010Service  {

	public Object selectBuyerList(Byc010SearchVO byc010SearchVO) throws Exception;

	public String selectDetail(Byc010SearchVO byc010SearchVO) throws Exception;

	public int saveBycAll(MultipartFile mf, String path, BycAllVO bycAllVO) throws Exception;
	
}