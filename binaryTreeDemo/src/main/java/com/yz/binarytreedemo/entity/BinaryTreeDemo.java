package com.yz.binarytreedemo.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/8 11:41
 */
@Data
public class BinaryTreeDemo {

    // 根节点
    private Node root;
    private List<Node> nodeList = new ArrayList<Node>();

    // 前序遍历方法 【根左右】
    public void preOrder(Node node) {

//        nodeList.add(node);

        if (node.getLChild() != null) {

            preOrder(node.getLChild());

        }

        if (node.getRChild() != null) {

            preOrder(node.getRChild());

        }

    }

    // 中序遍历法【左根右】
    public void inOrder(Node node) {

        if (node.getLChild() != null) {

            inOrder(node.getLChild());

        }

//        nodeList.add(node);

        if (node.getRChild() != null) {

            inOrder(node.getRChild());

        }

    }

    // 后序遍历法【左右根】
    public void postOrder(Node node) {

        if (node.getLChild() != null) {

            postOrder(node.getLChild());

        }

        if (node.getRChild() != null) {

            postOrder(node.getRChild());

        }

//        nodeList.add(node);

    }


    // 计算深度
    public int caclDepth(Node node) {

        if (node.getLChild() == null && node.getRChild() == null) {

            return 1;

        }

        int leftDepth = 0;
        int rightDepth = 0;
        if (node.getLChild() != null) {

            leftDepth = caclDepth(node.getLChild());

        }
        if (node.getRChild() != null) {

            rightDepth = caclDepth(node.getRChild());

        }

        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;

    }

    public void creat(Object[] objs) {
        nodeList = new ArrayList<Node>();
        //        将一个数组的值依次转换为Node节点
        for (Object o : objs) {
            nodeList.add(new Node(o,null,null));
        }
//        第一个数为根节点
        root = nodeList.get(0);
//        建立二叉树
        for (int i = 0; i < objs.length / 2; i++) {
//            左孩子
            nodeList.get(i).setLChild(nodeList.get(i * 2 + 1));
//            右孩子
            if (i * 2 + 2 < nodeList.size()) {//避免偶数的时候 下标越界
                nodeList.get(i).setRChild(nodeList.get(i * 2 + 2));
            }

        }

    }


    public static void main(String[] args) {
        BinaryTreeDemo tree = new BinaryTreeDemo();
        Object[] a = {2, 4, 5, 7, 32, 51, 22};
        tree.creat(a);
        System.out.println("根节点：" + tree.getRoot().getData());
        // 先序排列
        tree.preOrder(tree.getRoot());
        System.out.println("树的深度是：" + tree.caclDepth(tree.getRoot()));
        System.out.println("先序排列后结果：");
        for (Node node : tree.getNodeList()) {

            System.out.print(node.getData() + "-->");

        }
        // 中序排列
        System.out.println();
        BinaryTreeDemo tree2 = new BinaryTreeDemo();
        tree2.creat(a);
        tree2.inOrder(tree2.getRoot());
        System.out.println("中序排列后结果：");
        for (Node node : tree2.getNodeList()) {

            System.out.print(node.getData() + "-->");

        }
        // 后序排列
        System.out.println();
        BinaryTreeDemo tree3 = new BinaryTreeDemo();
        tree3.creat(a);
        tree3.postOrder(tree3.getRoot());
        System.out.println("后序排列后结果：");
        for (Node node : tree3.getNodeList()) {
            System.out.print(node.getData() + "-->");
        }
        System.out.println();
    }

}

