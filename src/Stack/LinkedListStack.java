package Stack;

import LinkedList.LinkedList;

/**
 * 使用链表实现栈
 * @param <E>
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;
    //链表动态增长的,所以没有容量这个东西

    public LinkedListStack(){
        list = new LinkedList<E>();
    }


    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Stack: top ");
        str.append(list);   //list重写了 toString方法
        return str.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();

        for (int i = 0 ; i< 5 ;i++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);

        System.out.println(stack.peek());
    }
}
