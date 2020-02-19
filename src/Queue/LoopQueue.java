package Queue;

/**
 * 循环队列,
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front,tail;  //头尾
    private int size;

    public LoopQueue(int capacity){
        //循环队列有一个空缺 ,创建时多分一个空间
        // tail == front 队列为空
        //  (tail + 1) % capacity == front 时 队满
        data = (E[]) new  Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length -1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    //tail == front 队列为空
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 入队,考虑扩容
     * @param e
     */
    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) //队满,扩容
            resize(2 * getCapacity());

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0 ; i < size ; i++){
            newData[i] = data[(i+front) % data.length];
        }
        data = newData;  //data 指向新数组
        front = 0;
        tail = size;
    }

    /**
     * 出队
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size -- ;
        if (size == getCapacity() / 4)
            resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is Empty");

        return data[front];
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(String.format("Queue: size = %d , capacity = %d\n",size,data.length-1));
        str.append("front [");
        for (int i = front ; i != tail ; i = (i+1) % data.length){
            str.append(data[i]);
            if ((i+1) % data.length != tail)  //最后元素 头尾相碰
                str.append(", ");
        }
        str.append("] tail");
        return str.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue = new LoopQueue<Integer>();
        for(int i = 0 ; i < 10 ; i++){
            loopQueue.enqueue(i);
            System.out.println(loopQueue);
            if (i%3 == 2) {
                loopQueue.dequeue();
                System.out.println(loopQueue);
            }
        }
    }
}
