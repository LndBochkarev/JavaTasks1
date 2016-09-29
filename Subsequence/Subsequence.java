package Subsequence;

import java.util.LinkedList;

public class Subsequence {
    public static void main(String[] args) {
        LinkedList<Object> longList = new LinkedList<Object>();
        LinkedList<Object> shortList = new LinkedList<Object>();

        shortList.add(5);
        shortList.add(8);
        shortList.add(943);

        longList.add(2);
        longList.add(5);
        longList.add(7);
        longList.add(8);
        longList.add(943);
        longList.add(800);

        System.out.println(subsequenceContains(longList, shortList));
    }


    public static boolean subsequenceContains(LinkedList<Object> longList, LinkedList<Object> shortList) {

        int j = 0;
        Object element = shortList.get(j);

        try {
            for (Object secondElement: longList) {
                if (element.equals(secondElement)) {
                    j++;
                    element = shortList.get(j);
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            return true;
        }
        return false;
    }
}
