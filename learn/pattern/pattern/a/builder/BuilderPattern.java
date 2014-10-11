
//Builder模式的例子
// 模式的宗旨是 将部件创建的细节 和部件的组装方法分离!!! 考兄弟悟性要高

package pattern.a.builder;

class director {
    
    //construct 中存放着组装不见的逻辑 注意 逻辑的分离 
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
