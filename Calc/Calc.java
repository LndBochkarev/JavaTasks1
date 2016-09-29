package Calc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Calc {
    public static final int PRECISION = 4;

    public static void main(String[] args) {

        String testArray[] = {
                "3 + 5.78 * 4",
                "(3 + 5) * 4",
                "(3 + 5) * 4 + (3 + 4)",
                "8 - (3 + 6) * 4 + (3 + 4)",
                "8 / (3 + 6) * 4 + (3 + 4) + 2",
                "8 / ((3 + 6) * 4) + (3 + 4) + 2",
                "((8 / ((3 + 6) * 4) + (3 - 4)) / 2)",

        };


        for (String element : testArray) {
            System.out.println(element + " = " + calculate(element));
        }
    }

    public static String calculate(String expression) {
        String result = null;
        try {
            double res = round(recursiveCalc(removeSpaces(expression)), PRECISION);
            result = String.valueOf(res);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return result;
    }

    public static double recursiveCalc(String expression) throws Exception {
        LinkedList<SimpleExpression> arrayOfExpressions;

        if (expression.indexOf('(') != -1) {

            LinkedList<Integer> encPos = OuterBracketsPos.get(expression);

            arrayOfExpressions = getListOfExpressions(expression, encPos);

        } else {
            arrayOfExpressions = new LinkedList<SimpleExpression>(
                    Arrays.asList(SimpleArrayProvider.get(expression)));
        }
        return SimpExCalc.calculate(arrayOfExpressions);

    }

    private static String removeSpaces(String string) {
        return string.replaceAll(" ", "");
    }

    private static LinkedList<SimpleExpression> getListOfExpressions(
            String expression, LinkedList<Integer> encPos) throws Exception {

        LinkedList<SimpleExpression> listOfExpressions = new LinkedList<SimpleExpression>();
        int startPos = 0;
        String notEnclosed;

        for (int i = 0; i < encPos.size(); i += 2) {

            {//outside brackets
                notEnclosed = expression.substring(startPos, encPos.get(i));
                if (notEnclosed.length() > 0) {
                    SimpleExpression simpleExArray[] = SimpleArrayProvider.get(notEnclosed);
                    listOfExpressions.addAll(Arrays.asList(simpleExArray));
                }
                if (expression.length() >= encPos.get(i + 1) + 3) {
                    startPos = encPos.get(i + 1) + 2;
                }
            }

            {//inside brackets
                if (expression.length() >= encPos.get(i + 1) + 2) {
                    listOfExpressions.add(new SimpleExpression(recursiveCalc(
                            expression.substring(encPos.get(i) + 1, encPos.get(i + 1))),
                            expression.codePointAt(encPos.get(i + 1) + 1)));
                } else {
                    if (encPos.getLast() == expression.length() - 1) {
                        listOfExpressions.add(new SimpleExpression(recursiveCalc(
                                expression.substring(encPos.get(i) + 1, encPos.get(i + 1)))));
                    } else {
                        throw new Exception("wat?");
                    }
                }
            }
        }

        if (startPos > encPos.getLast() + 1) {//last not enclosed expression
            notEnclosed = expression.substring(encPos.getLast() + 2, expression.length());
            if (notEnclosed.length() > 0) {
                SimpleExpression simpleExArray[] = SimpleArrayProvider.get(notEnclosed);
                listOfExpressions.addAll(Arrays.asList(simpleExArray));
            }
        }

        if (listOfExpressions.size() == 0) {
            throw new Exception("Empty listOfExpressions");
        }

        return listOfExpressions;
    }

    private static double round(double value, int places) {

        BigDecimal bdVal = new BigDecimal(value);
        bdVal = bdVal.setScale(places, RoundingMode.HALF_UP);
        return bdVal.doubleValue();
    }
}
