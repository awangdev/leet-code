H

MaxTree值得记。
Stack从下到上，最大在下。维持和利用这个性质：
把所有小于curr node的，全Pop出来，最后一个小于Curr的，放在curr.left.
接下去stack里面的一定是大于curr,那就变成curr的left parent.
结尾：stack底部一定是最大的那个，也就是max tree的头。

妙啊！！！

```
/*
Given an integer array with no duplicates. A max tree building on this array is defined as follow:

The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray divided by the root number.
Construct the max tree by the given array.

Example
Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:

    6
   / \
  5   3
 /   / \
2   0   1
Challenge
O(n) time and memory.

Tags Expand 
LintCode Copyright Stack Cartesian Tree
*/

/*
Can't fingure express tree, so look at Max Tree problem first
Good explain here:http://blog.welkinlan.com/2015/06/29/max-tree-lintcode-java/

Iterate from left of array to right. So, every element we take, it will be the current right-most element.
Goal: find left-child, and find its left-parent.
Do for loop on all nodes.Remember to push the stack on every for iteration.
1. Left-child: use while loop to find the very last smaller node(comparing with curr Node), and set that as left-child.
2. Then, of course, the node left in stack (if still existing a node), will be the first larger node. That, will become curr
	node's left parent.
3. At the end, we need to return largest element, which is root. Just by poping off all nodes will give us the bottom-largest-node

Note: Interesting behavior:
Stack: always stores the largest value at bottom. In above example, when 6 gets in stack, it will never come back.
All smaller element (smaller than current point) will be poped out, 
and of course, the last-possible-smaller element will be the largest smaller point on stack, then we attach it to current node.

These behavior keeps larger value on upper level of the tree

*/

public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
    	if (A == null || A.length == 0) {
    		return null;
    	}
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	for (int i = 0; i < A.length; i++) {
    		TreeNode node = new TreeNode(A[i]);
    		while (!stack.isEmpty() && node.val >= stack.peek().val) {
    			node.left = stack.pop();
    		}
    		if (!stack.isEmpty()) {
    			stack.peek().right = node;
    		}
    		stack.push(node);
    	}

    	TreeNode rst = stack.pop();
    	while(!stack.isEmpty()) {
    		rst = stack.pop();
    	}
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