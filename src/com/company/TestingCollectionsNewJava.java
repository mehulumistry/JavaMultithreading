package com.company;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.Map.*;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Date: 11/27/20
 * Time: 10:39 PM
 */
public class TestingCollectionsNewJava {

    public static void main(String[] args) {


        Queue<String> ll = new LinkedList<String>();

        List<String> list = List.of("fdsf");
        String[] strings = new String[100];

        String[] dsfsdf = list.toArray(strings);


        var firstName = 7;

        firstName = 9;

        System.out.println(firstName);

        List<Integer> listInts = new ArrayList<>(List.of(1, 2, 3, 4, 9, 5, 6, 67, 7, 8, 89, 9));

        // Immutable way of sorting -> with streams
        var newList = listInts.stream().sorted().collect(Collectors.toList());

        // mutable way of sorting -> with Collections utils
        Collections.sort(listInts);

        System.out.println(newList);
        var test = Collections.nCopies(5, listInts);

        Collections.swap(listInts, 1 , 5);



        TreeMap<String, Integer> treeMap = new TreeMap<>();


        treeMap.put("a", 1);
        treeMap.put("b", 9);
        treeMap.put("c", 0);
        treeMap.put("d",11);

        System.out.println(treeMap);
        System.out.println(sortByValues(treeMap));

        // Using Stream

//        LinkedHashMap<String, Integer> linkedHashMap =
//                 treeMap.entrySet()
//                        .stream()
//                        .sorted(Map.Entry.comparingByValue())
//                        .collect(Collectors.toMap(
//                                Entry::getKey, Entry::getValue,
//                                (e1, e2) -> e1,
//                                java.util.LinkedHashMap::new
//                        ));


        var result = testCurrying(5, 6, (x, y) -> x * y, Integer::sum);

        TestDBO testingCollections = TestingCollectionsNewJava::new;

        var joiner = new StringJoiner("-", "(", ")");
        joiner.add("Test");
        joiner.add("Test1");
        System.out.println(joiner);

        // Functional Interface is functionality to implement functions to perform some
        // functions instead of data for eg. Runnable Interface, Callable, Comparators

        // Functional Interfaces are important to operate lambda
        // Function is implemented with Predicate and Consumer
        // Consumer returns nothing -> streams.forEach uses it.

        // Ref: https://www.youtube.com/watch?v=lX0PzE0Dho0&list=RDCMUCiz26UeGvcTy4_M3Zhgk7FQ&index=10

        // Supplier -> takes no input and returns Output

    }

    public void testFn() {
        DataBaseDBO ref = TestingCollectionsNewJava::testCurrying;

        BiFunction<Integer, Integer, Integer> testDBO = TestDBO::print;
        ref.print();
    }

    private static void testCurrying() {
    }

    // https://www.geeksforgeeks.org/comparable-vs-comparator-in-java/

    // With Comparable we only get one chance to sort the collection solution -> Comparator

    public static <K, V extends Comparable<V>> Map<K,V> sortByValues(final Map<K,V> map) {

        Comparator<K> compareByValues = (o1, o2) -> {
            var left = map.get(o1);
            var right = map.get(o2);

            return left.compareTo(right);
        };

        Map<K,V> newMap = new TreeMap<>(compareByValues);

        newMap.putAll(map);
        return newMap;
    }


    Function<Integer, Function<Integer, Integer>> currying = u -> v -> u * v;

    // Currying in Java
    static Integer testCurrying(
            Integer x,
            Integer y,
            BinaryOperator<Integer> fn,
            BiFunction<Integer, Integer, Integer> biFn
    ) {
       return fn.apply(x, y) + biFn.apply(x, y);
    }

}

@FunctionalInterface
interface DataBaseDBO {
    void print();
}

@FunctionalInterface
interface TestDBO {
    static int print(int a, int b) {
        return a + b + 4;
    }
    void testDBOPrint();
}
