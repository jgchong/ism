import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import nfm.com.exl.util.ExcelRead;
import nfm.com.exl.util.ExcelReadOption;
import nfm.com.ord.service.impl.Ord010ServiceUtil;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class testmain {
	/*
		private static boolean lowerCheck;
	    private static int size;

	    public static String getKey(int size1, boolean lowerCheck1) {
	        size = size1;
	        lowerCheck = lowerCheck1;
	        return init();
	    }

	    private static String init() {
	        Random ran = new Random();
	        StringBuffer sb = new StringBuffer();
	        int num = 0;

	        do {
	            num = ran.nextInt(75)+48;
	            if (num>=48 && num<=57) {
	                sb.append((char)num);
	            }else {
	                continue;
	            }
	        } while (sb.length() < size);
	        if(lowerCheck) {
	            return sb.toString().toLowerCase();
	        }
	        return sb.toString();
	    }
*/
	public static void main(String[] args) {
		//String key = getKey(6, false);
		//System.out.println("key="+key);
/*
    	JSONArray jsonArray = new JSONArray();
		for (int i=0;i<3;i++) {
	    	JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("url", i);
	    	jsonObject.put("context", "2");
	    	jsonObject.put("version", "3");
	    	jsonArray.add(jsonObject);
		}

    	System.out.println(jsonArray.toString());
		String data = "주문순번^수령인^배송지주소^배송메모^옵션명(수량)^^^^^^^^^^^^^^^^^";

		String[] userTitleNameArray = data.split("\\^");
System.out.println(userTitleNameArray.length);
		for (String item : userTitleNameArray ){
            System.out.println("aaa" + item);
        }
*/
		/*

		File convFile = new File( "E:\\임시\\까치\\종다리\\0208_신세계B2E★.xls");
		//File convFile = new File( "E:\\임시\\까치\\종다리\\0208_현대홈쇼핑★.xls");
		//File convFile = new File( "E:\\임시\\까치\\종다리\\어치\\0208_현대리바트★.xls");
		
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(convFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","AA","AB","AC","AD","AE","AF","AG","AH","AI","AJ","AK","AL","AM","AN","AO","AP","AQ","AR","AS","AT","AU","AV","AW","AX","AY","AZ","BA","BB","BC");
        excelReadOption.setStartRow(1);

        int rowCnt = 1;
        rowCnt = Ord010ServiceUtil.getRealHeadRow(excelReadOption);
        
	    excelReadOption.setStartRow(rowCnt); //위에서 찾은 유효한 행으로 다시 setting 후 읽어서 타이틀 get
        List<Map<String, String>>excelContent1 =ExcelRead.read(excelReadOption);
        Iterator<Map<String, String>> excelItem1 = excelContent1.iterator();
        
        String retStr = "";
        int blankFielsCnt = 0;
		Map<String, String> excelItemInfo = (Map<String, String>) excelItem1.next();
	    for(String item : excelReadOption.getOutputColumns()){
	    	String itemval = excelItemInfo.get(item);
		    System.out.print(item + "/");
		    System.out.println(itemval);
			if ( (itemval != null)&&(!"".equals(itemval)) ) {
				retStr += (itemval + "^");
			}else{
				if ("".equals(itemval) ) blankFielsCnt++;
			}
	    }
        System.out.println("blankFielsCnt = " + blankFielsCnt);
        if (blankFielsCnt > 0) {
		    excelReadOption.setStartRow(rowCnt+1); //위에서 찾은 유효한 행으로 다시 setting 후 읽어서 타이틀 get
	        List<Map<String, String>>excelContent2 =ExcelRead.read(excelReadOption);
	        Iterator<Map<String, String>> excelItem2 = excelContent2.iterator();
	        
	
			Map<String, String> excelItemInfo2 = (Map<String, String>) excelItem2.next();
		    for(String item : excelReadOption.getOutputColumns()){
		    	String itemval = excelItemInfo2.get(item);
			    System.out.print(item + "/");
			    System.out.println(itemval);
				if ( (itemval != null)&&(!"".equals(itemval)) ) {
					retStr += (itemval + "^");
				}
		    }
        }
	    System.out.println("ret >>> " + retStr);
*/
		Timestamp tsp = new Timestamp(System.currentTimeMillis());
		System.out.println(tsp);
		
		tsp = new Timestamp(System.currentTimeMillis());
		System.out.println(tsp);
		
		tsp = new Timestamp(System.currentTimeMillis());
		System.out.println(tsp);
	}

}
