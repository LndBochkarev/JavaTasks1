package Calc;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.LinkedList;
import java.util.ListIterator;

public class SimpExCalc {

    public static double calculate(LinkedList<SimpleExpression> exList) throws Exception {

        if (exList.size() == 1) {
            return exList.getFirst().getValue();
        } else {
            ListIterator<SimpleExpression> iterator;
            SimpleExpression prevElement;
            SimpleExpression element;

            for (int priority = 1; priority <= 2; priority++) {
                iterator = exList.listIterator();
                prevElement = iterator.next();

                while (iterator.hasNext()) {
                    element = iterator.next();
                    //System.out.println("prev = " + prevElement.getValue() + " el = " + element.getValue() + " priority " + priority);

                    if (prevElement.getPriority() == priority) {
                        prevElement.setValue(performOperation(
                                prevElement.getValue(),
                                element.getValue(),
                                prevElement.getOperation()));
                        if (element.getOperation() != -1) {
                            prevElement.setOperation(element.getOperation());
                        }
                        iterator.remove();

                        //System.out.println("prelval = " + prevElement.getValue() + " " + (char)prevElement.getOperation());
                    } else {
                        prevElement = element;
                    }
                }
            }

            if (exList.size() == 1) {
                return exList.getFirst().getValue();
            } else {
                throw new Exception("calculateSimpExpr error, listSize = " + exList.size());
            }
        }
    }

    private static double performOperation(double value1, double value2, int operation) throws Exception {
        switch (operation) {
            case '+':
                return value1 + value2;
            case '-':
                return value1 - value2;
            case '*':
                return value1 * value2;
            case '/':
                return value1 / value2;
        }
        throw new Exception("performOp: no operation");
    }
}
