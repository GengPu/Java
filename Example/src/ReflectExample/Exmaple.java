package ReflectExample;

public class Exmaple {

	public ReflectServiceImpl getInstance() {
		ReflectServiceImpl object = null;
			try {
				object = (ReflectServiceImpl) Class.forName("ReflectExample.ReflectServiceImpl").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return object;
	}
	public static void main(String[] args) {
		try {
			int i = 0/1;
		} finally {
			// TODO: handle finally clause
		}
	}
}
