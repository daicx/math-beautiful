package com.skrtu.math.node;

/***
 * @Author dcx
 * @Description //链表反转
 * @Date 16:34 2020/6/3
 **/
public class ReverseList {
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /***
     * @Author dcx
     * @Description //双指针
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :39.5 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @Date 16:34 2020/6/3
     * @Param [head]
     * @return com.skrtu.math.node.ReverseList.ListNode
     **/
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            //保存下一个节点
            next = cur.next;
            //将下一个节点指向pre
            cur.next = pre;
            //右移pre
            pre = cur;
            //右移cur
            cur = next;
        }
        return pre;
    }
}
