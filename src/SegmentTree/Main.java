package SegmentTree;

public class Main {
    public static void main(String[] args) {

        Integer[] nums = {-2,0,3,-5,2,-1};

        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, new Merger<Integer>() {  //匿名内部类
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });

        System.out.println(segmentTree.query(0,3));



        SegmentTree<Integer> tree = new SegmentTree<Integer>(nums, (a, b) -> a-b); //lamde 表达式 线段树记录 a-b
        System.out.println(tree.query(0,2));
    }
}
