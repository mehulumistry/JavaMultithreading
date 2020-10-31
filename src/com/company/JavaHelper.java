package com.company;

class JavaHelper {


    static int getIndexFromArray(int[] arr, int value) {
        for(int i=0;i<=arr.length-1;i++){
            boolean isContain = arr[i] == value;
            if(isContain) return i;
        }
        return -1;
    }

    static boolean isEven(int i) {
        return (i & 1) == 0;
    }

    boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n%2 == 0 || n%3 == 0) return false;
        long sqrtN = (long)Math.sqrt(n)+1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }


}
