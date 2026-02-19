/**
 * This program creates a binary tree and finds its height (maximum depth)
 * using recursion and tree traversal.
 */
package datastructures.treesandgraphs.trees;

// Represents a tree node
class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

// Contains binary tree logic
class BinaryTree {
    Node root;

    // Insert node to keep tree complete (level order)
    void insert(int data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
            return;
        }

        java.util.Queue<Node> q = new java.util.LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node temp = q.poll();

            if (temp.left == null) {
                temp.left = newNode;
                return;
            } else {
                q.add(temp.left);
            }

            if (temp.right == null) {
                temp.right = newNode;
                return;
            } else {
                q.add(temp.right);
            }
        }
    }

    // Inorder traversal (Left → Root → Right)
    void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Returns height of tree using recursion
    int height(Node root) {
        if (root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right) + 1;
    }
}

// Main class only calls methods
public class FindHeight {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        // Insert nodes automatically
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);

        System.out.print("Inorder Traversal: ");
        tree.inorder(tree.root);

        System.out.println("\nHeight = " + tree.height(tree.root));
    }
}