package LeetCode;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        List<Integer> list = new LinkedList<>();
        //找到一个相交的数后,将该数从set中移除,这样就不会有重复的交集了
        for (int num : nums2) {
            if (set.contains(num)){
                list.add(num);
                set.remove(num);
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0 ; i < list.size() ; i ++){
            ret[i] = list.get(i);
        }

        return ret;
    }
}
