package DataReadUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import GenericRepo.FileLocations;

public class ReadPropertyFile implements FileLocations {
	
	public String readData(String data) throws IOException
	{
		
		FileInputStream fis=new FileInputStream(propertyPath);
		
		Properties prop=new Properties();
		
		prop.load(fis);
		
		if(data.equals("url"))
		{
			data=prop.getProperty("url");
		}
		if(data.equals("username"))
		{
			data=prop.getProperty("username");
		}
		if(data.equals("password"))
		{
			data=prop.getProperty("password");
		}
		
		return data;
		
		
	}

}
