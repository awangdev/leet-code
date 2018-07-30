H
1527913608
tags: BST, Binary Search, Divide and Conquer, Binary Indexed Tree, Segment Tree

给一串数字nums[], 求一个新数组result, where result[i] = # of smaller items on right of nums[i]

#### Binary Search
- sort and insert 进一个新list, 新的list是sorted
- 从末尾 i = n-1 遍历nums[]
- 每一次insert nums[i] 进list的位置, 就是# of smaller items on right side of nums[i]
- 每次记录下result[i]
- **问题**: 这里的binary search 是用 `end = list.size(); while(start<end){...}`做的, 可否换成用`end=list.size() - 1`?


#### Segment Tree based on actual value
- Build segment tree based on min/max values of array: set each possible value into leaf
- query(min, target - 1): return count # of smaller items within range [min, target - 1]
- Very similar to `Count of Smaller Number`, where segment tree is built on actual value!!
- IMPORTANT: goal is to find elements on right -> elements processed from left-hand-side can be removed from segment tree
- Use `modify(root, target, -1)` to remove element count from segment tree. Reuse function
- time: `n * log(m)`, where m = Math.abs(max-min). log(m) is used to modify() the leaf element

##### Segment Tree solution - tricky part:
- negative nubmer works oddly with mid and generates endless loop in build(): `[-2, -1]` use case
- build entire segment tree based on [min, max], where min must be >= 0. 
- we can do this by adding Math.abs(min) onto both min/max, as well as +diff during accessing nums[i]



#### Binary Indexed Tree
- TODO, have code

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


class Solution {
    public List<Integer> countSmaller(int[] nums) {
        // check edge case, and create result
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        // create new list for sorting && insertion
        List<Integer> list = new ArrayList<>();

        // for loop, add each number to list 
        // each insertion will be a binary search against the new list
        for (int i = nums.length - 1; i >= 0; i--) {
            int start = 0, end = list.size();
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (list.get(mid) >= nums[i]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            result.add(0, end);
            list.add(end, nums[i]);
        }
        return result;
    }
}

// Binary Indexed Tree? (Fenwick tree) 
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
		if(nums == null || nums.length == 0) {
            return result;
        }
        int n = nums.length;
		Node root = new Node(nums[n - 1]);
		for(int i = n - 1; i >= 0; i--) {
            result.add(0, smaller(root, nums[i]));
        }
			
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

        // else
        return node.left == null ? 0 : node.left.smallerCount;	
	}
}

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
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int diff = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min < 0) {
            diff = Math.abs(min);
            max += diff;
            min += diff;
        }
        // build tree
        root = build(min, max);
        for (int num : nums) {
            modify(root, num + diff, 1);
        }
        // remove curr element, and query for all remaining elements
        for (int num : nums) {
            modify(root, num + diff, -1);
            result.add(query(root, min, num + diff - 1));
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
        if (value <= mid) {
            modify(root.left, value, count);
        } else {
            modify(root.right, value, count);
        }
        root.count = root.left.count + root.right.count;
    }
    
    // start query: get count # of elements within range [start, end]
    private int query(SegmentTreeNode root, int start, int end) {
        if (root == null) return 0;
        if (root.start == start && root.end == end) return root.count;
        
        int mid = (root.start + root.end) / 2;
        if (end < mid) {
            return query(root.left, start, end);
        }
        if (start > mid) {
            return query(root.right, start, end);
        }
        // start <= mid < end
        return query(root.left, start, mid) + query(root.right, mid + 1, end);
    }
}




// Segment tree, timesout. Same reason as in `count of smaller number`
class Solution {
    class SegmentTreeNode{
		int start, end, val;
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
        int n = nums.length;
        // build tree
        root = build(0, n - 1);

        // query for each elements
        for (int i = 0; i < n; i++) {
            result.add(query(root, nums, i, n - 1, nums[i]));
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
    
    private int query(SegmentTreeNode root, int[] nums, int start, int end, int target) {
        // compare when start==end
        if (root.start == start && root.end == end && start == end) {
            if (nums[start] < target) return 1;
            return 0;
        }
        int mid = (root.start + root.end) / 2;
        if (end <= mid) {
            return query(root.left, nums, start, end, target);
        }
        if (start > mid) {
            return query(root.right, nums, start, end, target);
        }
        // aggregate left + right query result
        return query(root.left, nums, start, root.left.end, target) + query(root.right, nums, root.right.start, end, target);
    }
}
```