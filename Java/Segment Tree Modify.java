M
1527895515
tags: Segment Tree, Binary Tree, Divide and Conquer, DFS, Lint

给一个segmentTree, node里面存max. 写一个modify function: modify(node, index, value).

#### Segment Tree, Divide and Conquer
- Recursively 在segment tree里面找index, update it with value.   
- 每个iteration，很可能（要么左手，要么右手）max就变了。所以每次都left.max and right.max compare一下
- 最后轮回到头顶，头顶一下包括头顶，就全部都是max了

```
/*
For a Maximum Segment Tree, which each node has an extra value max 
to store the maximum value in this node's interval.

Implement a modify function with three parameter root, index and value 
to change the node's value with [start, end] = [index, index] to the new given value. 
Make sure after this change, every node in segment tree still has the max attribute with the correct value.

Do Segment Tree build, Query problems first

Example
For segment tree:

                      [1, 4, max=3]
                    /                \
        [1, 2, max=2]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]

if call modify(root, 2, 4), we can get:

                      [1, 4, max=4]
                    /                \
        [1, 2, max=4]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=4], [3, 3, max=0], [4, 4, max=3]

or call modify(root, 4, 0), we can get:

                      [1, 4, max=2]
                    /                \
        [1, 2, max=2]                [3, 4, max=0]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=0]
Note
We suggest you finish problem Segment Tree Build and Segment Tree Query first.

Challenge
Do it in O(h) time, h is the height of the segment tree.

Tags Expand 
LintCode Copyright Binary Tree Segment Tree
*/

/*
Change value at leaf, and update all the parents' max till root.
1. find the target node recursively
    compare target against root.start, root.end. 
    choose dfs
    compare && update 
2. in each dfs, update max
    end case: start/end == target index, update node value, then return.

*/
class SegmentTreeNode {
    SegmentTreeNode left, right;
    int start, end, max;
    public SegmentTreeNode(int start, int end, int max) {
        this.start = start;
        this.end = end;
        this.max = max;
    }
}
class Solution {
    public void modify(SegmentTreeNode root, int target, int value) {
        // check edge case
        if (root == null) {
            return;
        }
        if (root.start == root.end && root.start == target) {
            root.max = value;
            return;
        }

        // dfs: find desired target direction, call modify()
        if (target <= root.left.end) {
            modify(root.left, target, value);
        } else if (target >= root.right.start){
            modify(root.right, target, value);
        } else {
            return;
        }

        // update max
        root.max = Math.max(root.left.max, root.right.max);
    }
}


/*
  Thought:
  Renew index x with new value, and update the max value alone the way.
  1. Use segmenttree property to find that leaf, which is node.start == node.end == index.
  2. Along the way, whenever going to one segment/interval, compare left.max and right.max again, and update max.
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
     *@param root, index, value: The root of segment tree and 
     *@ change the node's value with [index, index] to the new given value
     *@return: void
     */
    public void modify(SegmentTreeNode root, int index, int value) {
      if (root.start == root.end && root.start == index) {
        root.max = value;
        return;
      }

      //Divide and seawrch
      int mid = (root.start + root.end)/2;
      if (index <= mid) {
        modify(root.left, index, value);
      } else {
        modify(root.right, index, value);
      }
      root.max = Math.max(root.left.max, root.right.max);
    }
}

```
