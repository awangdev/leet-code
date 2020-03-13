M
tags: Segment Tree, Binary Tree, Divide and Conquer, Lint

给一个区间[startIndex, endIndex], 建造segment tree structure, return root node.

#### Segment Tree
- Usage
  - which of these intervals contain a given point
  - which of these points are in a given interval
- Recursively build the binary tree
  - 左孩子：（A.left, (A.left+A.rigth)/2）   
  - 右孩子：（(A.left+A.rigth)/2＋1， A.right）   

```
/*
The structure of Segment Tree is a binary tree which each node 
has two attributes start and end denote an segment / interval.

start and end are both integers, they should be assigned in following rules:

- The root's start and end is given by build method.
- The left child of node A has start=A.left, end=(A.left + A.right) / 2.
- The right child of node A has start=(A.left + A.right) / 2 + 1, end=A.right.
- if start equals to end, there will be no children for this node.

Implement a build method with two parameters start and end, 
so that we can create a corresponding segment tree 
with every node has the correct start and end value, 
return the root of this segment tree.

Clarification
Segment Tree (a.k.a Interval Tree) is an advanced data structure 
which can support queries like:

- which of these intervals contain a given point
- which of these points are in a given interval

Example
Given start=0, end=3. The segment tree will be:

               [0,  3]
             /        \
      [0,  1]           [2, 3]
      /     \           /     \
   [0, 0]  [1, 1]     [2, 2]  [3, 3]

Given start=1, end=6. The segment tree will be:

               [1,  6]
             /        \
      [1,  3]           [4,  6]
      /     \           /     \
   [1, 2]  [3,3]     [4, 5]   [6,6]
   /    \           /     \
[1,1]   [2,2]     [4,4]   [5,5]

Clarification
Segment Tree (a.k.a Interval Tree) is an advanced data structure which can support queries like:

which of these intervals contain a given point
which of these points are in a given interval
See wiki:
Segment Tree
Interval Tree

Tags Expand 
LintCode Copyright Binary Tree Segment Tree
*/

public class Solution {
    /*
     * @param start: start value.
     * @param end: end value.
     * @return: The root of Segment Tree.
     */
    public SegmentTreeNode build(int start, int end) {
    	if (start > end) return null;
        else if (start == end) return new SegmentTreeNode(start, end);

    	SegmentTreeNode node = new SegmentTreeNode(start, end);
      int mid = start + (end - start) / 2;
    	node.left = build(start, mid); // recursive on the rest of the range
    	node.right  = build(mid + 1, end);
    	return node;
    }
}

/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end) {
 *         this.start = start, this.end = end;
 *         this.left = this.right = null;
 *     }
 * }
 */
 
```