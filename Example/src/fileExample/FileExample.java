package fileExample;

import java.io.File;

public class FileExample {

	public static void main(String[] args) {
		File file = new File("E:\\Example\\");
		String[] filename = file.list();
		for (int i = 0; i < filename.length; i++) {
			System.out.println(filename[i]);
		}
	}
}
