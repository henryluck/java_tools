
// Template Method ģʽ����

package pattern.c.template_method;

abstract class father {
	abstract void med0();
	
	abstract void med1();
	
	//operationΪһ��ģ�巽��
	public void operation() {
		med0();
		med1();
		// and other logic
	}
}

class child extends father {
	public void med0() {
		System.out.println("the med0 method");
	}
	
	public void med1() {
		System.out.println("the med1 method");
	}
}

public class TemplateMethodPattern {

	public static void main(String[] args) {
	}
}
