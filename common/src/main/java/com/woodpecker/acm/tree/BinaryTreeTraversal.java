package com.woodpecker.acm.tree;

import lombok.Data;

import java.util.Stack;

/**
 * 二叉树的借助栈结构、及神级方法的先中后序遍历
 *
 * @author Glenn
 * @since 2017-03-25
 */
@SuppressWarnings("all")
public class BinaryTreeTraversal {

    /**
     * 二叉树先序遍历：借助栈和神级遍历
     *
     * @param head 头节点
     */
    public static void preTraversal(Node head) {
        /**
         * 借助栈
         */
        if (head == null) {
            return;
        } else {
            System.out.print("preTraversal:");
            Stack<Node> temp = new Stack<>();
            // 头节点压入栈
            temp.push(head);
            while (!temp.isEmpty()) {
                head = temp.pop();
                System.out.print(head.dataValue + " ");
                // 右孩子先入栈
                if (head.right != null) {
                    temp.push(head.right);
                }
                // 左孩子后入栈
                if (head.left != null) {
                    temp.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 二叉树中序遍历：借助栈和神级遍历
     *
     * @param head 头节点
     */
    public static void midTraversal(Node head) {
        if (head == null) {
            return;
        } else {
            System.out.print("midTraversal:");
            Stack<Node> temp = new Stack<>();
            temp.push(head);
            while (!temp.isEmpty() || head != null) {
                // 连续把二叉树最左一条斜线上的节点压入栈
                if (head.left != null) {
                    temp.push(head.left);
                    head = head.left;
                // 弹出栈顶节点，让其右孩子继续上述过程
                } else {
                    head = temp.pop();
                    System.out.print(head.dataValue + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
        /**
         * Morris遍历
         */
        if (head == null) {
            return;
        } else {
            System.out.print("midTraversal:");
            Node cur1 = head;
            Node cur2 = null;
            while (cur1 != null) {
                cur2 = cur1.left;
                if (cur2 != null) {
                    while (cur2.right != null && cur2.right != cur1) {
                        cur2 = cur2.right;
                    }
                    if (cur2.right == null) {
                        // 把以cur1为头节点的左子树最右节点的right空指针指向cur1
                        cur2.right = cur1;
                        cur1 = cur1.left;
                        continue;
                    } else {
                        cur2.right = null;
                    }
                    System.out.print(cur1.dataValue + " ");
                    cur1 = cur1.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 二叉树后序遍历：借助栈和神级遍历
     *
     * @param head 头节点
     */
    public static void behTraversal(Node head) {
        if (head == null) {
            return;
        } else {
            // 借助两个栈来完成
            Stack<Node> temp1 = new Stack<>();
            Stack<Node> temp2 = new Stack<>();
            System.out.print("behTraversal:");
            temp1.push(head);
            while (!temp1.isEmpty()) {
                head = temp1.pop();
                temp2.push(head);
                // 入栈顺序很重要
                if (head.left != null) {
                    temp1.push(head.left);
                }
                if (head.right != null) {
                    temp1.push(head.right);
                }
            }
            // temp2栈中的顺序已是后序
            while (!temp2.isEmpty()) {
                System.out.print(temp2.pop() + " ");
            }
        }
        System.out.println();
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
