package com.yz.binarytreedemo.entity;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public List<Node> getResult() {
        return result;
    }

    public void setResult(List<Node> result) {
        this.result = result;
    }

    public Tree() {
        init();
    }

    private void init() {
        Node x = new Node("X", null, null);
        Node y = new Node("Y", null, null);
        Node d = new Node("D", x, y);
        Node b = new Node("B", d, null);
        Node e = new Node("E", null, null);
        Node f = new Node("F", null, null);
        Node c = new Node("C", e, f);
        Node a = new Node("A", b, c);
        root = a;
    }

    // 根节点
    private Node root;
    private List<Node> result = new ArrayList<Node>();

    // 前序遍历方法 【根左右】
    public void preOrder(Node node) {

        result.add(node);

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

        result.add(node);

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

        result.add(node);

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

    public static void main(String[] args) {

        Tree tree = new Tree();
        System.out.println("根节点：" + tree.getRoot().getData());
        // 先序排列
        tree.preOrder(tree.getRoot());
        System.out.println("树的深度是：" + tree.caclDepth(tree.getRoot()));
        System.out.println("先序排列后结果：");
        for (Node node : tree.getResult()) {

            System.out.print(node.getData() + "-->");

        }
        // 中序排列
        System.out.println();
        Tree tree2 = new Tree();
        tree2.inOrder(tree2.getRoot());
        System.out.println("中序排列后结果：");
        for (Node node : tree2.getResult()) {

            System.out.print(node.getData() + "-->");

        }
        // 后序排列
        System.out.println();
        Tree tree3 = new Tree();
        tree3.postOrder(tree3.getRoot());
        System.out.println("后序排列后结果：");
        for (Node node : tree3.getResult()) {
            System.out.print(node.getData() + "-->");

        }
        System.out.println();
    }

}