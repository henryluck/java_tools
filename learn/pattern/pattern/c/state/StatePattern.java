
//Stateģʽ����
//��һ��״̬��״̬�������װ��state��

package pattern.c.state;

class Context {
	private State state = new CloseState();
	
	public void changeState(State state) {
		this.state = state;
	}
	
	public void ruquest() {
		state.handle(this);
	}
}

interface State {
	void handle(Context context);
}

//��ʾ��״̬
class OpenState implements State{
	public void handle(Context context) {
		System.out.println("do something for open");
		context.changeState(new CloseState());
	}
}

//��ʾ�ر�״̬
class CloseState implements State{
	public void handle(Context context) {
		System.out.println("do something for open");
		context.changeState(new OpenState());
	}
}

public class StatePattern {
	public static void main(String[] args) {
	}
}
