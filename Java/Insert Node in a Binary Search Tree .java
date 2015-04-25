/*
43% Accepted
Given a binary search tree  and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.

Example
Given binary search tree as follow:

     2

  /    \

1        4

         /   

       3 

after Insert node 6, the tree should be:

     2

  /    \

1        4

         /   \ 

       3        6

Challenge
Do it without recursion

Tags Expand 
Binary Search Tree LintCode Copyright

Thinking process:
Binary Search Tree:
parent must < left node
parent must > right node
use a dummy node runNode to flow around on the binary search tree, compare with target node.
Find the leaf node and add into appropriate pos.
*/

public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) {
            root = node;
            return root;
        }
        TreeNode runNode = root;
        TreeNode parentNode = null;
        while (runNode != null) {
            parentNode = runNode;
            if (runNode.val > node.val) {
                runNode = runNode.left;
            } else {
                runNode = runNode.right;
            }
        }//while
        
        if (parentNode != null) {
            if (parentNode.val > node.val) {
                parentNode.left = node;
            } else {
                parentNode.right = node;
            }
        }
        return root;
    }
}

