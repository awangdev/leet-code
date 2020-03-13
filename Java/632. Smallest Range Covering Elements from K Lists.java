H
tags: Sliding Window, Two Pointers, Hash Table
time: O(nlogn), n = total elements
space: O(n) to store sorted list

#### Method1: Sliding Window
- First sort all of the items together by actual val using `Node {int val, int row}`
- Slinding window goal: 
    - 1) use right to find range that touches all rows, 
    - 2) use left to shrink the range
- Sliding Window Template
    - move right pointer
    - Counts[i] = # of elements used in left/right range
        - when counts[i] == 0, countUnique++; the number of row/list being included
    - when count == row size: 
        - processing & save shorter range by using left/right Pointers
        - move left pointer; when counts[i] == 0, countUnique--
- time: O(nlogn) for initial sort and then O(n) to process
- space: O(n)
- What is hard here? To think of the idea of counting one usage of each row: 
    - when each all rows are used at least 1 time
    - calculate the min dist

#### Method2 PQ?, Similar to merging k array
- https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/discuss/104893/Java-Code-using-PriorityQueue.-similar-to-merge-k-array
- https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/solution/

```
/**
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

 

Example 1:

Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
 

Note:

The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.

*/

class Solution {
    private class Node{
        int val, row;
        public Node(int val, int row) {
            this.val = val;
            this.row = row;
        }
    }
    
    public int[] smallestRange(List<List<Integer>> nums) {
        
        List<Node> list = sortByVal(nums);
        int n = nums.size(), left = 0, right = 0, count = 0;
        int minStartVal = -1, minDist = Integer.MAX_VALUE;
        int[] rowFreq = new int[n]; // row freq
        
        // goal: 1) use right to find range that touches all rows, 2) use left to shrink the range
        while (right < list.size()) {
            Node node = list.get(right);
            // move right: if this is very first time when this row is visited, count++
            if (rowFreq[node.row]++ == 0) count++;
            // process count == n: all  rows have been visisted
            while (count == n) {
                Node leftNode = list.get(left);
                int dist = node.val - leftNode.val + 1;
                if (dist < minDist) {
                    minStartVal = leftNode.val;
                    minDist = dist;
                }
                // move left
                while (left <= right && leftNode.val == list.get(left).val) {
                    if (--rowFreq[list.get(left).row] == 0) count--; // this breaks `count==n`
                    left++;
                }
            }
            right++;
        }
        
        return new int[]{minStartVal, minStartVal + minDist - 1};
    }
    
    private List<Node> sortByVal(List<List<Integer>> nums) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);
            for (int val : row) list.add(new Node(val, i));
        }
        Collections.sort(list, Comparator.comparing(a -> a.val));
        return list;
    }
}

```