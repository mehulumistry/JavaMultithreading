package com.company.multithreading;

/**
 * Date: 10/30/20
 * Time: 4:51 PM
 */

/* Want to check if Thread.sleep locks the Class */

public class ThreadSleepTest {

    public static void main(String[] args) {

        ConcurrencyTestObject cto = new ConcurrencyTestObject();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        cto.setCounter(cto.getCounter() + 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.setName("T1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(500);
                        System.out.println(cto.getCounter());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        t2.setName("T2");

        t1.start();
        t2.start();


    }

}

class ConcurrencyTestObject {

    int counter = 0;

    public int getCounter() {
        System.out.println(Thread.currentThread().getName() + " Reading");
        setCounter(counter - 1);
        return counter;
    }

    /* Thread.sleep holds the lock and go to sleep leads to performance issues */
    /* Use .wait() */
    /* https://rules.sonarsource.com/java/tag/multi-threading/RSPEC-2276 */
    public synchronized void setCounter(int counter) {
        try {
            System.out.println(Thread.currentThread().getName() + " Writing");
            this.counter = counter;
            System.out.println(Thread.currentThread().getName() + " fall asleep");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " woke up");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    ConcurrencyTestObject() {}

}
