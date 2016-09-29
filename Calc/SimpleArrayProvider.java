package Calc;

import java.util.Collections;
import java.util.LinkedList;

public class SimpleArrayProvider {
    public static SimpleExpression[] get(String expression) {//expression like 3 + 5 / 7
        LinkedList<Operation> opList = new LinkedList<Operation>();
        int opArray[] = {
                '+', '-', '*', '/'
        };
        int position;

        //array of operations
        for (int operation: opArray) {//throws OutOfBounds if expression is incorrect
            position = expression.indexOf(operation);

            while (position != -1) {
                opList.add(new Operation(operation, position));
                position = expression.indexOf(operation, position + 1);
            }
        }
        Collections.sort(opList);

        SimpleExpression[] exArray = new SimpleExpression[opList.size()];
        int startPos = 0;
        int cycleVar = 0;
        for (Operation operation: opList) {

            exArray[cycleVar] = (new SimpleExpression(
                    Double.valueOf(expression.substring(startPos, operation.getPos())),
                    operation.getOperation()));

            startPos = operation.getPos() + 1;
            cycleVar++;
        }
        if (startPos != expression.length()) {
            SimpleExpression exArray2[] = new SimpleExpression[exArray.length + 1];
            System.arraycopy(exArray, 0, exArray2, 0, exArray.length);
            exArray2[exArray.length] = new SimpleExpression(
                    Double.valueOf(expression.substring(startPos, expression.length())));
            return exArray2;
        }


        return exArray;
    }
}
