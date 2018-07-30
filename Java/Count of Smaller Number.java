M
1532929143
tags: Segment Tree, Binary Search, Lint

给一串数字, array size = n. 给一串query: 每个query是一个数, 目的找 count# items smaller than query element.

#### Segment Tree
- 和平时的segment tree问题不同。 [0 ～ n] 代表实际数字: based on real value的segment tree.
- Modify时，把array里面的value带进去，找到特定的位子, 然后count + 1. 
- 最终在SegmentTree leaf上面全是array里面实际的数字。
- node.count: 在node range里面的有多少个数字

##### right use of modify()
- build() 只是 empty segment tree, 没有property
- modify() 需要: 1. 找到left, update count+=1; 2. aggregate all parent when after returning
- 所以每一个modify 都是在整个path上所有的node上 + count

##### query trick
- 在query前，给进去的start和end是： 0 ~ value-1.   
- `value-1`: 找比自己所在range小1的range（那么自然而然地就不包括自己了），这样就找到了smaller number.   

##### About other basic segment tree setup
- [那么其他做过的SegmentTree是怎么样呢？]   
- 那些构成好的SegmentTree(找min,max,sum)也有一个Array。但是构成Tree时候，随Array的index而构架。   
- 也就是说，假如有Array[x,y,....]:在leaf,会有[0,0] with value = x. [1,1] with value = y. 
- [但是这题]   
- 构成时，是用actual value.也就是比如Array[x,y,....]会产生leaf:[x,x]with value = ..; [y,y]with value =...    
- 其实很容易看穿:   
- 若给出一个固定的array构成 SegmentTree，那估计很简单：按照index从0~array.lengh，leaf上就是[0,0] with value = x.
- 若题目让构造一个空心SegmentTree, `based on value 0 ~ n-1 (n <= 10000)`, 然后把一个Array的value modify 进去。   
- 这样八成是另外一种咯。

