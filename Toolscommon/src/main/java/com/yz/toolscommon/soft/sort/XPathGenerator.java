package com.yz.toolscommon.soft.sort;

import org.w3c.dom.*;

public class XPathGenerator {
    public static String generateXPath(Node node) {
        if (node == null) {
            return "";
        }

        StringBuilder xpathBuilder = new StringBuilder();

        // 递归处理节点和其父节点
        while (node != null) {
            int index = getIndexOfNode(node);
            String nodeName = node.getNodeName();
            String nodeXPath = "/" + nodeName + (index > 0 ? "[" + index + "]" : "");
            xpathBuilder.insert(0, nodeXPath);
            node = node.getParentNode();
        }

        return xpathBuilder.toString();
    }

    private static int getIndexOfNode(Node node) {
        int index = 0;
        Node prevSibling = node.getPreviousSibling();

        while (prevSibling != null) {
            if (prevSibling.getNodeName().equalsIgnoreCase(node.getNodeName())) {
                index++;
            }
            prevSibling = prevSibling.getPreviousSibling();
        }

        return index;
    }
}
