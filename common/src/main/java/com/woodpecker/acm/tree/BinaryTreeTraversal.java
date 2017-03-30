package com.woodpecker.acm.tree;

import lombok.Data;

import java.util.Stack;

/**
 * 二叉树的借助栈结构、及神级方法的先中后序遍历
 *
 * @author Glenn
 * @since 2017-03-25
 */
public class BinaryTreeTraversal {

    /**
     * 二叉树先序遍历：借助栈和神级遍历
     *
     * @param head 头节点
     */
    public void preTraversal(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> temp = new Stack<>();
        while (head != null && !temp.isEmpty()) {
            temp.push(head);
            System.out.println(head.getDataValue());
            head = head.left;

        }
    }

    /**
     * 定义二叉树
     */
    @Data
    private class Node {
        /**
         * 节点值
         */
        private int dataValue;
        /**
         * 左孩子节点
         */
        private Node left;
        /**
         * 右孩子节点
         */
        private Node right;
    }
}
