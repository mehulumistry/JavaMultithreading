package com.company;

import java.util.List;

/**
 * Date: 9/13/20
 * Time: 3:28 PM
 */

class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {

        if(head != null) {

            ListNode reverseLN = new ListNode(head.val, null);

            while (head.next != null) {
                ListNode temp = head.next;
                reverseLN = new ListNode(temp.val, reverseLN);
                head = head.next;
            }

            return reverseLN;
        } else {
            return head;
        }

    }

}

class ReverseListTest {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(3);

        l1.next = l2;
        l2.next = l3;

        ListNode rll = new ReverseLinkedList().reverseList(l1);
        System.out.println(rll);

    }
}
