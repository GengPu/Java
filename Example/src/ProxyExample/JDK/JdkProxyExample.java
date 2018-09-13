package ProxyExample.JDK;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyExample implements InvocationHandler{

	private Object target = null;//真实对象、
	
	public Object bind(Object target){
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("代理方法");
		System.out.println("调度真实对象之前的服务");
		Object obj = method.invoke(target, args);
		//通过调度找到代理之后的对象
		return obj;
	}

}
