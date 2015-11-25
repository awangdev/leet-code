又一个Segment tree的例子。
把min number存在区间里面。

类似的有存:max, sum, min, count

如果考到的几率不高。那么这一系列题目就是练习写代码的能力，和举一反三的心态。
```
/*
Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the minimum number between index start and end in the given array, return the result list.

Example
For array [1,2,7,8,5], and queries [(1,2),(0,4),(2,4)], return [2,1,5]

Note
We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Challenge
O(logN) time for each query

Tags Expand 
LintCode Copyright Binary Tree Segment Tree
*/

/*
	Thoughts:
	Build a SegmentMinTree.
	Do search using the interval
*/

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */
public class Solution {
	class SegmentMinTreeNode {
		int start,end,min;
		SegmentMinTreeNode left, right;
		public SegmentMinTreeNode(int start, int end, int min) {
			this.start = start;
			this.end = end;
			this.min = min;
			this.left = null;
			this.right = null;
		}
	}

	public SegmentMinTreeNode build(int start, int end, int[] A) {
		if (start == end) {
			return new SegmentMinTreeNode(start, end, A[start]);
		}
		int min = (start + end) / 2;
		SegmentMinTreeNode left = build(start, min, A);
		SegmentMinTreeNode right = build(min + 1, end, A);
		SegmentMinTreeNode node = new SegmentMinTreeNode(start, end, Math.min(left.min, right.min));
		node.left = left;
		node.right = right;
		
		return node;
	}

	public int search(SegmentMinTreeNode root, int start, int end){
		if (root.start == start && root.end == end) {
			return root.min;
		}

		int mid = (root.start + root.end) / 2;
		if (end <= mid) {
			return search(root.left, start, end);
		}
		if (start > mid) {
			return search(root.right, start, end);
		}

		return Math.min(search(root.left, start, root.left.end), search(root.right, root.right.start, end));
	}


    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public ArrayList<Integer> intervalMinNumber(int[] A, ArrayList<Interval> queries) {
    	ArrayList<Integer> rst = new ArrayList<Integer>();
    	if (A == null || A.length == 0 || queries == null || queries.size() == 0) {
    		return rst;
    	}
    	SegmentMinTreeNode root = build(0, A.length - 1, A);
    	for (Interval range : queries) {
    		int min = search(root, range.start, range.end);
    		rst.add(min);
    	}
    	return rst;
    }
}


















```