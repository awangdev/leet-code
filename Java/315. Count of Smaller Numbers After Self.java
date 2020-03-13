H
tags: BST, Binary Search, Divide and Conquer, Binary Indexed Tree, Segment Tree
time: O(nlogn)
space: O(n)

给一串数字nums[], 求一个新数组result, where result[i] = # of smaller items on right of nums[i]

#### Method1: Binary Search on processed list
- What if `the processed list is sorted`, so that I can BinarySeach for curr target?
    - process from end
    - binary search for `index to insert new element` in sorted ascending list
    - that index = # of smaller numbers; record it for final result
- time: O(nlogn)
- space: O(n)


#### Method2: Segment Tree based on actual value
- Segment Tree functions:
    - `Build`: construct segment tree based on min/max range: at leaf node, update count of numbers in range
    - `modify(SegmentTreeNode root, int value, int count)`: find leaft at with value, and update count for leaf & all parent nodes
    - `query(SegmentTreeNode root, int start, int end)`: return count # of numbers in range [start, end]
- Very similar to `Count of Smaller Number`, where segment tree is built on actual value!!
- IMPORTANT to drop processed number from left-hand-side: 
    - only find on remaining numbers. 
    - Utilize `modify(root, target, -1)` to erase element count & update the tree.
- time: `n * log(m)`, where m = Math.abs(max-min). log(m) is used to modify() the leaf element
- space: O(m)
- `Define the positive range`
    - negative nubmer division `rounds up towards 0` (this is a problem). (i.e. `(-2 - 1) / 2 = -1.5 = -1`), which causes range error.
    - We want the entire segment tree range to be ascending, and we want the mid = (start+end)/2 to round down.
    - Solution: 
        - build entire segment tree based on [min, max], where min must be >= 0. 
        - we can do this by adding Math.abs(min) onto both min/max, as well as +offset during accessing nums[i]



#### Method3: Binary Search Tree
- https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76580/9ms-short-Java-BST-solution-get-answer-when-building-BST
- Assume we have a BST, where each node has smallerCount and a val, for any new target, how to find smaller items?
    - 1) add the # of smaller count to current node
    - 2) compare:
        - if target < node.val, keep searching `countVisit(node.left, target)`
        - if target > node.val: 1) add currNode.smallerCount, 2) minus node.right.smallertCount (reduce double-counting), 3) plus `countVisit(node.right, target)`
    - remember to create left/right node before dfs countVisit into the sides.


#### Method4: Binary Indexed Tree

```
/*
You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
*/

/*
1. Sort the element and insert into new list.
2. During sort/insertion: use two pointer + binary search: the right pointer will be # of items greater than current index
3. save the # into result
*/

/*
- Method1: binary Search:
    - process from rightside: find the first item nums[i] > target: i should be the # to record
    - and add to sorted position: O(nlogn)
*/
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return rst;
        
        // for loop, add each number to list 
        // each insertion will be a binary search against the new list
        for (int i = nums.length - 1; i >= 0; i--) {
            int biggerIndex = search(list, nums[i]);
            rst.add(0, biggerIndex);
            list.add(biggerIndex, nums[i]);
        }
        return rst;
    }
    
    // find firt index i, where num[i] > target; that's the place to insert nums[i] in sorted list
    private int search(List<Integer> list, int target) {
        if (list.size() == 0) return 0;
        int start = 0, end = list.size() - 1;
        if (target <= list.get(start)) return start;
        if (target > list.get(end)) return end + 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int num = list.get(mid);
            if (num < target) start = mid;
            else end = mid; // num >= end
        }
        if (target <= list.get(start)) return start;
        return end;
    }
}

// Method2: SegmentTree
class Solution {
    class SegmentTreeNode{
		int start, end, count;
		SegmentTreeNode left, right;
		public SegmentTreeNode(int start, int end){
			this.start = start;
            this.end = end;
		}
	}
    SegmentTreeNode root;
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if(nums == null || nums.length == 0)  return result;
        // Find range
        int[] range = findRange(nums);
        int n = nums.length, min = range[0], max = range[1], offset = range[2];
        
        // build tree
        root = build(min, max);
        for (int num : nums) modify(root, num + offset, 1);

        // remove curr element, and query for all remaining elements
        for (int num : nums) {
            modify(root, num + offset, -1);
            result.add(query(root, min, num + offset - 1));
        }

		return result;
	}
    
    // build empty map
    private SegmentTreeNode build(int start, int end) {
        if (start > end) return null;
        if (start == end) return new SegmentTreeNode(start, end);
        int mid = (start + end) / 2;
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        node.left = build(start, mid);
        node.right = build(mid + 1, end);
        return node;
    }
    
    // modify leaf segment node with value, and update all parents alone the way.
    private void modify(SegmentTreeNode root, int value, int count) {
        if (root.start == value && root.end == value) {
            root.count += count;
            return;
        }
        int mid = (root.start + root.end) / 2;
        if (value <= mid) modify(root.left, value, count);
        else modify(root.right, value, count);
        root.count = root.left.count + root.right.count;
    }
    
    // start query: get count # of elements within range [start, end]
    private int query(SegmentTreeNode root, int start, int end) {
        if (root == null) return 0;
        if (root.start == start && root.end == end) return root.count;
        
        int mid = (root.start + root.end) / 2;
        if (end < mid) return query(root.left, start, end);
        if (start > mid) return query(root.right, start, end);
        // start <= mid < end
        return query(root.left, start, mid) + query(root.right, mid + 1, end);
    }
    
    private int[] findRange(int[] nums) {
        int diff = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min < 0) {
            diff = Math.abs(min);
            max += diff;
            min += diff;
        }
        return new int[]{min, max, diff};
    }
}


// Method3: Binary Search Tree
class Solution {
    class Node{
		int val, smallerCount;
		Node left, right;
		public Node(int val){
			this.val = val;
		}
	}
    
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if(nums == null || nums.length == 0) return result;
        int n = nums.length;
		Node root = new Node(nums[n - 1]);
		for(int i = n - 1; i >= 0; i--) result.add(0, smaller(root, nums[i]));
			
		return result;
	}
	public int smaller(Node node, int val){
		node.smallerCount++;
		if(node.val < val){
			if(node.right == null) node.right = new Node(val);
			return node.smallerCount - 1 - node.right.smallerCount + smaller(node.right, val);
		} else if(node.val > val){
			if(node.left == null) node.left = new Node(val);
			return smaller(node.left, val);
		}

        // else node.val == val
        return node.left == null ? 0 : node.left.smallerCount;	
	}
}




```