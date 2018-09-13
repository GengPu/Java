package RunnableExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具现成（业务线程）
 * @author ligen
 *
 */
public class RunnableUtil implements Runnable{

	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	public RunnableUtil() {
		// TODO 补充list中的数据，主线程数据唯一
		Map<String,String> map = new HashMap<String ,String>();
		map.put("001", "这是第一条数据");
		list.add(map);
		map.put("002", "这是第二条数据");
		list.add(map);
		map.put("003", "这是第三条数据");
		list.add(map);
		map.put("004", "这是第四条数据");
		list.add(map);
		map.put("005", "这是第五条数据");
		list.add(map);
		map.put("006", "这是第六条数据");
		list.add(map);
		map.put("007", "这是第七条数据");
		list.add(map);
		map.put("008", "这是第八条数据");
		list.add(map);
		map.put("009", "这是第九条数据");
		list.add(map);
		map.put("010", "这是第十条数据");
		list.add(map);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
