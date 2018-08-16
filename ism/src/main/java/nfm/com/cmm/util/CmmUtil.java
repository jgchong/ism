package nfm.com.cmm.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import egovframework.com.cmm.service.CmmnDetailCode;

public class CmmUtil {
    public static String cmmCodeOptionConvert(List<CmmnDetailCode> listCmmCode, String selVal) {

    	String retVal = "";
    	
	    for(CmmnDetailCode vo : listCmmCode){
	    	String selected = "";
	    	if (selVal.equals(vo.getCode())) selected = "selected";
	    	retVal += "<option value='"+vo.getCode()+"' "+selected+">"+vo.getCodeNm()+"</option>"; 
	    }
	    
	    return retVal;
    }

	public static String getNumbering(String prefix, String suffix, boolean tempkeyuse) {
		GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = gc.getTime();
        
        String tempkey = "";
        if (tempkeyuse) {
        	tempkey = new TempKey().getKey(6, false);
        }
        String str = prefix + sf.format(d) + suffix + tempkey;
		
		return str;
	}
}
