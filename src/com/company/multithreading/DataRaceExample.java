package com.company.multithreading;

import java.util.ArrayList;

/**
 * Date: 11/12/20
 * Time: 11:39 AM
 */

public class DataRaceExample {



    public static void main(String[] args) {

        SharedDataRace dataRace = new SharedDataRace();

        Thread icrementThread = new Thread(new DataRaceIcrementThread(dataRace));

        Thread checkThread = new Thread(new DataRaceCheckThread(dataRace));



        icrementThread.start();

        checkThread.start();

    }



    public static class DataRaceIcrementThread extends Thread {



        SharedDataRace dataRace;



        public DataRaceIcrementThread(SharedDataRace dataRace) {

            this.dataRace = dataRace;

        }



        @Override

        public void run() {

            for (int i = 0;i<1000000;i++)

                dataRace.increment();

        }

    }



    public static class DataRaceCheckThread extends Thread {



        SharedDataRace dataRace;



        public DataRaceCheckThread(SharedDataRace dataRace) {

            this.dataRace = dataRace;

        }



        @Override

        public void run() {



            for (int i = 0;i<1000000;i++)

                dataRace.checkDataRace();

        }

    }

    public static class SharedDataRace {

        private int x;

        private int y;



        public void increment() {

            Object mutex = new Object();

            synchronized (mutex) {

                x++;

                y++;

            }

        }



        public void checkDataRace() {

            Object mutex = new Object();

            synchronized (mutex) {

                if (y>x) {

                    System.out.println("Data Race Condition Reached and y : x "+y+" : "+x);

                }

            }

        }



    }

}
