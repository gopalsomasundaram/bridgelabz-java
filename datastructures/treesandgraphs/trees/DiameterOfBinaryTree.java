/**
 * This program implements a binary tree with automatic insertion,
 * inorder traversal, and computes the diameter (longest path)
 * using optimized recursion with height calculation.
 */
package datastructures.treesandgraphs.trees;

// Binary tree logic
class BinaryTreeDiameter {
    Node root;
    int diameter = 0;

    // Level order insertion
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
            } else q.add(temp.left);

            if (temp.right == null) {
                temp.right = newNode;
                return;
            } else q.add(temp.right);
        }
    }

    // Inorder traversal
    void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Returns height and updates diameter
    int height(Node root) {
        if (root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }

    // Get diameter
    int getDiameter() {
        height(root);
        return diameter;
    }
}

// Main class
public class DiameterOfBinaryTree {
    public static void main(String[] args) {

        BinaryTreeDiameter tree = new BinaryTreeDiameter();

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);

        System.out.print("Inorder: ");
        tree.inorder(tree.root);

        System.out.println("\nDiameter = " + tree.getDiameter());
    }
}
