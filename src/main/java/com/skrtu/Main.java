package com.skrtu;

import com.skrtu.math.node.ListNode;

/**
 * https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
 */
public class Main {


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;
        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }
        return pa;
    }


    public static void main(String[] args) {

    }
}
