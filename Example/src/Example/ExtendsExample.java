package Example;

public class ExtendsExample extends Example{

	 void go(){
		System.out.println("ExtendsExample");
	}
	public static void main(String[] args) {
		Example a = new Example();
		Example b = new ExtendsExample();
		a.getGo();
		b.getGo();
	}
}
