package com.skrtu.math.node;

//https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/submissions/
public class DeleteRepeat2 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        int val = head.val;
        ListNode next = head.next;
        if (next == null) {
            return head;
        }
        if (next.val != val) {
            //此处把递归回来的数据,进行了再复制
            head.next = deleteDuplicates(head.next);
        } else {
            //往后找出不相同的值
            while (next != null) {
                if (next.val != val) {
                    break;
                }
                next = next.next;
            }
             head = deleteDuplicates(next);
        }
        return head;
    }

}
