M

给了segment Tree, node里面有Max value, 找[start,end]里面的max

[start,end]跟mid相比，可能：   
全在mid左   
全在mid右   
包含了mid： 这里要特别break into 2 query method   

按定义：   
mid = (root.start + root.end)/2

```
/*
For an integer array (index from 0 to n-1, where n is the size of this array), 
in the corresponding SegmentTree, each node stores an extra attribute max to denote 
the maximum number in the interval of the array (index from start to end).

Design a query method with three parameters root, start and end,
 find the maximum number in the interval [start, end] by the given root of segment tree.

Example
For array [1, 4, 2, 3], the corresponding Segment Tree is:

                  [0, 3, max=4]
                 /             \
          [0,1,max=4]        [2,3,max=3]
          /         \        /         \
   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
query(root, 1, 1), return 4

query(root, 1, 2), return 4

query(root, 2, 3), return 3

query(root, 0, 2), return 4

Note
It is much easier to understand this problem if you finished Segment Tree Build first.

Tags Expand 
LintCode Copyright Binary Tree Segment Tree
*/

/*
	Thoughts:
	Search the segment tree, and find the node that matches the interval (start, end)
	if (start == root.start && right == root.end) return max;
	if end <= (root.left + root.right) / 2 : go left;
	if start> (root.left + root.right): go right
	However if start <= mid < end, break it into 2 segments and meger afterwards.
*/
/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, max;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int max) {
 *         this.start = start;
 *         this.end = end;
 *         this.max = max
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     *@param root, start, end: The root of segment tree and 
     *                         an segment / interval
     *@return: The maximum number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
    	if (start == root.start && end == root.end) {
    		return root.max;
    	}
    	int mid = (root.start + root.end)/2;
    	if (end <= mid) {
    		return query(root.left, start, end);
    	}
    	if (start > mid) {
    		return query(root.right, start, end);
    	}
    	//start <= mid && end > mid
    	int maxLeft = query(root.left, start, root.left.end);
    	int maxRight = query(root.right, root.right.start, end);

    	return Math.max(maxLeft, maxRight);
    }
}

```