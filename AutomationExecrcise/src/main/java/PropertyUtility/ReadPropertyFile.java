package PropertyUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
	
	public String readData(String data) throws IOException
	{
		
		//1. Convert physical file into java understandable
		FileInputStream file=new FileInputStream("./src/test/resources/CommonData.properties");
		
		//2. Create and object ref veriable for properties file
		Properties prop=new Properties();
		
		//3. Load the properties or data into load()
		prop.load(file);

		//4. Fetch the properties data
		if(data.equals("url"))
		{
			data=prop.getProperty("url");
		}
		if(data.equals("homePageTitle"))
		{
			data=prop.getProperty("homePageTitle");
		}
		if(data.equals("login_email"))
		{
			data=prop.getProperty("login_email");
		}
		if(data.equals("password"))
		{
			data=prop.getProperty("password");
		}
		
		return data;
		
	}
	
	
	public void writeData() throws IOException
	{
		
		//1. Convert physical file into java understandable
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		
		//2. Create and object ref veriable for properties file
		Properties prop=new Properties();
		
		//3. Load the properties or data into load()
		prop.load(fis);

		//4. Write the data using put()
		prop.put("batchCode", "SeleniumM13");
		prop.put("Contact", "9876543210");
		
		//5. Convert java data into physical file
		FileOutputStream fos=new FileOutputStream("./src/test/resources/CommonData.properties");
		
		//6. Store the data in properties file
		prop.store(fos, "Updated successfull");
		
	}

}
