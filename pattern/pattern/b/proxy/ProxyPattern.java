
//Proxyģʽ����

package pattern.b.proxy;

interface Subject {
	
	void request();
	
}

//������������ĵط�
class RealSubject implements Subject {
	
	public void request() {
		
		System.out.println("real access");
		
	}
	
}

//ProxySubject�����û��������� ����REalSubject�Ĵ���
//�ڴ�����֮�ϵ�һ��  ���������ǰ���� ����� ���������֤�Ƿ�������
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


//java�����ṩ������ʹ�÷������

public class ProxyPattern {

	public static void main(String[] args) {
	}

}
