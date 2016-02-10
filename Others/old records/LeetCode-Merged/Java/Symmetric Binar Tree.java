注意Symmetric Binary Tree的例子和定义。
并不是说左右两个tree相等。
```
/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

Hide Tags Tree Depth-first Search

*/
/*
	Recursive
*/
public class Solution {
    /**
     * @param root, the root of binary tree.
     * @return true if it is a mirror of itself, or false.
     */
    public boolean isSymmetric(TreeNode root) {
    	if (root == null) {
    		return true;
    	}
    	return check(root.left, root.right);
    }

   	public boolean check(TreeNode A, TreeNode B) {
   		if (A == null && B == null) {
   			return true;
   		} else if (A == null || B == null) {
   			return false;
   		}
   		return A.val == B.val && check(A.left, B.right) && check(A.right, B.left);
   	}
}


//Non-recursive, iterative
/*
	Thoughts:
	Use 2 stack to hold the child that's needed to compare.
	Have to use stack, otherwise, can't iterate through root node.
*/

public class Solution {
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}

		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		s1.push(root.left);
		s2.push(root.right);
		while (!s1.isEmpty() && !s2.isEmpty()) {
			TreeNode node1 = s1.pop();
			TreeNode node2 = s2.pop();
			if (node1 == null && node2 == null) {
				continue;
			} else if (node1 == null || node2 == null) {
				return false;
			} else if (node1.val != node2.val) {
				return false;
			}
			s1.push(node1.left);
			s2.push(node2.right);
			s1.push(node1.right);
			s2.push(node2.left);	
		}

		return true;
	}
}



```