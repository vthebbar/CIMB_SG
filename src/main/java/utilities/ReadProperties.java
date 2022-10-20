package utilities;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class ReadProperties {
	
	static Properties prop = new Properties();
	
	public static String get_property_value_by_key(String key) {
		
	
		//Load file
		String propFilePath= System.getProperty("user.dir")+"/src/test/resources/config.properties";
		
		try {
		FileInputStream fis = new FileInputStream(propFilePath);
		prop.load(fis);
		}
		catch(Exception e) {
			System.out.println("Exception in loading properties file:"+ e);
		}
		
		// Read data
		String value = prop.getProperty(key).toString();
		if(StringUtils.isEmpty(value)) {
			System.out.println("Value not specified in properties file for key:" + key);
		}
		
		return value;
	}
}
