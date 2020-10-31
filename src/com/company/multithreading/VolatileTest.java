package com.company.multithreading;

/**
 * Date: 9/25/20
 * Time: 10:22 PM
 */


public class VolatileTest {

    public static void main(String[] args) {

        // What I think volatile is?
        /*
         * if you make variable volatile then JVM will not cache it and it will be in the main memory.
         * So T1 and T2 will see the same variable and will update the variable in same location, instead in their own caches
         * So I think it is not thread safe but it is memory safe.
         *
         */

        // if volatile is thread safe? -> No

        int count = 0;



    }

}


