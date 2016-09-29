package Calc;

public class Bracket implements Comparable<Bracket> {
    private int type;
    private int pos;

    public Bracket(int type, int pos) {
        this.type = type;
        this.pos = pos;
    }

    public int getType() {
        return type;
    }

    public int getPos() {
        return pos;
    }

    @Override
    public int compareTo(Bracket br) {
        if (pos > br.getPos()) {
            return 1;
        } else {
            return -1;
        }
    }
}
