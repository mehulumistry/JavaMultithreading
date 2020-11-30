package com.company.multithreading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * Date: 11/28/20
 * Time: 3:18 PM
 */


public class AdderAndAccumulator {


    public static void main(String[] args) {

        /* Adder and Accumulator users Striped64 class */

        LongAdder counter = new LongAdder();

        Runnable stream = () -> IntStream
                        .range(0, 1)
                        .forEach(ele -> counter.increment());

        var noOfThreads = 16;
        var executorService = Executors.newFixedThreadPool(noOfThreads);

        for (int i = 0; i < noOfThreads; i++) {
            executorService.submit(stream);
        }

        System.out.println(counter.sum());

        /* We use ConcurrentHashMap when a high level of concurrency is required.
        But already SynchronizedMap is present so what advantages does ConcurrentHashMap
        have over synchronized map.Both are thread safe. The major advantage is in case of
        synchronizedMap every write operation acquires lock on entire SynchronizedMap
         while in case of ConcurrentHashMap the lock is only on one of the segments.*/

        // Read dynamic Striping -> https://www.baeldung.com/java-longadder-and-longaccumulator

        // Concept is similar to segment level locking ~ ConcurrentHashMap
        // Lock-free solution

        LongAccumulator accumulator = new LongAccumulator(Long::sum, 0L);
        Runnable streamAcc = () -> IntStream
                .range(0, 1)
                .forEach(accumulator::accumulate);



    }


}
