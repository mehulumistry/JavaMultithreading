package com.company.multithreading;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date: 9/26/20
 * Time: 3:53 PM
 */

/* https://www.javamex.com/tutorials/synchronization_wait_notify_4.shtml */

// https://howtodoinjava.com/java/multi-threading/when-to-use-countdownlatch-java-concurrency-example-tutorial/

/* Prior to Java5, one way to coordinate the threads was to use wait-notify mechanism
* Using wait and notify we can implement latch
*
* After java synchronized thread uses CountAndSwap
* https://www.javamex.com/tutorials/synchronization_concurrency_6b.shtml
* */
public class CustomLatch {

    int counter;

    CustomLatch(int numberOfThreads) {
        counter = numberOfThreads;
    }

    synchronized void awaitMainThreadUntilAllThreadsFinishes() throws InterruptedException {
        if (counter > 0) {
            // the wait will send the thread to waiting queue
            wait();

        }
    }

    synchronized void decCounterWhenThreadTaskFinishes() {
        counter = counter - 1;
        System.out.println(counter);
        if(counter <= 0) {
            // notify the waiting threads
            // notifyAll();
            notify();
        }

    }

}


class CustomLatchTest {
    public static void main(String[] args) throws InterruptedException {

        /* Make program where main thread waits until the execution of service small threads finishes */

        int numberOfThreads = 10;
        CustomLatch cl = new CustomLatch(numberOfThreads);

        for(int i = 0; i<numberOfThreads; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("Running Thread ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        cl.decCounterWhenThreadTaskFinishes();
                    }
                }
            });

            t1.start();
        }

        // your first async await -> yeahhh

        cl.awaitMainThreadUntilAllThreadsFinishes();

        System.out.println("Do something");

    }
}

// CountDownLatch
// If we want to wait for all the task/threads upto some point of execution use cyclic barrier

// Ref: https://www.baeldung.com/java-countdown-latch
// Ref: https://www.youtube.com/watch?v=J3QZ5gfCtAg&list=RDCMUCiz26UeGvcTy4_M3Zhgk7FQ&index=9
