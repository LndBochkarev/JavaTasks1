package Calc;

public class SimpleExpression {
    private double value;
    private int operation;
    private int priority = 0;

    public SimpleExpression(double value, int operation) {
        this.value = value;
        this.operation = operation;
        priority = calcPriority(operation);
    }

    public SimpleExpression(double value) {//last value
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setOperation(int operation) {
        this.operation = operation;
        priority = calcPriority(operation);
    }

    public double getValue() {
        return value;
    }

    public int getOperation() {
        return operation;
    }

    public int getPriority() {
        return priority;
    }

    private int calcPriority(int operation) {
        if (operation == '*' || operation == '/') {
            return 1;
        } else {
            return 2;
        }
    }
}
