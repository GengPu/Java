package StaticExample;
/**
 * 静态代码块  静态方法  静态变量的练习
 * @author ligen
 *
 */
public class StaticExample {

	static String str = "创建的变量";
	private String privstr = "1";
	
	
	static {
		System.out.println("静态代码块：开始执行");
		String dir = System.getProperty("user.dir");
		String encoding = System.getProperty("file.encoding");
		System.out.println("dir:"+dir);
		System.out.println("encoding:"+encoding);
		System.out.println("静态代码块：结束执行");
	}
	
	public StaticExample() {
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("main方法：开始执行");
		
		System.setProperty("file.encoding", "utf-8");
		
		
		
		System.out.println("main方法：结束执行");
	}
	
	
	public String getPrivstr() {
		System.out.println("输出变量");
		return privstr;
	}

	public void setPrivstr(String privstr) {
		System.out.println("设置变量");
		this.privstr = privstr;
	}


	static String str1 = "创建的变量";
}
