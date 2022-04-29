package com.skrtu.math.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BFS {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> arrs = new ArrayList<>();
        LinkedList<TreeNode> linkList = new LinkedList<>();
        linkList.offer(root);
        while (!linkList.isEmpty()) {
            int size = linkList.size();
            List<Integer> items = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = linkList.poll();
                if (poll != null) {
                    items.add(poll.getVal());
                    TreeNode right = poll.getRight();
                    TreeNode left = poll.getLeft();
                    if (left != null) {
                        linkList.offer(left);
                    }
                    if (right != null) {
                        linkList.offer(right);
                    }
                }
            }
            arrs.add(items);
        }
        return arrs;
    }

    public static void main(String[] args) {

    }
}
