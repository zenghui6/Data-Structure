package Queue;

/**
 * 动态数组实现的队列,实际上每次出队列都要将队列后面的元素前移,
 * 出队操作很浪费资源.循环队列,出队移动头结点,入队移动尾节点
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity){
        array = new Array<E>(capacity);
    }

    public ArrayQueue(){
        array = new Array<E>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 入队: 数组末尾入队
     * @param e
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 出队: 从队头出对
     * @return
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获取队头
     * @return
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Queue: ");
        str.append("front [");
        for (int i = 0 ; i < array.getSize() ; i++){
            str.append(array.get(i));
            if (i != array.getSize()-1 )
                str.append(", ");
        }
        str.append("] tail");
        return str.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        for (int i=0 ; i<10 ;i++){
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
