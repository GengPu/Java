package Example;

import java.text.DecimalFormat;

public class Example {
	public static void main(String[] args) {
//		MapExample exp = new MapExample();
//		Map<String,String> map = new HashMap<>();
//		map.put("a", "a");
//		System.out.println(map.toString());
//		exp.setValue(map);
//		System.out.println(map.toString());
//		MapExample.staticsetValue(map);
//		System.out.println(map.toString());
//
//		DomainExample dexp = new DomainExample();
//		dexp.setA("a");
//		System.out.println(dexp.toString());
//		exp.setValue(dexp);
//		System.out.println(dexp.toString());
//		MapExample.staticsetValue(dexp);
//		System.out.println(dexp.toString());
		
		Double FSalePrice = null;
		DecimalFormat numberFormat = new DecimalFormat("#,##0.00");//定义格式化输出
		String formatstr = "";//定义返回结果
//处理0 和 小于数值1的问题(例如:0.05会转为.05) jdk1.10没有这个问题
//		if(FSalePrice.compareTo(Double.valueOf(1)) < 0){
//		    numberFormat = new DecimalFormat("#,##0.00");
//		}
//		formatstr = FSalePrice.compareTo(Double.valueOf(0)) == 0?"0.00":numberFormat.format(FSalePrice == null ? 0.0:FSalePrice);
		formatstr = numberFormat.format(FSalePrice == null ? 0.0:FSalePrice);
		System.out.println(formatstr);
	}
	
	
	
	 void go(){
		System.out.println("Example");
	}
	void getGo(){
		go();
	}
}
