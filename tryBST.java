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

