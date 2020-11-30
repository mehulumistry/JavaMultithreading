package com.company.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date: 9/26/20
 * Time: 10:04 PM
 */

/* Difference between ReadWriteLock and Reentrant Lock
*
* ReadWriteLock has two types of lock Reader and Writer Lock
* Scheduler allows reader threads to read the state even if the
* writer thread is waiting, once all reader threads reads the shared state
* writer thread writes to the state.
* Multiple threads can acquire the read lock and can access it.
* https://www.youtube.com/watch?v=7VqWkc9o7RM&t=414s
* */

// https://www.javaspecialists.eu/archive/Issue215-StampedLock-Idioms.html

// https://stackoverflow.com/questions/49372668/implementing-a-resource-read-write-lock-in-java
//
/*
*
* Multiple Threads can acquire Read lock as long as no other thread is holding write lock
* Only preferred when reads are higher then writes.
* */


public class CustomReadWriteLock {

    private volatile boolean WRITE_FLAG = false;
    private AtomicInteger noOfReaderThreads = new AtomicInteger(0);

    private Thread writeThreadOwner;

    public synchronized boolean acquireReadLock() throws InterruptedException {
        while(WRITE_FLAG) wait();
        noOfReaderThreads.incrementAndGet();
        return true;
    }

    public synchronized void releaseReadLock() {
        if(noOfReaderThreads.get() <= 0) throw new IllegalStateException();
        noOfReaderThreads.decrementAndGet();
        if(noOfReaderThreads.get() == 0) notifyAll();
    }

    public synchronized boolean acquireWriteLock() throws InterruptedException {
        if(noOfReaderThreads.get() != 0) wait();

        writeThreadOwner = Thread.currentThread();
        WRITE_FLAG = true;

        return true;
    }

    public synchronized void releaseWriteLock() {
        if(!WRITE_FLAG || writeThreadOwner != Thread.currentThread()) throw new IllegalStateException();

        writeThreadOwner = null;
        WRITE_FLAG = false;
        notifyAll();
    }
}


class WorkerThread implements Runnable {

    CustomReadWriteLock readWriteLock;

    WorkerThread(CustomReadWriteLock rwl) {
        this.readWriteLock = rwl;
    }

    public void read() throws InterruptedException {

        readWriteLock.acquireReadLock();
        try {
            // do something
        } finally {
            readWriteLock.releaseReadLock();
        }

    }

    public void write() throws InterruptedException {
        readWriteLock.acquireWriteLock();
        try {
            // do something
        } finally {
            readWriteLock.releaseWriteLock();
        }
    }

    @Override
    public void run() {
        System.out.println("Started ...");

        while(!Thread.currentThread().isInterrupted()) {
            // perform some Action
        }
    }
}
