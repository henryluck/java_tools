
//Composite 模式例子
//在用户的角度 并不知道 不见是单独的还是符合的 只要调用接口级方法 operation 

package pattern.b.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 安全模式 在接口级只提供部分功能 leaf 和 composite 有不同的功能
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

// 透明模式 接口定义全部功能 leaf中可能使用空方法或抛出异常 活着写一层 抽象类 写上默认实现方法(极端情况下 是空或异常)


interface Component2 {
    
    public void operation();
    
    public void addComponent(Component2 com);
    
    public void removeComponent(int index);
    
}

class leaf2 implements Component2 {
    
    public void operation() {
        System.out.println("this is the leaf1");
    }
    
    //这个使用空方法
    public void addComponent(Component2 com) {}
    
    //这个使用不支持异常
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

//也可以用一个抽象类

abstract class AbstractComponent implements Component2{
    public void operation() {}
    
    public void addComponent(Component2 com) {}
    
    abstract public void removeComponent(int index);    
}

public class CompositePattern {

    public static void main(String[] args) {
    }
    
}
