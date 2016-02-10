屌炸天的4行代码。
我洋洋洒洒那么多行，最后还不work.看了solution, 如此精简。

主要想法：
Recursive用好。首先在这个level比一比，可否成。
不成的话，另立门户。
然后左右开弓。再把结果拿过来比较一下就好了。简单明了。
```
/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

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


/*
Attemp1, failed.
Thoughts:
Seems like backtracking: we find the lowest level, then return depth, and add it on parent's record if consectutive.
always record a max value.
If node.left != null || node.right != null, if consecutive, node.depth = node.child.depth  +1
If node.left == null && node.right == null, return 1;


public class Solution {
	private int max = Integer.MIN_VALUE;
    public int longestConsecutive(TreeNode root) {
    	dfsOnNode(root);
        return max;
    }

    public int dfsOnNode(TreeNode root) {
    	int depth = 0;
    	int localMax = Integer.MIN_VALUE;
        if (root == null) {
        	return depth;
        }
        if (root.left == null && root.right == null) {//ON:right4,
        	depth++;
        	return depth;
        }
        if (root.right != null) {//root.right = 3, right4,
        	int childDepth = longestConsecutive(root.right);//ON: 3, right4
        	if (root.val + 1 == root.right.val) {//3+1=4
        		depth = childDepth + 1;//1+1=2
        	} else {
        		depth = 1;
        	}
        }
        localMax = Math.max(localMax, depth);
        if (root.left != null) {
        	int childDepth = longestConsecutive(root.left);
        	if (root.val + 1 == root.left.val) {
        		depth = childDepth + 1;
        	} else {
        		depth = 1;
        	}
        }
        localMax = Math.max(localMax, depth);
        max = Math.max(max, localMax);
        return localMax;
    }
}
*/
```