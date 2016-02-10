其实是segment tree 每个node上面加个sum。
构建tree记得怎么弄就好了。很简单的。
但是，我犯了错..在search的时候求mid时，忘记了以root为基准（我用interval.start and interval.end为基准，当然错了）
但实际上search起来就是binary search的想法，在interval/segment tree上面跑。顺顺哒。
```
/*

Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the sum number between index start and end in the given array, return the result list.

Example
For array [1,2,7,8,5], and queries [(0,4),(1,2),(2,4)], return [23,9,20]

Note
We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Challenge
O(logN) time for each query
*/

/*
	Thoughts:
	Feels like constructing segment tree, and attach 'interval sum' to each node, after conquer its left and right child's sum.
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
	public class SegmentSumTreeNode {
		public int start, end;
		public long sum;
		public SegmentSumTreeNode left,right;
		public SegmentSumTreeNode(int start, int end, long sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
			this.left = null;
			this.right = null;
		}
	}

	public SegmentSumTreeNode buildTree(int[] A, int start, int end) {
    	if (start == end) {
    		return new SegmentSumTreeNode(start, end, A[start]);
    	}
    	int mid = (start + end)/2;
    	SegmentSumTreeNode leftChild = buildTree(A, start, mid);
    	SegmentSumTreeNode rightChid = buildTree(A, mid + 1, end);

    	SegmentSumTreeNode node = new SegmentSumTreeNode(start, end, leftChild.sum + rightChid.sum);
    	node.left = leftChild;
    	node.right = rightChid;

    	return node;
    }

    public long searchTree(SegmentSumTreeNode root, int start, int end) {
    	if (root.start == start && root.end == end) {
    		return root.sum;
    	}
    	int mid = (root.start + root.end)/2;
    	if (end <= mid) {
    		return searchTree(root.left, start, end);
    	} else if (start > mid) {
    		return searchTree(root.right, start, end);
    	}
    	//start <= mid < end
    	return searchTree(root.left, start, root.left.end) + searchTree(root.right, root.right.start, end);
    }

    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public ArrayList<Long> intervalSum(int[] A, ArrayList<Interval> queries) {
    	ArrayList<Long> rst = new ArrayList<Long>(); 
        if (A == null || A.length == 0 || queries == null || queries.size() == 0) {
        	return rst;
        }
        SegmentSumTreeNode root = buildTree(A, 0, A.length - 1);

        for (Interval range : queries) {
        	long sum = 0;
        	/*
        	//Check for errors, but don't have to do these checks
            //Well, it's being checked in segment query II
        	if (range.start < root.start && range.end > root.end) {
        		sum = root.sum;
        	} else if (range.start < root.start && range.end <= root.end) {
        		sum = searchTree(root, root.start, range.end);
        	} else if (range.start >= root.start && range.end > root.end) {
        		sum = searchTree(root, range.start, root.end);
        	} else {
        		sum = searchTree(root, range.start, range.end);
        	}*/
        	sum = searchTree(root, range.start, range.end);
        	rst.add(sum);
        }
        return rst;
    }
  
}
```