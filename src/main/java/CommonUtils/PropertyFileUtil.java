package CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {

		
		public String getDataFromPropertyFile(String key) throws IOException {
				FileInputStream file1=new FileInputStream("src\\test\\resources\\Leads.properties.txt");
				Properties p= new Properties();
				p.load(file1);
				String value = p.getProperty(key);
				return value;
	}

}
