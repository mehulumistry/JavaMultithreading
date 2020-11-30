package com.company.multithreading;

/**
 * Date: 11/29/20
 * Time: 11:52 AM
 */
import java.util.List;
import java.util.stream.Stream;

public class MultiExecutor {

    // Add any necessary member variables here

    Stream<Thread> threads = Stream.empty();

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        // Complete your code here

        var allThreads = tasks.stream().map(task -> {
            var t = new Thread(task);
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return t;
        });
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here

        threads.forEach(Thread::start);
    }
}