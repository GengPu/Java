package StaticExample;

public class MainExample {

	public static void main(String[] args) {
		System.out.println("��ʼִ��MainExample��main");
		StaticExample e = new StaticExample();
		e.setPrivstr("111111");
		System.out.println("-------------"+e.getPrivstr());
	}
}
