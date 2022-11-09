package com.skuu.math.node;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class DeleteIndexAtN {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode listNode = new ListNode(0, head);
        ListNode second = listNode;
        int count = 0;
        while (first != null) {
            count++;
            if (count > n){
                second = second.next;
            }
            first = first.next;
        }
        second.next = second.next.next;
        return listNode.next;
    }
}
