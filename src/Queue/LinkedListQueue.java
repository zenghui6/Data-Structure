package Queue;

/**
 * 双链表实现队列,尾部插入,头部出
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }
    //队列头,队列尾
    private Node head,tail;
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
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
     * 入队
     * @param e
     */
    @Override
    public void enqueue(E e) {
        if (tail == null){     //空链表,添加第一个
            tail = new Node(e);
            head = tail;
        }
        else {      //非空链表
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        if (tail == null)
            throw new IllegalArgumentException("Dequeue failed. Queue is Empty");

        Node ret = head;
        head = head.next;
        ret.next = null;
        //如何出队后链表为空 head == tail == null
        if(head == null)
            tail = null;

        size --;

        return ret.e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Queue: front ");

        Node cur = head;
        while (cur != null){
            str.append(cur + "->");
            cur = cur.next;
        }
        str.append("null tail");
        return str.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
        for (int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }

    }


}
