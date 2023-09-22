package com.softroad.filefortreedemo.entity;

import java.util.*;

/**
 * @author yanzhao
 * @version 1.0
 * @classname MutlTree
 * @date 2022/8/16 19:40
 * @description TODO
 */
public class TreeData extends MultiwayTree {

    //添加方法重载
    public void add(String parentId, String nodeId, String data) {
        add(loopById(parentId), nodeId, data);
    }

    //添加
    public Node add(Node parentNode, String nodeId, String data) {
        Node newNode = new Node(nodeId, data, parentNode, parentNode.getNodeItemNum() + 1);
        parentNode.addChildrenNode(newNode);
        return newNode;
    }

    //添加
    public Node add(Node parentNode, Node copyNode) {
        return copyNode.clone(parentNode.getNodeItemNum() + 1, parentNode);
    }

    // 清空数据
    public void clear() {
        getRoot().getChildrenList().clear();
    }

    /**
     * 合并
     *
     * @param oldNodeRoot
     * @param newNodeRoot
     */
    public void merge(Node oldNodeRoot, Node newNodeRoot) {
        int nodeItemNum = oldNodeRoot.getNodeItemNum() == oldNodeRoot.getNodeItemNum() ? oldNodeRoot.getNodeItemNum() : 0;
        while (loopForItemNum(nodeItemNum, oldNodeRoot).size() > 0) {
            List<Node> oldNodeList = loopForItemNum(nodeItemNum, oldNodeRoot);
            List<Node> newNodeList = loopForItemNum(nodeItemNum, newNodeRoot);

            for (int index = 0; index < oldNodeList.size(); index++) {
                Node oldNode = oldNodeList.get(index);

                if (oldNode.isCheckMergeIdFlag() == false) {
                    // 查看是旧节点是否在新树中存在，如果不存在，就删除
                    if (newNodeList.stream().noneMatch(newChildNode -> newChildNode.getId().equals(oldNode.getId()))) {
                        delete(oldNode.getParentNode(), oldNode.getId());
                    }
                }

                // 如果值不相同，就合并
                if (oldNode.isCheckMergeIdFlag() == true && oldNode.isCheckMergeValueFlag() == false) {
                    oldNode.updateData(loopById(oldNode.getId(), newNodeRoot, nodeItemNum, index));
                }
            }

            for (int i = 0; i < newNodeList.size(); i++) {
                Node newNode = newNodeList.get(i);
                if (newNode.isCheckMergeIdFlag() == false) {
                    List<Node> parentNodeList = loopForItemNum(newNode.getParentNode().getNodeItemNum(), newNodeRoot);
                    int parentNodeIndex = 0;
                    for (int i1 = 0; i1 < parentNodeList.size(); i1++) {
                        if (parentNodeList.get(i1) == newNode.getParentNode()) {
                            parentNodeIndex = i1;
                        }
                    }
                    // 旧树中 添加 新节点
                    Node parentNode = loopById(newNode.getParentNode().getId(), oldNodeRoot, newNode.getParentNode().getParentNode().getNodeItemNum(), parentNodeIndex);
                    if (null != parentNode) {
                        add(parentNode, newNode.getId(), newNode.getData());
                    }
                }
            }
            nodeItemNum += 1;
        }
    }

    /**
     * 如果id相同，说明节点存在；如果值相同，说明两次结果相同
     *
     * @param oldNodeRoot
     * @param newNodeRoot
     */
    public void checkNode(Node oldNodeRoot, Node newNodeRoot) {

        int nodeItemNum = oldNodeRoot.getNodeItemNum() == newNodeRoot.getNodeItemNum() ? oldNodeRoot.getNodeItemNum() : 1;
        loopForItemNum(nodeItemNum);
        while (loopForItemNum(nodeItemNum).size() > 0) {

            List<Node> oldNodeList = loopForItemNum(nodeItemNum, oldNodeRoot);
            List<Node> newNodeList = loopForItemNum(nodeItemNum, newNodeRoot);

            for (int i = 0; i < oldNodeList.size(); i++) {
                Node oldNode = oldNodeList.get(i);
                Node newNode = i > newNodeList.size() - 1 ? null : newNodeList.get(i);

                //父级节点不同的时候，子节点不再比较
                if (null != oldNode.getParentNode() && !oldNode.getParentNode().isCheckMergeIdFlag()) continue;

                if (null != newNode && oldNode.getId().equals(newNode.getId())) {
                    oldNode.setCheckMergeIdFlag(true);
                    newNode.setCheckMergeIdFlag(true);
                    if (oldNode.getData().equals(newNode.getData())) {
                        oldNode.setCheckMergeValueFlag(true);
                        newNode.setCheckMergeValueFlag(true);
                    }
                } else {
                    Optional<Node> newNodeStream = newNodeList.stream().filter(newNodeTtem -> oldNode.getId().equals(newNodeTtem.getId())).findFirst();
                    if (newNodeStream.isPresent()) {
                        oldNode.setCheckMergeIdFlag(true);
                        newNodeStream.get().setCheckMergeIdFlag(true);
                        if (oldNode.getData().equals(newNodeStream.get().getData())) {
                            oldNode.setCheckMergeValueFlag(true);
                            newNodeStream.get().setCheckMergeValueFlag(true);
                        }
                    }
                }

            }
            nodeItemNum += 1;
        }
    }

    /**
     * 加载数据
     *
     * @param treeMap
     * @param parentNode
     */
    public void loadTreeMap(TreeMap<String, Object> treeMap, Node parentNode) {
        Node newNode = null;
        for (Map.Entry<String, Object> treeMapEntry : treeMap.entrySet()) {
            if (treeMapEntry.getValue() instanceof String) {
                newNode = add(parentNode, treeMapEntry.getKey(), String.valueOf(treeMapEntry.getValue()));
            } else if (treeMapEntry.getValue() instanceof ArrayList) {
                ArrayList<TreeMap<String, Object>> treeMapList = (ArrayList<TreeMap<String, Object>>) treeMapEntry.getValue();
                for (TreeMap<String, Object> treeMapItem : treeMapList) {
                    loadTreeMap(treeMapItem, newNode);
                }
            }
        }
    }


    /**
     * 遍历结点 并打印. 同时按每个结点所在深度 在结点前打印不同长度的空格
     *
     * @param changeNode 根结点
     * @param depth      结点深度：1
     */
    public void queryAll(Node changeNode, int depth) {
        List<Node> sonList = changeNode.getChildrenList();
        String space = generateSpace(depth);    //根据深度depth,返回(depth*3)长度的空格

        if (sonList == null || sonList.isEmpty()) {
            return;
        }

        for (int i = 0; i < sonList.size(); i++) {
            System.out.println(space + "---"      //打印空格 和结点id，name
                    + "<" + sonList.get(i).getId() + ">" + sonList.get(i).getData());

            if (i == 0) {
                depth = depth + 1;  //结点深度+1，每个for循环仅执行一次。作为子结点sonList.get(i)的深度
            }
            queryAll(sonList.get(i), depth);
        }

    }

    //打印空格
    public static String generateSpace(int count) {
        count = count * 3;
        char[] chs = new char[count];
        for (int i = 0; i < count; i++) {
            chs[i] = ' ';
        }
        return new String(chs);
    }

}

