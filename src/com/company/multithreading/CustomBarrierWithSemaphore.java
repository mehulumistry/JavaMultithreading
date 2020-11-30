package com.company.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 11/29/20
 * Time: 12:05 PM
 */
public class CustomBarrierWithSemaphore {


    // we want some threads to finish their work before starting part 2

    private int noOfWorkers = 0;
    Semaphore semaphore = new Semaphore(0);
    Lock reentrantLock = new ReentrantLock();
    private int counter = 0;

    CustomBarrierWithSemaphore(int workerThreads) {
        this.noOfWorkers = workerThreads;
    }

    /* All threads will call barrier and if counter goes to noOfWorkers release the semaphore */
    public void barrier() {
        reentrantLock.lock();
        boolean isLastWorker = false;

        try {
            counter++;
            if(counter == noOfWorkers) isLastWorker = true;
        } finally {
            reentrantLock.unlock();
        }

        if(isLastWorker) {
            // semaphore will release all the blocked threads
            semaphore.release(noOfWorkers - 1);
        } else {
            try {
                // the thread will get block here, cannot acquire the lock because permit is zero.
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

class CoordinatedWorkRunner implements Runnable {

    CustomBarrierWithSemaphore barrier;
    CoordinatedWorkRunner(CustomBarrierWithSemaphore barrierWithSemaphore) {
        this.barrier = barrierWithSemaphore;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + " part 1 of the work is finished");

        barrier.barrier();

        // Performing Part2
        System.out.println(Thread.currentThread().getName()
                + " part 2 of the work is finished");
    }
}

class CustomBarrierWithSemaphoreTest {
    public static void main(String[] args) {
        int numberOfThreads = 200; //or any number you'd like
        List<Thread> threads = new ArrayList<>();
        CustomBarrierWithSemaphore barrier = new CustomBarrierWithSemaphore(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            threads.add(new Thread(new CoordinatedWorkRunner(barrier)));
        }

        for(Thread thread: threads) {
            thread.start();
        }
    }
}
