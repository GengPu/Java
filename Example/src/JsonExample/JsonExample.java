package JsonExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class JsonExample {

	/**
	 *	校验根据前台传过来得json串转化成的json对象
	 * @param json
	 * @return
	 */
	public static JSONObject checkAndPutNull(JSONObject json){
	
		Set<String> keys = json.keySet();
		List<Object> list = new ArrayList<Object>();
		String str = "";
		for (String key : keys) {
			if(("attr".equals(key) || "prop".equals(key)) /* 这里需要控制这个key不是那种数组的key 比如："a":"123" */ && json.get(key) == null) {
				json.put(key, str);
			}else if("list".equals(key) /* 这里需要控制这个key是那种数组 比如："b:[{"a":"123"},{{"a":"123"}] */ && json.get(key) == null) {
				json.put(key, list);
			}
		}
		return json;
	}
	
	public static void main(String[] args) {
		
		String jsonstr = "{\"attr\":null , \"list\":null , \"prop\":\"123\"}";
		
		JSONObject json = JSONObject.parseObject(jsonstr);
		System.out.println("转花前：\t"+json.toString());
		json = checkAndPutNull(json);
		System.out.println("转化后：\t"+json.toString());

		
	}
}
