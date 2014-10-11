
//Decorator ģʽ����

package pattern.b.decorator;

//Դ������������Ĺ�ͬ�ӿ�
interface Component {
    void operation();
}

class ConComponent implements Component {
    public void operation() {
        System.out.println("the begin operation");
    }
}

//û���ṩ��component��ֵ�ķ��� �������������Ϊ����� ���ﲢû�г���ķ������ǲ������������ʵ��
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
        //!!ע������ �����ṩ�˹��ܵ���� 
        // �������Decorator�ĺ��Ĳ��� �����޸Ĺ��ܶ�����ӹ��� ��һ��component����װ������ö���Ľӿ�
        //���� �ڴ˹�����ӹ��� ����ʵ�ֽӿڷ����Ĺ���
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
        //ע�� ���λ���Ǵ�һ��ConComponent��ʼ��!!
        Component component = new ConDecotor2(new ConDecotor1(new ConComponent()));
        component.operation();
    }
}
