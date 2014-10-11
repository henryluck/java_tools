
//Builderģʽ������
// ģʽ����ּ�� ������������ϸ�� �Ͳ�������װ��������!!! ���ֵ�����Ҫ��

package pattern.a.builder;

class director {
    
    //construct �д������װ�������߼� ע�� �߼��ķ��� 
    public Product construct(Builder builder) {
        builder.buildPart1();
        builder.buildPart2();
        operation();
        Product product = builder.retrieveProduct();
        return product;
    }
    
    public void operation() {}
}

class Product {}

interface Builder {
    
    void buildPart1();
    
    void buildPart2();
    
    Product retrieveProduct();
    
}

class ConBuilder1 implements Builder {
    
    public void buildPart1() {}
    
    public void buildPart2() {}
    
    public Product retrieveProduct() {
        return null;
    }
    
}

class ConBuilder2 implements Builder {
    
    public void buildPart1() {}
    
    public void buildPart2() {}
    
    public Product retrieveProduct() {
        return null;
    }    
    
}

public class BuilderPattern {

    public static void main(String[] args) {
    }
    
}
