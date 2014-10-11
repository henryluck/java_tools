
//Strategy 模式例子
//Strategy中封装了算法

package pattern.c.strategy;

interface Strategy {
    void stMed();
}

class Constrategy1 {
    public void stMed() {}
}

class constrategy2 {
    public void stMed() {}
}

class Context {
    private Strategy strategy;
    
    public Context (Strategy strategy) {
        this.strategy = strategy;
    }
    
    public void ContextInterface() {
        strategy.stMed();
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
    }
}
