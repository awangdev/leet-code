M


给的是Array。注意找区间内的max, assign给区间。   其余和普通的segment tree build一样   

给了array,但是并不根据array里的内容排位，而是依然根据index in [0, array.length - 1]割开区间，break到底，   
最终start==end。同时assign max=A[start] or A[end]

往上,parent一层的max:就是比较左右孩子,其实都是在两个sub-tree里面比较sub-tree的max。   

这就好做了：   
先分，找到left/right，比较max,在create current node,再append到当前node上面。

实际上是depth-first, 自底向上建立起的。

```
/*
The structure of Segment Tree is a binary tree which each node has two attributes start and end 
denote an segment / interval.
start and end are both integers, they should be assigned in following rules:
The root's start and end is given by build method.
The left child of node A has start=A.left, end=(A.left + A.right) / 2.
The right child of node A has start=(A.left + A.right) / 2 + 1, end=A.right.
if start equals to end, there will be no children for this node.

Implement a build method with a given array, so that we can create a corresponding segment tree 
with every node value represent the corresponding interval max value in the array, 
return the root of this segment tree.


Example
Given [3,2,1,4]. The segment tree will be:
                 [0,  3] (max = 4)
                  /            \
        [0,  1] (max = 3)     [2, 3]  (max = 4)
        /        \               /             \
[0, 0](max = 3)  [1, 1](max = 2)[2, 2](max = 1) [3, 3] (max = 4)
Clarification
Segment Tree (a.k.a Interval Tree) is an advanced data structure which can support queries like:
which of these intervals contain a given point
which of these points are in a given interval
See wiki: Segment Tree Interval Tree
Tags Expand 
Segment Tree
*/

/*
Thoughts:
Similar to Segment Tree, where the start and end are just : 0 and arrary.length -1.
For max value:  How about a backgracking? SO: split and recursive build tree first; 
each recursion, compare the returnning left/right child's max value, and then put into curr node's max.
Note: each leaf node will have start==end, so their max is really easy to figure out.
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
     *@param A: a list of integer
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        return buildHelper(0, A.length - 1, A);
    }

    public SegmentTreeNode buildHelper(int start, int end, int[] A) {
        if (start > end) {
            return null;
        } else if (start == end) {
            return new SegmentTreeNode(start, end, A[end]);
        }
        
        SegmentTreeNode leftChild = buildHelper(start, (start + end)/2, A);
        SegmentTreeNode rightChild  = buildHelper((start + end)/2 + 1, end, A);

        SegmentTreeNode node = new SegmentTreeNode(start, end, Math.max(leftChild.max, rightChild.max));
        node.left = leftChild;
        node.right = rightChild;
        return node;
    }
}
```