package LinkedList;

/**
 * 使用虚拟头结点的技巧,可以不用对第一个头节点特殊处理
 * @param <E>
 */
public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
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

    //虚拟头节点
    private Node dummyHead;
    private int size;

    public LinkedList(){
        //链表初始化时有一个虚拟节点
        dummyHead = new Node(null,null);
        size = 0;
    }

    /**
     * 获取大小
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 判断链表为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }


    /**
     * 中间插入
     */
    public void add(int index,E e){

        if (index <0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        //虚拟头结点
            Node prev = dummyHead;
            for (int i = 0 ; i < index ; i++)
                prev = prev.next;

            Node node = new Node(e);
            node.next = prev.next;
            prev.next = node;

            size++;
        }

    /**
     * 头插法
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 尾插法,调用中间插法,在size位置
     */
    public void addLast(E e){
        add(size,e);
    }

    /**
     * 获得链表的第index(0-based)个位置的元素
     */
    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        Node cur = dummyHead.next;
        for (int i = 0 ; i < index ; i++)
            cur = cur.next;
        return cur.e;
    }
    /**
     * 获取链表的第一个元素
     */
    public E getFirst(){
        return get(0);
    }
    /**
     * 获取链表的最后一个元素
     */
    public E getLast(){
        return get(size-1);
    }

    /**
     * 修改链表第index位置的元素为e
     */
    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index");

        Node cur = dummyHead.next;
        for (int i =0 ; i < index ; i ++ )
            cur = cur.next;
        cur.e = e;
    }

    /**
     * 查找链表是否有元素e
     */
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    /**
     * 删除节点
     */
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        Node prev = dummyHead;
        for (int i = 0 ; i < index ; i++)
            prev = prev.next;       //找到要删除节点的前一个节点

        //找到要删除节点
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    //删除第一个节点
    public E removeFirst(){
        return remove(0);
    }
    //删除最后一个节点
    public E removeLast(){
        return remove(size - 1);        //size指向链表尾部为空的地方
    }
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("LinkedList: ");

        Node cur = dummyHead.next;
        while (cur != null){
            str.append(cur + "->");     //Node重写了toString()
            cur = cur.next;
        }
        str.append("NUll");

        return str.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for(int i = 0 ; i < 5 ; i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2,666);
        System.out.println(linkedList);

        linkedList.set(1,5555);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList);
    }
}

//p25it
