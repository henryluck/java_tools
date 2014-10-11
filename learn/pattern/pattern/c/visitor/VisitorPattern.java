
//Visitor模式例子
//双向传入

package pattern.c.visitor;
import java.util.*;

interface Visitor {
	void visitConElementA(ConElementA conElementA);
	void visitConElementB(ConElementB conElementB);
}

class ConVisitor1 implements Visitor{
	public void visitConElementA(ConElementA conElementA) {
		String value = conElementA.value;
		conElementA.operation();
		//do something
	}
	public void visitConElementB(ConElementB conElementB) {
		String value = conElementB.value;
		conElementB.operation();
        //do something
	}
}

class ConVisitor2 implements Visitor{
	public void visitConElementA(ConElementA conElementA) {
		String value = conElementA.value;
		conElementA.operation();
        //do something
	}
	public void visitConElementB(ConElementB conElementB) {
		String value = conElementB.value;
		conElementB.operation();
        //do something
	}
}

interface Element {
	void accept(Visitor visitor);
}

class ConElementA implements Element {
	String value = "aa";
	
	public void operation() {}
	
	public void accept(Visitor visitor) {
		visitor.visitConElementA(this);
	}
}

class ConElementB implements Element {
	String value = "bb";
	
	public void operation() {}
	
	public void accept(Visitor visitor) {
		visitor.visitConElementB(this);
	}
} 

class ObjectStructure {
	private List Elements = new ArrayList();
	
	public void action(Visitor visitor) {
		Iterator it = Elements.iterator();
		Element element = null;
		while (it.hasNext()) {
			element = (Element) it.next();
			element.accept(visitor);
		}
	}
	
	public void add(Element element) {
		Elements.add(element);
	}  
}

public class VisitorPattern {
	public static void main(String[] args) {
		ObjectStructure objs = new ObjectStructure();
		objs.add(new ConElementA());
		objs.add(new ConElementA());
		objs.add(new ConElementB());
		objs.action(new ConVisitor1());
	}
}
