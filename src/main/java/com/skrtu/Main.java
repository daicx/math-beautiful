package com.skrtu;

import com.skrtu.math.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
 *
 */
public class Main {


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<List<Integer>> objects = new ArrayList<>();
        LinkedList<TreeNode> link = new LinkedList<>();
        link.offer(root);
        while (!link.isEmpty()) {
            ArrayList<Integer>  objects1 = new ArrayList<>();
            int size = link.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = link.poll();
                if (poll == null) {
                    break;
                }
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
        System.out.println(objects);
        return objects.size();
    }


    public static void main(String[] args) {

    }
}
