
//Adapter模式的例子

package pattern.b.Adapter;

// 类适配
interface Target1 {
	
	void request();
	
}

class Adaptee1 {
	
    public void specRequest() {}

}

class Adapter1 extends Adaptee1 implements Target1 {

	public void request() {
		super.specRequest();
	}

}

//对象适配

interface Target2 {
	
	void request();

}

class Adaptee2 {

    public void specRequest() {}

}

class Adapter2 implements Target2 {

	private Adaptee2 adaptee;
	
	public Adapter2(Adaptee2 adaptee) {
		this.adaptee = adaptee;
	}
	
	public void request() {
    	adaptee.specRequest();
	}

}

public class AdapterPattern {

	public static void main(String[] args) {
	}
	
}
