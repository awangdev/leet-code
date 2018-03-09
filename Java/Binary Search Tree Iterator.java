M
1518626557
tags: Stack, Tree, Design

画一下, BST in order traversal. 用stack记录最小值, 放在top. O(h) space.
每次消耗TreeNode, 都看看rightNode(其实就是下一个最小的candidate), 并且一条龙stack叠上rightNode所有的left子孙.

Previous Notes:
用O(h)空间的做法：

理解binary search tree inorder traversal的规律：
   先找left.left.left ....left 到底，这里是加进stack.
   然后考虑parent,然后再right.

例如这题：
   stack里面top，也就是tree最左下角的node先考虑,取名rst.
   其实这个rst拿出来以后, 它也同时是最底层left null的parent，算考虑过了最底层的parent。
   最后就考虑最底层的parent.right, 也就是rst.right.

注意:
   next()其实有个while loop, 很可能是O(h).题目要求average O(1),所以也是okay的.


用O(1)空间的做法：不存stack, 时刻update current为最小值。

找下一个最小值,如果current有right child：   
   和用stack时的iteration类似,那么再找一遍current.right的left-most child,就是最小值了。
   
如果current没有right child:    
    那么就要找current node的右上parent, search in BinarySearchTree from root.

注意：
   一定要确保找到的parent满足parent.left == current.
   反而言之，如果current是parent的 right child, 那么下一轮就会重新process parent。
   但是有错:binary search tree里面parent是小于right child的，也就是在之前一步肯定visit过，如此便会死循环。


```
/*
Design an iterator over a binary search tree with the following rules:

Elements are visited in ascending order (i.e. an in-order traversal)
next() and hasNext() queries run in O(1) time in average.

Example
For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]

   10
 /    \
1      11
 \       \
  6       12
Challenge
Extra memory usage O(h), h is the height of the tree.

Super Star: Extra memory usage O(1)

Tags Expand 
Binary Tree LintCode Copyright Non Recursion Binary Search Tree Google LinkedIn Facebook
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
    final Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode rst = stack.pop();
        if (rst.right != null) {
            TreeNode node = rst.right;
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return rst.val;
    }
}

//Recap 02.24.2016: Similar to solution below.  O(h) space.
//Stack, inorder traversal; first add left node till end. Each next() trigger a iteration. 
public class BSTIterator {
    public Stack<TreeNode> stack = new Stack<TreeNode>();
    
    //@param root: The root of binary tree.
    //Add till end of left
    public BSTIterator(TreeNode root) {
        if (root == null) {
            return;
        }
        stack.push(root);
        while (root.left != null) {
            stack.push(root.left);
            root = root.left;
        }
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return stack.size() > 0;
    }
    
    //@return: return next node
    public TreeNode next() {
        TreeNode node = stack.pop();
        if (node.right != null) {
            TreeNode temp = node.right;
            stack.push(temp);
            while (temp.left != null) {
                stack.push(temp.left);
                temp = temp.left;
            }            
        }
        return node;
    }
}



/*
    Previous correct implementation, O(h) space.
    Thoughts:http://blog.csdn.net/u014748614/article/details/46800891
    Put all left nodes into stack. Then top of stack must be the first element in in-order-traversal.
    We never add right node into stack directly, but ever time before returnning the rst node, we take care of rst.right right away.
        That is, find next() when rst.right as root.
    very smart use of a 'currnt' node.
    It's like a pointer on the tree, but only operates when that current node is not null, and under condition of having left child.

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






