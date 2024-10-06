package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtils {
	
	XSSFWorkbook wb;

	public ExcelFileUtils(String excelpath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelpath);
		wb = new XSSFWorkbook(fi);
	}
	
	public int rowCount(String SheetName)
	{
		int rc = wb.getSheet(SheetName).getLastRowNum();
		return rc;
	}
	
	public String getCellData(String sheetname, int row, int col)
	{
		String data = "";
		
		if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType() == CellType.NUMERIC)
		{
			int cellData = (int) wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
			data = String.valueOf(cellData);
		}
		else
		{
			data = wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}
	
	public void setCellData(String sheetname, int row, int col, String status, String outputpath) throws Throwable
	{
		wb.getSheet(sheetname).getRow(row).createCell(col).setCellValue(status);
		
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setBold(true);
		
		if(status.equalsIgnoreCase("Pass"))
			font.setColor(IndexedColors.GREEN.getIndex());
		else if(status.equalsIgnoreCase("Fail"))
			font.setColor(IndexedColors.RED.getIndex());
		else if(status.equalsIgnoreCase("Blocked"))
			font.setColor(IndexedColors.BLUE.getIndex());
		
		style.setFont(font);
		wb.getSheet(sheetname).getRow(row).getCell(col).setCellStyle(style);	
		
		FileOutputStream fo = new FileOutputStream(outputpath);
		wb.write(fo);
	}
}
