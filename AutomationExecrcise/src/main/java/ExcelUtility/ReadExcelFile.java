package ExcelUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import GenericRepository.FrameworkConstant;

/**
 * This Class is used to provide the Utility Methods to handle Excel File
 * 
 *   @author Aditya
 **/

public class ReadExcelFile implements FrameworkConstant{
	public FileInputStream fis=null;
	public FileOutputStream fos=null;
	public Workbook wb=null;
	public Sheet sheet=null;

	public String readData(String sheet, int row, int column) throws EncryptedDocumentException, IOException
	{
		//Step 1: Convert physical file to java readable object By using 
		//FileInputStream class
		fis=new FileInputStream(excelpath);


		//Step 2: Open the workbook By creating WB-Factory
		wb=WorkbookFactory.create(fis);


		//Step 3: Fetch the data
		String result=wb.getSheet(sheet).getRow(row).getCell(column).toString();
		String data="";

		if (result.contains(".0")) {
			try {
				data = String.valueOf((int) Double.parseDouble(result));
			} catch (NumberFormatException e) {
				result = result; // in case the string has "." but isn't a valid number
			}
		} else {
			data = result;
		}


		//Strp 4: Close the workbook
		wb.close();
		fis.close();


		return data;
	}

	public void updateData(String sheetName, int rowNum, int columnNum, String data) throws EncryptedDocumentException, IOException 
	{

		//Step 1: Convert physical file to java readable object By using 
		//FileInputStream class
		fis = new FileInputStream(excelpath);

		//Step 2: Open the workbook By creating WB-Factory
		wb = WorkbookFactory.create(fis);

		//Step 3: Update the data in Existing Sheet, Row, new Cell/Column
		wb.getSheet(sheetName).getRow(rowNum).createCell(columnNum).setCellValue(data);

		//Step 4: Convert java data into pgysical file
		fos = new FileOutputStream(excelpath);

		//Step 5: Write the data
		wb.write(fos);


		//Strp 6: Close the workbook
		wb.close();
		fis.close();
		fos.close();

	}


	public void writeData(String sheetName, int rowNum, int cellNum, String data) throws EncryptedDocumentException, IOException 
	{
		//Step 1: Convert physical file to java readable object By using 
		//FileInputStream class
		fis = new FileInputStream(excelpath);

		//2. Create a workbook
		wb = WorkbookFactory.create(fis);

		//3. Write in the new Sheet , new Row, new Cell/Column
		wb.createSheet(sheetName).createRow(rowNum).createCell(cellNum).setCellValue(data);

		//4. Convert java data into pgysical file
		fos = new FileOutputStream(excelpath);

		//5. Write the data
		wb.write(fos);

		//6. Close the workbook
		wb.close();		fis.close();		fos.close();
	}

}
