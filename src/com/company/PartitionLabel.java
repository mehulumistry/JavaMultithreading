package com.company;

import java.util.*;

/**
 * Date: 9/4/20
 * Time: 11:56 AM
 */

/* Sorted hash Map, what to use? */

public class PartitionLabel {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Solution1 sol = new Solution1();
        System.out.println(sol.partitionLabels("khjhkjgkjhjkhkjhjkhkjhkhk"));
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        PerformanceTest.usedMemInMB();
    }
}

class Solution1 {

    public List<Integer> partitionLabels(String S) {

        HashMap<Character, Integer> endIndxMap = new HashMap<>();
        List<Integer> partLab = new ArrayList<>();

        for(int i=0;i<S.length();i++){
            char ch = S.charAt(i);
            if(endIndxMap.get(ch) != null) endIndxMap.put(ch, i);
            else endIndxMap.put(ch, i);
        }

        int j = 0;
        int subStrStart = 0;

        for(int i=0; i<S.length();i++){
            char chr = S.charAt(i);

            j = Math.max(j, endIndxMap.get(chr));

            if(i==j) {
                partLab.add(i - subStrStart + 1);
                subStrStart = i + 1;
            }
        }

        return partLab;
    }
}
