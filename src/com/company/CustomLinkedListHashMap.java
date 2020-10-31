package com.company;

/**
 * Date: 9/14/20
 * Time: 1:18 PM
 */

import java.lang.ref.WeakReference;

/**
 *
 * Implement WeakHashMap/table -> Use WeakReference and ReferenceQueue -> for sending objects for garbage collection https://www.baeldung.com/java-weakhashmap
 *
 *Implement your own general stack queue DS
 * ArrayDeque -> primitive way of implementing Stacks and Queues, faster bcs locality of references
 *
 * From Scratch:
 *  * Search, Remove, Delete, Order preserve (access, insertion) O(1)
 *  * Try to replicate LinkedHashMap
 *  * LinkedHashSet, -> implement with linkedList and also try with arrays
 *
 * Access Most recent -> how will you do?
 *
 * IdentityHashMap -> which uses identity instead of hashCode, saves time
 * One important case is where you are dealing with reference types (as opposed to values) and you really want the correct result. Malicious objects can have overridden hashCode and equals methods getting up to all sorts of mischief. Unfortunately, it's not used as often as it should be. If the interface types you are dealing with don't override hashCode and equals, you should typically go for IdentityHashMap.
 *
 * Implement Navigable Map -> SortedMap -> TreeMap
 *
 * FWIW, TreeMap and TreeSet are not "tree APIs". They are tree-based implementations of the Map and Set APIs. The tree-ness is totally concealed by the public APIs, making these two classes completely unsuitable for use as general purpose trees.
 *
 *  Also think in terms of Threads how you will handle and how they handle it
 *
 **/

/* Identity HashMap */

//        Map<String, String> hm = new HashMap<>();
//        Map<String, String> ihm = new IdentityHashMap<>();
//
//        // Putting key and value in HashMap and IdentityHashMap Object
//        hm.put("hmkey","hmvalue");
//        hm.put(new String("hmkey"),"hmvalue1");
//        ihm.put("ihmkey","ihmvalue");
//        ihm.put(new String("ihmkey"),"ihmvalue1");
//
//        // Print Size of HashMap and WeakHashMap Object
//        // hm.size() will print 1 since it compares the objects logically
//        // and both the keys are same
//        System.out.println("Size of HashMap is : "+hm.size());
//
//        // ihm.size() will print 2 since it compares the objects by reference
//        System.out.println("Size of IdentityHashMap is : "+ihm.size());

class IntegerKey implements CustomHashCode {
    private int key;
    IntegerKey(int k) {
        this.key = k;
    }
    @Override
    public int getHashCode() {
        return key;
    }
}

public class CustomLinkedListHashMap {
    public static void main(String[] args) throws Exception {


        CustomHashMap chm = new CustomHashMap(5);

        IntegerKey k1 = new IntegerKey(2);
        IntegerKey k2 = new IntegerKey(4);

        chm.put(k1, "hello_key1");
        chm.put(k2, "hello_key2");

        System.out.println(chm.get(k2));
        System.out.println(chm.get(new IntegerKey(9)));
    }
}

interface CustomHashCode {
    public int getHashCode();
}

//class CustomKey implements CustomHashCode {
//
//    @Override
//    public int getHashCode() {
//        return new Random().nextInt();
//    }
//}

class Entry<K extends CustomHashCode, V> {

    private K key;
    private V value;

    Entry(){}

    Entry(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return key.getHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj);
    }
}

class CustomHashMap<K extends CustomHashCode,V> extends Entry<K,V> {

    private int size = 0;

    // insert Node in Object table if you want iterator
    /* Object -> V */
    private Object[] table ;

    CustomHashMap(int capacity) {
        new Entry<K,V>();
        table = new Object[capacity];
        size = capacity;
    }

    public void put(K key, V value) throws Exception {
        Entry e = new Entry<>(key, value);
        int hashCode = e.getKey().getHashCode();
        if(hashCode < size) {
            table[hashCode] = e.getValue();
        } else throw new Exception("Increase the HashMap size to at least " + hashCode);
    }

    public V get(K key) {
        int hashCode = key.getHashCode();
        Object value = table[hashCode];
        try {
            return (V) value;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



}

class WeakCustomHashMap<K extends CustomHashCode,V> extends Entry<K,V>  {

    CustomHashMap<K,V> chm = new CustomHashMap<>(10);

    WeakCustomHashMap(K key) {
        WeakReference<K> wr = new WeakReference<K>(key);
        wr.enqueue();
    }

}

