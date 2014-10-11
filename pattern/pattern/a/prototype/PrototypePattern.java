
//PrototypeÄ£Ê½Àý×Ó

package pattern.a.prototype;

interface Prototype {
    Prototype myclone();
}

class ConPrototype1 implements Prototype{
    private String a;
    
    public ConPrototype1(String a) {
        this.a = a;
    }
    
    public Prototype myclone() {
        return new ConPrototype1(a);
    }
}

class ConPrototype2 implements Prototype{
    private int b;
    
    public ConPrototype2(int b) {
        this.b = b;
    }
    
    public Prototype myclone() {
        return new ConPrototype2(b);
    }
}

public class PrototypePattern {
    public static void main(String[] args) {
        Prototype inst1 = new ConPrototype1("testStr1");
        Prototype inst2 = null;
        inst2 = inst1.myclone();
    }
}
