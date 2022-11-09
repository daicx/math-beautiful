package com.skuu.math.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
 * 广度优先搜索
 */
public class BFS {

    public Integer maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<List<Integer>> objects = new ArrayList<>();
        LinkedList<TreeNode> link = new LinkedList<>();
        link.offer(root);
        while (!link.isEmpty()) {
            ArrayList<Integer> objects1 = new ArrayList<>();
            int size = link.size();
            //每一层
            for (int i = 0; i < size; i++) {
                TreeNode poll = link.poll();
                assert poll != null;
                objects1.add(poll.val);
                TreeNode left = poll.left;
                TreeNode right = poll.right;
                if (left != null) {
                    link.offer(left);
                }
                if (right != null) {
                    link.offer(right);
                }
            }
            objects.add(objects1);
        }
        return objects.size();
    }

    public static void main(String[] args) {
        String a = "aaa";
        String b = "aaa";
        System.out.println("aab" == "aab");
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
