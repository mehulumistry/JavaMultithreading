package com.company.multithreading;


import java.util.concurrent.CompletableFuture;

/**
 * Date: 11/27/20
 * Time: 7:35 PM
 */

/* Eager initialization
*  Lazy initialization
*
*
* */

public class DoubleCheckLocking {

    public void main(String[] args) {

        var instance1 = getTrulySingletonThreadSafeResource(); //instance 1
        var instance2 = getTrulySingletonThreadSafeResource(); // instance 2

    }

    // Here volatile will make sure assigning Object to an reference is volatile(atomic and preserve order)
    volatile ExpensiveResource resource;

    ExpensiveResource getExpensiveResourceFail1() {

        // what if multiple threads tries to access it?
        // T1, T2 they will end up creating multiple instance
        if(resource == null) {
            resource = new ExpensiveResource();
        }
        return resource;

    }

    ExpensiveResource getExpensiveResourceFail2() {

        // adding synchronized will make it thread-safe,
        // but it will consume lot of resources
        // Thread will enter the synchronization block even if resource is already created
        // and this will consume lot of CPU cycle, unnecessary entering to synchronized block.
        synchronized (this) {
            if(resource == null) {
                resource = new ExpensiveResource();
            }
        }
        return resource;

    }

    ExpensiveResource getExpensiveResourceSomeWhatAccepted() {

        // Adding double checks
        // if resource is null then only enter the synchronized block
        if(resource == null) {
            synchronized (this) {
                if (resource == null) {
                    resource = new ExpensiveResource();
                }
            }
        }

        return resource;
    }

    ExpensiveResource getTrulySingletonThreadSafeResource() {

        // making resource volatile
        // the below resource variable is not inside synchronized block,
        // so multiple threads will try to get the value of the resource
        if(resource == null) {
            synchronized (this) {
                if (resource == null) {
                    /*Thread A notices that the value is not initialized, so it obtains the lock and begins
                    to initialize the value.
                    Due to the semantics of some programming languages, the code generated by the compiler
                    is allowed to update the shared variable to point to a partially constructed object before
                    A has finished performing the initialization. For example, in Java if a call to a constructor
                    has been inlined then the shared variable may immediately be updated once the storage has been
                    allocated but before the inlined constructor initializes the object.[6]
                    Thread B notices that the shared variable has been initialized (or so it appears), and returns
                    its value. Because thread B believes the value is already initialized, it does not acquire
                    the lock. If B uses the object before all of the initialization done by A is seen by B
                    (either because A has not finished initializing it or because some of the initialized
                    values in the object have not yet percolated to the memory
                    B uses (cache coherence)), the program will likely crash.*/


                    // assigning and creating resource is multi step process
                    // assigning reference to resource
                    // resource = some memory address where heap is now creating it's ExpensiveResource() object
                    // Thread 2/3.. will try to access the variable resource and which will be not null.
                    // but resource may or may not be fully created, which will result into false sharing.
                    // so need to make resource volatile.
                    // volatile will make sure no reordering happens while creating resource and hence will make sure the object is created fully.
                    resource = new ExpensiveResource();
                }
            }
        }

        return resource;
    }



}

class ExpensiveResource {

    ExpensiveResource() {
        this.getFromDB();
        this.getFromNetwork();
    }
    void getFromDB() {}
    void getFromNetwork() {}

}

// Why this is not thread safe?

// increment synchronizes on class level and decrement on object level
// both can change the count
// Also, since count is static, modifying it from decrement which is a synchronized instance method is
// unsafe since it can be called on different instances and modify count concurrently that way.

class ThreadSafeClass extends Thread
{

    private static int count = 0;

    ThreadSafeClass() {}

    // With static -> synchronization is done on the class-level
    // Non static method -> synchronize on object level
    public synchronized static void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public static int getCount() {
        return count;
    }
}

/* The output of the below if 3 or 4, not thread safe */

class TestThreadSafeClass {

    public static void main(String[] args) {

        var classInstance = new ThreadSafeClass();

        Runnable r1 = classInstance::decrement;
        Runnable r2 = ThreadSafeClass::increment;

        ThreadSafeClass.increment();
        ThreadSafeClass.increment();


        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r2);
        Thread t4 = new Thread(r2);
        Thread t5 = new Thread(r1);

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(ThreadSafeClass.getCount());
        assert ThreadSafeClass.getCount() == 0;

    }
}





