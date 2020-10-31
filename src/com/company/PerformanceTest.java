package com.company;

/**
 * Date: 9/4/20
 * Time: 10:54 PM
 */
public class PerformanceTest {
    private static final long MEGABYTE = 1024L * 1024L;
    private static final long KILOBYTE = 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static long bytesToKilobytes(long bytes) {
        return bytes / KILOBYTE;
    }

    public static void usedMemInMB() {
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is Kilobytes: " + bytesToKilobytes(memory));
        System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));
    }
}
