package JsonExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class JsonExample {

	/**
	 *	У�����ǰ̨��������json��ת���ɵ�json����
	 * @param json
	 * @return
	 */
	public static JSONObject checkAndPutNull(JSONObject json){
	
		Set<String> keys = json.keySet();
		List<Object> list = new ArrayList<Object>();
		String str = "";
		for (String key : keys) {
			if(("attr".equals(key) || "prop".equals(key)) /* ������Ҫ�������key�������������key ���磺"a":"123" */ && json.get(key) == null) {
				json.put(key, str);
			}else if("list".equals(key) /* ������Ҫ�������key���������� ���磺"b:[{"a":"123"},{{"a":"123"}] */ && json.get(key) == null) {
				json.put(key, list);
			}
		}
		return json;
	}
	
	public static void main(String[] args) {
		
		String jsonstr = "{\"attr\":null , \"list\":null , \"prop\":\"123\"}";
		
		JSONObject json = JSONObject.parseObject(jsonstr);
		System.out.println("ת��ǰ��\t"+json.toString());
		json = checkAndPutNull(json);
		System.out.println("ת����\t"+json.toString());

		
	}
}
