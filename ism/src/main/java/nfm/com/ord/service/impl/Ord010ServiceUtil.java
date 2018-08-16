package nfm.com.ord.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nfm.com.cmm.util.TempKey;
import nfm.com.exl.util.ExcelRead;
import nfm.com.exl.util.ExcelReadOption;
 
public class Ord010ServiceUtil {
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static int getRealHeadRow(ExcelReadOption excelReadOption) {
        List<Map<String, String>>excelContent =ExcelRead.read(excelReadOption);
        Iterator excelItem = excelContent.iterator();

        //유효한 타이틀 행을 찾는다 필드가 5개가 모두 있는 경우를 시작으로 한다.
        int rowCnt = 1;
        
        while (excelItem.hasNext()) {
        	Map<String, String> excelItemInfo = (Map<String, String>) excelItem.next();

        	if ( (excelItemInfo.get("B") != null) &&
           		 (excelItemInfo.get("C") != null) &&
        		 (excelItemInfo.get("D") != null) &&
        		 (excelItemInfo.get("E") != null) &&
        		 (excelItemInfo.get("F") != null) &&
        		 (!"".equals(excelItemInfo.get("B"))) &&
        		 (!"".equals(excelItemInfo.get("C"))) &&
        		 (!"".equals(excelItemInfo.get("D"))) &&
        		 (!"".equals(excelItemInfo.get("E"))) &&
        		 (!"".equals(excelItemInfo.get("F"))) ) {

        		break;
        	}
        	rowCnt++;
        }

        return rowCnt;
    }

	public static String getOrderTempKey() {
		GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = gc.getTime();
        String str = sf.format(d) + new TempKey().getKey(6, false);
		
		return str;
	}
    
}