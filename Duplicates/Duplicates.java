package Duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.nio.file.StandardOpenOption;

public class Duplicates {
    public static void main(String[] args) {

        File inputFile = new File("src/Duplicates/InputFile.txt");
        File outputFile = new File("src/Duplicates/OutputFile.txt");

        System.out.println(dupliSearch(inputFile, outputFile));
    }

    public static boolean dupliSearch(File input, File output) {
        LinkedList<String> list;
        try {
            list = new LinkedList<String>(Files.readAllLines(input.toPath()));

            list = removeDuplicates(list);
            Collections.sort(list);

            Files.write(output.toPath(), list, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        }

        return true;
    }

    public static LinkedList<String> removeDuplicates(LinkedList<String> list) {

        int listSize = list.size();
        int count;
        String element;
        String duplicate;

        int hasCycleVar;
        ListIterator<String> iterator;

        for (int i = 0; i < listSize; i++) {
            element = list.get(i);
            iterator = list.listIterator(listSize);
            count = 0;

            for (hasCycleVar = listSize - 1; hasCycleVar > i; hasCycleVar--) {
                duplicate = iterator.previous();
                if (element.equals(duplicate)) {
                    count++;
                    iterator.remove();
                }
            }

            listSize = list.size();
            list.set(i, element + "[" + count + "]");
            //todo: try Builder
        }

        return list;
    }

}


