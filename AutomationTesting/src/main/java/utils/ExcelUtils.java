package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	static XSSFWorkbook w;
	static XSSFSheet s;
	
	public static void setupExcel(String filePath) throws IOException{
		FileInputStream fs = new FileInputStream(filePath);
		w = new XSSFWorkbook(fs);
		s = w.getSheet("Sheet1");
	}
	
	public static String getCellData(int r, int c) {
		String data = s.getRow(r).getCell(c).toString();
		return data;
	}
	
	public static int getRowCount() {
		int rowCount = s.getPhysicalNumberOfRows();
		return rowCount;
	}
}
