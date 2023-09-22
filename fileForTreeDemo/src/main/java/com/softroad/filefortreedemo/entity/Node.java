package com.softroad.filefortreedemo.entity;

import cn.hutool.json.JSONUtil;
import com.softroad.filefortreedemo.config.TreeAttribute;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

/**
 * @author yanzhao
 * @version 1.0
 * @classname Node
 * @date 2022/8/16 19:38
 * @description TODO
 */
@Data
@RequiredArgsConstructor
public class Node implements Cloneable, Serializable {
    private String id;          //结点id
    private String data;        //结点数据
    private List<Node> childrenList = new ArrayList<>(); //该结点的 子结点集合
    private int nodeItemNum; // 树节点层高

    private boolean checkMergeIdFlag = false;// 是否内ID相同
    private boolean checkMergeValueFlag = false;// 是否内容相同

    private Node parentNode;// 父节点

    public Node(String id, int nodeItemNum) {
        setId(id);
        setNodeItemNum(nodeItemNum);
    }

    public Node(String id, String data, Node parentNode) {
        setId(id);
        setData(data);
        setParentNode(parentNode);
    }

    public Node(String id, Node parentNode, int nodeItemNum) {
        this(id, nodeItemNum);
        setParentNode(parentNode);
    }

    public Node(String id, String data, Node parentNode, int nodeItemNum) {
        this(id, data, parentNode);
        setNodeItemNum(nodeItemNum);
    }

    public Node(String id, String data, int nodeItemNum) {
        this(id, nodeItemNum);
        setData(data);
    }

    // 添加孩子节点
    public void addChildrenNode(Node node) {
        childrenList.add(node);
    }


    @Override
    protected Node clone() {
        Node node = new Node(id, data, parentNode, nodeItemNum);
        node.setCheckMergeIdFlag(checkMergeIdFlag);
        node.setCheckMergeValueFlag(checkMergeValueFlag);
        ListIterator<Node> nodeListIterator = node.getChildrenList().listIterator();
        while (nodeListIterator.hasNext()) {
            node.addChildrenNode(nodeListIterator.next().clone());
        }
        return node;
    }

    protected Node clone(int nodeItemNum, Node parentNode) {
        Node node = new Node(id, data, parentNode, nodeItemNum);
        ListIterator<Node> nodeListIterator = node.getChildrenList().listIterator();
        while (nodeListIterator.hasNext()) {
            node.addChildrenNode(nodeListIterator.next().clone(nodeItemNum++, node));
        }
        return node;
    }

    public void updateData(Node newNode) {
        setData(newNode.getData());
    }

    public boolean checkChildren(Node childrenNode) {
        return getChildrenList().stream().allMatch(node -> node.getId().equals(childrenNode.getId()) && node.getData().equals(childrenNode.getData()));
    }

    @Override
    public String toString() {
        return "Node{" + "id='" + id + '\'' + ", data='" + data + '\'' + ", nodeItemNum=" + nodeItemNum + ", checkMergeIdFlag=" + checkMergeIdFlag + ", checkMergeValueFlag=" + checkMergeValueFlag + ", parentNode=" + parentNode + '}';
    }

    public void updateKey() {
        if (id.equals("root") || nodeItemNum == 1) {
            ListIterator<Node> nodeListIterator = getChildrenList().listIterator();
            while (nodeListIterator.hasNext()) {
                nodeListIterator.next().updateKey();
            }
        } else {
            List<TreeAttribute> treeAttributes = JSONUtil.toList(id,TreeAttribute.class);
            Optional<TreeAttribute> attribute = treeAttributes.stream().filter(treeAttribute -> treeAttribute.getKey().equals("keyId")).findFirst();
            setId(attribute.get().getValue());
            ListIterator<Node> nodeListIterator = getChildrenList().listIterator();
            while (nodeListIterator.hasNext()) {
                nodeListIterator.next().updateKey();
            }
        }
    }
}
