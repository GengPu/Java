package ReflectExample;

public class ParentExample {

	public void say(String businessinsid,String busstatime ,String businessData){
		System.out.println(businessinsid+"----"+busstatime+"---"+businessData);
		System.out.println("im parent");
	}
	
	public void getMoney(){
		
		System.out.println("parent get Money");
	}
	
	
	public static void main(String[] args) {
		String businessinsid="123", busstatime="2018-06-14", businessData="ABC";
		
		
		ParentExample model = new ChildExample();
		model.say(businessinsid, busstatime, businessData);
//		model.getMoney();
	}
}
