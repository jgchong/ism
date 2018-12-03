package nfm.com.ord.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface Ord010Service  {

	// 2018-11 LDC 상단에서 정보 받아오기.
	public String readExcelFile(List<MultipartFile> mf, List<String> dataInfoList) throws Exception;

	public int selectListTotCnt(Ord010SearchVO ord010SearchVO) throws Exception;

	public Object selectList(Ord010SearchVO ord010SearchVO) throws Exception;

	public String selectApiDataDetail(String cum030id) throws Exception;

	public void saveApiDetailData(Ismodo020VO ismodo020vo) throws Exception;

	public String readExcelFile(MultipartFile mf) throws Exception;

	public void saveManualDetailData(String cum030id, String userTitleNames, String sysmTitleNames, String assgTitleNames, String priceopts) throws Exception;

	public String selectManualDataDetail(String cum030id) throws Exception;

	public String readOrderExcelFile(MultipartFile mf, int filecum010id, int filecum030id) throws Exception;
	
}