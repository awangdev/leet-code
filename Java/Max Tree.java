M
1520828407
tags: Tree, Stack

==== Monotonous Stack
用到bottom->top递减的stack: 最底下的root维持成最大的element.
过程当中, 一旦遇到currNode.val > stack.peek(), 就意味着需要把这个currNode放在 stack的底层位置.
也就是说, 遇到这个条件, process, pop()所有 currNode.val > stack.peek(), 最后把currNode加进去.

maxTree题目本身的要求是: 大的在最中间, 左右两边的subTree也要是maxTree:
- Monotonous Stack在这里帮助 keep/track of max value, 但是left/right tree的logic是MaxTree独有的.
- left/right node的assignment是根据题目要求: 中间最大值分开后, 左边的是左边subTree, 右边的作为右边subTree.

==== Previous notes
Should memorize MaxTree. 依次类推，会做Min-Tree, Expression Tree

Stack里，最大的值在下面。利用此性质，有这样几个step:
1   
把所有小于curr node的，全Pop出来, while loop, keep it going.    
最后pop出的这个小于Curr的node：它同时也是stack里面pop出来小于curr的最大的一个，最接近curr大小。（因为这个stack最大值靠下面）    
把这个最大的小于curr的node放在curr.left.    

2   
那么，接下去stack里面的一定是大于curr：   
那就变成curr的left parent. set stack.peek().right = curr.

3   
结尾：stack底部一定是最大的那个，也就是max tree的头。



```
/*
LeetCode: Maximum Binary Tree
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].
*/

/*
Thoughts:
Goal: find the max value and save it at last, as top of the tree. Find max, could take O(n) for unsorted list.
Here, we use a monotonous descending stack: if any current value < stack.peek(), save it in the peek.

- If any current value > stack.peek(), we'll save the stack.pop() as left child of curr node. (while loop)
- If fact, we'll do while loop until the largest candidate (less than current node) shows up. 
- For anything left in the stack, it must be larger than curr node, so set curr node as right child of stack.peek()
- In the end, we'll pop() all nodes off from the monotonous dscending stack, and use the bottom node as root.

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
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int n = nums.length;
        Stack<TreeNode> stack = new Stack<>();
        for (int num : nums) {
            TreeNode currNode = new TreeNode(num);
            // Pop the smaller node in the stack and set as left child of currNode. 
            // Loop until the largest candidate is found, and only then, settle on it.
            while (!stack.isEmpty() && num >= stack.peek().val) {
                currNode.left = stack.pop();
            }
                
            // Set currNode as right children, if stack is not empty: there must be larger node.
            if (!stack.isEmpty()) {
                stack.peek().right = currNode;
            }
                
            // Push currNode, as the being largest of all, into the stack.
            stack.push(currNode);
        }
        
        // Find root
        TreeNode root = null;
        while (!stack.isEmpty()) {
            root = stack.pop();
        }
        return root;
    }
}



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