H

与Count of Smaller Number非常类似。以实际的value来构成segment tree，leaf上存（count of smaller number）。

Trick: 先Query，再modify.   
每次Query时候，A[i]都还没有加入到Segment Tree 里面，而A[i+1,...etc]自然也还没有加进去。   
那么就自然是coutning smaller number before itself.   
刁钻啊！   

另外注意：   
在modify里面：多Check了root.start <= index 和  index <= root.end。 过去都忽略了。以后可以把这个也写上。   
（其实是Make sense的，就是更加严格地check了index再 root.left 或者 root.right里面的站位）   

```
/*
Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) . 
For each element Ai in the array, count the number of element before this element Ai is smaller than 
it and return count number array.

Example
For array [1,2,7,8,5], return [0,1,2,3,2]

Note
We suggest you finish problem Segment Tree Build, Segment Tree Query II and 
Count of Smaller Number before itself I first.

Tags Expand 
LintCode Copyright Binary Tree Segment Tree
*/

/*
	Thoughts:
	Just like Count of Smaller Number (in all given array A):
	Create segment tree on index (0 ~ 10000)
	Modify it to store count of equal or smaller numbers comparing to itself.
	However, do query on every A[i] before calling 'modify'!!!!This is the trick.
	Every time, before adding a new count information into the tree, do a query and return result. This way, it's always checking numbers before itself.
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
		if (root.start <= index && index <= mid) {
			modify(root.left, index, count);
		}
		if (mid < index && index <= root.end) {
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
     * @return: Count the number of element before this element 'ai' is 
     *          smaller than it and return count number array
     */ 
    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
    	ArrayList<Integer> rst = new ArrayList<Integer>();
    	
    	SegmentTreeNode root = build(0, 10000);

    	for (int i = 0; i < A.length; i++) {
    		int count = 0;
    		if (A[i] > 0) {
    			count = query(root, 0, A[i] - 1);
    		}
    		rst.add(count);
    		modify(root, A[i], 1);
    	}
    	return rst;
    }
}


```