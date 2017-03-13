package lab1;

import lab2.Indexer;
import lab4.Operations;
import lab4.QuerryInterpreter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunMain {

	public static void main(String [] args) throws IOException {
//        Indexer indexer = new Indexer();
//        indexer.doDirectIndex("working_dir");
//		indexer.readDirectIndexedFiles();
//		indexer.putInvertedIndexToFile();

//        Operations operations = new Operations();
//
//        List<String> list1 = new ArrayList<>(Arrays.asList("ana", "are", "mere", "gutui", "limonada"));
//        List<String> list2 = new ArrayList<>(Arrays.asList("ana", "are", "mere", "pere", "cirese", "lamai", "limonada"));
//
//        System.out.println(operations.doUnion(list1, list2));
//        System.out.println(operations.doIntersection(list1, list2));
//        System.out.println(operations.doDifference(list1, list2));
//        System.out.println(operations.doDifference(list2, list1));

//        System.out.println(QuerryInterpreter.interpretQuerry("If love")); //union
//        System.out.println(QuerryInterpreter.interpretQuerry("If +love")); //intersection
//        System.out.println(QuerryInterpreter.interpretQuerry("If -love")); //difference
//        System.out.println(QuerryInterpreter.interpretQuerry("love -If")); //difference

        System.out.println(QuerryInterpreter.interpretQuerry("browse content at Berlin"));
        System.out.println(QuerryInterpreter.interpretQuerry("browse content -at Berlin"));
        System.out.println(QuerryInterpreter.interpretQuerry("browse content at -Berlin")); // empty
        System.out.println(QuerryInterpreter.interpretQuerry("browse content at -Berlin +Berlin")); // empty
        System.out.println(QuerryInterpreter.interpretQuerry("browse content at -Berlin Berlin")); // all

        System.out.println(QuerryInterpreter.interpretQuerry("browse +content at +Berlin")); // 1

    }
}
