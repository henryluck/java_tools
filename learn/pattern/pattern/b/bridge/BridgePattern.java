
// Bridge ģʽ����
//��һ���Ĳ��� �ĳ�����һ������ �����������һ�������н��� �������ڹ�����չ

package pattern.b.bridge;

interface Implementor {
    void realOperation();
}

class ConImplementor1 implements Implementor {
    public void realOperation() {
        System.out.println("do the real operation1");
    }
}

class ConImplementor2 implements Implementor {
    public void realOperation() {
        System.out.println("do the real operation2");
    }
}

abstract class Abstraction {
    protected Implementor imp;
    
    public Abstraction(Implementor imp) {
        this.imp = imp;
    }
    
    protected void med0() {}
    
    abstract public void operation();
}

class ConAbstraction extends Abstraction{
    public ConAbstraction(Implementor imp) {
        super(imp);
    }
    
    public void operation() {
        med0();
        imp.realOperation();
    }
}

public class BridgePattern {
    public static void main(String[] args) {
        Implementor imp = new ConImplementor1();
        Abstraction abs = new ConAbstraction(imp);
        abs.operation();
    }
}
