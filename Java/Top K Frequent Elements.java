M
1528247446
tags: Hash Table, Heap, PriorityQueue

给一串数字, 找到top k frequent element, 并且time complexity 要比nLogN要好

#### PriorityQueue
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

 /*
Thoughts:
O(nLogn) means we can not run priorityQueue.offer within the loop, because the queue.offer operation includes sorting time O(logn).
We can:
1. Use a map to track the occurrence of integer in a Record object
2. Use priority queue to sort the record by occurrence
*/
class Solution {
    class Record {
        public int value;
        public int occurrence;
        public Record(int value) {
            this.value = value;
            this.occurrence = 1;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        final List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result; 
        }
        // Initialize hashmap
        final HashMap<Integer, Record> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new Record(nums[i]));
            } else {
                map.get(nums[i]).occurrence += 1;
            }
        }
        
        // Prepare PriorityQueue
        final PriorityQueue<Record> queue = new PriorityQueue<Record>(10, new Comparator<Record>() {
            public int compare(Record a, Record b) {
                return b.occurrence - a.occurrence;
            }
        });
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