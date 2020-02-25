package Map;

public class LinkedListMap<K,V> implements Map<K,V> {

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Node() {
            this(null,null);
        }

        @Override
        public String toString() {
            return key.toString()+ " : " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 一个查找key对应节点的辅助函数
     * @param key
     * @return
     */
    private Node getNode(K key){

        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.key.equals(key))
                return cur;

            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);;
        if (node == null){
            Node newNode = new Node(key,value);
            newNode.next = dummyHead.next;
            dummyHead.next = newNode;
            size ++;
        }
        else {  //添加重复的key,value 会更新这个key对应的value
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null){
            if (prev.next.key.equals(key))
                break;          //找到了key相等的节点,当前prev是该节点的前一个节点

            prev = prev.next;
        }
        if (prev.next != null){
            Node delNode = prev.next;
            prev.next  = delNode.next;
            delNode.next = null;
            size -- ;

            return delNode.value;
        }

        return null;    //如果根本没有找到这个节点,就返回null
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {

        Node node = getNode(key);
        if (node == null)
                throw new IllegalArgumentException(key + "doesn't exits!");

        else
            node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }


    public static void main(String[] args) {
        LinkedListMap<Character,Integer> map = new LinkedListMap<Character, Integer>();
       map.add('a',100);
       map.add('b',50);
       map.add('c',80);
       map.add('d',10);

        System.out.println(map.getSize());
        System.out.println(map.get('b'));
//        map.set('f',9);
        map.set('a',0);
        System.out.println(map.getSize());
        map.remove('a');
        System.out.println(map.get('a'));
        System.out.println(map.getSize());
    }

}
