
//Facade ������
//����ϵͳ�Ͻ�����һ�� ����ʹ�øо��Ƚϼ� Facade�ķ����з�װ����ϵͳ�������߼�

package pattern.b.facade;

class Exa1 {
    public void med0() {}
}

class Exa2 {
    public void themed() {}
}

//�����һ���򵥵�Facade�ķ���������
class Facade {
    public void facdeMed() {
        Exa1 exa1 = new Exa1();
        exa1.med0();
        
        Exa2 exa2 = new Exa2();
        exa2.themed();
    }
}

public class FacadePattern {
    public static void main(String[] args) {
    }
}
