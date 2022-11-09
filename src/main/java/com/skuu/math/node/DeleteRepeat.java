package com.skuu.math.node;

public class DeleteRepeat {

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;

        }
    }

    public static void dele(Node head) {
        if (head == null) {
            return;
        }
        int val = head.val;
        Node next = head.next;
        if (next == null){
            return;
        }
        if (next.val != val) {
            System.out.println(val);
        }
        dele(head.next);
    }

}
