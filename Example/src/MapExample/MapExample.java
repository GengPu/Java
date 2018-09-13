package MapExample;

import java.util.HashMap;
import java.util.Map;

import Example.DomainExample;

public class MapExample {

	public void setValue(Map<String, String> map){
		map.put("b", "b");
	}
	
	public static void staticsetValue(Map<String, String> map){
		map.put("c", "c");
	}
	
	public void setValue(DomainExample exp){
		exp.setB("b");
	}
	
	public static void staticsetValue(DomainExample exp){
		exp.setC("c");
	}
	
	public static void main(String[] args) {
		MapExample exp = new MapExample();
		Map<String,String> map = new HashMap<>();
		map.put("a", "a");
		System.out.println(map.toString());
		exp.setValue(map);
		System.out.println(map.toString());
		MapExample.staticsetValue(map);
		System.out.println(map.toString());

	}
}
