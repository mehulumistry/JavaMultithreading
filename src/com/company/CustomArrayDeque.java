package com.company;

import java.util.ArrayDeque;

/**
 * Date: 9/18/20
 * Time: 9:15 AM
 */

/**/
public class CustomArrayDeque<K> {

    //private int size = 0;

    private Object[] elements;
    private transient int tail;
    private transient int head;

    CustomArrayDeque(int capacity) {
        this.elements = new Object[capacity];
        this.head = - 1;
        this.tail = elements.length;
    }

    public void doubleCapacity() {
        int length = elements.length;
        int newCapacity = length * 2;

        int numberOfElementsToRight = head == -1     ? 0  : length - head;
        int numberOfElementsToLeft  = tail == length ? 0  : tail + 1;

        int newTail = numberOfElementsToLeft == 0  ? newCapacity : tail;
        int newHead = numberOfElementsToRight == 0 ? head : newCapacity - numberOfElementsToRight;

        if(newCapacity < 0) throw new IllegalStateException("Queue too big");
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, numberOfElementsToLeft);

        if(numberOfElementsToRight > 0) {
            System.arraycopy(elements, head, newElements, newHead, numberOfElementsToRight);
        }

        head = newHead;
        tail = newTail;
        this.elements = newElements;
    }

    public void addFirst(K k) {
        int temp = head - 1;

        if(head == -1){
            temp = elements.length - 1;
        }

        if(temp < 0 || temp == tail) {
            doubleCapacity();
            addFirst(k);
        } else {
            head = temp;
        }

        elements[head] = k;
    }

    public void addLast(K k) {
        int temp = tail + 1;

        if(tail == elements.length) {
            temp = 0;
        }
        if(temp >= elements.length || temp == head) {
           doubleCapacity();
        }
        tail = temp;

        elements[tail] = k;
    }

    public K removeFirst() {
        if(head >= elements.length) return null;
        if(head == -1) {
            head = head + 1;
        }
        K res = (K) elements[head];
        elements[head] = null;
        head = head + 1;
        return res;

    }

    public K removeLast() {
        if(tail < 0) return null;
        if(tail == elements.length) {
            tail = elements.length - 1;
        }
        K res = (K) elements[tail];
        elements[tail] = null;
        tail = tail - 1;
        return res;
    }

    public K getFirst() {
        return (K) elements[head];
    }

    public K getLast() {
        return (K) elements[tail];
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    @Override
    public String toString() {
        String[] logs = new String[this.elements.length];
        for (int i = 0; i< elements.length; i++) {
            if(elements[i] != null) {
                logs[i] = elements[i].toString();
            }
        }
        return String.join(",", logs);
    }
}


class CustomArrayDequeTest {
    public static void main(String[] args) {
        CustomArrayDeque<Integer> cad = new CustomArrayDeque<>(0);

        cad.addFirst(1);
        cad.addFirst(2);
        cad.addLast(5);
        cad.addLast(6);
        cad.addFirst(9);

        System.out.println(cad.toString());

        cad.removeFirst();
        cad.removeLast();
        cad.removeLast();

        cad.addFirst(1);
        cad.addFirst(2);
        cad.addLast(5);
        cad.addLast(6);
        cad.addFirst(9);

        System.out.println(cad.toString());


        CustomArrayDeque<String> stackString = new CustomArrayDeque<>(1);
        stackString.addFirst("a");
        stackString.addFirst("b");
        stackString.addFirst("c");
        stackString.addFirst("d");

        System.out.println(stackString.removeFirst());
        System.out.println(stackString.removeFirst());
        System.out.println(stackString.removeFirst());
        System.out.println(stackString.removeFirst());
        System.out.println(stackString.removeFirst());
        System.out.println(stackString.removeFirst());

        CustomArrayDeque<String> queue = new CustomArrayDeque<>(1);

        queue.addLast("a");
        queue.addLast("b");
        queue.addLast("c");
        queue.addLast("d");

        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());


        ArrayDeque<Integer> t = new ArrayDeque<>();

        t.addFirst(1);
        t.addFirst(2);
        t.addFirst(3);
        t.addFirst(4);

        t.removeLast();
        t.removeLast();
        t.removeLast();


    }
}
