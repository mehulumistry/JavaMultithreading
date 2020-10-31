package com.company;

import javax.sound.sampled.Line;
import java.util.List;

/**
 * Date: 9/11/20
 * Time: 1:41 AM
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution4 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        int carry = 0;
        boolean isCarry = false;

        ListNode ln = new ListNode(0, null);
        ListNode prev = ln;

        while (l1 != null || l2 != null) {
            boolean isl1Null = l1 == null;
            boolean isl2Null = l2 == null;

            ListNode temp1 = isl1Null ? new ListNode(0) : l1;
            ListNode temp2 = isl2Null ? new ListNode(0) : l2;

            int total = temp1.val + temp2.val + carry;

            int totalTemp = (total % 10);
            carry = total / 10;

            if(carry > 0) {
                isCarry = true;
            } else isCarry = false;


            ListNode temp = new ListNode(totalTemp);
            prev.next = temp;

            prev = prev.next;


            l1 = temp1.next;
            l2 = temp2.next;

        }

        if(isCarry){
            prev.next = new ListNode(carry, null);
        }

        return ln.next;
    }
}

class Main {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
       // ListNode l3 = new ListNode(3);

        l1.next = l2;
       // l2.next = l3;

        ListNode ll1 = new ListNode(1);
//        ListNode ll2 = new ListNode(6);
//        ListNode ll3 = new ListNode(4);

//        ll1.next = ll2;
//        ll2.next = ll3;

        ListNode t = new Solution4().addTwoNumbers(l1, ll1);
        System.out.println(t.val);
    }
}
