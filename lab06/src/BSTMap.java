import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B {
    class Node {
        private K key;
        private V value;
        Node left, right;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private Node root;
    private int size;
    private Set<K> keys;

    public BSTMap(){
        size = 0;
        root = null;
        keys = new TreeSet<>();
    }
    @Override
    public void put(Object key, Object value) {
        root = put(root, (K)key, (V)value);
    }
    private Node put(Node node, K key, V value) {
        if (node == null) {
            this.size++;
            keys.add(key);
            return new Node(key, value);
        }
        else{
            int compare = key.compareTo(node.key);
            if (compare < 0) node.left = put(node.left, key, value);
            else if (compare > 0) node.right = put(node.right, key, value);
            else node.value = value;
            return node;
        }

    }

    @Override
    public Object get(Object key) {
        Node node = getnode (root, (K)key);
        if (node == null) return null;
        else return node.value;
    }
    private Node getnode (Node node, K key) {
        if (node == null) return null;
        int compare = key.compareTo(node.key);
        if (compare < 0) return getnode (node.left, key);
        else if (compare > 0) return getnode (node.right, key);
        else return node;
    }

    @Override
    public boolean containsKey(Object key) {
        Node node = getnode (root, (K)key);
        if (node == null) return false;
        else return true;
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
        keys.clear();
    }

    @Override
    public Set keySet() {

        return keys;
    }

    @Override
    public Object remove(Object key) {
        if (! containsKey(key)){
            return null;
        }
        else{
            Object result = get(key);
            root = removenode (root, (K)key);
            size--;
            keys.remove(key);
            return result;
        }
    }
    private Node removenode (Node node, K key) {
        if (node == null) return null;
        int compare = key.compareTo(node.key);
        if (compare < 0) node.left = removenode (node.left, key);
        else if (compare > 0) node.right = removenode (node.right, key);
        else{
            if (node.left == null && node.right == null) {
                node = null;
            }
            else if (node.left != null && node.right == null) {
                node = node.left;
            }
            else if (node.left == null && node.right != null) {
                node = node.right;
            }
            else{
                Node minNode = findMin(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = removenode(node.right, minNode.key);
            }
        }
        return node;
    }
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public Iterator iterator() {
        return keys.iterator();
    }
    public void printInOrder() {


    }
}
