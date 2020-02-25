package Map;

import BST.BST;

public class BSTMap<K extends Comparable<K> ,V> implements Map<K,V> {

    private class Node{
        public K key;
        public V value;
        public Node  left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }
    private Node add(Node node , K key, V value){
        if (node == null){
            size++;
            return  new Node(key,value);
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }
        else if (key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }
        else   //存在key时覆盖value
            node.value = value;

        return node;
    }

    /**
     * 返回以node为根节点的二分搜索树中,key所在的节点
     * @param node
     * @param key
     * @return
     */
    private  Node getNode(Node node , K key){
        if (node == null)
            return null;

        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left,key);
        else
            return getNode(node.right,key);
    }


    @Override
    public V remove(K key) {
        Node node = getNode(root,key);  //key是否在map中
        if (node != null){
            root = remove(root,key);
            return node.value;
        }

        return null;
    }

    /**
     * 删除到以node为根的二分搜索树中key的节点,递归算法
     * @param node 以node为根的二叉树
     * @param key  待删除key
     * @return 删除节点后新的二分搜索树
     */
    private Node remove(Node node, K key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            return node;
        }
        else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            return  node;
        }else {     //找到了要删除的节点
            //待删除节点左子树为空的情况
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size -- ;
                return rightNode;
            }

            //待删除节点右子树为空的情况
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size -- ;
                return leftNode;
            }
            //待删除节点左右子树均不为空的情况

            //找到比待删除节点大的最小节点,即待删除节点右子树的最小节点
            Node successor = minimum(node.right);  //右子树的最小值
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }



    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if (node == null)
            throw new  IllegalArgumentException(key+"doesn't exit");

        else
            node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在订单节点
     * @param node
     * @return
     */
    private Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node){

        if (node.left == null){     //找到了最小的
            Node rightNode = node.right;
            node.right = null;
            size -- ;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }
}
