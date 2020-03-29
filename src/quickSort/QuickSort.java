package quickSort;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Description: 快速排序
 * @Author zeng
 * @Date 2020/3/29
 **/
public class QuickSort {

    public void quickSort(int[] arr) {
        if (arr.length<=1) {
            return;
        }
        quick(arr,0,arr.length-1);
    }

    private void quick(int[] arr , int start , int end){
        if (start >= end) {
            return;
        }

        //确定key值
        int key = selectKey(arr,start,end);
        //[start...key-1] 是所有小于key的 继续排序
        quick(arr,start,key-1);
        //[key+1...end]是所有大于key的 继续排序
        quick(arr,key+1,end);
    }

    /**
     * l .... r
     * l....j....r
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private int selectKey(int[] arr, int start,int end){
        //先假设排序区间的第一个值为key
        int key = arr[start];
        //用j-1 标记 < key 区间的最后一个元素 , i 标记定位的元素
        int j = start;
        int i = start+1;
        while (i <= end){
            //遍历
            if (arr[i] < key){
                //将小于key的移到j+1的位置
                swap(arr,i,j+1);
                j++;
            }
            i++;
        }
        //将key 与 j交换位置
        swap(arr,start,j);
        return j;
    }

    /**
     * 三路快排
     * @param arr
     * @return
     */
    public void quickSort3(int[] arr){
        if (arr.length<=1){
            return ;
        }
        quickSort3(arr,0,arr.length-1);
    }

    private void quickSort3(int[] arr,int start, int end){
        if (start >end){
            return;
        }
        //找到一个随机的key,key的index范围在start-end之间
        int random = (int) (Math.random() * (end - start + 1)) + start;
        swap(arr,start,random);
        int key = arr[start];
        int lt = start, gt = end+1;
        for (int i = start+1 ; i<gt ; i++){
            if (arr[i] < key){
                //放到小的区域
                swap(arr,i,lt+1);
                lt++;
                i++;
            }else if (arr[i] > key){
                //放到大的区域
                swap(arr,i,gt-1);
                gt--;
            }
        }
        swap(arr,lt,start);
        quickSort3(arr, start, lt-1);
        quickSort3(arr,gt,end);
    }

    private void swap(int [] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {9,6,3,5,7,1,0,70,7,6,4,5,9,70,70,70};
        int[] arr2 = {9,8,10,80,70,6,5,6,4,8,9,7,0,70,70,70,80};
        quickSort.quickSort(arr);
        quickSort.quickSort3(arr2);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
    }
}
