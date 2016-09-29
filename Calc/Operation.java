package Calc;

public class Operation implements Comparable<Operation> {
    private int operation = -1;
    private int pos;

    public Operation(int operation, int pos) {
        this.operation = operation;
        this.pos = pos;
    }

    public int getOperation() {
        return operation;
    }

    public int getPos() {
        return pos;
    }

    @Override
    public int compareTo(Operation o) {
        if (pos > o.getPos()) {
            return 1;
        } else {
            return -1;
        }
        //zero is an unreachable statement in case this class should be used to
    }
}
