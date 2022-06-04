package com.skrtu.math.string;

import com.skrtu.math.node.ListNode;

public class Main {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode next = head.next;
        if (next == null) {
            return head;
        }
        if (head.val == next.val) {
            while (next != null) {
                if (next.val != head.val) {
                    break;
                }
                next = next.next;
            }
            head = deleteDuplicates(next);
        } else {
            head.next = deleteDuplicates(next);
        }
        return head;
    }


    public static void main(String[] args) {

    }
}
