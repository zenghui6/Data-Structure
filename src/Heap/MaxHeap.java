package Heap;

import Array.Array;

import java.util.Random;

/**
 * 使用动态数组实现最大堆
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;      //这里使用我们自己写的动态数组

    public MaxHeap(int capacity){
        data = new Array<E>(capacity);
    }

    public MaxHeap(){
        data = new Array<E>();
    }

    public MaxHeap(E[] arr){
        data = new Array<E>(arr);
        for (int i = parent(arr.length-1); i >= 0  ; i--) {
            siftDown(i);
        }
    }

    /**
     * @return 堆中元素个数
     */
    public int size(){
        return data.getSize();
    }

    /**
     * 表示堆是否为空
     * @return boolean
     */
    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 计算节点的父节点的位置
     * @param index
     * @return 父节点的index
     */
    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent");

        return (index - 1) / 2;
    }

    /**
     * 计算节点的左子树             1
     * @param index            2     3
     * @return               4          5
     */
    private int leftChild(int index){
        return index * 2 + 1;
    }

    /**
     *
     * @param index
     * @return
     */
    private int rightChild(int index){
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     * @param e
     */
    public void add(E e){
        data.addLast(e);        //先加入到数组尾部,即树根
        siftUp(data.getSize()-1);      //从树根开始上浮
    }

    /**
     * 元素上浮
     * @param k
     */
    private void siftUp(int k){  //如果父亲节点小于该节点,则该节点上浮,然后继续循环比较
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }

    /**
     * 查看堆中的最大元素
     * @return
     */
    public  E findMax(){
        if(data.getSize() == 0)
            throw  new  IllegalArgumentException("Can not findMax when heap is empty");

        return data.get(0);
    }

    /**
     * 取出堆中的最大元素
     * 将 0 与size-1 交换,然后removeLast,然后sift down
     */
    public E extractMax(){
        E ret = findMax();

        data.swap(0,data.getSize() - 1);
        data.removeLast();
        siftDown(0);    //下沉

        return ret;

    }

    /**
     * 下沉
     * @param k
     */
    private void siftDown(int k){

        while (leftChild(k) < data.getSize()){  //如果k节点有左孩子
            int j = leftChild(k);
            if(j+1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {         //如果k有右孩子
                //如果左右孩子都有,且右孩子大于左孩子
                j = rightChild(k);      //data[j] 是 leftChild 和 rightChild 中的最大值
            }

            if (data.get(k).compareTo(data.get(j)) >= 0)
                //该节点大于它的两个孩子节点,符合最大堆定义
                break;

            else {
                data.swap(k,j);
                k = j;
            }
        }
    }

    /**
     * 取出最大元素,并替换成e  , 可以先取出最大元素再添加e ,但可以简化为一步,直接将对顶元素更换然后sift Down
     * @param e
     * @return
     */
    public E replace(E e){
        E ret = findMax();
        data.set(0,e);      //1. 先替换

        siftDown(0);    //2. sift Down
        return ret;
    }

    public void heapify(){

    }

    public static void main(String[] args) {
        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1 ; i< n ;i++)
            if (arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed");
    }
}
