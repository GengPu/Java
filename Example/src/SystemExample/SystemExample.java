package SystemExample;

import java.util.Properties;

public class SystemExample {

	public static void main(String[] args) {
		Properties p = System.getProperties();
		p.list(System.out);
		
		System.out.println(System.getProperty("file.encoding"));
//		System.getProperty();
	}
	public void printAllProperty() {
		
		
	}
}
