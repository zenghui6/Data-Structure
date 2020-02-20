package BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
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

    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();
       int[] nums = {5,3,6,8,4,2,6};
       for (int num : nums)
           bst.add(num);

        bst.preOrder();
        System.out.println("------------");
        bst.inOrder();
        System.out.println("------------");

        bst.postOrder();
        System.out.println("------------");

        bst.preOrderNR();
        System.out.println("------------");
        bst.levelOrder();

    }

}
