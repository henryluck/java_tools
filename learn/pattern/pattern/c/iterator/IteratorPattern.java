
//IteratorÄ£Ê½Àý×Ó

package pattern.c.iterator;

interface Aggregate {
	MyIterator Iterator();
}

class ConAggregate {
	public MyIterator Iterator() {
		return new ConMyIterator();
	}
}

interface MyIterator {
	Object First();
	Object Last();
	boolean hasNext();
	Object Next();
}

class ConMyIterator implements MyIterator{
	Object[] objs = new Object[100];
	int index = 0;
	
	public Object First() {
		index = 0;
		return objs[index];
	}
	
	public Object Last() {
		index = objs.length - 1;
		return objs[index];
	}
	
	public boolean hasNext() {
		return index < objs.length;
	}
	
	public Object Next() {
		if (index == objs.length - 1) {
			return null;
		} else {
			return objs[++index];
		}
	}
}

public class IteratorPattern {

	public static void main(String[] args) {
	}
}
