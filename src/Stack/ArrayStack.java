package Stack;

import Array.Array;

/**
 * 基于动态数组实现栈
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {

    Array<E> array;

    //传入容积
    public ArrayStack(int capacity){
        array = new Array<E>(capacity);
    }

    public ArrayStack(){
        this(10);
    }

    /**
     * 获取容积
     * @return
     */
    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 入栈
     * @param e
     */
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    /**
     * 出栈
     * @return
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 栈顶元素
     * @return
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Stack: ");
        str.append("[");
        for (int i =0 ;i <array.getSize();i++){
            str.append(array.get(i));
            if (i != array.getSize() - 1)
                str.append(", ");
        }
        str.append("] top");
        return str.toString();
    }
}
