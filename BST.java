/**
 * algorithm 3.3: binary search tree symbol table
 * <p>
 * a symbol table implementation using a binary search tree, in which in each node
 * refers to the root of a left subtree -- which contains nodes with smaller
 * keys -- and a right subtree -- which contains nodes with larger keys.
 *
 * @param <Key>
 * @param <Value>
 * @author Bleart Nuredini
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;
    private int cnt;

    private class Node {
        private Value val;
        private Key key;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }

        public String toString(Node x) {
            return x.key + " - " + x.val;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    // get() with recursion
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null; // base case

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    // get() with iteration
    /*
    public Value get(Key key) {
        Node x = root;

        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }

        return null;
    }
     */

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);   // base case

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;

        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        // search for node who's key is the 'key' parameter
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { // search key has been found
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // print the keys in order
    public void print() {
        print(root);
    }

    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        System.out.println(x);
        print(x.right);
    }

    // returns the longest distance from the root to a leaf of the BST
    public int height1() {
        return (root);
    }

    private int height1(Node x) {
        if (x == null) return -1;

        return 1 + Math.max(height1(x.left), height1(x.right));
    }

    // checks if the BST is balanced; that is, if the heights of the subtrees differ by at most one
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node x) {
        if (Math.abs((x.left) - height1(x.right)) > 1)
            return false;
        else
            return true;
    }

    public static void main(String[] args) {
        BST<String, Integer> st = new BST<String, Integer>();
        st.put("e", 1);
        st.put("a", 2);
        st.put("s", 2);
        st.put("y", 2);
        st.put("q", 2);
        st.put("u", 2);
        st.put("e", 2);
        st.put("s", 2);
        st.put("t", 2);
        st.put("i", 2);
        st.put("o", 2);
        st.put("n", 2);

        // use the recursive version of height()
        System.out.println(st. ());

        /*
        System.out.println("Input: ");
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            st.put(s, 999);
        }

        System.out.println("Output: ");
         */
    }
}
