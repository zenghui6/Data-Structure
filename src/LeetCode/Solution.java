package LeetCode;

import java.util.*;

class Solution {

    /**
     * 内部类,频次
     */
    private class Freq {
        int e , freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        //统计
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num,map.get(num) + 1);

            else
                map.put(num,1);
        }

        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>(new Comparator<Freq>() {        //匿名内部类的比较器
            @Override
            public int compare(Freq o1, Freq o2) {
                return o1.freq - o2.freq;
            }
        });      //指定比较器的优先队列
        for (int key : map.keySet() ) {        //遍历map中的key
            if (priorityQueue.size() < k)       //将前 k 个元素加入queue中
                priorityQueue.add(new Freq(key,map.get(key)));

            else if (map.get(key) > priorityQueue.peek().freq){        //这是个最小堆的优先队列,队首最小,如果小于队首,入优先队列
                priorityQueue.remove();
                priorityQueue.add(new Freq(key,map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!priorityQueue.isEmpty()){
            res.add(priorityQueue.remove().e);
        }
        return res;
    }
}
