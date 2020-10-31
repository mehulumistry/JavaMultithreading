package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 9/17/20
 * Time: 2:26 PM
 */

// synchronized keyword -> only allow one thread at a time -> synchronized(object)


public class TestingConcurrency {


    public static void main(String[] args) {

        List<Integer> al = new ArrayList<>();

        for(int i = 0; i<10; i++) {
            Thread t1 = new Thread(new Task("T"+i, al, 10));
            t1.start();
        }

    }

}

class Task implements Runnable {
    String name;
    final List<Integer> al;
    Integer size;

    Task(String tName, List<Integer> array, Integer id) {
        name = tName;
        size = id;
        al = array;
    }

    private synchronized void addSync(int i) {
        al.add(i);
    }

    @Override
    public void run() {
        System.out.println("Running Thread -> " + name);
        try {
            for(int i =0; i<size ;i++){
                Thread.sleep(10);
                synchronized(al){
                    al.add(i);
                    System.out.println("Thread -> " + this.name + " "+ i + " size " + al.size());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

