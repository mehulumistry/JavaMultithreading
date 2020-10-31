package com.company.multithreading;


/**
 * Date: 9/26/20
 * Time: 1:14 AM
 */

/* Implement your own Java Future
*  Task, Promise and Async Await.
* */


// Submitting Callable to ThreadPool service
// Implement the below with simple threads
// Also try
// 1) Make thread pool run some task in it.
// 2) The variable which it updates should reflect in the second thread service or Thread Pool

// How you reuse the thread? You can only start the thread once then how thread reuse concept works?

/** JavaFuture is blocking one with .get() method
 *  CompletableFuture is NonBlocking and Async it sends the task to Pool and when the task completes it
 *  call the callback function with the return value.
 *
 *  Await is like blocking Future. Future.get()
 *
 * */


public class CustomFuture {


    public static void main(String[] args) {

        // tryAcquire and return callable

    }
}
