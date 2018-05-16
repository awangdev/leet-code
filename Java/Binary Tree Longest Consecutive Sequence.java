M
tags: Tree, dfs

找到binary tree 里的最长 consecutive sequence.

#### DFS
- Divide and Conquer. dfs
- 分开 看左边/右边
- 如果左边满足连续递增的规则, dfs (depth + 1), 如果不满足规则, dfs(depth = 1)
- 右边也是一样
- 对结果跟max作比较, return

```
/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree 
along the parent-child connections. The longest consecutive path need to be from parent to child 
(cannot be the reverse).

For example,
   1
    \\
     3
    / \\
   2   4
        \\
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \\
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

Tags:Tree
Similar Problems: (H) Longest Consecutive Sequence

*/
/*
Thoughts: 
1. Mark depth at each level
2. discuss wether extend or start new depth (per problem requirement)
*/
class Solution {
    public int longestConsecutive (TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 1);
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }

        // check node
        int leftDepth = (node.left != null && node.val + 1 == node.left.val) ? 
            dfs(node.left, depth + 1) : dfs(node.left, 1);
        int rightDepth = (node.right != null && node.val + 1 == node.right.val) ? 
            dfs(node.right, depth + 1) : dfs(node.right, 1);
        return Math.max(depth, Math.max(leftDepth, rightDepth));
    }
}

/*
// DFS, actually find all the items in rst: List<List<Integer>>. It's not requried in this problem.
public class Solution {
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<List<Integer>> rst = new ArrayList<>();
        dfs(rst, new ArrayList<>(), root);
        List<Integer> list = new ArrayList<>();
        for (List<Integer> candidate: rst) {
            list = candidate.size() > list.size() ? candidate : list;
        }
        return list.size();
    }

    private void dfs(List<List<Integer>> rst,
                     List<Integer> list,
                     TreeNode node) {
        if (node == null) {
            if (list != null && list.size() > 0) {
                rst.add(new ArrayList<>(list));
            }
            return;
        }

        // check node
        if (list.size() > 0 && list.get(list.size() - 1) != node.val - 1) {
            rst.add(new ArrayList<>(list));
            dfs(rst, new ArrayList<>(), node);
        } else {
            list.add(node.val);
            dfs(rst, list, node.left);
            dfs(rst, list, node.right);
            list.remove(list.size() - 1);
        }
    }
}
*/

/*
Attemp2: http://www.cnblogs.com/jcliBlogger/p/4923745.html.
The original solution has just 4 lines of C++ code. That hurts.
The concept is very much similar as my attempt1, though the code is more clear with recursive call
1. pass alone a depth. 
2. if consecutive, depth++; else, start from depth 1
3. Go deeper on both left, and right; both with new depth: currDepth;
4. Compare the Max of currDept, left's return, right's return.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int longestConsecutive(TreeNode root) {
        return recursiveHelper(root, null, 0);
    }

    public int recursiveHelper(TreeNode curr, TreeNode parent, int depth) {
    	if (curr == null) {
    		return 0;
    	} 
    	int currDepth = 0;
    	if (parent != null && parent.val + 1 == curr.val) {
    		currDepth = depth + 1;
    	} else {
    		currDepth = 1;
    	}
    	return Math.max(currDepth, Math.max(recursiveHelper(curr.left, curr, currDepth), recursiveHelper(curr.right, curr, currDepth)));
    }
}


```