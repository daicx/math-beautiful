package com.skrtu.math.tree;

/***
 * @Author dcx
 * @Description //TODO
 * 遍历方式和根的取值顺序有关
 * 前序遍历: 遍历顺序为,根左右
 * 中序遍历: 遍历顺序为,左根右
 * 后序遍历: 遍历顺序为,左右根
 *
 * @Date 17:08 2020/6/3
 **/
public class PreListTree {
    private static StringBuilder str = new StringBuilder();

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    public static void preScan(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        str.append(treeNode.val);
        preScan(treeNode.left);
        preScan(treeNode.right);
    }

    public static void middleScan(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        preScan(treeNode.left);
        str.append(treeNode.val);
        preScan(treeNode.right);
    }

    public static void proScan(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        preScan(treeNode.left);
        preScan(treeNode.right);
        str.append(treeNode.val);
    }


    public static void main(String[] args) {
        TreeNode treeNode = initData();
//        preScan(treeNode);
        proScan(treeNode);
        System.out.println(str);
    }


    public static TreeNode initData() {
        TreeNode treeNode = new TreeNode(0);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode2.right = treeNode4;
        return treeNode;
    }
}
