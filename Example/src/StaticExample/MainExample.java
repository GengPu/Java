package StaticExample;

public class MainExample {

	public static void main(String[] args) {
		System.out.println("开始执行MainExample的main");
		StaticExample e = new StaticExample();
		e.setPrivstr("111111");
		System.out.println("-------------"+e.getPrivstr());
	}
}
