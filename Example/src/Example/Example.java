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
		DecimalFormat numberFormat = new DecimalFormat("#,##0.00");//�����ʽ�����
		String formatstr = "";//���巵�ؽ��
//����0 �� С����ֵ1������(����:0.05��תΪ.05) jdk1.10û���������
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
