M
tags: Hash Table, Heap, PriorityQueue, MinHeap, MaxHeap
time: O(n)
space: O(n)

给一串数字, 找到top k frequent element, 并且time complexity 要比nLogN要好

#### Method1: Bucket Sort. HashMap + bucket List[]
- Use HashMap to store <num, freq>
- Bucket `List<Integer>[]`: stores <count, list unique element with that count>
    - Size of the data structure will be uniqe item size.
    - The bucket[i] stores item at frequency i
- Simply loop from bucket.length -> 0, when bucket[i] not null, add to result.
- Solid O(n)


#### Method2: PriorityQueue, MinHeap
- Use regualr priorityQueue to sort by frequency ascendingly
- the queue.peek() record has lowest frequency, which is replacable
- Always only maintain k elements in the queue, so sorting is O(logk)
- IMPORTANT: remember to `rst.add(0, x)` for desired ordering
- time faster than maxHeap: O(nlogk)
- option1: just use `map<num, freq>`; option2: use `class Record {int num; int freq}`

#### MaxHeap Attempt. INCORRECT
- 题目有提醒: 必须beetter than O(nLog(n)).
- max heap approach stores all nodes: it is wrong
    - even though freq count size m < n, but it can be m == n. ALL unique. 
    - then it is O(nlogN) again.
- therefore, storing all items into pq is INCORRECT.

```
/**
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

*/

// Method1: bucket sort. Hashmap with count List[]
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> rst = new ArrayList<>();
        if (nums == null) return rst; 

        int n = nums.length;
        Map<Integer, Integer> map = buildFreqMap(nums);
        
        List<Integer>[] bucket = new List[n + 1]; // [0 ~ n]
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            int count = entry.getValue();
            if (bucket[count] == null) bucket[count] = new ArrayList<>();
            bucket[count].add(entry.getKey());
        }
        // Start from largest bucket
        for(int i = n; i >= 0 && rst.size() < k; i--) {
            if(bucket[i] != null) rst.addAll(bucket[i]);
        }
        return rst;
    }

    private Map<Integer, Integer> buildFreqMap(int[] nums) { // init hashmap. O(n)
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        return map;
    }
}


//Method2: Min Heap. O(n) space, O(nlogk) time.
//Option1: Use just map<Integer, Integer>
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null) return result; 

        // init hashmap. O(n)
        Map<Integer, Integer> map = buildFreqMap(nums);
        
        // Prepare PriorityQueue
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, Comparator.comparing(r -> r.getValue()));

        for (Map.Entry<Integer, Integer> entity: map.entrySet()) {
            queue.offer(entity);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        
        while (!queue.isEmpty()) result.add(0, queue.poll().getKey());
        return result;
    }

    private Map<Integer, Integer> buildFreqMap(int[] nums) { // init hashmap. O(n)
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        return map;
    }
}
// Method2, option2: use object Record.
class Solution {
    class Record {
        int value, freq = 0;
        public Record(int value) {
            this.value = value;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null) return result; 

        // init hashmap. O(n)
        Map<Integer, Record> map = buildFreqMap(nums);
        
        // Prepare PriorityQueue
        PriorityQueue<Record> queue = new PriorityQueue<>(k, Comparator.comparing(r -> r.freq));

        for (Record record: map.values()) {
            queue.offer(record);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        
        while (!queue.isEmpty()) result.add(0, queue.poll().value);
        return result;
    }

    private Map<Integer, Record> buildFreqMap(int[] nums) { // init hashmap. O(n)
        Map<Integer, Record> map = new HashMap<>();
        for (int num : nums) {
            map.putIfAbsent(num, new Record(num));
            map.get(num).freq += 1;
        }
        return map;
    }
}



/*
Attempt with MaxHeap: INCORRECT
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