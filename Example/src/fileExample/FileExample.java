package fileExample;

import java.io.File;

public class FileExample {

	public static void main(String[] args) {
		File file = new File("E:\\˴������\\����2016-12-12\\������\\������\\ϵͳ����ά���׶�");
		String[] filename = file.list();
		for (int i = 0; i < filename.length; i++) {
			System.out.println(filename[i]);
		}
	}
}
