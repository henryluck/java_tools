
//Interpreterģʽ����
/*
 * Variable ��ʾ���� �洢����������
 * Constant �ս���ʽ
 * And ��Ĺ�ϵ ˫Ŀ
 * Not ���Ĺ�ϵ ��Ŀ
 */

package pattern.c.interpreter;

import java.util.*;

class Context {
    private Map variables = new HashMap();
    
    public boolean lookUp(Variable name) {
        Boolean value = (Boolean) variables.get(name);
        if (value == null) {
            return false;
        }
        return value.booleanValue();
    }
    
    public void bind(Variable name, boolean value) {
        variables.put(name, new Boolean(value));
    }
}

interface Expression {
    boolean interpret(Context cont);
}

class Constant implements Expression {
    private boolean value;
    
    public Constant(boolean value) {
        this.value = value;
    }
    
    public boolean interpret(Context cont) {
        return value;
    }
}

class Variable implements Expression{
    private String name;
    
    public Variable(String name) {
        this.name = name;
    }
    
    public boolean interpret(Context cont) {
        return cont.lookUp(this);
    }
}

class And implements Expression {
    private Expression left;
    private Expression right;
    
    public And(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    } 
    
    public boolean interpret(Context cont) {
        return left.interpret(cont) && right.interpret(cont);
    }
}

class Not implements Expression {
    private Expression expression;
    
    public Not(Expression expression) {
        this.expression = expression;
    }
    
    public boolean interpret(Context cont) {
        return ! expression.interpret(cont);
    }
}

public class InterpreterPattern {
    public static void main(String[] args) {
        Context cont = new Context();
        Variable variable = new Variable("parameter1");
        cont.bind(variable, true);
        Expression and = new And(new Not(new Constant(true)), new And(new Constant(false), new Variable("parameter1"))); 
        //  (!(true)) && ((false)&&(true))
        and.interpret(cont);
    }
}
