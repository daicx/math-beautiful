package com.skuu.math.array;


/***
 *给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * <a href="https://leetcode.cn/problems/add-two-numbers/">...</a>
 **/

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }
}

public class AddTwoNumbers {
    /***
     * 关键点：
     * 1。链表的循环。
     * 2。链表的新增。
     * 损耗:
     * 时间复杂度:O(max(m,n))
     * 空间复杂度:O(max(m,n))
     *
     * @Date 17:25 2020/5/8
     * @Param [l1, l2]
     * @return com.skrtu.math.common.ListNode
     **/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryNum = 0;
        ListNode res = new ListNode();
        ListNode tmp = res;
        while (l1 != null && l2 != null) {
            int val1 = l1.val;
            int val2 = l2.val;
            int sum = val1 + val2 + carryNum;
            if (sum > 9) {
                carryNum = 1;
                tmp.next = new ListNode(sum - 10);
            } else {
                carryNum = 0;
                tmp.next = new ListNode(sum);
            }
            tmp = tmp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int val = l1.val;
            int sum = val + carryNum;
            if (sum > 9) {
                carryNum = 1;
                tmp.next = new ListNode(sum - 10);
            } else {
                carryNum = 0;
                tmp.next = new ListNode(sum);
            }
            tmp = tmp.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int val = l2.val;
            int sum = val + carryNum;
            if (sum > 9) {
                carryNum = 1;
                tmp.next = new ListNode(sum - 10);
            } else {
                carryNum = 0;
                tmp.next = new ListNode(sum);
            }
            tmp = tmp.next;
            l2 = l2.next;
        }
        if (carryNum > 0) {
            tmp.next = new ListNode(carryNum);
        }
        return res.next;
    }

    public static void main(String[] args) {
        //第一个node
        ListNode listNode2 = new ListNode(2);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode4;
        listNode4.next = listNode3;
        //第二个node
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l4 = new ListNode(4);
        l5.next = l6;
        l6.next = l4;

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode listNode = addTwoNumbers.addTwoNumbers(listNode2, l5);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }
}