```
/*
Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) 
and an query list. For each query, give you an integer, return the number of element in the array that 
are smaller than the given integer.


Example
For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]

Note
We suggest you finish problem Segment Tree Build and Segment Tree Query II first.

Challenge
Could you use three ways to do it.

1. Just loop
2. Sort and binary search
3. Build Segment Tree and Search.

Just loop
Sort and binary search
Build Segment Tree and Search.
Tags Expand 
Binary Search LintCode Copyright Segment Tree

*/

/*
Thought1是失败的，因为虽然省了空间，但是search time还是O(n).
Thought2才是真正的segment tree (based on index interval).
*/
/*
	Thought2: http://www.jiuzhang.com/solutions/count-of-smaller-number/
	Build a tree based on index 0 ~ 10000. Then use modify to update the tree with proper 'count' value
	Use query method to search for final results.
	Each A[i] will be stored at index value of A[i]. 
	Count: how many numbers do we have from bottom till this level, including the A[i] itself.
		For example, at the lowest A[i] spot, SegmentTreeNode(i,i), the count == 1.

	Note:Again, be careful on calculating the mid. It's usually based on root.start and root.end, 
	instead of the target start,end interval.
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
		}
	}
	
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
    	List<Integer> rst = new ArrayList<>();
    	
		// build segment tree based off value with node.count = 0
    	SegmentTreeNode root = build(0, 10000);
		// populate count from leaf -> root
    	for (int value : A) {
    		modify(root, value, 1);
    	}
		// Find item in target range [0~ n - 1]
    	for (int query : queries) {
    		int count = 0;
    		if (query > 0) {//Given value has to be in n's range: [0, 10000]
    			count = query(root, 0, query - 1);
    		}
    		rst.add(count);
    	}
    	return rst;
    }

	/*	Build a empty segment tree based on index */
	public SegmentTreeNode build(int start, int end) {
		if (start > end) return null;
		if (start == end) return new SegmentTreeNode(start, end);
	    
		SegmentTreeNode root = new SegmentTreeNode(start, end);
		int mid = start + (end - start) / 2;
		root.left = build(start, mid);
		root.right = build(mid + 1, end);
		return root;
	}

	/*	
		Update the tree with 'count': from bottom to this specific tree node, how many integers do we have.
		Reason of modify function: once children node is updated, parent node will update as well.
	*/
	public void modify(SegmentTreeNode root, int index, int count){
		if (root.start == index && root.end == index) {
			root.count += count; // basically + 1
			return;
		}
		int mid = root.start + (root.end - root.start)/2;
		if (index <= mid) {
			modify(root.left, index, count);
		} else { //index > mid
			modify(root.right, index, count);
		}
		root.count = root.left.count + root.right.count;
	}
	
	/*	Look for that number based on start&&end*/
	public int query(SegmentTreeNode root, int start, int end) {
		if (root.start == start && root.end == end) return root.count;
		
		int mid = root.start + (root.end - root.start)/2;
		if (end <= mid) {
			return query(root.left, start, end);
		} else if (start > mid) {
			return query(root.right, start, end);
		}
		// start <= mid && mid < end) 
		return query(root.left, start, mid) + query(root.right, mid + 1, end);
	}
}

// All the same, execpt, modify() does not need to take `count` input. Count is always +1 when the target element is found.
public class Solution {

	public class SegmentTreeNode {
		public int start,end;
		public int count;
		public SegmentTreeNode left, right;
		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.count = 0;
		}
	}
	
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
    	List<Integer> rst = new ArrayList<>();
    	
    	SegmentTreeNode root = build(0, 10000);
    	for (int value : A) {
    		modify(root, value);
    	}
    	for (int query : queries) {
    		int count = 0;
    		if (query > 0) {//Given value has to be in n's range: [0, 10000]
    			count = query(root, 0, query - 1);
    		}
    		rst.add(count);
    	}
    	return rst;
    }

	/*	Build a empty segment tree based on index*/
	public SegmentTreeNode build(int start, int end) {
		if (start > end) return null;
		if (start == end) return new SegmentTreeNode(start, end);
	    
		SegmentTreeNode root = new SegmentTreeNode(start, end);
		int mid = start + (end - start) / 2;
		root.left = build(start, mid);
		root.right = build(mid + 1, end);
		return root;
	}

	/*	Update the tree with 'count': from bottom to this specific tree node, how many integers do we have.*/
	public void modify(SegmentTreeNode root, int index){
		if (root.start == index && root.end == index) {
			root.count += 1;
			return;
		}
		int mid = root.start + (root.end - root.start)/2;
		if (index <= mid) {
			modify(root.left, index);
		} else { //index > mid
			modify(root.right, index);
		}
		root.count = root.left.count + root.right.count;
	}
	
	/*	Look for that number based on start&&end*/
	public int query(SegmentTreeNode root, int start, int end) {
		if (root.start == start && root.end == end) return root.count;
		
		int mid = root.start + (root.end - root.start)/2;
		if (end <= mid) {
			return query(root.left, start, end);
		} else if (start > mid) {
			return query(root.right, start, end);
		}
		// start <= mid && mid < end) 
		return query(root.left, start, mid) + query(root.right, mid + 1, end);
	}
}

/*
	Time limit exceeded...
	Because: If we build the tree based on given index 0~n, and build 'query' method based on its max values. 
	It will work for small scale, but when it gets larger, we could be doing O(n)*m all the time. 
	Everytime we search root.left and root.right, which is not binary search style : )

	Thoughts:
	Build SegmentTree, store max
	1st attempt: time exceeds. Because the 'query part' is actually not segment tree search. 
	Doing search based on max value can find answer, but it's O(n) search.
	Note:
	This segment tree problem gives queries of actaul value, rather than a range(start,end) that we can directly use in segment tree. 
	So we need some sort of conversion, that still provide (start,end) to search
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