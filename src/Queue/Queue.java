package Queue;

public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    void enqueue(E e);  //入队
    E dequeue();
    E getFront();
}
