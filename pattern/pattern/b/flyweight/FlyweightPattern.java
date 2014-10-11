
//Flywight 模式例子
// 将共同的跟不同的属性分离  共享共同属性 而不同属性由外部传入 进行特定操作

package pattern.b.flyweight;
import java.util.HashMap;
import java.util.Map;

// 1没有办法通过obj.value方式 2没有改变属性的方法 3不能通过继承重置属性 所以这个对象是 immutable 不可变的
final class State {
    private String value = null;
    
    public State(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}

interface Flyweight {
    void operation(String extrinsicState);
}

class ConFlyweight1 implements Flyweight {
    //state 表示享元的内部状态
    //或者用构造函数传入
    private State state = new State("state1");
    
    //out 为外部传给享元的 外部状态
    public void operation(String out) {
        //使用外部状态和内部状态来执行 operation操作
        System.out.println("ConFlyweight1: " + out + state.getValue());
    }
}

// 充数的
class ConFlyweight2 implements Flyweight {
    private State state = new State("state2");
    
    public void operation(String out) {
        System.out.println("ConFlyweight2: " + state.getValue() + out );
    }
}

class FlyweightFactory {
    Map flyweights = new HashMap();
    
    public Flyweight getFlyweight(String key) {
        if (flyweights.containsKey(key)) {
            return (Flyweight) flyweights.get(key);
        } else {
            // 这里就随便写的
            Flyweight flyweight = null;
            if (key.charAt(key.length() - 1) == '1') {
                flyweight = new ConFlyweight1();
                flyweights.put(key, flyweight);
            } else {
                flyweight = new ConFlyweight2();
                flyweights.put(key, flyweight);
            }
            return flyweight;
        }
    }
}

public class FlyweightPattern {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1a = factory.getFlyweight("flytest1");
        flyweight1a.operation("outparam1");
    }
}
