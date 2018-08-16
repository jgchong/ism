package nfm.com.exl.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
    /**
     * 엑셀 download 위해...
     * 엑셀파일을 읽어서 Workbook 객체에 리턴한다.
     * XLS와 XLSX 확장자를 비교한다.
     * 
     * @param filePath
     * @return
     * 
     */
    private int mStartRow = 1;
    private int mStartCol = 1;
    private int mWidth = 5000;
    private String[] dataTypes;

    private IndexedColors mHeaderColor =  IndexedColors.LIGHT_CORNFLOWER_BLUE;
    private IndexedColors mDataColor =  IndexedColors.WHITE;

    //private XSSFWorkbook mWorkbook;
    private Workbook mWorkbook;

    private String mSheetName = "sheet1";

    private List<Object> mHeader;
    private List<List<Object>> mData;
    
    private InputStream mReadFile;

    private String excelType = "xlsx"; //HSSF : xls, XSSF : xlsx, SXSSF : sxlsx 
    
    public ExcelManager(List<Object> header, List<List<Object>> data) {
        mHeader = header;
        mData = data;
    }
    
    public ExcelManager() {

    }
    
    public ExcelManager(InputStream excelFile) {
    	mReadFile = excelFile;
    }

    public void setStartRow(int startRow) {
        mStartRow = startRow;
    }

    public void setStartCol(int startCol) {
        mStartCol = startCol;
    }

    public void setSheetName(String sheetName) {
        mSheetName = sheetName;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public void setHeaderColor(IndexedColors headerColor) {
        mHeaderColor = headerColor;
    }

    public void setDataColor(IndexedColors dataColor) {
        mDataColor = dataColor;
    }

    public void setCellDataType(String[] dataTypes) {
    	this.dataTypes = dataTypes;
    }

    public void setExcelType(String excelType) {
    	this.excelType = excelType;
    }

    public byte[] makeExcel() {
		
    	if ("xlsx".equals(excelType)) {
    		mWorkbook = new XSSFWorkbook();
    	}else if ("xls".equals(excelType)) {
    		mWorkbook = new HSSFWorkbook();
    	}else if ("sxlsx".equals(excelType)) {
    		mWorkbook = new SXSSFWorkbook();
    	}
        Sheet sheet = mWorkbook.createSheet(mSheetName);
        Row headerRow = sheet.createRow(mStartRow);

        int headerCount = mHeader.size();

		CellStyle cellStyle = getHeaderStyle();
        for (int i=mStartCol; i<headerCount+mStartCol; i++) {
            Cell headerCell = headerRow.createCell(i, Cell.CELL_TYPE_STRING);
            setCell(headerCell, String.valueOf(mHeader.get(i - mStartCol)), mHeaderColor.getIndex(), cellStyle);
            sheet.setColumnWidth(i, mWidth);
        }

        int dataCount = mData.size();

        cellStyle = getBodyStyle();
        for (int i=mStartRow+1; i<dataCount+mStartRow+1; i++) {
            Row dataRow = sheet.createRow(i);

            List<Object> data = mData.get(i - (mStartRow + 1));

            int count = data.size();

            for (int j=mStartCol; j<count+mStartCol; j++) {
            	String dataType = "";
            	if ( (dataTypes != null) && (dataTypes.length > 0) ) {
            		dataType = dataTypes[j - mStartCol];
            	}
            	Cell dataCell;
            	//System.out.println("jgc debug dataType = "+dataType);
            	if ("N".equals(dataType)) {
            		dataCell = dataRow.createCell(j, Cell.CELL_TYPE_NUMERIC);
            	}else if ("F".equals(dataType)) {
            		dataCell = dataRow.createCell(j, Cell.CELL_TYPE_FORMULA);
            	}else{
            		dataCell = dataRow.createCell(j, Cell.CELL_TYPE_STRING);
            	}

                Object cell = data.get(j - mStartCol);
                String cellStr;

                if (cell instanceof Date) {
                    cellStr = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",Locale.US).format(cell);
                } else {
                    cellStr = String.valueOf(cell);
                }

                setCell(dataCell, cellStr, mDataColor.getIndex(), cellStyle);
                sheet.setColumnWidth(i, mWidth);
            }
        }
        byte[] bytes = new byte[0];

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            mWorkbook.write(out);
            bytes = out.toByteArray();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    
    public List<List<Object>> readExcel(){
    	List<List<Object>> excelData = new ArrayList<List<Object>>();
    	List<Object> cellData = null;
    	
        try {

        	if ("xlsx".equals(excelType)) {
        		mWorkbook = new XSSFWorkbook(mReadFile);
        	}else if ("xls".equals(excelType)) {
        		mWorkbook = new HSSFWorkbook(mReadFile);
        	}else if ("sxlsx".equals(excelType)) {
        		mWorkbook = new SXSSFWorkbook(-1);
        	}
        	
			Sheet sheet    =  null;
			Row row     =  null;
			Cell cell    =  null;
			
			FormulaEvaluator evaluator = mWorkbook.getCreationHelper().createFormulaEvaluator();
			String data = "";
			
			int sheetNum =  mWorkbook.getNumberOfSheets();
			for(int i=0;i<sheetNum;i++){//시트가 여러개 있을 경우
			    sheet = mWorkbook.getSheetAt(i);
			    
			    int lastRowNum = sheet.getLastRowNum();
			    for(int r=sheet.getFirstRowNum();r<=lastRowNum;r++){//row를 읽는다.
			    	row = sheet.getRow(r);
			        if(row== null) continue;
			        
		    		int lastCellNum = row.getLastCellNum();
			        cellData = new ArrayList<Object>();
			        for(int c=row.getFirstCellNum();c<=lastCellNum;c++){//cell을 읽는다.
				        cell   =  row.getCell(c);
				        //if(cell== null) continue;
				        if(cell== null) {cellData.add(""); continue;}
				        
				        switch(cell.getCellType()){
					        case Cell.CELL_TYPE_NUMERIC:
//					        	cellData.add(cell.getNumericCellValue());
					        	cellData.add(new BigDecimal(cell.getNumericCellValue()).toPlainString());
					        	break;
					        case Cell.CELL_TYPE_STRING:
					        	cellData.add(cell.getStringCellValue());
					        	break;
					        case Cell.CELL_TYPE_FORMULA :
					        	//cellData.add(cell.getCellFormula());
					        	if(!(cell.toString().equalsIgnoreCase("")) ){
					        		if(evaluator.evaluateFormulaCell(cell)==Cell.CELL_TYPE_NUMERIC){
					        			double fddata = cell.getNumericCellValue();
					        			DecimalFormat df = new DecimalFormat();
					        			data = df.format(fddata);
					        		}else if(evaluator.evaluateFormulaCell(cell)==Cell.CELL_TYPE_STRING){
					        			data = cell.getStringCellValue();
					        		}else if(evaluator.evaluateFormulaCell(cell)==Cell.CELL_TYPE_BOOLEAN){
					        			boolean fbdata = cell.getBooleanCellValue();         
					        			data = String.valueOf(fbdata);         
					        		}
					        		cellData.add(data);
					        	}
					        	break;
					        default:
					        	cellData.add("");
				       }
			        }
				    excelData.add(cellData);
			    }
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return excelData;
    }

    private void setCell(Cell headerCell, String data, short index, CellStyle cellStyle) {
        headerCell.setCellValue(data);
        headerCell.setCellStyle(cellStyle);
    }
    
    private CellStyle getHeaderStyle() {
		CellStyle cellStyle = mWorkbook.createCellStyle();
		
		cellStyle.setBorderBottom(CellStyle.BORDER_THICK);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderLeft(CellStyle.BORDER_THICK);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderRight(CellStyle.BORDER_THICK);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderTop(CellStyle.BORDER_THICK);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        
        cellStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
/*
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(index);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
*/
		
		return cellStyle;
    }
    
    private CellStyle getBodyStyle() {
		CellStyle cellStyle = mWorkbook.createCellStyle();
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		
		return cellStyle;
    }
}