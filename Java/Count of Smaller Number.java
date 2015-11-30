和平时的segment tree问题不同。
这个给了实际的value，而还是造一个based on index的segment tree才行。
Thought1是失败的，因为虽然省了空间，但是search time还是O(n).
Thought2才是真正的segment tree (based on index interval).

重要trick:
在query前，给进去的start和end是： 0 ~ value-1.
value-1就是说，找比自己所在range小1的range（那么自然而然地就不包括自己了），这样就找到了smaller number.
这个trick还挺刁钻的。
```
/*
Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an query list. For each query, give you an integer, return the number of element in the array that are smaller than the given integer.


Example
For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]

Note
We suggest you finish problem Segment Tree Build and Segment Tree Query II first.

Challenge
Could you use three ways to do it.

Just loop
Sort and binary search
Build Segment Tree and Search.
Tags Expand 
Binary Search LintCode Copyright Segment Tree

*/
/*
	Thought2: http://www.jiuzhang.com/solutions/count-of-smaller-number/
	Build a tree based on index 0 ~ 10000. Then use modify to update the tree with proper 'count' value
	Use query method to search for final results.
	Each A[i] will be stored at index value of A[i]. 
	Count: how many numbers do we have from bottom till this level, including the A[i] itself.
		For example, at the lowest A[i] spot, SegmentTreeNode(i,i), the  count == 1.

	Note:Again, be careful on calculating the mid. It's usually based on root.start and root.end, instead of the target start,end interval.
*/

public class Solution {

	public class SegmentTreeNode {
		public int start,end;
		public int count;
		public SegmentTreeNode left, right;
		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.count = 0;
			this.left = null;
			this.right = null;
		}
	}

	/*	Build a empty segment tree based on index*/
	public SegmentTreeNode build(int start, int end) {
		if (start > end) {
			return null;
		}
		if (start == end) {
			return new SegmentTreeNode(start, end);
		}
		SegmentTreeNode root = new SegmentTreeNode(start, end);
		int mid = start + (end - start) / 2;
		root.left = build(start, mid);
		root.right = build(mid + 1, end);
		return root;
	}

	/*	Update the tree with 'count': from bottom to this specific tree node, how many integers do we have.*/
	public void modify(SegmentTreeNode root, int index, int count){
		if (root.start == index && root.end == index) {
			root.count += count;
			return;
		}
		int mid = root.start + (root.end - root.start)/2;
		if (index <= mid) {
			modify(root.left, index, count);
		}
		if (index > mid) {
			modify(root.right, index, count);
		}
		root.count = root.left.count + root.right.count;
	}
	
	/*	Look for that number based on start&&end*/
	public int query(SegmentTreeNode root, int start, int end) {
		if (root.start == start && root.end == end) {
			return root.count;
		}
		int sum = 0;
		int mid = root.start + (root.end - root.start)/2;
		if (end <= mid) {
			sum += query(root.left, start, end);
		} else if (start > mid) {
			sum += query(root.right, start, end);
		} else if (start <= mid && mid < end) {
			sum += query(root.left, start, mid);
			sum += query(root.right, mid + 1, end);
		}
		return sum;
	}


   /**
     * @param A: An integer array
     * @return: The number of element in the array that 
     *          are smaller that the given integer
     */
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
    	ArrayList<Integer> rst = new ArrayList<Integer>();
    	
    	SegmentTreeNode root = build(0, 10000);
    	for (int value : A) {
    		modify(root, value, 1);
    	}
    	for (int value : queries) {
    		int count = 0;
    		if (value > 0) {
    			count = query(root, 0, value - 1);
    		}
    		rst.add(count);
    	}
    	return rst;
    }
}






/*
	Time limit exceeded...
	Because: If we build the tree based on given index 0~n, and build 'query' method based on its max values. It will work for small scale, but when it gets larger, we could be doing O(n)*m all the time. Everytime we search root.left and root.right, which is not binary search style : )

	Thoughts:
	Build SegmentTree, store max
	1st attempt: time exceeds. Because the 'query part' is actually not segment tree search. Doing search based on max value can find answer, but it's O(n) search.
	Note:
	This segment tree problem gives queries of actaul value, rather than a range(start,end) that we can directly use in segment tree. So we need some sort of conversion, that still provide (start,end) to search
*/

public class Solution {
	public class SegmentTreeNode {
		int start, end, max;
		SegmentTreeNode left,right;
		public SegmentTreeNode(int start, int end, int max){
			this.start = start;
			this.end = end;
			this.max = max;
			this.left = null;
			this.right = null;
		}
	}

	public SegmentTreeNode build(int[] A, int start, int end){
	    if (A == null || A.length == 0) {
	        return null;
	    }
		if (start == end) {
			return new SegmentTreeNode(start, end, A[start]);
		}

		int mid = (start + end)/2;
		SegmentTreeNode left = build(A, start, mid);
		SegmentTreeNode right = build(A, mid + 1, end);
		SegmentTreeNode node = new SegmentTreeNode(start, end, Math.max(left.max, right.max));
		node.left = left;
		node.right = right;

		return node;
	}

	public int query(SegmentTreeNode root, int val) {
		if (root == null) {
			return 0;
		}
		if (root.start == root.end && root.max >= val) {//leaf, stll >= val
			return 0;
		}
		if (root.max < val) {
			return root.end - root.start + 1;
		}
		//root.max > val, but unknown if root.children could < val
		return query(root.left, val) + query(root.right, val);
	}


   /**
     * @param A: An integer array
     * @return: The number of element in the array that 
     *          are smaller that the given integer
     */
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
    	ArrayList<Integer> rst = new ArrayList<Integer>();
    	if (queries == null || queries.length == 0) {
    		return rst;
    	}

    	SegmentTreeNode root = build(A, 0, A.length - 1);
    	for (int num : queries) {
    	    int count = 0;
    	    if (root != null) {
    	       count = query(root, num);
    	    }
    		rst.add(count);
    	}
    	return rst;
    }
}


```