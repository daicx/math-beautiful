package com.skrtu.math.tree;

import java.util.ArrayList;
import java.util.List;

/***
 * @Author dcx
 * @Description //TODO
 * 遍历方式和根的取值顺序有关
 * 前序遍历: 遍历顺序为,根左右
 * 中序遍历: 遍历顺序为,左中根
 * 后序遍历: 遍历顺序为,左右根
 *
 * @Date 17:08 2020/6/3
 **/
public class PreListTree {
    class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /***
     * @Author dcx
     * @Description //前序遍历
     * @Date 17:08 2020/6/3
     * @Param [treeNode, list]
     * @return void
     **/
    private void preList(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        list.add(treeNode.val);
        preList(treeNode.left, list);
        preList(treeNode.right, list);
    }

    /***
     * @Author dcx
     * @Description //TODO 中序遍历
     * @Date 17:13 2020/6/3
     * @Param [treeNode, list]
     * @return void
     **/
    private void midList(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        preList(treeNode.left, list);
        list.add(treeNode.val);
        preList(treeNode.right, list);
    }

    /***
     * @Author dcx
     * @Description
     * 后续遍历
     * @Date 17:13 2020/6/3
     * @Param [treeNode, list]
     * @return void
     **/
    private void nextList(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        preList(treeNode.left, list);
        preList(treeNode.right, list);
        list.add(treeNode.val);
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> objects = new ArrayList<>();
        preList(root, objects);
        return objects;
    }
}
