package com.company.multithreading;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * Date: 9/26/20
 * Time: 10:04 PM
 */


/* acquireLock on Object
*
* If other thread tries to acquire send it to wait queue
*
* releaseLock
*
* */

public class CustomLock {

    private Object lockObject = new Object();
    private Thread owner;
    private int lockCount = 0;

    public boolean acquireLock(int maxWait) throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        synchronized (lockObject) {
            if(owner == currentThread) {
                this.lockCount += 1;
                return true;
            }

            long waitedSoFar = 0L;
            while(owner != null) {
                long t0 = System.currentTimeMillis();
                long timeToWait = maxWait - waitedSoFar;

                if(timeToWait <= 0) return false;

                lockObject.wait(timeToWait);

                if(owner != null) waitedSoFar += System.currentTimeMillis() - t0;

            }

            owner = currentThread;
            lockCount = 1;
            return true;
        }
    }


    public void releaseLock() {

        synchronized (lockObject) {
            if(owner == Thread.currentThread()) {
                lockCount -=1;
                if(lockCount == 0) {
                    owner = null;
                    lockObject.notify();
                }
            } else {
                // Only owner can release the lock;
                throw new IllegalStateException();
            }
        }
    }

}
