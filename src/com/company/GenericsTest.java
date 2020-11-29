package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Date: 11/27/20
 * Time: 11:27 AM
 */


public class GenericsTest {

    public static void main(String[] args) {

        DoStuffBro dsb = new DoStuffBro();
        dsb.addToListObj(new LinkedHashMap<>());
        dsb.addToListObj(8);
        dsb.addToListObj(new General<Shape>());
        System.out.println(dsb.listOfObj);
    }

}

class General<T> {


}

// Ref: https://www.freecodecamp.org/news/understanding-java-generic-types-covariance-and-contravariance-88f4c19763d2/
/* Lower Bound, Upper Bound */
class Idea {}
class Form extends Idea {}
class Shape extends Form {}
class Circle extends Shape {}
class Rectangle extends Shape {}

class DoStuffBro {

    // List[+T]
    public List<? extends Shape> coVar;
    // List[-T]
    public List<? super Shape> contraVar;

    public List<Object> listOfObj;

    public List<?> listOfWildCard;

    DoStuffBro() {
        this.coVar = new LinkedList<>();
        this.contraVar =  new LinkedList<>();
        this.listOfObj =  new LinkedList<>();
        this.listOfWildCard = new LinkedList<>();

        List<Object> objList = Arrays.asList(5, (Object)"x");
        List<String> stringList = Arrays.asList("A","B","C");
        List<Integer> intList = Arrays.asList(1, 2, 3);

        printList(objList);            // valid
        printList(stringList);        // valid
        printList(intList);            // valid

        printObjectList(objList);        // valid
//        printObjectList(stringList);    // invalid. compiler error
//        printObjectList(intList);        // invalid. compiler error

        printObjectListWithWildCard(stringList);
        printObjectListWithWildCard(intList);

    }


    public <T> void addToListWildCard(T obj) {
        //this.listOfWildCard.add(obj);
    }


    public void addToListObj(Object obj) {
        this.listOfObj.add(obj);
    }

    /* Not possible to add in covar list because we don't know the typ of ele to add */
    public <T extends Shape> void addToCoVar(T ele) {

        this.coVar.add(null);
    }

    /* It is possible bcs whatever the supertype is base type is Shape, WRITE-ONLY */
    public void addToContraVar(Shape ele) {
        this.contraVar.add(ele);
    }

    /* Not possible, it will throw error because super type cannot cast to sub type */
    public Shape getFromContraVar(int idx) {
        return (Shape) this.contraVar.get(idx);
    }

    /* READ-ONLY */
    public Shape getFromCoVar(int idx) {
        return this.coVar.get(idx);
    }


    /* Raw List accepts all types of list */
    public static void printList(List l){
        for (Object obj : l) {
            System.out.println(obj);
        }
    }

    public static void printObjectListWithWildCard(List<? extends Object> l){
        for (Object obj : l) {
            System.out.println(obj);
        }
    }

    public static void printObjectList(List<Object> l){
        for (Object obj : l) {
            System.out.println(obj);
        }
    }

    /* Regarding generics, this means that parameterized types are not stored in the bytecode. */

    /* NOt allowed because compiler erases all the generic type parameters during compile time, so doesn't how to differentiate */
//    public void print(List<String> lst) {}
//    public void print(List<Double> lst) {}

//    public static <E> void typeErasureFn(List<E> list) {
//        if (list instanceof ArrayList<Integer>) {
//
//        }
//    }

    // Ref: https://stackify.com/jvm-generics-type-erasure/

    public static void typeErasureFn(List<?> list) {
        if (list instanceof ArrayList<?>) {

        }
    }

    /* You can pass class which helps you do determine the class */
    public <T> void withClass(List<T> list, Class<T> clazz) {
        if(clazz == Integer.class){}
        if(clazz == String.class){}
    }


}
