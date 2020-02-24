package Set;

import BST.BST;

import java.util.ArrayList;
import java.util.List;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<E>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
        List<Character> list = new ArrayList<>();
        for (int i=0 ; i<1000 ; i++){
            list.add('a');
            list.add('b');
            list.add('c');
        }
        System.out.println(list.size());

        BSTSet<Character> set = new BSTSet<Character>();
        for (Character c : list){
            set.add(c);
        }

        System.out.println(set.getSize());
    }
}
