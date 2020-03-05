package Trie;

import java.util.TreeMap;

public class Trie {

    private class Node{
        public boolean isWord;  //这个节点是否是单词尾
        public TreeMap<Character,Node> next; //下一个节点map

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    //获得Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    /**
     * 将字符串拆解为一个一个字符,添加到节点中
     * @param word
     */
    public void add(String word){
        Node cur = root;
        for (int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);

            //没有在它的下一个节点找到字符 c 的映射,则将c加入map
            if (cur.next.get(c) == null)
                cur.next.put(c,new Node());

            //找到了c的映射
            cur = cur.next.get(c);
        }

        //一直找到了最后一个字母,这个就是单词的尾部,判断是否已经有这个单词了.
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询单词word是否在Trie中
     * @param word
     * @return
     */
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);

            if (cur.next.get(c) == null)
                return false;

            cur = cur.next.get(c);
        }
        return cur.isWord;      //panda 中pan 的 n 节点isWord = false;
    }

    /**
     * 查询是否在Tire中有单词以prefix为前缀
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0 ; i < prefix.length() ; i ++){
            char c = prefix.charAt(i);

            if (cur.next.get(c) == null)
                return false;

            cur = cur.next.get(c);
        }
        return true;
    }

}
