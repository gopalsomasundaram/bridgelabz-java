/**
 * This program implements a binary tree with automatic insertion,
 * inorder traversal, and finds the Lowest Common Ancestor (LCA)
 * of two given nodes using recursion.
 */

package datastructures.treesandgraphs.trees;

// Binary tree logic
class BinaryTreeLCA {
    Node root;

    // Level order insertion (complete tree)
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

    // Find LCA using recursion
    Node findLCA(Node root, int p, int q) {
        if (root == null) return null;

        if (root.data == p || root.data == q)
            return root;

        Node left = findLCA(root.left, p, q);
        Node right = findLCA(root.right, p, q);

        if (left != null && right != null)
            return root;

        return (left != null) ? left : right;
    }
}

// Main class
public class FindLCA {
    public static void main(String[] args) {

        BinaryTreeLCA tree = new BinaryTreeLCA();

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);

        System.out.print("Inorder: ");
        tree.inorder(tree.root);

        Node lca = tree.findLCA(tree.root, 4, 5);
        System.out.println("\nLCA = " + lca.data);
    }
}
