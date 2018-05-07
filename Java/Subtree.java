E
1525671122
tags: Tree, DFS

给一个binary tree s, 和一个binary tree t, 检查t是不是s的subtree.

#### DFS
- 跟 identical binary tree的写法很像
- 只有 current s.val = t.val 的时候才需要compare same tree.
- 其他情况, 继续recursively isSubtree
- 注意：即使找到T1 == T2, 但很可能只是数字相同（这里不是binary search tree!!）, 而children不同
- 所以同时要继续recursively isSubtree(T1.left, T2) ...etc.

```
/**
Given two non-empty binary trees s and t, check whether tree t has exactly 
the same structure and node values with a subtree of s. 
A subtree of s is a tree consists of a node in s and all of this node's descendants. 
The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
 */






/*
You have two every large binary trees: T1, with millions of nodes, 
and T2, with hundreds of nodes. 

Create an algorithm to decide if T2 is a subtree of T1.

Example
T2 is a subtree of T1 in the following case:

       1                3
      / \              / 
T1 = 2   3      T2 =  4
        /
       4
T2 isn't a subtree of T1 in the following case:

       1               3
      / \               \
T1 = 2   3       T2 =    4
        /
       4
Note
A tree T2 is a subtree of T1 if there exists a node n in T1 
such that the subtree of n is identical to T2. 

That is, if you cut off the tree at node n, the two trees would be identical.

Tags Expand 
Recursion Binary Tree

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
Thoughts: similar to compare identical trees.
Except: only start compare if s.val == t.val, otherwise, keep dfs.
*/
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == null && t == null;
        }
        boolean checkSubTree = false;
        if (s.val == t.val) {
            checkSubTree = sameTree(s, t);
        }
        return checkSubTree || isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean sameTree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == null && t == null;
        }
        return s.val == t.val && sameTree(s.left, t.left) && sameTree(s.right, t.right);
    }
}

/**
Previous notes
Thoughts:
When T2 == null, reardless of T1 == null or NO, it can always return true;
WHen T2 != null, T1==null returns false;
1. recursively compare the two nodes: if both null, okay; if everything goes well, get deeper into the child nodes.
2. resursively check subtree: check root.left or root.right comparing with T2.


 */
public class Solution {
    /**
     * @param T1, T2: The roots of binary tree.
     * @return: True if T2 is a subtree of T1, or false.
     */
    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        if (T2 == null) {
            return true;
        } else if (T1 == null) {
            return false;
        } else {
            return compare(T1, T2) || isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
        }
    }
    //Recursive compare
    public boolean compare(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } 
        if (node1 == null || node2 == null){
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return compare(node1.left, node2.left) && compare(node1.right, node2.right);
    }
}


// 2.22 recap: Find T2 first, then do a divide and conquer compare
public class Solution {
    /**
     * @param T1, T2: The roots of binary tree.
     * @return: True if T2 is a subtree of T1, or false.
     */
    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        if (T1 == null || T2 == null) {
            return T2 == null;
        }
        
        if (T1.val == T2.val) {
            return compare(T1, T2) || isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
        } else {
            return isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
        }
    }
    
    public boolean compare(TreeNode T1, TreeNode T2) {
        if (T1 == null && T2 == null) {
            return true;
        } else if (T1 == null || T2 == null) {
            return false;
        }
        if (T1.val != T2.val) {
            return false;
        }
            
        return compare(T1.left, T2.left) && compare(T1.right, T2.right);
    }
}

```