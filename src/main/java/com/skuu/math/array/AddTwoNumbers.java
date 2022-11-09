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
 **/

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

public class AddTwoNumbers {
    /***
     * @Author dcx
     * @Description //TODO
     * 损耗:
     * 时间复杂度:O(max(m,n))
     * 空间复杂度:O(max(m,n))
     * 执行用时 :2 ms, 在所有 Java 提交中击败了99.93%的用户
     * 内存消耗 :39.5 MB, 在所有 Java 提交中击败了94.74%的用户
     *
     * @Date 17:25 2020/5/8
     * @Param [l1, l2]
     * @return com.skrtu.math.common.ListNode
     **/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //进位时携带的数
        int carry = 0;
        //结果node组成点
        ListNode result = new ListNode(0);
        //指向result的用来添加数据的节点
        ListNode tmp = result;
        while (l1 != null || l2 != null) {
            //取值
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            //加和
            int sum = a + b + carry;
            //大于10就要进位
            carry = sum / 10;
            //添加节点,如果进位了,添加的是余数
            tmp.next = new ListNode(sum % 10);
            //指针后移
            tmp = tmp.next;
            //移动
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        //判断是否发生最后一位进位
        if (carry > 0) {
            tmp.next = new ListNode(carry);
        }
        return result.next;
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

