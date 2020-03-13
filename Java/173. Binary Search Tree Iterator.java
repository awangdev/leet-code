M
tags: Stack, Tree, Design, BST
time: O(1) average
space: O(h)

#### BST in order traversal
- 用stack记录最小值, 放在top. O(h) space.
- 每次消耗TreeNode, 都看看rightNode(其实就是下一个最小的candidate), 并且一条龙stack叠上rightNode所有的left子孙.

#### Previous Notes:
- 用O(1)空间的做法：不存stack, 时刻update current为最小值。
- 找下一个最小值,
    - 如果current有right child: 和用stack时的iteration类似,那么再找一遍current.right的left-most child,就是最小值了。
    - 如果current没有right child: 那么就要找current node的右上parent, search in BinarySearchTree from root.
- 注意：
    - 一定要确保找到的parent满足parent.left == current.
    - 反而言之，如果current是parent的 right child, 那么下一轮就会重新process parent。
    - 但是有错:binary search tree里面parent是小于right child的，也就是在之前一步肯定visit过，如此便会死循环。


```
/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Example
For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]

   7
 /    \
3      15
     /    \
    9     12
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false

Note: 
next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */

 /*
Thoughts:
Inorder traversal, always having the smallest on top.
Use stack to keep smallest item on top of stack; when consuming an item, always find right, and dive in and stack most left children
*/
public class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        pushLeftNodes(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode rst = stack.pop();
        pushLeftNodes(rst.right);
        return rst.val;
    }

    private void pushLeftNodes(TreeNode node) {
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}

/**
    Below are from LintCode, not passing LeetCode
*/

/*
    Alternative O(h) space Solution.
    Keep it light in constructor, more in next()
*/
public class BSTIterator {
	public Stack<TreeNode> stack = new Stack<TreeNode>();
	public TreeNode current;
    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
    	current = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
    	return current != null || !stack.isEmpty();
    }
    
    //@return: return next node
    public TreeNode next() {
    	while (current != null) {
    		stack.push(current);
    		current = current.left;
    	}
    	TreeNode rst = stack.pop();
    	current = rst.right;
    	return rst;
    }
}

/*
    Use O(1) space, which means we will not use O(h) stack.

    To begin:
    1. hasNext()? current.val <= endNode.val to check if the tree is fully traversed.

    2. Find min via left-most: We can alwasy look for left-most to find next minimum value.
    
    3. Once left-most min is checked (name it `current`). Next min will be 2 cases:
        If current.right != null, we can keep looking for current.right's left-most child, as next min.
        Or, we need to look backwards for parent. Use binary search tree to find current's parent node.

    Note: when doing binary search for parent, make sure it satisfies parent.left = current.
    
    Because:If parent.right == current, that parent must has been visited before. In binary search tree,
    we know that parent.val < parent.right.val. We need to skip this special case, since it leads
    to ifinite loop.

*/


public class BSTIterator {
    public TreeNode root;
    public TreeNode current;
    public TreeNode endNode;
    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        if (root == null) {
            return;
        }
        this.root = root;
        this.current = root;
        this.endNode = root;
        
        while (endNode != null && endNode.right != null) {
            endNode = endNode.right;
        }
        while (current != null && current.left != null) {
            current = current.left;
        }
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return current != null && current.val <= endNode.val;
    }
    
    //@return: return next node
    public TreeNode next() {
        TreeNode rst = current;
        //current node has right child
        if (current.right != null) {
            current = current.right;
            while (current.left != null) {
                current = current.left;
            }
        } else {//Current node does not have right child.
            current = findParent();
        }
        return rst;
    }

    //Find current's parent, where parent.left == current.
    public TreeNode findParent(){
        TreeNode node = root;
        TreeNode parent = null;
        int val = current.val;
        if (val == endNode.val) {
            return null;
        }
        while (node != null) {
            if (val < node.val) {
                parent = node;
                node = node.left;
            } else if (val > node.val) {
                node = node.right;
            } else {//node.val == current.val
                break;
            }
        }
        return parent;
    }
}


```






