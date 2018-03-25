M
tags: BST

会iterative traverse Binary Search Tree就好（Stack && handle left-dig-down）, 然后create Doubly-ListNode 时候注意就好.

注意inorder traversal在check right node的事后，    
不论right == null or != null, 每次都要强行move to right.    

如果不node = node.right,     
很可能发生窘境：       
node alays = stack.top(), 然后stack.top()一直是一开始把left 全部遍历的内容。所以就会infinite loop, 永远在左边上下上下。      

```
/*

Convert a binary search tree to doubly linked list with in-order traversal.

Example
Given a binary search tree:

    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5

Tags Expand 
Linked List
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
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */ 
 
 
/*
    Thoughts:
    Inorder with 1 stack: peek add left till end, pop and add, then push right node.
    
    Everytime when pop out a node and add, make it a new boubllistnode
        dNode.next = curr
        curr.pre = dNode.next
        dNode = dNode.next
        
    boarder case: if null, return a null.
*/
public class Solution {
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        if (root == null) {
            return null;
        }
        //Init stack
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;    
        stack.push(node);
        //Create DoublyListNode header
        DoublyListNode dummy = new DoublyListNode(0);
        DoublyListNode dNode = dummy;
        
            
        while(!stack.isEmpty()) {
            while (node != null && node.left != null) {
                stack.push(node.left);
                node = node.left;
            }
            //add node
            node = stack.pop();
            DoublyListNode curr = new DoublyListNode(node.val);
            dNode.next = curr;
            curr.prev = dNode;
            dNode = dNode.next;
            
            //check right node and add to stack
            node = node.right;
            if (node != null) {
                stack.push(node);
            }  
        }
        
        return dummy.next;
        
    }
}













```