package com.company.multithreading;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 10/15/20
 * Time: 2:45 PM
 */
public class Threads101 {


    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object t = null;
                t.toString();
                throw new RuntimeException("What are you doing Thread???");
            }
        });

        /* OS can schedule based on thread priority */
        thread1.setPriority(Thread.MAX_PRIORITY);

        /* If thread throws some exception, you can do clean up here and can catch exception */

        thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                System.out.println("Thread thrown an error ");
            }
        });

        thread1.start();

    }
}
