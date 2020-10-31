package com.company.multithreading;


import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;

/**
 * Date: 9/27/20
 * Time: 4:30 AM
 */

// Single Producer and Multiple Consumers
// Condition -> Producer cannot wait, it is continuously producing
// Consumer thread can wait while it is adding
final class MyProg implements Runnable {
    public void run() {
        while (!Thread.interrupted()) {
            try {
                System.out.println("interrupted");
                Thread.sleep(1000);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }


}

class Test  {
    public static void main(String[] args) {
        MyProg t = new MyProg();
        Thread tt = new Thread(t);
        tt.start();
    }
}

public class RoughObservable {

    LinkedList<Double> stream = new LinkedList<>();
    int counterCapacity = 5;
    int valCount = 0;

    public void runThread() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                while(true) {
                    synchronized (stream) {
                        try {
                            if(valCount == counterCapacity) {
                                System.out.println("Producer Waiting....");
                                stream.wait();
                            }
                            valCount += 1;
                            double rand = Math.random();
                            stream.addLast(rand);
                            System.out.println("Added "+ rand);
                            System.out.println("Producer Waiting....");
                            // Remove stream.wait() to fill the producer first and then start consuming
                            stream.wait();
                            // If you remove notify, chances of deadlock
                            stream.notify();
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }



            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    synchronized (stream) {
                        try {
                            if(stream.size() > 0){
                                double output = stream.removeFirst();
                                System.out.println("Removed " + output + " Size " + stream.size());
                                stream.notify();
                                Thread.sleep(2000);
                            } else {
                                System.out.println("Consumer Waiting....");
                                stream.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        RoughObservable ro = new RoughObservable();
        ro.runThread();

    }

}
