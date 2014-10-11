
//Decorator 模式例子

package pattern.b.decorator;

//源部件和修饰类的共同接口
interface Component {
    void operation();
}

class ConComponent implements Component {
    public void operation() {
        System.out.println("the begin operation");
    }
}

//没有提供给component赋值的方法 所以声明这个类为抽象的 这里并没有抽象的方法就是不像让这个类有实例
abstract class Decotor implements Component{
    private Component component;
    
    public void operation() {
        component.operation();
    }
}

class ConDecotor1 extends Decotor {
    private Component component;
    
    public ConDecotor1(Component component) {
        this.component = component;
    }    
    
    public void operation() {
        super.operation();
        //!!注意这里 这里提供了功能的添加 
        // 这里就是Decorator的核心部分 不是修改功能而是添加功能 将一个component传入装饰类调用对象的接口
        //方法 在此过程添加功能 重新实现接口方法的功能
        med0();
    }
    
    private void med0() {
        System.out.println("1");
    }
}

class ConDecotor2 extends Decotor {
    private Component component;
    
    public ConDecotor2(Component component) {
        this.component = component;
    }    
    
    public void operation() {
        super.operation();
        med0();
    }
    
    private void med0() {
        System.out.println("2");
    }
}

//class  

public class DecoratorPattern {
    public static void main(String[] args) {
        //注意 起点位置是从一个ConComponent开始的!!
        Component component = new ConDecotor2(new ConDecotor1(new ConComponent()));
        component.operation();
    }
}
