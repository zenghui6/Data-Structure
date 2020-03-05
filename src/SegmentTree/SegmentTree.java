package SegmentTree;

/**
 * 线段树
 * @param <E>
 */
public class SegmentTree<E> {

    //一个静态数组,用来存储叶子数据
    private E[] data;
    //一个tree数组,用来存储所有树节点
    private E[] tree;
    //merger融合器,用户指定线段树的功能
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0 ; i < arr.length ; i++)
            data[i] = arr[i];

        //根据计算得出 n 个元素构成的线段树大约需要4n的空间
        tree = (E[]) new  Object[4*arr.length];

        //开始创建线段树, tree index 为 0  [0,data.length-1]
        buildSegmentTree(0,0,data.length-1);
    }

    /**
     * 在treeIndex的位置创建表示区间[l...r]的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex,int l ,int r){

        //该区间只有一个元素,那就是叶子节点了,就直接在树中赋值
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        // 不是叶子节点
        int leftTreeIndex  = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        //计算子树的区间范围
         int mid = l + (r-l)/2;  //区间中间
        //左子树 leftTreeIndex [l,mid]   右子树rightTreeIndex [mid+1,r]
        buildSegmentTree(leftTreeIndex , l , mid);
        buildSegmentTree(rightTreeIndex, mid+1, r);

        //然后给线段树的非叶子节点赋值,这与线段树的功能有关,比如这里是区间之和(使用merger融合器指定线段树功能)
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);

    }

    /**
     * 返回区间[queryL, queryR]的值
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL,int queryR){
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal");         //数据校验

        return query(0, 0 , data.length-1 , queryL , queryR);
    }

    /**
     * 在以treeIndex为根的线段树中[l....r] 的范围 ,搜索区间[queryL ... queryR]的值
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex , int l , int r, int queryL , int queryR){
        if ( l == queryL && r == queryR){
            return tree[treeIndex];
        }

        int mid = l + (r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1)       //要查询的最小值比中间值还小, 那就直接区右子树区查询
            return query(rightTreeIndex,mid + 1,r,queryL,queryR);

        else if (queryR <= mid) //与右边没关系
            return query(leftTreeIndex,l , mid , queryL, queryR);

        //l 在左边子树, r 在右边子树
        E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
        E rightResult = query(rightTreeIndex,mid+1 , r,mid+1 , queryR);

        //融合结果
        return  merger.merge(leftResult,rightResult);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if (index <  0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");

        return data[index];
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return 2*index + 1;
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return 2*index + 2;
    }

}
