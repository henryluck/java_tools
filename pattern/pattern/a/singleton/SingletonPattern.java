
//Singletonģʽ����

package pattern.a.singleton;

// һ�ּ򵥵���ʽ
class SingletonExample {
	
	private static SingletonExample instance;
	
	private SingletonExample() {}
	
	public static SingletonExample getInstance() {
		
		if (instance == null) {
			instance = new SingletonExample();
		}
		return instance;
	}		
	
	synchronized public static SingletonExample getInstance1() {
		
		if (instance == null) {
			instance = new SingletonExample();
		}
		return instance;
		
	}	
	
	public static SingletonExample getInstance2() {
		
		synchronized(SingletonExample.class) {
			if (instance == null) {
				instance = new SingletonExample();
			}
		}
		return instance;
		
	}
}

//���������ʱ ��ʼ��ֻ����һ��
class SingletonExample2 {
	
	private static SingletonExample2 instance = new SingletonExample2();
	
	private SingletonExample2() {}
	
	public static SingletonExample2 getInstance() {
		
		return instance;
		
	}
}

public class SingletonPattern {

	public static void main(String[] args) {
	}
}
