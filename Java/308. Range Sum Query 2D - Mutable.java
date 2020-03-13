H
tags: Segment Tree, Binary Indexed Tree
time: build(n), update(logn), rangeRuery(logn + k)
space: O(n)

#### Segment Tree
- Same concept as turning an array into a binary segment tree,
    - HOWEVER, this is a 4-nary segmenet tree
- Reference. 307 Range Sum Query
- Range Query concept:
    - Using the input range, sum up everything in the range
    - sometimes the input range cover multiple segments, then dive into the segments (still use original range)
    - once we found a bounded segment (completely surrounded by input range), return segment value.
- Handling end stage, there are two approaches:
    - ApproachA: check at beginning of recursive call (i.e in `build()`, `updateNode()`, `rangeQuery()`).
        - pro: calling recursive function blindly; code is easy.
        - con: be really clear about termination state, and catch it.
    - ApproachB: check & come up with correct query condition before recursive call
        - pro: input to recursive function is assumed to be correct
        - con: sometimes really hard to write the conditions before recursive call; code is hard.

```

/*

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.

*/

class NumMatrix {
    class SegTreeNode {
        int row1, row2, col1, col2, sum;
        SegTreeNode topL, topR, downL, downR;
        public SegTreeNode(int row1, int col1, int row2, int col2, int sum) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.sum = sum;
        }
    }
    // nice wrapper class to simplify query.
    class QueryNode {
        int row1, row2, col1, col2;
        public QueryNode(int row1, int col1, int row2, int col2) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
        }
    }

    SegTreeNode root;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return;
        root = build(matrix, 0, 0, matrix.length - 1 , matrix[0].length - 1);
    }
    
    public void update(int row, int col, int val) {
        updateNode(root, row, col, val);
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return rangeQuery(root, new QueryNode(row1, col1, row2, col2));
    }
    
    private SegTreeNode build(int[][] matrix, int row1, int col1, int row2, int col2) {
        if (row2 < row1 || col2 < col1) return null;
        if (row1 == row2 && col1 == col2) return new SegTreeNode(row1, col1, row2, col2, matrix[row1][col1]);
        int rowMid = row1 + (row2 - row1) / 2, colMid = col1 + (col2 - col1) / 2;
        SegTreeNode node = new SegTreeNode(row1, col1, row2, col2, 0);
        node.topL = build(matrix, row1, col1, rowMid, colMid);
        node.topR = build(matrix, row1, colMid + 1, rowMid, col2);
        node.downL = build(matrix, rowMid + 1, col1, row2, colMid);
        node.downR = build(matrix, rowMid + 1, colMid + 1, row2, col2);
        node.sum = sum(node);
        return node;
    }
    
    private void updateNode(SegTreeNode node, int row, int col, int val) {
        if (node == null) return;
        if (row < node.row1 || row > node.row2 || col < node.col1 || col > node.col2) return; // target (row, col) fall out of range; end call
        if (node.row1 == row && node.row2 == row && node.col1 == col && node.col2 == col) { // direct match
            node.sum = val;
            return;
        }
        updateNode(node.topL, row, col, val);
        updateNode(node.topR, row, col, val);
        updateNode(node.downL, row, col, val);
        updateNode(node.downR, row, col, val);
        node.sum = sum(node);
    }
    
    private int rangeQuery(SegTreeNode node, QueryNode queryNode) {
        if (node == null) return 0;
        if (node.row2 < queryNode.row1 || node.row1 > queryNode.row2
            || node.col2 < queryNode.col1 || node.col1 > queryNode.col2) // segment tree node falls out of target range; end call
            return 0;
        if (node.row1 >= queryNode.row1 && node.row2 <= queryNode.row2
            && node.col1 >= queryNode.col1 && node.col2 <= queryNode.col2) // segment tree node is surrounded by range, return the node.sum
            return node.sum;
        return rangeQuery(node.topL, queryNode) + rangeQuery(node.topR, queryNode)
            + rangeQuery(node.downL, queryNode) + rangeQuery(node.downR, queryNode);
        
    }
    
    private int sum(SegTreeNode node) {
        int sum = 0;
        sum += node.topL == null ? 0 : node.topL.sum;
        sum += node.topR == null ? 0 : node.topR.sum;
        sum += node.downL == null ? 0 : node.downL.sum;
        sum += node.downR == null ? 0 : node.downR.sum;
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
```