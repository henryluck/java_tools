
//FactoryMethod模式的例子 

package pattern.a.factoryMethod;

interface Product {
  
  void operation();

}

class ConProduct1 implements Product {
  
  //不同的实现可以有不同的产品操作
  public void operation() {}

}

class ConProduct2 implements Product {
  
  //不同的实现可以有不同的产品操作
  public void operation() {}

}


interface Factory {
  
  Product CreateProduct();

  void operation1();
  
}

class ConFactory1 implements Factory {
  
  //不同的实体场类 在创建产品是可以有不能的创建方法
  public Product CreateProduct() {
      Product product = new ConProduct1();
      operation1();
      return product;
  }
  
  public void operation1() {}

  public static Factory getFactory() {
      return new ConFactory1();
  }
}

class ConFactory2 implements Factory {
  
  //不同的实体场类 在创建产品是可以有不能的创建方法
  public Product CreateProduct() {
      Product product = new ConProduct2();
      operation1();
      return product;
  }
  
  public void operation1() {}

  //
  public static Factory getFactory() {
      return new ConFactory2();
  }
}

public class FactoryMethod {
  public static void main(String[] args) {
      Factory factory = ConFactory1.getFactory();
      Product product = factory.CreateProduct();
      product.operation();
  }
}




