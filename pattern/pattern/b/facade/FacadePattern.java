
//Facade 的例子
//在子系统上建立了一层 对外使用感觉比较简单 Facade的方法中封装与子系统交互的逻辑

package pattern.b.facade;

class Exa1 {
    public void med0() {}
}

class Exa2 {
    public void themed() {}
}

//这就是一个简单的Facade的方法的例子
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
