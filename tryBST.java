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
            
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.key = minValue(root.right);
            root.right = delete(root.right, root.key);
        }

        return root;
    }
      public void removeEvens(tNode node) {
        if (node == null) return;

        removeEvens(node.left);
        removeEvens(node.right);

        if (node.key % 2 == 0) {
            root = delete(root, node.key);
        }
    }

    public static double average(double[] arr) {
        double sum = 0;
        for (double v : arr) sum += v;
        return sum / arr.length;
    }

    public static double stdDev(double[] arr, double mean) {
        double sum = 0;
        for (double v : arr)
            sum += Math.pow(v - mean, 2);

        return Math.sqrt(sum / arr.length);
    }
     public static void main(String[] args) {

        int runs = 30;
        double[] populateTimes = new double[runs];
        double[] deleteTimes = new double[runs];

        int n = 15; // adjust this to get >1000ms
        int max = (int)Math.pow(2, n) - 1;

        for (int i = 0; i < runs; i++) {

            tryBST tree = new tryBST();

            long start = System.nanoTime();
            tree.buildBalanced(1, max);
            long end = System.nanoTime();
            populateTimes[i] = (end - start) / 1_000_000.0;

    
            if (!tree.isBST()) {
                System.out.println("Tree is NOT a BST!");
                return;
            }

            start = System.nanoTime();
            tree.removeEvens(tree.root);
            end = System.nanoTime();
            deleteTimes[i] = (end - start) / 1_000_000.0;
        }
        double avgPopulate = average(populateTimes);
        double avgDelete = average(deleteTimes);

        double stdPopulate = stdDev(populateTimes, avgPopulate);
        double stdDelete = stdDev(deleteTimes, avgDelete);

        System.out.println("Number of keys (n): " + n);
        System.out.println();

        System.out.println("Method                  Avg Time (ms)    Std Dev");
        System.out.println("------------------------------------------------");

        System.out.printf("Populate tree           %.2f           %.2f\n",
                avgPopulate, stdPopulate);

        System.out.printf("Remove evens            %.2f           %.2f\n",
                avgDelete, stdDelete);
    }
}
//Yamukela Anga Mafenuka 4394348