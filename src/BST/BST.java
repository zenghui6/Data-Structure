package BST;

import java.util.*;

/**
 * 二分搜索树, 因为元素必须要有可比性,所以要继承Comparable接口
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;  //左右孩子

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向二分搜索树添加元素,采用递归的方式
     */
    public void add(E e){
        root = add(root,e);
    }

    private Node add(Node node,E e){
        //根节点为空,就创建一个根节点
        if (node == null){
            size ++ ;
            return new Node(e);
        }

        //小于根节点,继续向左指数中添加
        if (e.compareTo(node.e) < 0){
           node.left =  add(node.left,e);
        }else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);

        return node;
    }

    /**
     * 二分搜索树是否包含元素
     * @param e
     * @return
     */
     public boolean contains(E e){
        return contains(root,e);
     }

     private boolean contains(Node node,E e){
         //到叶子节点了,还没有找到
         if (node == null)
             return false;

         //找到了
         if (e.compareTo(node.e) == 0)
             return true;
         //左子树递归
         else if (e.compareTo(node.e) < 0)
             return contains(node.left,e);
         //右子树递归
         else
             return contains(node.right,e);
     }

    /**
     * 先序遍历
     */
    public void preOrder(){
         preOrder(root);
    }
    private void preOrder(Node root){
        //递归结束条件
        if (root == null)
            return;

        System.out.println(root.e);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node root){
        if (root == null)
            return;

        inOrder(root.left);
        System.out.println(root.e);
        inOrder(root.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node root){
        if (root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.e);
    }

    /**
     * 二分搜索树的非递归前序遍历
     * 先根入栈,然后出栈(打印),出栈时将左右子树入栈(如果非空),直到栈为空
     */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            //左右子树入栈,必须先入栈右子树,栈是先入后出
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二分搜索树的层序变量,使用队列实现
     * 根入队,根出队(打印),左右子树入队(非空),直到队列为空
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                queue.add(cur.left);

            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    /**
     * 寻找二分搜索树的最小值
     */
    public E minimum(){
        if (size == 0)
            throw  new IllegalArgumentException("BST is Empty !");
        return  minimum(root).e;
    }
    //返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树的最大值
     * @return
     */
    public E maximum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is Empty!");

        return maximum(root).e;
    }
    private Node maximum(Node node){
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    /**
     * 删除最小元素
     * @return 删除的元素
     */
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        //不是最小值,即有左子树,左子树继续递归
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除最大值
     * @return 要删除的值
     */
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size -- ;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除指定节点:
     * 1.删除只有左孩子的节点,返回右节点
     * 2.删除只有右孩子的节点,返回左节点
     * 3.删除叶子节点与删除只有左孩子的节点是一样的,相当于左孩子为空,返会左孩子就行
     * 4.删除左右都有孩子的节点: 找它的前驱(左子树的最大值)或后继(右子树的最小值)
     */
    public void remove(E e){
        root = remove(root,e);
    }

    /**
     * 删除以node为根的二分搜索树中值为e的节点,递归算法
     * @param node
     * @param e
     * @return 删除节点后新的二分搜索树的根
     */
    private Node remove(Node node,E e){
        //树为空
        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }
        else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }
        else {//找到了要删除的节点

            //待删除节点左子树为空
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size -- ;
                return rightNode;
            }

            //待删除节点右子树为空
            else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return  leftNode;
            }

            //待删除节点左右子树均不为空的情况
            Node successor = minimum(node.right);      //替代节点,这里size--了
            successor.right = removeMax(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }





    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();
        Random random = new Random();

        int n = 1000;
        for (int i=0 ; i < n ; i ++){
            bst.add(random.nextInt(10000));
        }
        bst.preOrder();

            ArrayList<Integer> nums = new ArrayList<>();
            while (!bst.isEmpty()) {
                nums.add(bst.removeMin());
            }

            System.out.println(nums);
            for (int i = 1 ; i < nums.size() ; i++){
                if (nums.get(i-1) > nums.get(i))
                    throw new  IllegalArgumentException("不是排序的");
            }


    }
}
