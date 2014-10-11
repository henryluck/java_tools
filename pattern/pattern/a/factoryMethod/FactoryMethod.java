
//FactoryMethodģʽ������ 

package pattern.a.factoryMethod;

interface Product {
  
  void operation();

}

class ConProduct1 implements Product {
  
  //��ͬ��ʵ�ֿ����в�ͬ�Ĳ�Ʒ����
  public void operation() {}

}

class ConProduct2 implements Product {
  
  //��ͬ��ʵ�ֿ����в�ͬ�Ĳ�Ʒ����
  public void operation() {}

}


interface Factory {
  
  Product CreateProduct();

  void operation1();
  
}

class ConFactory1 implements Factory {
  
  //��ͬ��ʵ�峡�� �ڴ�����Ʒ�ǿ����в��ܵĴ�������
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
  
  //��ͬ��ʵ�峡�� �ڴ�����Ʒ�ǿ����в��ܵĴ�������
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




