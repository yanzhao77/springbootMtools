package com.yz.binarytreedemo.entity;

import lombok.Data;
import lombok.NonNull;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/8 11:42
 */
@Data
public class Node {

    private Object data;
    private Node lChild;
    private Node rChild;

    public Node(Object data, Node lChild, Node rChild) {
        this.data = data;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    @Override
    public String toString() {
        return "Node [date=" + data + ", lChild=" + lChild + ", rChild=" + rChild + "]";
    }
}
