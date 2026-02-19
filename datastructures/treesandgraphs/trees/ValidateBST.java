/**
 * This program implements a binary tree, performs inorder traversal,
 * and checks whether the tree satisfies Binary Search Tree (BST) properties.
 */

package datastructures.treesandgraphs.trees;

// Contains binary tree logic
class BinarySearchTree {
    Node root;

    // Insert method (BST style insertion)
    Node insertBST(Node root, int data) {
        if (root == null) return new Node(data);

        if (data < root.data)
            root.left = insertBST(root.left, data);
        else
            root.right = insertBST(root.right, data);

        return root;
    }

    // Inorder traversal
    void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Validate BST using range method
    boolean isBST(Node root, int min, int max) {
        if (root == null) return true;

        if (root.data <= min || root.data >= max)
            return false;

        return isBST(root.left, min, root.data) &&
                isBST(root.right, root.data, max);
    }
}

// Main class
public class ValidateBST {

    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();

        // Build BST
        tree.root = tree.insertBST(tree.root, 50);
        tree.root = tree.insertBST(tree.root, 30);
        tree.root = tree.insertBST(tree.root, 70);
        tree.root = tree.insertBST(tree.root, 20);
        tree.root = tree.insertBST(tree.root, 40);
        tree.root = tree.insertBST(tree.root, 60);
        tree.root = tree.insertBST(tree.root, 80);

        System.out.print("Inorder Traversal: ");
        tree.inorder(tree.root);

        boolean result = tree.isBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE);

        System.out.println("\nIs Valid BST = " + result);
    }
}
