package Example;
/** 
* @author li E-mail: virgil_lgp@163.com (mine) | li_gpu@sundatasoft.com (compeny)
* @version ����ʱ�䣺2018��12��21�� ����4:48:16 
* ��˵�� String һЩ�����������Լ��෽������
*/
public class StringExample {

	public static void main(String[] args) {
		String str ="123456�����ligengpu";
		
		String str2 = str.substring(0, 8);
		
		// 123@|@123123
		
		StringBuilder sb = new StringBuilder("123456�����ligengpu");
		sb.setLength(8);
		StringBuffer sbu = new StringBuffer();
		sbu.setLength(0);
		
		if ("" == "0") {
			
		}
		System.out.println(str2);
		System.out.println(sb);
	}
}
