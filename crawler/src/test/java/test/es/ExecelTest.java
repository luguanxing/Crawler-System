package test.es;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class ExecelTest {

	@Test
	public void TestExcel() throws Exception {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("1");
		Row row = sheet.createRow(1);
		Cell cell = row.createCell(1);
		cell.setCellValue("hhh");
        FileOutputStream fileOut=new FileOutputStream("e:\\1.xls");
        wb.write(fileOut);
        fileOut.close();
	}
	
}
