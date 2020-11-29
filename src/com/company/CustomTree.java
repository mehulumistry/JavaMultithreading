package com.company;

import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * Date: 9/23/20
 * Time: 10:42 PM
 */

/* Think of maintaining access/insertion order in the tree */

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode parent;
    TreeNode(int v) {
        left = null;
        right = null;
        parent = null;
        value = v;
    }

    TreeNode(int v, TreeNode parent){
        value = v;
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}

enum DecisionLogic {
    LEFT_SMALL_RIGHT_BIG, LEFT_BIG_RIGHT_SMALL
}

public class CustomTree {

    TreeNode root;
    DecisionLogic logic;

    CustomTree(DecisionLogic decisionLogic){
        logic = decisionLogic;
    }


    public TreeNode addOrSearch(int input, boolean orSearch) {
        if(root == null) {
            TreeNode newNode = new TreeNode(input);
            root = newNode;
            System.out.println();
            return newNode;
        } else {
            TreeNode prevNode = root;
            TreeNode nextNode = compareAndGetOrSetNewNode(prevNode, input, true);

            while(nextNode != null && nextNode != prevNode){
                prevNode = nextNode;
                nextNode = compareAndGetOrSetNewNode(prevNode, input, true);
            }

            if(nextNode == prevNode) {
                if(orSearch) {
                    System.out.println("Value found");
                    return prevNode;
                }
                else {
                    System.out.println("Node already exists, no need to add the same value");
                    return null;
                }
            } else {
                if(orSearch) {
                    System.out.println("Value not found");
                    return null;
                }
                else {
                    String prev = prevNode.parent != null ? String.valueOf(prevNode.parent.value) : "";

                    System.out.println("Node added after value " + prevNode.value + " " + prev);
                    return prevNode;
                }
            }

        }
    }

    public void delete(int value) {
        TreeNode tn = addOrSearch(value, true);
        if(tn != null) {
            tn = null;
        } else {
            System.out.println("Value not found");
        }
    }

    public TreeNode compareAndGetOrSetNewNode(TreeNode current, int newValue, boolean isSet) {
        switch (logic) {
            case LEFT_SMALL_RIGHT_BIG: {
                return getSetTreeNode(current, newValue, newValue > current.value, isSet);
            }
            case LEFT_BIG_RIGHT_SMALL: {
                return getSetTreeNode(current, newValue, newValue < current.value, isSet);
            }

            default: return null;

        }
    }

    private TreeNode getSetTreeNode(TreeNode current, int newValue, boolean b, boolean isSet) {
        if(b) {
            if(current.right != null) return current.right; else {
                if(isSet) {
                    TreeNode newNode = new TreeNode(newValue, current);
                    current.setRight(newNode);
                }
                return null;
            }
        } else if(newValue == current.value) return current;
        else {
            if(current.left != null) return current.left; else {
                if(isSet) {
                    TreeNode newNode = new TreeNode(newValue, current);
                    current.setLeft(newNode);
                }
                return null;
            }
        }
    }

    public void iterateOverTreeAndReturn() {

    }


}

class CustomTreeTest {
    public static void main(String[] args) {

        CustomTree ct = new CustomTree(DecisionLogic.LEFT_SMALL_RIGHT_BIG);

        ct.addOrSearch(3, false);
        ct.addOrSearch(5, false);
        ct.addOrSearch(1, false);
        ct.addOrSearch(4, false);

        ct.addOrSearch(2, false);
        ct.addOrSearch(0, false);
        ct.addOrSearch(3, false);
        ct.addOrSearch(5, false);

        ct.addOrSearch(2, true);
        ct.addOrSearch(11, true);


    }
}
