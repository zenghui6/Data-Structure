package Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * 自己的数组
 */
public class Array<E> {
    private E[] data;
    private int size;   //数组的元素个数

    /**
     * 有参构造函数,指定数组的容量
     * @param capacity
     */
    public Array(int capacity){
        data = (E[]) new Object[capacity];        //泛型数组的声明方法,java支持new泛型
        size = 0;
    }

    /**
     * 默认构造函数调用有参构造函数
     */
    public Array(){
        this(10);
    }

    /**
     * 获取数组内元素个数
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取数组容量
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 判断数组是否为空
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向末尾添加数据
     * @param e
     */
    public void addLast(E e){
        add(size,e);
    }
    //在数组头添加元素
    public void addFirst(E e){
        add(0,e);
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }
    /**
     * 向任意index位置添加数据,  动态数组扩容
     * @param index
     * @param e
     */
    public void add(int index, E e){
        if (index < 0 || index >size)
            throw new IllegalArgumentException("Add failed , Require index >= 0 and index <=size");

        if (size == data.length)    //数组满了,扩容
            resize(2 * data.length);

        for(int i = size-1 ; i>= index;i--){    //元素向后移
            data[i+1] = data[i];
        }
        data[index] = e;     //填补
        size++;
    }

    /**
     * 获取index 索引位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed , Index is illegal");
        return data[index];
    }

    /**
     * 修改索引位置的元素
     * @param index
     * @param e
     * @return
     */
    public E set(int index ,E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed, Index is Illegal");
        return data[index] = e;
    }

    /**
     * 判断是否包含元素
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0 ;i< size ;i++){
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    /**
     * 寻找数组中的元素,找到返回index ,否则返回 -1
     * @param e
     * @return
     */
    public int find(E e){
        for (int i = 0 ;i< size;i++){
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    /**
     * 寻找数组中所有该元素的位置,并返回一个列表
     * @param e
     * @return list
     */
    public List findAll(E e){
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < size;i++){
            if (data[i].equals(e))
                list.add(i);
        }
        if (list.isEmpty())
            list.add(-1);
        return list;
    }

    /**
     * 删除index位置的元素,返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove Failed. Require index >= 0 index < size");

        E ret = data[index];
        for (int i = index+1 ; i < size; i++ ){
            data[i-1]  = data[i];
        }
        size--;
        //因为是引用,浪费空间,手动清除,垃圾回收就会快速回收
        data[size] = null;

        //缩容
        if (size == data.length / 4 && data.length/2 != 0)  //lazy
            resize(data.length/2);
        return ret;
    }

    /**
     * 删除第一个元素并返回删除的元素
     * @return
     */
    public  E removeFirst(){
        return remove(0);
    }

    /**
     * 删除最后的元素并返回
     * @return
     */
    public E removeLast(){
        return remove(size-1);
    }

    /**
     * 删除Element
     * @param e
     */
    public void removeElement(E e){
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    /**
     * 删除所有该元素
     * @param e
     */
    public void removeAllElement(E e){
        List<Integer> list = findAll(e);
        for (Integer i : list ) {
            remove(i);
        }

    }
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(String.format("Array: size = %d , capacity = %d \n",size,data.length));
        str.append("[");
        for (int i = 0 ; i<size ; i++){
            str.append(data[i]);
            if (i != size-1){
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }

    /**
     * 动态数组扩容
     * 新建一个大数组,将数据移动到新数组中,然后指向新数组
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0 ; i < size ; i++)
            newData[i] = data[i];
        data = newData;
    }
}
