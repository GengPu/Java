package StaticExample;
/**
 * ��̬�����  ��̬����  ��̬��������ϰ
 * @author ligen
 *
 */
public class StaticExample {

	static String str = "�����ı���";
	private String privstr = "1";
	
	
	static {
		System.out.println("��̬����飺��ʼִ��");
		String dir = System.getProperty("user.dir");
		String encoding = System.getProperty("file.encoding");
		System.out.println("dir:"+dir);
		System.out.println("encoding:"+encoding);
		System.out.println("��̬����飺����ִ��");
	}
	
	public StaticExample() {
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("main��������ʼִ��");
		
		System.setProperty("file.encoding", "utf-8");
		
		
		
		System.out.println("main����������ִ��");
	}
	
	
	public String getPrivstr() {
		System.out.println("�������");
		return privstr;
	}

	public void setPrivstr(String privstr) {
		System.out.println("���ñ���");
		this.privstr = privstr;
	}


	static String str1 = "�����ı���";
}
