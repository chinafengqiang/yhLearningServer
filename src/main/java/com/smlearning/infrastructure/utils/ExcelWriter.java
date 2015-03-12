package com.smlearning.infrastructure.utils;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelWriter {
	/**
	 * 实例工作簿
	 */
	private HSSFWorkbook workbook = new HSSFWorkbook();
	
	/**
	 * 定义表格
	 */
	private HSSFSheet sheet;
	
	/**
	 * 构造方法
	 * @param sheetName　表格名
	 */
	public ExcelWriter(String sheetName) {
		
		sheet = workbook.createSheet(sheetName);
	}
	
	/**
	 * 创建单元格
	 * @param rowIndex行的数
	 * @param colIndex单元格行数
	 * @param value设置值
	 * @param style样式
	 * @param width宽度
	 * @return 返回单元格对像
	 */
	public HSSFCell createCell(Integer rowIndex, Integer colIndex, String value, HSSFCellStyle style, Integer width) {
		
		HSSFRow row = sheet.createRow(rowIndex.shortValue());
		HSSFCell cell = row.createCell(colIndex.shortValue());
		
		cell.setCellValue(new HSSFRichTextString(value));
		cell.setCellStyle(style);
		sheet.setColumnWidth(colIndex.shortValue(), width.shortValue());
		return cell;
	}
	
	/**
	 * 创建样式
	 * @param font字体
	 * @param alignment边框
	 * @return　返回样式
	 */
	public HSSFCellStyle createDefaultStyle(HSSFFont font, Short alignment) {
		
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(alignment);
		return style;
	}
	
	/**
	 * 获得工作簿
	 * @return　返回工作簿对像
	 */
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}
}
