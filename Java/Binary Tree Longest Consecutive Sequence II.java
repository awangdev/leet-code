M
1526453838
tags: Tree, DFS, Divide and Conquer, Double Recursive

找到binary tree 里的最长 consecutive sequence. Sequence可以递增递减, Sequence顺序可以回溯parent.

#### DFS
- Similar to Binary Tree Longest Consecutive Sequence I
- 只不过可以递增递减, 还有连接上parent的方向.
- 对于任何一个节点, 都可能: 
- 1. 自己跟两个child链接, 成为一个sequence
- 2. 左边孩子, 右边孩子各自是一个consecutive sequence, 但是不跟root相连
- main function 一开始就divide成这三份, 然后dfs
- dfs take diff == 1, diff == -1, 来做递增递减的校对.
- dfs rules:
- 1. if node == null, leaf depth = 0
- 2. if not consecutive, reset the depth = 0 (same for both left child, and right child)
- 3. compare the leftDepth && rightDepth to find the maximum
- 4. diff is the same in the same dfs loop to maintain consistant increase/decrease

##### 注意
- dfs的结果很可能是0, 如果没有任何结果, 那么上一层的caller depth = dfs() + 1 = 1
- 那么回归到root, dfs的结果很可能就是1.
- 可能会问: 那么在tree里面的partial sequence (不连接到root)的被忽略了?
- 这里 `longestConsecutive(root.left)` 就很重要了
- 这一步特地忽略掉了root, 然后走下去一层: 因为是recursive, 所以还会继续divde && conquer
- 最后, 任何一层的孩子都会被照顾到.

```
/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, 
[1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. 
On the other hand, the path can be in the child-Parent-child order, 
where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].
*/
class Solution {
    public int longestConsecutive (TreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = dfs(root, 1) + dfs(root, -1) + 1;
        return Math.max(result, Math.max(longestConsecutive(root.left), longestConsecutive(root.right)));
    }

    // diff can be 1,-1, indicating increasing or decreasing by 1
    private int dfs(TreeNode node, int diff) {
        if (node == null) {
            return 0;
        }
        int leftDepth = 0, rightDepth = 0;
        // check node
        if (node.left != null && node.val - node.left.val == diff) {
            leftDepth = dfs(node.left, diff) + 1;
        }
        if (node.right != null && node.val - node.right.val == diff) {
            rightDepth = dfs(node.right, diff) + 1;
        }
        return Math.max(leftDepth, rightDepth);
    }
}


// NOT quite working, and too complicated
class Solution {
    int max = 0;
    public int longestConsecutive (TreeNode root) {
        if (root == null) {
            return 0;
        }
        int directDepth = dfs(root, 1);
        return Math.max(max, directDepth);
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        int val = node.val;
        // check node
        int leftDepth = 0, rightDepth = 0;
        if (node.left != null) {
            if (depth < 0 && node.left.val + 1 == val) {
                leftDepth = dfs(node.left, depth - 1);
            } else if (depth > 0 && node.left.val - 1 == val) {
                leftDepth = dfs(node.left, depth + 1);
            }
        }
        if (node.right != null) {
            if (depth < 0 && node.right.val + 1 == val) {
                rightDepth = dfs(node.right, depth - 1);
            } else if (depth > 0 && node.right.val - 1 == val) {
                rightDepth = dfs(node.right, depth + 1);
            }
        }
        // regular depth compare between math, left, right
        max = Math.max(max, Math.max(
                       Math.abs(leftDepth), Math.abs(rightDepth)));
        
        // Calculate overall connected depth
        if (node.left != null && node.right != null &&
            (node.left.val + 1 == node.right.val - 1 || 
             node.left.val - 1 == node.right.val + 1)) {
            max = Math.max(max, Math.abs(leftDepth)+Math.abs(rightDepth) - 1);
        }

        // Return the consecutive depth based on input depth
        if (depth > 0) {
            return leftDepth > 0 && rightDepth > 0 ? 
                   Math.max(leftDepth, rightDepth) : 
                   (leftDepth > 0 ? leftDepth : rightDepth);
        }


        return leftDepth < 0 && rightDepth < 0 ? 
               Math.min(leftDepth, rightDepth) : 
               (leftDepth > 0 ? rightDepth : leftDepth);
    }
}

```