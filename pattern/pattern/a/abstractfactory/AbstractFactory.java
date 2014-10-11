
//AbstractoryFactoryģʽ������ 
//�Ҿ������ģʽ��FactoryMethod ���Ǵ�����Ʒ��������������

package pattern.a.abstractfactory;

//��ƷA�Ľӿں�ʵ��
interface ProductA {}

class ProductA1 implements ProductA {}

class PorductA2 implements ProductA {}

//��ƷB�Ľӿں�ʵ��
interface ProductB {}

class ProductB1 implements ProductB {}

class ProductB2 implements ProductB {}

//���̺͹�����ʵ��
interface Factory {
    
    ProductA CreateA();
    
    ProductB CreateB();

}

//1��������1ϵ�в�Ʒ
class ConFactory1 implements Factory {
    
    public ProductA CreateA() {
        return new ProductA1();
    }
    
    public ProductB CreateB() {
        return new ProductB1();
    }
    
}

//2��������2ϵ�в�Ʒ
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
        //1��������1���Ʒ
        Factory factory1 = new ConFactory1();
        ProductA a1 = factory1.CreateA();
        ProductB b1 = factory1.CreateB();
        
        //2��������2���Ʒ
        Factory factory2 = new ConFactory1();
        ProductA a2 = factory2.CreateA();
        ProductB b2 = factory2.CreateB();        
    }
}
