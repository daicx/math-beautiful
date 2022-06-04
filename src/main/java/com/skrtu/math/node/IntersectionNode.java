package com.skrtu.math.node;

import java.util.HashMap;

/***
 * @Author dcx
 * @Description //链表相交
 * 时间复杂度 O(m+n)
 *
 * 空间复杂度 O(1)
 *
 * 执行用时 :1 ms, 在所有 Java 提交中击败了99.97%的用户
 *
 * 内存消耗 :42.9 MB, 在所有 Java 提交中击败了57.14%的用户
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/comments/
 * @Date 14:20 2020/6/3
 * @Param
 * @return
 **/
public class IntersectionNode {
    class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /***
     * @Author dcx
     * @Description //map消除双层循环法
     *
     * 时间复杂度 O(m+n)
     *
     * 空间复杂度 O(n)
     *
     * 执行用时 :9 ms, 在所有 Java 提交中击败了17.06%的用户
     *
     * 内存消耗 :43.5 MB, 在所有 Java 提交中击败了7.14%的用户
     *
     * @Date 15:08 2020/6/3
     * @Param [headA, headB]
     * @return com.skrtu.math.node.IntersectionNode.ListNode
     **/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode, Integer> map = new HashMap<>();
        while (headA != null) {
            map.put(headA, 1);
            headA = headA.next;
        }
        while (headB != null) {
            if (map.containsKey(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /***
     * @Author dcx
     * @Description //双指针算法
     * @Date 15:20 2020/6/3
     * @Param [headA, headB]
     * @return com.skrtu.math.node.IntersectionNode.ListNode
     **/
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode tmpA = headA, tmpB = headB;
        while (tmpA != tmpB) {
            tmpA = tmpA == null ? headB : tmpA.next;
            tmpB = tmpB == null ? headA : tmpB.next;
        }
        return tmpA;
    }
}
