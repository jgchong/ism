package nfm.com.po.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.LoginVO;

public interface Po010Service  {

	public Object selectWhsList() throws Exception;

	public Object selectBycList() throws Exception;

	public String selectPoo010ListJson(int poo010id, String pocotype) throws Exception;

	public String savePoo010(int poo010id, String pocotype, String savedata) throws Exception;

	public String selectOrm010ListJson(int poo010id, String pocotype) throws Exception;

	public void savePoo020(int byc010id, String apiurl, String apicontext, String apiversion) throws Exception;

	public String selectPoo020Json(int byc010id) throws Exception;

	public String savePoList(String[] odm010idArr, Po010SaveVO po010SaveVO, LoginVO loginVO) throws Exception;

	public String batchupExcel(List<MultipartFile> fileList) throws Exception;

	public String selectUserListJson(int keyid, String pocotype) throws Exception;
	
}