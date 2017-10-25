E

Binary Tree的一个基本题。   
遍历到底，比较sum vs. target。   
注意divide的情况。要把遍历的例子写写。   

LeetCode: Path Sum II

```
/*
Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.

A valid path is from root node to any of the leaf nodes.

Example
Given a binary tree, and target = 5:

     1
    / \
   2   4
  / \
 2   3
return

[
  [1, 2, 2],
  [1, 4]
]
Tags Expand 
Binary Tree Binary Tree Traversal
*/

/*
	Thoughts:
	path: has to be from root to leaf.
	binary tree: no order logic in the tree.
	DPS on all nodes. If final sum == target, add list of nodes into rst
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

/*
3.1.2016 Recap
Same approach
*/
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (root == null) {
            return rst;
        }    
        dfs(rst, new ArrayList<Integer>(), root, 0, sum);
        
        return rst;
    }
    
    public void dfs(List<List<Integer>> rst, ArrayList<Integer> list, TreeNode node, int add, int sum) {
        list.add(node.val);
        if (node.left == null && node.right == null) {
            if (add + node.val == sum) {
                rst.add(new ArrayList<Integer>(list));                   
            }
            return;
        }
        if (node.left != null) {
            dfs(rst, list, node.left, add + node.val, sum);
            list.remove(list.size() - 1);
        }
        if (node.right != null) {
            dfs(rst, list, node.right, add + node.val, sum);
            list.remove(list.size() - 1);
        }
    }
}

public class Solution {
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
    	List<List<Integer>> rst = new ArrayList<List<Integer>>();
    	if (root == null) {
    		return rst;
    	}
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	list.add(root.val);
    	traversal(rst, list, root, root.val, target);
    	return rst;
    }


    public void traversal(List<List<Integer>> rst, ArrayList<Integer> list, TreeNode node, int sum, int target) {
    	if (node.left == null && node.right == null) {
    		if (sum == target) {
    			rst.add(new ArrayList<Integer>(list));
    		}
    		return;
    	}
    	if (node.left != null) {
    	    list.add(node.left.val);
    		traversal(rst, list, node.left, sum + node.left.val, target);
    		list.remove(list.size() - 1);
    	}
    	if (node.right != null) {
    	    list.add(node.right.val);
    		traversal(rst, list, node.right, sum + node.right.val, target);
    		list.remove(list.size() - 1);
    	}
    }
}





```