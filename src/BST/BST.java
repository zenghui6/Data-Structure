package BST;

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




}
