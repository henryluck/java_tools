
//Proxy模式例子

package pattern.b.proxy;

interface Subject {
	
	void request();
	
}

//真正处理请求的地方
class RealSubject implements Subject {
	
	public void request() {
		
		System.out.println("real access");
		
	}
	
}

//ProxySubject是与用户交互的类 他是REalSubject的代理
//在处理功能之上的一层  这里可以做前操作 后操作 例如可以验证是否处理请求。
class ProxySubject implements Subject {
	
	private RealSubject real;
	
	public ProxySubject(RealSubject real) {
		this.real = real;
	}
	
	//
	public void request() {
		
		preRequest();
		real.request();
		afterRequest();
		
	}
	
	private void preRequest() {}
	
	private void afterRequest() {}
	
}


//java自身提供代理类使用反射机制

public class ProxyPattern {

	public static void main(String[] args) {
	}

}
