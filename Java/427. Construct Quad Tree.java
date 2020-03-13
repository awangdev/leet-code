M
tags: Tree
time: O(n^2)
space: O(n^2)

#### Basic Impl
- build tree recursively by definition
- O(n^2) time and space due to single visit to all nodes
```
/*
We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false. The root node represents the whole grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same.

Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.

Your task is to use a quad tree to represent a given grid. The following example may help you understand the problem better:

Given the 8 x 8 grid below, we want to construct the corresponding quad tree:

It can be divided according to the definition above:
 

The corresponding quad tree should be as following, where each node is represented as a (isLeaf, val) pair.

For the non-leaf nodes, val can be arbitrary, so it is represented as *.



Note:

N is less than 1000 and guaranteened to be a power of 2.
If you want to know more about the quad tree, you can refer to its wiki.
*/

/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/
class Solution {
    public Node construct(int[][] grid) {
        if (grid == null || grid.length == 0) return null;
        return dfs(grid, 0, 0, grid.length);
    }
    
    private Node dfs(int[][] grid, int i, int j, int n) {
        boolean val = grid[i][j] == 1;
        if (n == 1) return buildLeaf(val);
        
        int len = n / 2;
        Node topL = dfs(grid, i, j, len);
        Node topR = dfs(grid, i, j + len, len);
        Node botL = dfs(grid, i + len, j, len);
        Node botR = dfs(grid, i + len, j + len, len);
        if (topL.isLeaf && topR.isLeaf && botL.isLeaf && botR.isLeaf
           && topL.val == topR.val && botL.val == botR.val && topL.val == botL.val) {
            return buildLeaf(val);
        }
        return new Node(val, false, topL, topR, botL, botR);
    }
    
    private Node buildLeaf(boolean val) {
        Node node = new Node();
        node.val = val;
        node.isLeaf = true;
        return node;
    }
}
```