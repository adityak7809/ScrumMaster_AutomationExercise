package DataReadUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import GenericRepo.FileLocations;

public class ReadExcelFile implements FileLocations {
	
	public String readData(String sheet, int row, int column) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(excelPath);
		
		Workbook book= WorkbookFactory.create(fis);
		
		String result=book.getSheet(sheet).getRow(row).getCell(column).toString();
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
		
		book.close();
		fis.close();
		
		return data;
	}

}
