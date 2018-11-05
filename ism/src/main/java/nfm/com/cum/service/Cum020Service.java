package nfm.com.cum.service;

import org.springframework.web.multipart.MultipartFile;

public interface Cum020Service  {
	public Object cum020ExcelDownload(int search_cum030id) throws Exception;

	public String readExcelFile(MultipartFile mf, int cum030id) throws Exception;

	public Object selectList040(String search_cum030id) throws Exception;
}