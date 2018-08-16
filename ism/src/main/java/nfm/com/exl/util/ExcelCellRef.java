package nfm.com.exl.util;

import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;
 
public class ExcelCellRef {
    /**
     * 2차 엑셀 upload 위해...
     * Cell에 해당하는 Column Name을 가젼온다(A,B,C..)
     * 만약 Cell이 Null이라면 int cellIndex의 값으로
     * Column Name을 가져온다.
     * @param cell
     * @param cellIndex
     * @return
     * CELL_TYPE_FORMULA 2018.01.25 수정
     */
    public static String getName(Cell cell, int cellIndex) {
        int cellNum = 0;
        if(cell != null) {
            cellNum = cell.getColumnIndex();
        }
        else {
            cellNum = cellIndex;
        }
        
        return CellReference.convertNumToColString(cellNum);
    }
    
    public static String getValue(Cell cell) {
        String value = "";
        
        if(cell == null) {
            value = "";
        }else{
        	//getCellFormula()-> 수식자체를 가져올때
        	//getNumericCellValue()-> 수식 반환값이 숫자일때
        	//getStringCellValue()-> 수식 반환값이 문자일때
	        switch(cell.getCellType()) {
	            case Cell.CELL_TYPE_FORMULA :
	                //value = cell.getNumericCellValue() + ""; //jgc 2018.01.25 수정  getCellFormula() 였음.
	            	value = cell.getCellFormula() + "";
	                break;
	            
	            case Cell.CELL_TYPE_NUMERIC :
	            	//jgc modify 180713
	                //value = (long) cell.getNumericCellValue() + "";
	            	//date의 경우 수식으로 계산되는 부분이 있어 수정
	            	if (HSSFDateUtil.isCellDateFormatted(cell)) {
	            		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            		value = formatter.format(cell.getDateCellValue());
	            	}else{
	            		value = (long) cell.getNumericCellValue() + "";
	            	}
	                break;
	                
	            case Cell.CELL_TYPE_STRING :
	                value = cell.getStringCellValue();
	                break;
	            
	            case Cell.CELL_TYPE_BOOLEAN :
	                value = cell.getBooleanCellValue() + "";
	                break;
	           
	            case Cell.CELL_TYPE_BLANK :
	                value = "";
	                break;
	            
	            case Cell.CELL_TYPE_ERROR :
	                value = cell.getErrorCellValue() + "";
	                break;
	            default:
	                value = cell.getStringCellValue();
	        }
        }
        
        return value;
    }
    
    public static String getValuedbl(Cell cell) {
        String value = "";
        
        if(cell == null) {
            value = "";
        }else{
        	//getCellFormula()-> 수식자체를 가져올때
        	//getNumericCellValue()-> 수식 반환값이 숫자일때
        	//getStringCellValue()-> 수식 반환값이 문자일때
	        switch(cell.getCellType()) {
	            case Cell.CELL_TYPE_FORMULA :
	                value = cell.getNumericCellValue() + ""; //jgc 2018.01.25 수정  getCellFormula() 였음.
	                break;
	            
	            case Cell.CELL_TYPE_NUMERIC :
	                //value = (long) cell.getNumericCellValue() + ""; //jgc 2018.01.31 수정 long 형변환 할 경우 숫자를 무조건 int로 변경해서 에러메세지 처리 못함 
	                value = cell.getNumericCellValue() + ""; //mnp 숫자인 경우 int 값을 넘으면 다른값을 가져와서 바꿈
	                break;
	                
	            case Cell.CELL_TYPE_STRING :
	                value = cell.getStringCellValue();
	                break;
	            
	            case Cell.CELL_TYPE_BOOLEAN :
	                value = cell.getBooleanCellValue() + "";
	                break;
	           
	            case Cell.CELL_TYPE_BLANK :
	                value = "";
	                break;
	            
	            case Cell.CELL_TYPE_ERROR :
	                value = cell.getErrorCellValue() + "";
	                break;
	            default:
	                value = cell.getStringCellValue();
	        }
        	System.out.println("jgc debug bbb = ["+value+"]");
        }
        
        return value;
    }
 
}