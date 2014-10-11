
//AbstractoryFactory模式的例子 
//我觉得这个模式与FactoryMethod 就是创建产品的数量上有区别

package pattern.a.abstractfactory;

//产品A的接口和实现
interface ProductA {}

class ProductA1 implements ProductA {}

class PorductA2 implements ProductA {}

//产品B的接口和实现
interface ProductB {}

class ProductB1 implements ProductB {}

class ProductB2 implements ProductB {}

//工程和工厂的实现
interface Factory {
    
    ProductA CreateA();
    
    ProductB CreateB();

}

//1工厂产生1系列产品
class ConFactory1 implements Factory {
    
    public ProductA CreateA() {
        return new ProductA1();
    }
    
    public ProductB CreateB() {
        return new ProductB1();
    }
    
}

//2工厂产生2系列产品
class ConFactory2 implements Factory {
    
    public ProductA CreateA() {
        return new PorductA2();
    }
    
    public ProductB CreateB() {
        return new ProductB2();
    }
    
}

class ConFactory {}

public class AbstractFactory {

    public static void main(String[] args) {
        //1工厂产生1类产品
        Factory factory1 = new ConFactory1();
        ProductA a1 = factory1.CreateA();
        ProductB b1 = factory1.CreateB();
        
        //2工厂产生2类产品
        Factory factory2 = new ConFactory1();
        ProductA a2 = factory2.CreateA();
        ProductB b2 = factory2.CreateB();        
    }
}
