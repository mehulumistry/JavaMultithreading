package com.company.multithreading;

import java.util.concurrent.Callable;

/**
 * Date: 11/28/20
 * Time: 5:28 PM
 */

// Ref: https://www.javaspecialists.eu/archive/Issue215-StampedLock-Idioms.html
// In stamped locking we also validate the time(stamp) which helps to read correct values.
// Will save us from A-B-A problem

public class StampedLock {

    public static void main(String[] args) {

        int noOfThreads = 200;


    }
}


class Point {

    // here we might have issues with data visibility. Each thread will have this x, y in their own stack.

    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public synchronized double distanceFromOrigin() {
        return Math.hypot(x, y);
    }

    public synchronized boolean moveIfAt(int oldX, int oldY,
                                         int newX, int newY) {
        if (x == oldX && y == oldY) {
            x = newX;
            y = newY;
            return true;
        } else {
            return false;
        }
    }
}

class Worker implements Callable<Double> {

    Point point;

    Worker(Point p) {
        this.point = p;
    }

    @Override
    public Double call() throws Exception {


        return null;
    }
}


