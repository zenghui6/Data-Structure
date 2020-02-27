package Queue;

import Heap.MaxHeap;

/**
 * 基于堆的优先队列
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E> > implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    /**
     * 入队,可以直接调用最大堆的add
     * @param e
     */
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    /**
     * 直接提取堆中的最大值
     * @return
     */
    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
