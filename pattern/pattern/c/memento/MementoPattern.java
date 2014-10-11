
//MementoÄ£Ê½Àý×Ó

package pattern.c.memento;

class Memento {
    String value1;
    int value2;
    
    public Memento(String value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}

class Originator {
    private String value1;
    private int value2;
    
    public Originator(String value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
    
    public void setMemento(Memento memento) {
        value1 = memento.value1;
        value2 = memento.value2;
    }
    
    public Memento createMemento() {
        Memento memento = new Memento(value1, value2);
        return memento;
    }
 
    public void setValue1(String value1) {
        this.value1 = value1;
    }
 
    public void setValue2(int value2) {
        this.value2 = value2;
    }
}

class CareTaker {
    private Memento memento;
    
    public Memento retrieveMemento() {
        return memento;
    }
    
    public void saveMemento(Memento memento) {
        this.memento = memento;
    }
}

public class MementoPattern {
    public static void main(String[] args) {
        Originator originator = new Originator("test1", 1);
        CareTaker careTaker = new CareTaker();
        
        //±£´æ×´Ì¬
        careTaker.saveMemento(originator.createMemento());
        
        originator.setValue1("test2");
        originator.setValue2(2);
        
        //»Ö¸´×´Ì¬
        originator.setMemento(careTaker.retrieveMemento());
    }
}
