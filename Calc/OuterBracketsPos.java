package Calc;

import java.util.Collections;
import java.util.LinkedList;

public class OuterBracketsPos {

    public static LinkedList<Integer> get(String expression) throws Exception {
        LinkedList<Bracket> bracketList = new LinkedList<Bracket>();
        int brArr[] = {'(', ')'};
        int position;

        for (int bracket : brArr) {//1 getting array of brackets
            position = expression.indexOf(bracket);

            while (position != -1) {
                bracketList.add(new Bracket(bracket, position));
                position = expression.indexOf(bracket, position + 1);
            }
        }
        Collections.sort(bracketList);

        //list of positions of brackets which enclosing expressions
        LinkedList<Integer> encPos = new LinkedList<Integer>();
        int bracketCounter = 0;
        int startPos = 0;

        for (Bracket bracket : bracketList) {//2 getting positions of expressions enclosed with brackets
            if (bracket.getType() == '(') {
                if (bracketCounter == 0) {
                    startPos = bracket.getPos();
                }
                bracketCounter++;
            } else {
                bracketCounter--;
            }
            if (bracketCounter == 0) {
                encPos.add(startPos);
                encPos.add(bracket.getPos());
            }
        }
        if (encPos.size() % 2 != 0) {
            throw new Exception("encPos.size not even");
        }

        return encPos;
    }
}
