package com.company.multithreading;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 11/11/20
 * Time: 5:50 PM
 */

/* https://stackoverflow.com/questions/32549628/java-volatile-synchronization-on-arraylist
* Create new copy of list instead of waiting to read.
*
* */
public class TestUdemy {
    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<>();

        Producer producer = new Producer(buffer);
        Consumer consumer1 = new Consumer(buffer);
        Consumer consumer2 = new Consumer(buffer);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();

    }//end of main
}//end of class Test


class Producer implements Runnable{
    private List<String> list;

    private final static Object lock = new Object();

    public Producer(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {

        String[] numbers = {"1","2","3","4","5"};

        for (String x: numbers){

            System.out.println("Adding number: " + x);

            //synchronized (list) {
                list.add(x);
            //}

            System.out.println(list);

//            try {
//                /* Thread sleep wont give up on the lock. */
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        System.out.println("Finished writing numbers, wrote 'EOF'");
        list.add(TestUdemy.EOF);


    }//end of run

}//end of Producer


class Consumer implements Runnable {
    private List<String> list;
    private final static Object lock = new Object();

    public Consumer(List<String> list) {
      this.list = list;

    }

    @Override
    public void run() {
        while (true){
            try {
                //synchronized (lock) {
                    if (list.isEmpty()){
                        continue;
                    }

                    if (list.get(0).equals(TestUdemy.EOF)){
                        System.out.println("exiting..");
                        break;
                    } else {
                        System.out.println(list);
                        System.out.println("Removed " + list.remove(0));

                    }
               // }

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());

            }
        }//end of endless loop
    }//end or run
}//end of Consumer
