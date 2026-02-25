/**
 * This program is a demonstration of the Interpreter design pattern.
 * It parses and evaluates simple addition and subtraction expressions.
 */

package ioprogramming.designpatterns;

interface Expression { int interpret(); }

class NumberExpression implements Expression {
    private int number;
    public NumberExpression(int number) { this.number = number; }
    public int interpret() { return number; }
}

class AddExpression implements Expression {
    private Expression left, right;
    public AddExpression(Expression l, Expression r) { left = l; right = r; }
    public int interpret() { return left.interpret() + right.interpret(); }
}

class SubExpression implements Expression {
    private Expression left, right;
    public SubExpression(Expression l, Expression r) { left = l; right = r; }
    public int interpret() { return left.interpret() - right.interpret(); }
}

public class InterpreterDemonstration {
    public static void main(String[] args) {
        // Simple manual parsing of "5 + 10 - 2"
        Expression exp = new SubExpression(
                new AddExpression(new NumberExpression(5), new NumberExpression(10)),
                new NumberExpression(2)
        );
        System.out.println("Result of 5 + 10 - 2 = " + exp.interpret());
    }
}