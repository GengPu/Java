package fileExample;

import java.io.File;

public class FileExample {

	public static void main(String[] args) {
		File file = new File("E:\\舜德数据\\威海2016-12-12\\需求变更\\需求变更\\系统生产维护阶段");
		String[] filename = file.list();
		for (int i = 0; i < filename.length; i++) {
			System.out.println(filename[i]);
		}
	}
}
