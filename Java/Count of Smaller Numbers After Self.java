R
1527913608
tags: BST, Binary Search, Divide and Conquer, Binary Indexed Tree, Segment Tree

给一串数字nums[], 求一个新数组result, where result[i] = # of smaller items on right of nums[i]

#### Binary Search
- sort and insert 进一个新list, 新的list是sorted
- 从末尾 i = n-1 遍历nums[]
- 每一次insert nums[i] 进list的位置, 就是# of smaller items on right side of nums[i]
- 每次记录下result[i]
- **问题**: 这里的binary search 是用 `end = list.size(); while(start<end){...}`做的, 可否换成用`end=list.size() - 1`?

#### Binary Indexed Tree
- TODO, have code

#### Segment Tree
- TODO, it seems too complicated for this problem.

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
```