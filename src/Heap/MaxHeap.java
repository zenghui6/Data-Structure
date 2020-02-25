package Heap;

import Array.Array;

/**
 * 使用数组实现最大堆
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



}
