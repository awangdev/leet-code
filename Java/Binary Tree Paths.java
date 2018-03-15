E
1519797926
tags: Binary Tree, DFS

返回所有root-to-leaf path

#### 方法1：   
Recursive:分叉. dfs.

#### 方法2:
- Iterative, 非递归练习了一下   
- 因为要每次切短list, 所以再加了一个Stack 来存level   


```
/*
Binary Tree Paths

Given a binary tree, return all root-to-leaf paths.

Example
Given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

[
  "1->2->5",
  "1->3"
]
Tags Expand 
Binary Tree Binary Tree Traversal Facebook Google
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
/*
Basic dfs, pass along sb, List<String>.
Save when root == null.
*/
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> rst = new ArrayList<>();
        if (root == null) {
            return rst;
        }
        dfs(rst, new ArrayList<>(), root);
        return rst;
    }
    
    public void dfs(List<String> rst, List<Integer> list, TreeNode node) {
        list.add(node.val);
        if (node.left == null && node.right == null) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < list.size() - 1; i++) {
                sb.append(list.get(i) + "->");
            }
            sb.append(list.get(list.size() - 1));
            rst.add(sb.toString());
            return;
        }
        
        if (node.left != null) {
            dfs(rst, list, node.left);
            list.remove(list.size() - 1);
        }
        if (node.right != null) {
            dfs(rst, list, node.right);
            list.remove(list.size() - 1);
        }
    }
}


/*
Previous notes
	Thoughts:
	Recursive, need a helper (root, arraylist<Integer> list, List<String>)
	Add curr.
	if(root.left == null && root.right == null) {
		convert to String, add to list.
	}
	Go left. remove last node.
	Go right, remove last node.

	time: 2715m
*/

/*
	Iterative:
	Use a stack. Note. Process push curr, right, left
	Special: use a levels stack, to track level. Because we are not backtracking in interative mode, 
	so we need manually adjust list's length back to be same level as next item in stack.
	time: 2715m
*/

public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
    	List<String> rst = new ArrayList<String>();
    	if (root == null) {
    		return rst;
    	}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	stack.push(root);

    	Stack<Integer> levels = new Stack<Integer>();
    	levels.push(0);

    	while (!stack.isEmpty()) {
    		int lv = levels.pop();
    		TreeNode node = stack.pop();
    		list.add(node.val);
    		
    		if (node.left == null && node.right == null) {
				StringBuffer sb = new StringBuffer();
	    		for (int i = 0; i < list.size() - 1; i++) {
	    			sb.append(list.get(i) + "->");
	    		}
	    		sb.append(list.get(list.size() - 1));
	    		rst.add(sb.toString());
	    		while (!levels.isEmpty() && list.size() > levels.peek()) {
	    			list.remove(list.size() - 1);
	    		}
    		}
    		
    		if (node.right != null) {
    			stack.push(node.right);
    			levels.push(lv + 1);	
    		}
    		if (node.left != null) {
    			stack.push(node.left);
    			levels.push(lv + 1);
    		}

    	}//end while

    	return rst;
    }
}
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










```