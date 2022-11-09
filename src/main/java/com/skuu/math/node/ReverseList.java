package com.skuu.math.node;

/***
 * @Author dcx
 * @Description //链表反转
 * @Date 16:34 2020/6/3
 * https://leetcode.cn/problems/reverse-linked-list/submissions/
 **/
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur;
        while (head != null) {
            ListNode next = head.next;
            cur = head;
            cur.next = pre;
            pre = cur;
            head = next;
        }
        return pre;
    }
}
