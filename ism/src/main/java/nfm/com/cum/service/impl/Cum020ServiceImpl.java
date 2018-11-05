package nfm.com.cum.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import nfm.com.cum.service.Cum020Service;
import nfm.com.cum.service.Ismcum040VO;
import nfm.com.exl.util.ExcelManager;
import nfm.com.exl.util.ExcelRead;
import nfm.com.exl.util.ExcelReadOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("cum020Service")
public class Cum020ServiceImpl extends EgovAbstractServiceImpl implements Cum020Service {
	
	/** cum020DAO */
	@Resource(name="cum020DAO")
	private Cum020DAO cum020DAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public Object cum020ExcelDownload(int search_cum030id) throws Exception {
		
		List<Ismcum040VO> listData = (List<Ismcum040VO>) cum020DAO.selectListCumProdList(search_cum030id);
		
		List<Object> header = new ArrayList<Object>();
	    List<List<Object>> data = new ArrayList<List<Object>>();

	    header.add("운영상품코드");
	    header.add("운영상품명");
	    header.add("매출처상품코드");
		
	    for(Ismcum040VO vo : listData){
	    	List<Object> obj = new ArrayList<Object>();
	    
	    	obj.add(vo.getItemcode());
	    	obj.add(vo.getItemname());
	    	obj.add(vo.getCumprodcode());

	    	data.add(obj);
	    }
		
	    String[] excelCellType = {"S","S","S"};
		
	    ExcelManager excelManager = new ExcelManager(header, data);
	    excelManager.setSheetName("매출처상품List");
	    excelManager.setWidth(6000);
	    excelManager.setCellDataType(excelCellType);
	    excelManager.setStartRow(0);
	    excelManager.setStartCol(0);
	    excelManager.setExcelType("xls");
	    
		byte[] bytes = excelManager.makeExcel();
		
		return bytes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String readExcelFile(MultipartFile mf, int cum030id) throws Exception {
		File convFile = new File( mf.getOriginalFilename());
		mf.transferTo(convFile);

        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(convFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C");
        excelReadOption.setStartRow(2);

        List<Map<String, String>>excelContent1 =ExcelRead.read(excelReadOption);
        Iterator excelItem1 = excelContent1.iterator();

        while (excelItem1.hasNext()) {
        	Map<String, String> excelItemInfo = (Map<String, String>) excelItem1.next();

        	System.out.println("cum030id = " + cum030id);
        	System.out.println("excel A = " + excelItemInfo.get("A"));
        	System.out.println("excel B = " + excelItemInfo.get("B"));
        	System.out.println("excel C = " + excelItemInfo.get("C"));

        	Ismcum040VO ismcum040VO = new Ismcum040VO();
        	ismcum040VO.setCum030id(cum030id);
        	ismcum040VO.setItemcode(excelItemInfo.get("A"));
        	ismcum040VO.setCumprodcode(excelItemInfo.get("C"));

        	cum020DAO.insertCum040(ismcum040VO);
        }
	    
	    convFile.delete();
	    
	    return "SUCCESS";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<?> selectList040(String search_cum030id) throws Exception {
		if ( (search_cum030id == null) || ("".equals(search_cum030id)) ) {
			search_cum030id = "-1";
		}

		HashMap hm = new HashMap();
		hm.put("cum030id", search_cum030id);
		
		List<?> result = cum020DAO.selectList040(hm);
		return result;
	}

}
