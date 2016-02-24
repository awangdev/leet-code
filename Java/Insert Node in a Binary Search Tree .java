E

往Binary Search Tree里面加东西，一定会找到一个合适的leaf加上去。

那么：就是说someNode.left or someNode.right是null时，就是insert node的地方。

找到那个someNode就按照正常的Binary Search Tree规律。

```

/*
Given a binary search tree and a new tree node, insert the node into the tree. 
You should keep the tree still be a valid binary search tree.

Example
Given binary search tree as follow, after Insert node 6, the tree should be:

  2             2
 / \           / \
1   4   -->   1   4
   /             / \ 
  3             3   6
Challenge
Can you do it without recursion?

Tags Expand 
LintCode Copyright Binary Search Tree

*/

//2.23.2016 recap
//Find node that has left or right==null. insert node on left/right
public class Solution {
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (node == null || root == null) {
            return node;
        }        
        TreeNode dummy = root;
        while (root != null) {
            if (node.val < root.val) {
                if (root.left == null) {
                    root.left = node;
                    break;
                }
                root = root.left;
            } else if (node.val > root.val) {
                if (root.right == null) {
                    root.right = node;
                    break;
                }
                root = root.right;
            }
        }
        return dummy;
    }
}



/*
Previous solution
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


```