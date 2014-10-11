
//ObservableÄ£Ê½Àý×Ó

package pattern.c.observable;
import java.util.*;

class State {}

interface Subject {
	void attach(Observer observer);
	
	void detach(Observer observer);
	
	void myNotify();
	
	State getState();
	
	void setState(State state);
}

class ConSubject implements Subject {
	List observers = new ArrayList();
	State state = new State();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	public void myNotify() {
		Iterator it = observers.iterator();
		Observer observer = null;
		while (it.hasNext()) {
			observer = (Observer) it.next();
			observer.update();
		}
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}	
}

interface Observer {
	void update();
}

class ConObserver1 implements Observer{
	private Subject subject;
	
	public ConObserver1(Subject subject) {
		this.subject = subject;
	}
	
	public void update() {
		State state = subject.getState();
		// do something with state
	}
}

class ConObserver2 implements Observer{
	private Subject subject;
	
	public ConObserver2(Subject subject) {
		this.subject = subject;
	}
	
	public void update() {
		State state = subject.getState();
		// do something with state
	}
}

public class ObservablePattern {
	public static void main(String[] args) {
	}
}
