package com.skuu.math;

import com.skuu.math.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 测试类
 *
 * @author dcx
 * @since 2023-02-20 16:58
 **/
public class MathTest {
    static class Node {
        public int num;
        public Node next;
    }

    public static void transferNode(TreeNode node) {

        ArrayList<List<Integer>> objects = new ArrayList<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        if (node == null){
            return;
        }
        linkedList.add(node);
        while (!linkedList.isEmpty()){
            int size = linkedList.size();
            ArrayList<Integer> listTmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = linkedList.poll();
                listTmp.add(poll.val);
                TreeNode right = poll.right;
                if (right!= null){
                    linkedList.add(right);
                }
                TreeNode left = poll.left;
                if (left!= null){
                    linkedList.add(left);
                }
            }
            objects.add(listTmp);
        }
    }

    public static void main(String[] args) {

    }
}
