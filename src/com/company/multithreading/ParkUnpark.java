package com.company.multithreading;

import java.nio.channels.CompletionHandler;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * Date: 10/3/20
 * Time: 11:07 AM
 */

// https://carlmastrangelo.com/blog/javas-mysterious-interrupt
// https://medium.com/@vikas.singh_67409/lock-on-linux-a-deep-dive-8269305d15dd

// park/unpark is the new version of suspend and resume thread
public class ParkUnpark {
    public static void main(String[] args) {

        Object counter = new Object();
        ReentrantLock iamLock = new ReentrantLock();

        Thread parkingThread = new Thread(() -> {
            while(true) {
                //iamLock.lock();
                System.out.println("Will go to sleep...");
                //sleepTwoSeconds();
                System.out.println("Parking...");
                // this call will return immediately since we have called  LockSupport::unpark
                // before this method is getting called, making the permit available
                LockSupport.park();
                //LockSupport.unpark(finalParkingThread);
//                try {
//
//                    System.out.println(counter);
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                //iamLock.unlock();
                System.out.println("After parking...");
            }

        });

        Thread parkingThread2 = new Thread(() -> {
            while(true) {
                System.out.println("2222 Will go to sleep...");
                //sleepTwoSeconds();
                System.out.println("2222 Parking...");
                // this call will return immediately since we have called  LockSupport::unpark
                // before this method is getting called, making the permit available
                LockSupport.park();
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                //LockSupport.unpark(parkingThread);
                System.out.println("2222 After parking...");
            }
        });

//        System.out.println("Interrupt...");
//        parkingThread.interrupt();

        parkingThread.setName("Parking Thread");
        parkingThread2.setName("Parking Thread2");


        parkingThread.start();
        parkingThread2.start();


        //sleepTwoSeconds();
        // hopefully this 1 seconxd is enough for "parkingThread" to start
        // _before_ we call un-park

        System.out.println("Un-parking...");
        // making the permit available while the thread is running and has not yet
        // taken this permit, thus "LockSupport.park" will return immediately
        LockSupport.unpark(parkingThread);
        LockSupport.unpark(parkingThread2);



    }

    private static void sleepTwoSeconds() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleepOneSecond() {
        try {
            LockSupport.parkNanos(1000);
            //Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
