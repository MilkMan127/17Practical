import java.util.*;

class tNode {
    int key;
    tNode left, right;

    public tNode(int key) {
        this.key = key;
        left = right = null;
    }
}
public class tryBST {

    tNode root;

    
    public tNode insert(tNode root, int key) {
        if (root == null) return new tNode(key);

        if (key < root.key)
            root.left = insert(root.left, key);
        else if (key > root.key)
            root.right = insert(root.right, key);

        return root;
    }
 public void insert(int key) {
        root = insert(root, key);
    }

    // BUILD BALANCED TREE
    public void buildBalanced(int start, int end) {
        if (start > end) return;

        int mid = (start + end) / 2;

        insert(mid);

        buildBalanced(start, mid - 1);
        buildBalanced(mid + 1, end);
    }
public boolean isBST(tNode node, int min, int max) {
        if (node == null) return true;

        if (node.key <= min || node.key >= max)
            return false;

        return isBST(node.left, min, node.key) &&
               isBST(node.right, node.key, max);
    }

    public boolean isBST() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
 public int minValue(tNode node) {
        while (node.left != null)
            node = node.left;
        return node.key;
    }

    
    public tNode delete(tNode root, int key) {
        if (root == null) return null;

        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            // node found
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.key = minValue(root.right);
            root.right = delete(root.right, root.key);
        }

        return root;
    }