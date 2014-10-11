
//Command 模式例子
//感觉将一个类的方法 分散开了 抽象成了接口方法 注意 参数和返回值要一致
//每个Command封装一个命令 也就是一种操作

package pattern.c.command;

//这个是逻辑真正实现的位置
class Receiver {
    public void med1() {}
    
    public void med2() {}
}

interface Command {
    // 要抽象成统一接口方法 是有局限性的
    void execute();
}

class ConCommand1 implements Command{
    private Receiver receiver;
    
    public ConCommand1(Receiver receiver) {
        this.receiver = receiver;
    }
    
    public void execute() {
        receiver.med1();
    }
}

class ConCommand2 implements Command{
    private Receiver receiver;
    
    public ConCommand2(Receiver receiver) {
        this.receiver = receiver;
    }
    
    public void execute() {
        receiver.med2();
    }
}

class Invoker {
    private Command command;
    
    public Invoker(Command command) {
        this.command = command;
    }
    
    public void action() {
        command.execute();
    }
}

public class CommandPattern {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        // 生成一个命令
        Command command = new ConCommand1(receiver);
        Invoker invoker = new Invoker(command);
        invoker.action();
    }
}
