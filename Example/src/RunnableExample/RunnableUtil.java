package RunnableExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �����ֳɣ�ҵ���̣߳�
 * @author ligen
 *
 */
public class RunnableUtil implements Runnable{

	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	public RunnableUtil() {
		// TODO ����list�е����ݣ����߳�����Ψһ
		Map<String,String> map = new HashMap<String ,String>();
		map.put("001", "���ǵ�һ������");
		list.add(map);
		map.put("002", "���ǵڶ�������");
		list.add(map);
		map.put("003", "���ǵ���������");
		list.add(map);
		map.put("004", "���ǵ���������");
		list.add(map);
		map.put("005", "���ǵ���������");
		list.add(map);
		map.put("006", "���ǵ���������");
		list.add(map);
		map.put("007", "���ǵ���������");
		list.add(map);
		map.put("008", "���ǵڰ�������");
		list.add(map);
		map.put("009", "���ǵھ�������");
		list.add(map);
		map.put("010", "���ǵ�ʮ������");
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
