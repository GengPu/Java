package Example;
/** 
* @author li E-mail: virgil_lgp@163.com (mine) | li_gpu@sundatasoft.com (compeny)
* @version 创建时间：2018年12月21日 下午4:48:16 
* 类说明 String 一些方法的运用以及多方法处理
*/
public class StringExample {

	public static void main(String[] args) {
		String str ="123456李庚谱ligengpu";
		
		String str2 = str.substring(0, 8);
		
		// 123@|@123123
		
		StringBuilder sb = new StringBuilder("123456李庚谱ligengpu");
		sb.setLength(8);
		StringBuffer sbu = new StringBuffer();
		sbu.setLength(0);
		
		if ("" == "0") {
			
		}
		System.out.println(str2);
		System.out.println(sb);
	}
}
