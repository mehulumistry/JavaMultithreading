package com.company.multithreading;

import java.util.ArrayList;

/**
 * Date: 9/25/20
 * Time: 3:51 PM
 */

// https://www.youtube.com/watch?v=Z5TRputhzHs&list=RDCMUCiz26UeGvcTy4_M3Zhgk7FQ&index=19
/* Want to design a class
 * which is lazily evaluated -> evaluated when referenced and also thread safe
 * 1) only evaluated when referred
 * 2) don't want to repeat the calculation
 * */

/*
Lazy loading is just a fancy name given to the process of initializing a class when it’s actually needed.
In simple words, Lazy loading is a software design pattern where the initialization of an object
occurs only when it is actually needed and not before to preserve simplicity of usage and improve performance.
Lazy loading is essential when the cost of object creation is very high and the use of the object
is very rare. So this is the scenario where it’s worth implementing lazy loading.The fundamental idea of lazy loading is to load object/data when needed.
*/

/*

Lazy initialization has two objectives:

- delay an expensive operation until it's absolutely necessary
- store the result of that expensive operation, such that you won't need to repeat it again

* */
// https://javarevisited.blogspot.com/2014/05/double-checked-locking-on-singleton-in-java.html
//https://medium.com/@kasunpdh/handling-java-memory-consistency-with-happens-before-relationship-95ddc837ab13


// Learn about atomicity, volatile, happens-before, synchronized, visibility
// Synchronization also blocks reader threads

// lazy val of scala is implemented similary and has performance penalty and problem of deadlocks because of synchronization
// https://blog.codecentric.de/en/2016/02/lazy-vals-scala-look-hood/
// Dotty solves the problem



/*
*
* When a concurrent programming is not correctly written,
* the errors tend to fall in to one of three categories:
*   1) atomicity
*      -- When you want to access something in shared state in any way obtain a mutual exclusion lock on it.
*       Eg. synchronized and intrinsic locks are the example
*   2) visibility
*      -- When an object is set to volatile that is just handling the visibility of the reference not the contents of the object itself.
*      -- main memory
*      Eg. volatile
*   3) ordering
*       -- There will be an ordering issues bcs JVM/compiler will try to optimize and change the order of the execution
*       This might sound perfect for the sequential program but for the multithreaded
*       it will create a problem. Thread ordering is unpredictable
*
*   Volatile -> provides visibility and ordering
*   Synchronized -> provides all three
* */


class NotThreadSafeLazyClassTest {


    public static void main(String[] args) {
        LazyClass<String> lazyClass = new LazyClass<>("Hello");
        int counter = 0;

        ArrayList<Thread> listOfThreads = new ArrayList<>();

        for(int i = 0; i<100000;i++) {

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Thread sleep will pause the current thread execution and will allow other threads to execute
                        // There will be race for accessing getValue after thread completes sleep
//                        if(!lazyClass.isInstanceExist()) {
//                            System.out.println(false);
//                        }

                        //Thread.sleep(100);
                        lazyClass.getValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            listOfThreads.add(t1);
        }

        for(int i = 0; i < listOfThreads.size(); i++) {
            Thread t = listOfThreads.get(i);
            t.start();
        }

    }


}

class LazyVal<T> {

    final private T val;
    LazyVal(T v) {
        this.val = v;
    }

    public T getVal() {
        return val;
    }
}


// Not thread safe

class LazyClass<T> {

    // Why we need volatile here?
    // Try removing volatile and adding it.
    // https://stackoverflow.com/questions/7855700/why-is-volatile-used-in-double-checked-locking#:~:text=volatile%20in%20turn%20establishes%20happens,holder%20inner%20static%20class%20instead.
    // Because the second thread will try to access it, before first one is done.
    private volatile LazyVal<T> lazyVal;
    final private T value;
    // ideally it should be once per application but in multithreaded it behaves differently

    LazyClass(T val){this.value = val;}

    /*
     * LazyVal class will not get created until getValue is not referenced
     * and it also stores the reference in lazyVal for future access.
     */

    public boolean isInstanceExist() {
        return lazyVal != null;
    }

    // Can synchronize the whole function but that will be a waste because
    // if(lazyVal == null) (1) statement is multithreading friendly
    // So sync is inside and double checking the instance
    public T getValue() throws InterruptedException {
        if(lazyVal == null) {
            System.out.println("Called");
            synchronized(LazyVal.class) {
                if(lazyVal == null) {
                    //Thread.sleep(100);
                    lazyVal = new LazyVal<T>(value);
                }
            }
        }
        return lazyVal.getVal();
    }




}

// It's a singleton pattern with thread safe implementation

class InitializationOnDemandHolder {

    private InitializationOnDemandHolder(){
        System.out.println("Constructor Called");
    }

    /* With static java lazy loads the class, and only provides the iodh when first referenced and with final the value is thread safe */
    static class LazyClassHolder {

        /* Why constructor cannot be staic -> bcs purpose of constructor is to instantiate the object, so no use of constructor here */
        LazyClassHolder() {
            System.out.println("LazyClassHolder Called");
        }

        /* static memory assigned during compile time and instantiated only once, and making final will make sure it is not override */
        static final InitializationOnDemandHolder iodh = new InitializationOnDemandHolder();
    }

    public static InitializationOnDemandHolder getInstance() {
        System.out.println("Get Instance");
        return LazyClassHolder.iodh;
    }

}

class IODHTest {
    public static void main(String[] args) {
        /* Note constructor is only called once*/
        InitializationOnDemandHolder iodh = InitializationOnDemandHolder.getInstance();
        InitializationOnDemandHolder iodh2 = InitializationOnDemandHolder.getInstance();
        InitializationOnDemandHolder iodh3 = InitializationOnDemandHolder.getInstance();
        InitializationOnDemandHolder iodh4 = InitializationOnDemandHolder.getInstance();

    }
}


// Using Enum is the best way to create Singleton pattern


