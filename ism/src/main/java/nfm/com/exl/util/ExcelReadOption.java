package nfm.com.exl.util;

import java.util.ArrayList;
import java.util.List;
 
public class ExcelReadOption {
    /**
     * 엑셀파일의 경로
     */
    private String filePath;
    
    /**
     * 추출할 컬럼 명
     */
    private List<String> outputColumns;
    
    /**
     * 추출을 시작할 행 번호
     */
    private int startRow;
    private int lastRow = 0;
    
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public List<String> getOutputColumns() {
        
        List<String> temp = new ArrayList<String>();
        temp.addAll(outputColumns);
        
        return temp;
    }
    public void setOutputColumns(List<String> outputColumns) {
        
        List<String> temp = new ArrayList<String>();
        temp.addAll(outputColumns);
        
        this.outputColumns = temp;
    }
    
    public void setOutputColumns(String ... outputColumns) {
        
        if(this.outputColumns == null) {
            this.outputColumns = new ArrayList<String>();
        }
        
        for(String ouputColumn : outputColumns) {
            this.outputColumns.add(ouputColumn);
        }
    }
    
    public int getStartRow() {
        return startRow;
    }
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
    
    /**
     * 중간 빈행이 있어 마지막행 추가여부 확인용 BC탑포인트몰 문제해결 위해 
     */
    public int getLstRow() {
        return lastRow;
    }
    
    /**
     * 중간 빈행이 있어 마지막행 추가여부 확인용 BC탑포인트몰 문제해결 위해 
     */
    public void setLstRow(int lastRow) {
        this.lastRow = lastRow;
    }
}