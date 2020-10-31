package com.company;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Date: 9/13/20
 * Time: 2:07 PM
 */
/* Key is to build small functions to do remove, add and use those to build cmplex functions like  moveToFirst and moveToLast */
class KeyValuePair<K,V> {
    private K key;
    private V value;

    KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    KeyValuePair<K,V> getKeyValuePair() {
        return new KeyValuePair(key, value);
    }

    public void setKeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

}


class DNode<K,V> {

    public K key = null;
    private V value = null;
    DNode<K,V> left = null;
    DNode<K,V> right = null;

    DNode(){}

    public K getKey() {
        return key;
    }

    String printKeyValue() {
        return key + " " + value;
    }

    DNode(K k, V v) {
        this.key = k;
        this.value = v;
    }
}

class CustomDoublyLinkedList<K,V> implements Iterable<DNode<K,V>> {


    private DNode head;
    private DNode tail;

    private int totalElements = 0;

    CustomDoublyLinkedList() {
        /* This is important creating two separate node for head and tail*/
        head = new DNode();
        tail = new DNode();
        head.right = tail;
        tail.left = head;
    }

    public void push(DNode newNode) {
        addLast(newNode);
    }

    public int size() {return totalElements;}


    public DNode get(int indx) {
        int itrIndx = 0;
        Iterator itr = iterator();
        DNode temp = null;

        while(itrIndx <= indx) {
            if(itr.hasNext()) {
                temp = (DNode) itr.next();
            }
            itrIndx++;
        }
        return temp;
    }

    public void moveNodeToFirst(DNode newNode){
        addFirst(newNode);
        remove(newNode);
    }

    public void moveNodeToLast(DNode newNode){
        addLast(newNode);
        remove(newNode);
    }

    public DNode addFirst(DNode newNode){
        DNode headRight = head.right;
        headRight.left = newNode;
        newNode.right = headRight;

        head.right = newNode;
        newNode.left = head;
        totalElements += 1;
        return newNode;
    }

    public DNode addFirst(K key, V value){
        DNode newNode = new DNode(key, value);
        DNode headRight = head.right;
        headRight.left = newNode;
        newNode.right = headRight;

        head.right = newNode;
        newNode.left = head;
        totalElements += 1;
        return newNode;
    }

    public DNode addLast(DNode newNode){
        DNode tailLeft = tail.left;
        tailLeft.right = newNode;
        newNode.left = tailLeft;

        tail.left = newNode;
        newNode.right = tail;
        totalElements += 1;
        return newNode;
    }

    public DNode addLast(K key, V value){
        DNode newNode = new DNode(key, value);
        DNode tailLeft = tail.left;
        tailLeft.right = newNode;
        newNode.left = tailLeft;

        tail.left = newNode;
        newNode.right = tail;
        totalElements += 1;
        return newNode;
    }

    public void removeFirst(){
        DNode headRight = head.right;
        if(headRight != null){
            remove(headRight);
        }
    }

    public void removeLast(){

        DNode tailLeft = tail.left;
        if(tailLeft != null) {
            remove(tailLeft);
        }
    }

    public DNode pop() {
        DNode temp = tail.left;
        tail = null;
        tail = temp;
        return temp;
    }

    public void remove(K key) {
        Iterator itr = iterator();
        K temp = null;
        int itrIndx = 0;
        DNode tempNode = null;
        while(itrIndx < totalElements && key != temp) {
            if(itr.hasNext()) {
                DNode<K,V> res = (DNode<K,V>) itr.next();
                temp = res.getKey();
                tempNode = res;
            }
            itrIndx++;
        }

        if(tempNode != null) remove(tempNode);
    }

    public void remove(DNode delete) {
        DNode leftNode = delete.left;
        DNode rightNode = delete.right;
        if(leftNode != null) leftNode.right = rightNode;
        if(rightNode != null) rightNode.left = leftNode;
        if(leftNode != null || rightNode != null) totalElements -= 1;
    }


    @Override
    public Iterator iterator() {
        return new Iterator() {
            DNode ref = head;
            @Override
            public boolean hasNext() {
                return ref.right != null && ref.right.right != null;
            }

            @Override
            public DNode<K,V> next() {
                ref = ref.right;
                return ref;
            }
        };
    }
}

public class LinkedHashMap<K,V> {

    HashMap<K,KeyValuePair<K,V>> map = new HashMap<>();
    CustomDoublyLinkedList<K, KeyValuePair<K,V>> list = new CustomDoublyLinkedList<>();

    void insertElement(K key, V element) {
        KeyValuePair<K,V> kv = new KeyValuePair<>(key, element);
        map.put(key, kv);
        list.addLast(key, kv);
    }

    KeyValuePair<K, V> search(K key) {
        return map.get(key);
    }

    void delete(K key) {
        KeyValuePair<K,V> value = map.get(key);
        if(value != null) {
            map.remove(key);
            list.remove(key);
        } else {
            System.out.println("Key not found");
        }
    }

    Iterator<DNode> getIter() {
        return list.iterator();
    }

}

class TestLinkedHashMap {
    public static void main(String[] args) {

        CustomDoublyLinkedList<Integer, String> cdll = new CustomDoublyLinkedList<>();
        DNode d1 = new DNode(1, "first");
        DNode d2 = new DNode(2, "second");
        DNode d3 = new DNode(3, "third");
        DNode d4 = new DNode(4, "fourth");
        DNode d5 = new DNode(5, "fifth");
        DNode d6 = new DNode(6, "sixth");


        cdll.addLast(d3);
        cdll.addLast(d4);
        cdll.addLast(d5);
        cdll.addLast(d6);

        cdll.addFirst(d2);
        cdll.addFirst(d1);

        cdll.removeFirst();
        cdll.removeLast();

        cdll.remove(3);

        //System.out.println(cdll.size());
        System.out.println(cdll.get(3).printKeyValue());

        Iterator<DNode> itr = cdll.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next().printKeyValue());
        }

        LinkedHashMap<Integer, String> clhs = new LinkedHashMap();

        clhs.insertElement(1, "hello1");
        clhs.insertElement(2, "hello2");
        clhs.insertElement(3, "hello3");

        clhs.delete(2);
        clhs.delete(3);

        Iterator<DNode> itr2 = clhs.getIter();
        while (itr2.hasNext()) {
            System.out.println(itr2.next().printKeyValue());
        }


    }
}
