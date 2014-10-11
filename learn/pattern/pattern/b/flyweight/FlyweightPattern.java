
//Flywight ģʽ����
// ����ͬ�ĸ���ͬ�����Է���  ����ͬ���� ����ͬ�������ⲿ���� �����ض�����

package pattern.b.flyweight;
import java.util.HashMap;
import java.util.Map;

// 1û�а취ͨ��obj.value��ʽ 2û�иı����Եķ��� 3����ͨ���̳��������� ������������� immutable ���ɱ��
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
    //state ��ʾ��Ԫ���ڲ�״̬
    //�����ù��캯������
    private State state = new State("state1");
    
    //out Ϊ�ⲿ������Ԫ�� �ⲿ״̬
    public void operation(String out) {
        //ʹ���ⲿ״̬���ڲ�״̬��ִ�� operation����
        System.out.println("ConFlyweight1: " + out + state.getValue());
    }
}

// ������
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
            // ��������д��
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
