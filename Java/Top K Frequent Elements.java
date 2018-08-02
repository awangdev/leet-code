M
1533166756
tags: Hash Table, Heap, PriorityQueue, MinHeap, MaxHeap
time: O(n)
space: O(n)

给一串数字, 找到top k frequent element, 并且time complexity 要比nLogN要好

#### HashMap + bucket List[]
- Use HashMap to store <num, freq>
- Reverse mapping <count, list unique element with that count> in a `bucket = new List[n]`. 
- Size of the data structure will be m <= n
- The bucket[count] preserves order from end of the array.
- Simply loop over the reversed map, we can find the top k items.
- Solid O(n)

#### PriorityQueue, MinHeap
- Use regualr priorityQueue to sort by frequency ascendingly
- the queue.peek() record has lowest frequency, which is replacable
- Always only maintain k elements in the queue, so sorting is O(logk)
- IMPORTANT: remember to `rst.add(0, x)` for desired ordering
- time faster than maxHeap: O(nlogk)

#### PriorityQueue, MaxHeap
- 题目有提醒: 必须beetter than O(nLog(n)), 也就是说明要O(n)
- 首先想到就是PriorityQueue, 并且不能queue.offer on the fly
- 那么就先count, O(n), using HashMap
- 再priorityQueue, (mLog(m)), m是unique 数字的总量
- 最终find top k, O(k)
- Overall time: O(n) + O(mLogm) + O(k) => O(n), if m is small enough


```
/**
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

// Hashmap with count List[]
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) return rst; 

        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        List<Integer>[] bucket = new List[n + 1]; // [0 ~ n]
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            int count = entry.getValue();
            if (bucket[count] == null) {
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(entry.getKey());
        }
        
        for(int i = n; i >= 0 && rst.size() < k; i--) {
            if(bucket[i] != null) {
                rst.addAll(bucket[i]);
            }
        }
        return rst;
    }
}
// Min Heap. O(n) space, O(nlogk) time.
class Solution {
    class Record {
        public int value;
        public int freq = 0;
        public Record(int value) {
            this.value = value;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result; 

        // init hashmap. O(n)
        Map<Integer, Record> map = new HashMap<>();
        for (int num : nums) {
            map.putIfAbsent(num, new Record(num));
            map.get(num).freq += 1;
        }
        
        // Prepare PriorityQueue
        PriorityQueue<Record> queue = new PriorityQueue<>(k, new Comparator<Record>() {
            public int compare(Record a, Record b) {
                return a.freq - b.freq;
            }
        });
        // Lambda comparator is slow
        //PriorityQueue<Record> queue = new PriorityQueue<>(Comparator.comparing(a -> a.freq));
        for (Record record: map.values()) {
            queue.offer(record);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        
        while (!queue.isEmpty()) {
            result.add(0, queue.poll().value);
        }
        return result;
    }
}

/*

MaxHeap
O(nLogn) means we can not run priorityQueue.offer within the loop, because the queue.offer operation includes sorting time O(logn).
We can:
1. Use a map to track the occurrence of integer in a Record object
2. Use priority queue to sort the record by occurrence
*/
class Solution {
    class Record {
        public int value;
        public int freq = 0;
        public Record(int value) {
            this.value = value;
        }
        public int getFreq() {
            return this.freq;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result; 

        // init hashmap. O(n)
        Map<Integer, Record> map = new HashMap<>();
        for (int num : nums) {
            map.putIfAbsent(num, new Record(num));
            map.get(num).freq += 1;
        }
        
        // Prepare PriorityQueue, using new lambda comparator interface
        PriorityQueue<Record> queue = new PriorityQueue<>(Comparator.comparing((Record a) -> a.getFreq()).reversed());
        for (Record record: map.values()) {
            queue.offer(record);
        }
        
        for (int i = 0; i < k; i++) {
            result.add(queue.poll().value);
        }
        return result;
    }
}



```