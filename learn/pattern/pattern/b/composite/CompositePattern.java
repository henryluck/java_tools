
//Composite ģʽ����
//���û��ĽǶ� ����֪�� �����ǵ����Ļ��Ƿ��ϵ� ֻҪ���ýӿڼ����� operation 

package pattern.b.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// ��ȫģʽ �ڽӿڼ�ֻ�ṩ���ֹ��� leaf �� composite �в�ͬ�Ĺ���
interface Component1 {
    
    public void operation();
    
}

class leaf1 implements Component1 {
    
    public void operation() {
        System.out.println("this is the leaf1");
    }
    
}

class Composite1 implements Component1 {
    private List components;
    
    public Composite1() {
        components = new ArrayList();
    }
    
    public void operation() {
        Iterator it = components.iterator();
        Component1 com = null;
        while (it.hasNext()) {
            com = (Component1) it.next();
            com.operation();
        }
    }
    
    public void addComponent(Component1 com) {
        components.add(com);
    }
    
    public void removeComponent(int index) {
        components.remove(index);
    }
    
}

// ͸��ģʽ �ӿڶ���ȫ������ leaf�п���ʹ�ÿշ������׳��쳣 ����дһ�� ������ д��Ĭ��ʵ�ַ���(��������� �ǿջ��쳣)


interface Component2 {
    
    public void operation();
    
    public void addComponent(Component2 com);
    
    public void removeComponent(int index);
    
}

class leaf2 implements Component2 {
    
    public void operation() {
        System.out.println("this is the leaf1");
    }
    
    //���ʹ�ÿշ���
    public void addComponent(Component2 com) {}
    
    //���ʹ�ò�֧���쳣
    public void removeComponent(int index){
        throw new UnsupportedOperationException();
    }
}

class Composite2 implements Component2 {
    private List components;
    
    public Composite2() {
        components = new ArrayList();
    }
    
    public void operation() {
        Iterator it = components.iterator();
        Component2 com = null;
        while (it.hasNext()) {
            com = (Component2) it.next();
            com.operation();
        }
    }
    
    public void addComponent(Component2 com) {
        components.add(com);
    }
    
    public void removeComponent(int index) {
        components.remove(index);
    }
    
}

//Ҳ������һ��������

abstract class AbstractComponent implements Component2{
    public void operation() {}
    
    public void addComponent(Component2 com) {}
    
    abstract public void removeComponent(int index);    
}

public class CompositePattern {

    public static void main(String[] args) {
    }
    
}
