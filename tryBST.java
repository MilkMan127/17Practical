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

