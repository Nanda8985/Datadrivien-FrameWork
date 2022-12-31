package utilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

public class ExcelFileUtiles {
XSSFWorkbook wb;
//constructor for reading path
public ExcelFileUtiles(String excelpath) throws Throwable
{
	FileInputStream fi = new FileInputStream(excelpath);
   wb = new XSSFWorkbook(fi);
}
//count no of rows in a sheet
 public int rowCount(String sheetname)
 {
	 return wb.getSheet(sheetname).getLastRowNum();
 }
 //get cell type data
 public String getCellData(String sheetname,int row,int collumn)
 {
	 String data = "";
	 if(wb.getSheet(sheetname).getRow(row).getCell(collumn).getCellType()==Cell.CELL_TYPE_NUMERIC)
	 {
	  int celldata = (int) wb.getSheet(sheetname).getRow(row).getCell(collumn).getNumericCellValue();
	  data = String.valueOf(celldata);
	 }
	 else
	 {
		 data = wb.getSheet(sheetname).getRow(row).getCell(collumn).getStringCellValue();
		 
	 }
	return data;
 }
 //method for set cell data
 public void setCellData(String sheetname,int row,int column,String status,String writeexcel) throws Throwable
 {
	 //get sheet from wb
	 XSSFSheet ws =wb.getSheet(sheetname);
	 //get row from sheet
	 XSSFRow rownum = ws.getRow(row);
	 //create cell in row
	 XSSFCell cell =rownum.createCell(column);
	 //write status 
	 cell.setCellValue(status);
	 if(status.equalsIgnoreCase("pass"))
	 {
		 XSSFCellStyle style = wb.createCellStyle();
		 XSSFFont font = wb.createFont();
		 font.setColor(IndexedColors.GREEN.getIndex());
		 font.setBold(true);
		 font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		 style.setFont(font);
		 rownum.getCell(column).setCellStyle(style);
		  
	 }
	 else if(status.equalsIgnoreCase("fail"))
	 {
		 XSSFCellStyle style = wb.createCellStyle();
		 XSSFFont font = wb.createFont();
		 font.setColor(IndexedColors.RED.getIndex());
		 font.setBold(true);
		 font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		 style.setFont(font);
		 rownum.getCell(column).setCellStyle(style);
	 }
	 else if(status.equalsIgnoreCase("blocked"))
	 {
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(column).setCellStyle(style);
	 }
	 FileOutputStream fo = new FileOutputStream(writeexcel);
	 wb.write(fo);
 }
	public static void main(String[] args) throws Throwable {
	ExcelFileUtiles xl = new ExcelFileUtiles("E:/sample1.xlsx");
	//count no of rows

	int rc = xl.rowCount("EmpData");
	System.out.println(rc);
	for(int i=1;i<=rc;i++)
	{
		String fname = xl.getCellData("EmpData", i, 0);
		String mname = xl.getCellData("EmpData", i, 1);
		String lname = xl.getCellData("EmpData", i, 2);
		String eid = xl.getCellData("EmpData", i, 3);
		System.out.println(fname+"  "+mname+"  "+lname+"  "+eid);
		//xl.setCellData("EmpData", i, 4, "pass", "E://result2.xlsx");
		//xl.setCellData("EmpData", i, 4, "fail", "E://result2.xlsx");
		xl.setCellData("EmpData", i, 4, "blocked", "E://result2.xlsx");
		
	}
			
	}

}
