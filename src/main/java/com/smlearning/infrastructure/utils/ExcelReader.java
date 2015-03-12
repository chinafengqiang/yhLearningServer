package com.smlearning.infrastructure.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelReader {

	/**
	 * 工作簿
	 */
	private Workbook workbook;
	
	/**
	 * 读excel文件
	 * @param file
	 * @throws IOException
	 */
	public ExcelReader(MultipartFile file) throws IOException {
		
//		InputStream is = file.getInputStream();
//		POIFSFileSystem pfs = new POIFSFileSystem(is);
//		workbook = new HSSFWorkbook(pfs);

		workbook = new XSSFWorkbook(file.getInputStream());
	}
	
	/**
	 * 获得工作簿表数据
	 * @return
	 */
	public Integer getSheetCount() {
		return workbook.getNumberOfSheets();
	}
	
	/**
	 * 获得表最后行数
	 * @param sheetIndex
	 * @return
	 */
	public Integer getRowCount(Integer sheetIndex) {
		return workbook.getSheetAt(sheetIndex).getLastRowNum() + 1;
	}
	
	/**
	 * 获得单元格最后行数
	 * @param sheetIndex
	 * @param rowIndex
	 * @return
	 */
	public int getColCount(Integer sheetIndex, Integer rowIndex) {
		return (int)workbook.getSheetAt(sheetIndex).getRow(rowIndex).getLastCellNum();
	}
	
	/**
	 * 获得单元格
	 * @param sheetIndex
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	public HSSFCell getCell(Integer sheetIndex, Integer rowIndex, int colIndex) {
		return (HSSFCell) workbook.getSheetAt(sheetIndex).getRow(rowIndex).getCell((short) colIndex);
	}
	
	/**
	 * 设置单元格的字符串值
	 * @param cell 单元格
	 * @return
	 */
	public String getCellValueByString(HSSFCell cell) {
		
		if (cell == null){
			return null;
		}else{
		    String cellValue = cell.getRichStringCellValue().toString(); 
		    return cellValue;
		}
	}
	
	public String  getCellValueByStringAndInteger(HSSFCell cell) {
		
	    if (cell == null) {
	    	return null;
	    }
	    
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			/*
			String num = Double.toString(cell.getNumericCellValue()).trim();
		
			
			if (num.length() > 0) {
				//return (Double.toString(cell.getNumericCellValue())).split(".")[0];
				return 
			}*/
			
			Double temp = cell.getNumericCellValue();
			if (temp != null) {
				BigDecimal bd = new BigDecimal(temp);
				
				return bd.toString();
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:
			String text = cell.getRichStringCellValue()
					.toString().trim();
			if (text.length() > 0) {
			    return cell.getRichStringCellValue().toString();
			}
			break;
		default:// skip
		
		}
		return null;
		
	}
	
	/**
	 * 设置单元格的整型值
	 * @param cell 单元格
	 * @return
	 */
	public Integer getCellValueByInteger(HSSFCell cell) {
		
		if (cell == null){
			return null;
		}else{
			//Double cellValue = (Double)cell.getNumericCellValue();
			//return cellValue.intValue();
			try {
				return Integer.parseInt(cell.getRichStringCellValue().getString());
			} catch (Exception ex) {
				return null;
			}
		}
	}
	
	/**
	 * 设置单元格的符点值
	 * @param cell 单元格
	 * @return
	 */
	public Double getCellValueByDouble(HSSFCell cell) {
		
		if (cell == null){
			return null;
		}else{
			//Double cellValue = (Double)cell.getNumericCellValue();
			//return cellValue;
			try{
				return Double.parseDouble(cell.getRichStringCellValue().getString());
			} catch (Exception ex){
				return null;
			}
		}
	}
	
	/**
	 * 设置单元格日期值
	 * @param cell 单元格
	 * @return
	 * @throws ParseException
	 */
	public Date getCellValueByDate(HSSFCell cell) throws ParseException {
		
		if (cell == null){
			return null;
		}else{
			if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
				return null;
			}
			if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				String cellValue = cell.getRichStringCellValue().toString();
				if (cellValue.trim().equals("")) {
					return null;
				} else {
					DateFormat df = DateFormat.getDateInstance();   
					return df.parse(cellValue); 
				}
			} else {
				Date cellValue = cell.getDateCellValue();
				return cellValue;
			}
		}
	}
}
