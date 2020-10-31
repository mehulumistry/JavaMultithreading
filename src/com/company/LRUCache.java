package com.company;

import java.util.*;
import java.util.LinkedHashMap;

/**
 * Date: 9/13/20
 * Time: 4:44 PM
 */

class Node {
    int value;
    int key;
    Node(int k, int v) {
        this.key = k;
        this.value = v;
    }
}



public class LRUCache {

    private LinkedHashMap<Integer, Integer> cacheMap;

    public LRUCache(int capacity) {
        this.cacheMap = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cacheMap.getOrDefault(key, -1);
    }


    public void put(int key, int value) {
        System.out.println("size " + cacheMap.size());
        cacheMap.put(key, value);
    }
}



class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(3);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        lRUCache.put(3, 3);
        lRUCache.put(4, 4);
        System.out.println(lRUCache.get(4));    // return 3
        System.out.println(lRUCache.get(3));    // return 4
        System.out.println(lRUCache.get(2));    // return 3
        System.out.println(lRUCache.get(1));    // return 4

        lRUCache.put(5, 5);


//        System.out.println(lRUCache.get(1));    // return 1
//        lRUCache.put(1, 5); // evicts key 2
//        lRUCache.put(1, 2);
        System.out.println(lRUCache.get(1));    // return 3
        System.out.println(lRUCache.get(2));    // return 4
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
        System.out.println(lRUCache.get(5));    // return 3
//        Comparator<CacheValue> comp = new Comparator<CacheValue>() {
//            @Override
//            public int compare(CacheValue o1, CacheValue o2) {
//                if(o1.value < o2.value) {
//                    return 1;
//                } else if(o1.value > o2.value) {
//                    return -1;
//                } else return 0;
//            }
//        };

//        PriorityQueue<CacheValue> pq = new PriorityQueue<>(comp);
//
//
//        pq.add(new CacheValue(1, 5));
//        pq.add(new CacheValue(4, 10));
//        pq.add(new CacheValue(7, -49));
//        pq.add(new CacheValue(10, 0));
//
//        System.out.println(pq.poll().value);
//        System.out.println(pq.poll().value);
//        System.out.println(pq.poll().value);
//        System.out.println(pq.poll().value);

        HashSet<Integer> ss = new HashSet<>();



    }

}
