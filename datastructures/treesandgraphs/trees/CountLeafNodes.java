/**
 * This program implements a binary tree with automatic insertion, inorder traversal,
 * and counts the number of leaf nodes using tree traversal and base conditions.
 */

package datastructures.treesandgraphs.trees;

// extends binary tree class with inorder and insert implemented and adds countleaves
 class BinaryTreeLeaf extends BinaryTree {
    int countLeaves(Node root) {
        if (root == null) return 0; // empty tree

        // leaf node condition
        if (root.left == null && root.right == null)
            return 1;

        // count in left + right subtrees
        return countLeaves(root.left) + countLeaves(root.right);
    }
}

// Main class
public class CountLeafNodes {

    public static void main(String[] args) {

        BinaryTreeLeaf tree = new BinaryTreeLeaf();

        // Insert nodes
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);

        System.out.print("Inorder Traversal: ");
        tree.inorder(tree.root);

        System.out.println("\nLeaf Nodes Count = " + tree.countLeaves(tree.root));
    }
}
