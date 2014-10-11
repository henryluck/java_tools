
//State模式例子
//将一种状态和状态的助理封装倒state中

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

//表示打开状态
class OpenState implements State{
	public void handle(Context context) {
		System.out.println("do something for open");
		context.changeState(new CloseState());
	}
}

//表示关闭状态
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
