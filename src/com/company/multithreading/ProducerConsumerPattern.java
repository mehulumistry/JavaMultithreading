package com.company.multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Date: 11/28/20
 * Time: 9:40 PM
 */
public class ProducerConsumerPattern {

    public static void main(String[] args) {

        Supplier<Integer> createItem = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return (int) (Math.random() * 10);
        };

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        Runnable producer = () -> {
            while(true) {
                queue.add(createItem.get());
            }
        };

        Runnable consumer = () -> {
            while(true) {
                System.out.println(queue.poll());
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();

        new Thread(consumer).start();
        new Thread(consumer).start();

        new Thread(producer).start();
        new Thread(consumer).start();



    }
}

// You can build producer/consumer with semaphore and with back-pressure.

// Copying data from the main-memory to cache
// and caches communicates with each other with cache coherency protocol(MOSI).
/*  Using this protocol, only one core can modify a variable at a time. If another core needs to
modify it too it will make sure to operate on the most up to date value,
either by forcing the first core to do a "writeback" or by "snooping" the value from the other core.
https://www.udemy.com/course/java-multithreading-concurrency-performance-optimization/learn/lecture/11200058#questions/12951292

Race conditions happen not because of that. What I described now is an atomic operation,
race conditions happen because of concurrent execution of non atomic operations.

Watch Scalable Producer and Consumer video

*/

/* What can be the problem in below code? */

class Message {
    private String message;
    private Semaphore reader = new Semaphore(0);
    private Semaphore writer = new Semaphore(1);

    public String read() {
        try{
            reader.acquire();
        } catch (InterruptedException e) {

        }
        // Problem is here!
        // when writer.release calls two threads can come into place.
        writer.release();
        // Another thread can can write() and next message may get return, and the original msg can be lost;
        //simple solution is:
//        String tempMessage = message;
//        writer.release();
//        return tempMessage;

        return message;
    }

    public void write(String message) {

        try{
            writer.acquire();
        } catch (InterruptedException e) {

        }
        this.message = message;
        reader.release();
    }
}

class Writer implements Runnable {
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    public void run() {
        String messages[] = {
                "Humpty Dumpty sat on a wall",
                "Humpty Dumpty had a great fall",
                "All the king's horses and all the king's men",
                "Couldn't put Humpty together again"
        };

        Random random = new Random();

        for (int i = 0; i<messages.length; i++) {
            message.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch(InterruptedException e) {

            }
        }
        message.write("Finished");
    }
}

class Reader implements Runnable {
    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    public void run() {
        Random random = new Random();
        for(String latestMessage = message.read(); !latestMessage.equals("Finished");
            latestMessage = message.read()) {
            System.out.println(latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch(InterruptedException e) {

            }
        }
    }
}
