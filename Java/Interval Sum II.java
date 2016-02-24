H

SegmentTree大集合。记得几个Methods: Build, Query, Modify. 不难。只是要都记得不犯错:)

```
/*
Given an integer array in the construct method, implement two methods query(start, end) and modify(index, value):

For query(start, end), return the sum from index start to index end in the given array.
For modify(index, value), modify the number in the given index to value

Example
Given array A = [1,2,7,8,5].

query(0, 2), return 10.
modify(0, 4), change A[0] from 1 to 4.
query(0, 1), return 6.
modify(2, 1), change A[2] from 7 to 1.
query(2, 4), return 14.
Note
We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Challenge
O(logN) time for query and modify.

Tags Expand 
LintCode Copyright Binary Tree Segment Tree
*/

/*
	Thought:
	This is doing the SegmentSumTree all over again, and also the Segment Tree Modify method.
	1. Create SegmentSumTreeNode
	2. Build it by segment tree definition
	3. query: binary search and calcualte sum
	4. modify: binary search, and re-compare the max at each level.
*/
public class Solution {
    /* you may need to use some attributes here */
	class SegmentSumTreeNode {
		int start,end;
		long sum;
		SegmentSumTreeNode left,right;
		public SegmentSumTreeNode(int start, int end, long sum){
			this.start = start;
			this.end = end;
			this.sum = sum;
			this.left = null;
			this.right = null;
		}
	}    
	public SegmentSumTreeNode build(int start, int end, int[] A) {
		if (start == end) {
			return new SegmentSumTreeNode(start, end, A[start]);
		}
		int mid = (start + end)/2;
		SegmentSumTreeNode left = build(start, mid, A);
		SegmentSumTreeNode right = build(mid + 1, end, A);

		SegmentSumTreeNode node = new SegmentSumTreeNode(start, end, left.sum + right.sum);
		node.left = left;
		node.right = right;	
		return node;
	}

	SegmentSumTreeNode root = null;
    /**
     * @param A: An integer array
     */
    public Solution(int[] A) {
    	if (A == null || A.length == 0) {
			return;
		}
		root = build(0, A.length - 1, A);
    }
    
    /**
     * @param start, end: Indices
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
    	return queryHelper(root, start, end);
    }

    public long queryHelper(SegmentSumTreeNode root, int start, int end){
    	if (start > end) {
    		return 0;
    	} else if (root.start == start && root.end == end) {
    		return root.sum;
    	}
    	int mid = (root.start + root.end)/2;
    	if (end <= mid) {
    		return queryHelper(root.left, start, end);
    	} else if (start > mid) {
    		return queryHelper(root.right, start, end);
    	}
    	return queryHelper(root.left, start, root.left.end) + queryHelper(root.right, root.right.start, end);
    }
    
    /**
     * @param index, value: modify A[index] to value.
     */
    public void modify(int index, int value) {
    	modifyHelper(root, index, value);
    }

    public void modifyHelper(SegmentSumTreeNode node, int index, int value) {
    	if (node.start == index && node.end == index) {
    		node.sum = value;
    		return;
    	}
    	int mid = (node.start + node.end)/2;
    	if (index <= mid) {
    		modifyHelper(node.left, index, value);
    	} else {
    		modifyHelper(node.right, index, value);
    	}
    	node.sum = node.left.sum + node.right.sum;
    }


}


```