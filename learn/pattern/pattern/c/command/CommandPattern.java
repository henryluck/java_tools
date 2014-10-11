
//Command ģʽ����
//�о���һ����ķ��� ��ɢ���� ������˽ӿڷ��� ע�� �����ͷ���ֵҪһ��
//ÿ��Command��װһ������ Ҳ����һ�ֲ���

package pattern.c.command;

//������߼�����ʵ�ֵ�λ��
class Receiver {
    public void med1() {}
    
    public void med2() {}
}

interface Command {
    // Ҫ�����ͳһ�ӿڷ��� ���о����Ե�
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
        // ����һ������
        Command command = new ConCommand1(receiver);
        Invoker invoker = new Invoker(command);
        invoker.action();
    }
}
