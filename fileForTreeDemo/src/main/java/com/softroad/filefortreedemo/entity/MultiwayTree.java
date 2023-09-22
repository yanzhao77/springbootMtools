package com.softroad.filefortreedemo.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * @classname AbstractTree
 * @date 2022/8/17 16:05
 * @description TODO
 */


public abstract class MultiwayTree {

    @Getter
    Node root = new Node("root", 0);    //树的根节点


    /**
     * 查询
     *
     * @return
     */
    public Node loopById(String nodeId) {
        return loopById(nodeId, getRoot(), 0);
    }

    /**
     * 查询
     *
     * @return
     */
    public Node loopById(String nodeId, Node parentNode, int nodeItemNum) {
        Node node = null;

        if (nodeId.equals(parentNode.getId())) {
            node = parentNode;
        } else {
            nodeItemNum = nodeItemNum == 0 ? parentNode.getNodeItemNum() : nodeItemNum;
            List<Node> nodes = loopForItemNum(nodeItemNum, parentNode);
            for (Node nodeItem : nodes) {
                if (nodeId.equals(nodeItem.getId())) {
                    node = nodeItem;
                    break;
                }
            }
            if (node == null) {
                nodeItemNum += 1;
                node = loopById(nodeId, parentNode, nodeItemNum);
            }
        }
        return node;
    }

    /**
     * 查询
     *
     * @return
     */
    public Node loopById(String nodeId, Node parentNode, int nodeItemNum, int index) {
        Node node = null;

        nodeItemNum = nodeItemNum == 0 ? parentNode.getNodeItemNum() : nodeItemNum;

        List<Node> nodeList = loopForItemNum(nodeItemNum, parentNode);
        for (int i = 0; i < nodeList.size(); i++) {
            Node nodeItem = nodeList.get(i);
            if (i == index && nodeId.equals(nodeItem.getId())) {
                node = nodeItem;
                break;
            }
        }
        return node;
    }

    /**
     * 获取同层级所有node
     *
     * @param nodeItemNum
     * @return
     */
    public List<Node> loopForItemNum(int nodeItemNum) {
        return loopForItemNum(nodeItemNum, getRoot());
    }

    /**
     * 获取同层级所有node
     *
     * @param nodeItemNum
     * @return
     */
    public List<Node> loopForItemNum(int nodeItemNum, Node node) {
        List<Node> nodeList = new ArrayList<>();
        for (Node childrenNode : node.getChildrenList()) {
            if (childrenNode.getNodeItemNum() < nodeItemNum) {
                nodeList.addAll(loopForItemNum(nodeItemNum, childrenNode));
            } else if (childrenNode.getNodeItemNum() == nodeItemNum) {
                nodeList.add(childrenNode);
            } else {
                nodeList.add(node);
            }
        }
        return nodeList;
    }


    /**
     * 插入新结点          输入父结点id进行定位 ，将新结点 插入到父结点的 sonList 中
     *
     * @param fatherId 新结点的 父id
     * @param newNode  新结点
     */
    public void insert(String fatherId, Node newNode) {
        loopById(fatherId, getRoot(), 0).addChildrenNode(newNode);
    }


    /**
     * 删除结点   注意:先判断 是否在删除 根结点. 若删除根结点,不必进入此方法 直接为null即可
     *
     * @param changeNode 根结点
     * @param id         待删除结点id
     */
    public void delete(Node changeNode, String id) {
        List<Node> sonList = changeNode.getChildrenList();

        if (sonList == null || sonList.isEmpty()) {
            return;
        } else {

            for (int i = 0; i < sonList.size(); i++) {

                if (id.equals(sonList.get(i).getId())) {
                    sonList.remove(i);
                    delete(new Node(), id);
                    break;
                } else {
                    delete(sonList.get(i), id);
                }

            }
        }
    }

    /**
     * 根据结点id  修改结点 name       //同理可根据结点name修改结点id
     *
     * @param changeNode 根结点
     * @param id         结点id
     * @param data       修改后的 新data
     */
    public void update(Node changeNode, String id, String data) {
        if (changeNode.getId().equals(id)) {
            changeNode.setData(data);
            return;
        }

        List<Node> sonList = changeNode.getChildrenList();
        if (sonList == null || sonList.isEmpty()) {
            return;
        } else {

            for (int i = 0; i < sonList.size(); i++) {
                update(sonList.get(i), id, data);
            }
        }
    }

    /**
     * 查询 某个结点 到根结点的路径
     *
     * @param changeNode 根结点
     * @param data       待查找的结点 name
     * @param wayList    路径
     */
    public void queryWayByData(Node changeNode, String data, ArrayList<String> wayList) {
        List<Node> sonList = changeNode.getChildrenList();

        wayList.add(changeNode.getData());
        if (sonList == null || sonList.isEmpty()) {
            return;
        } else {
            for (int i = 0; i < sonList.size(); i++) {

                if (data.equals(sonList.get(i).getData())) {
                    for (int j = 0; j < wayList.size(); j++) {
                        System.out.print(wayList.get(j) + "->");
                    }
                    System.out.println(sonList.get(i).getData());
                    break;
                }
                queryWayByData(sonList.get(i), data, wayList);

            }
        }
    }


}