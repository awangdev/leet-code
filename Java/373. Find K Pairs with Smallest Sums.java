M
tags: MinHeap, MaxHeap, Heap
time: O(klogk)
space: O(k)

#### Method1: MinHeap wiht k size
- This approach follows the pattern of finding min pair: 
  - 1) only need to store k pairs
  - 2) always start from min of A list and min of B list
  - 3) pre-build k pairs honoring A list, and then pick the min pair, and start swapping with min of list B 
- First attemp all first k pairs from nums1[i] against nums2[0] <=k : O(k)
- Use queue to pull min node and save results
- Use the nums1 val from the min node, pair up with nums2[j], add back to queue to sort
- overall runtime: O(klogk)
- space: O(k)

#### Method2: MaxHeap with k size
- Brutle: build all pairs time O(mn), sort with maxHeap pq with k size, and find top k
- overall time: O(mnLogK)
- space: O(k)

```
/*

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]] 
Explanation: The first 3 pairs are returned from the sequence: 
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence: 
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
*/

/*
Method1: MinHeap
*/
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> rst = new ArrayList<>();
        if (k <= 0 || nums1.length == 0 || nums2.length == 0) return rst;

        Queue<List<Integer>> queue = initQueue(k);
        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < m && i <= k; i++) {
            queue.offer(Arrays.asList(nums1[i], nums2[0], 0));
        }
        
        while(!queue.isEmpty() && k-- != 0) {
            List<Integer> node = queue.poll();
            rst.add(Arrays.asList(node.get(0), node.get(1)));
            int tailIndex = node.get(2) + 1;
            if (tailIndex >= n) continue;
            queue.offer(Arrays.asList(node.get(0), nums2[tailIndex], tailIndex));
        }
        
        return rst;
    }
    
    private PriorityQueue<List<Integer>> initQueue(int k) {
        return new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            public int compare(List<Integer> a, List<Integer> b) {
                return a.get(0) + a.get(1) - b.get(0) - b.get(1);
            }
        });
    }
}

/*
Method2:
Brutle: build all pairs time O(mn), sort with maxHeap pq with k size, and find top k
overall time: O(mnLogK)
space: O(k)
*/
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> rst = new ArrayList<>();
        if (k <= 0) return rst;

        Queue<List<Integer>> queue = initQueue(k);
        int m = nums1.length, n = nums2.length;
        for (int a : nums1) {
            for (int b : nums2) {
                queue.offer(Arrays.asList(a, b));
                if (queue.size() > k) queue.poll();
            }
        }  
        
        while(!queue.isEmpty()) {
            rst.add(0, queue.poll());
        }
        
        return rst;
    }
    
    private PriorityQueue<List<Integer>> initQueue(int k) {
        return new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            public int compare(List<Integer> a, List<Integer> b) {
                return b.get(0) + b.get(1) - a.get(0) - a.get(1);
            }
        });
    }
}


```