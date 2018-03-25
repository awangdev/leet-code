H
tags: BST

方法1: Brutle一点。找到target和target的parent.    
把target remove时，把target的children nodes 重新排列组成新的BST: inorder traversal, build tree based on inorder traversal list.

方法2: 分析规律,先找到target和parent, 然后根据性质，把target remove时，移动children nodes, 保证还是BST。

```
/*
Given a root of Binary Search Tree with unique value for each node.  Remove the node with given value. 
If there is no such a node with given value in the binary search tree, do nothing. 
You should keep the tree still a binary search tree after removal.

Example
Given binary search tree:

          5

       /    \

    3          6

 /    \

2       4

Remove 3, you can either return:

          5

       /    \

    2          6

      \

         4

or :

          5

       /    \

    4          6

 /   

2

Tags Expand 
LintCode Copyright Binary Search Tree


*/

//Recap, 2024.2016
//Find target. Serialize (inorder) target's children. De-serialize the list, without target.
public class Solution {
    public TreeNode removeNode(TreeNode root, int value) {
        if (root == null) {
            return root;
        }
        //Find target and target.parent
        TreeNode parent = findTargetParent(root, value);    
        TreeNode target;
        if (parent == null) {
            return root;
        }
        if (parent.val == value) {
            target = parent;  
        } else if (parent.left != null && parent.left.val == value) {
            target = parent.left;
        } else {
            target = parent.right;
        }
        
        
        //Build inorder traversal based on target node.
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        inorderTraversal(target, list, target);
        
        //Build new subtree and attach to original tree
        if (parent.val == value) {
            return buildBFS(list, 0, list.size() - 1);
        }
        if (parent.left != null && parent.left.val == value) {
            parent.left = buildBFS(list, 0, list.size() - 1);
        } else {
            parent.right = buildBFS(list, 0, list.size() - 1);
        }

        return root;
    }
    
    //Find and return target, where target.val == value
    public TreeNode findTargetParent(TreeNode node, int value) {
        if (node == null || node.val == value) {
            return node;
        } 
        
        if (value < node.val) {
            if (node.left != null && value == node.left.val) {
                return node;
            }
            return findTargetParent(node.left, value);
        } else {
            if (node.right != null && value == node.right.val) {
                return node;
            }
            return findTargetParent(node.right, value);
        }
    }
    
    //Traverse target's children and build a inorder list
    public void inorderTraversal(TreeNode node, ArrayList<TreeNode> rst, TreeNode skipNode) {
        if (node.left != null) {
            inorderTraversal(node.left, rst, skipNode);
        }
        if (node.val != skipNode.val) {
            rst.add(new TreeNode(node.val));
        }
        if (node.right != null) {
            inorderTraversal(node.right, rst, skipNode);
        }
    }
    
    //Build tree based on given inorder-traversal list
    public TreeNode buildBFS(ArrayList<TreeNode> list, int start, int end) {
        if (start < end) {
            int mid = start + (end - start)/2;
            TreeNode root = list.get(mid);
            root.left = buildBFS(list, start, mid - 1);
            root.right = buildBFS(list, mid + 1, end);
            return root;
        }
        if (start == end) {
            return list.get(start);
        }
        
        return null;
    }
    
}



/*
Thoughts:
We can think of a couple different cases: where is that target node to remove? It can be root, a child (a couple more situations)
Note: before going futher, remember the technique to rip off parent node. In a binary tree, L > parent > R, so always find the L's right-most node and replace parent.
Cases1:
0. Root is target: find L's right-most node, replace root.
case1. A node with 2 null children: set target.parent.L/R = null
case2. A node with just just left node: set target.parent.L/R = target.right
case3. A node with left ndoe != null: find target.right-most node's left side X and replace target.parent.L/R.value = X.value. Remove set that X to null.

Divide into 2 major task:
1. Find the target node, and it's parent.
2. Remove that node (most complex logic)
*/
public class Solution {
    public TreeNode removeNode(TreeNode root, int value) {
        if (root == null || (root.left == null && root.right == null)) {
            return null;
        }
        TreeNode dummy = new TreeNode(0);;
        dummy.left = root;
        //Find node
        TreeNode parent = findTargetParent(dummy, root, value);
        TreeNode child;
        if (parent.left != null && parent.left.val == value) {
            child = parent.left;
        } else if (parent.right != null && parent.right.val == value) {
            child = parent.right;
        } else {
            return dummy.left;
        }
        //Delete that node:
        deleteTargetNode(parent, child);
        return dummy.left;
    }


    //Find target node
    public TreeNode findTargetParent(TreeNode parent, TreeNode node, int value){
        if (node == null || node.val == value) {
            return parent;
        }
        
        if (value < node.val) {
            return findTargetParent(node, node.left, value);
        } else {
            return findTargetParent(node, node.right, value);
        }
    }
    //Delete node
    public void deleteTargetNode(TreeNode parent, TreeNode target) {
        //Case1 + case2: (target.L == null && target.R == null) || (target.R == null && target.L != null)
        if (target.right == null) {
            if (parent.left == target) {
                parent.left = target.left;
            } else {
                parent.right = target.left;
            }
        } else {//Case3: when target.right != null
            TreeNode replaceNode = target.right;
            TreeNode replaceParent = target;
            while(replaceNode.left != null) {
                replaceParent = replaceNode;
                replaceNode = replaceNode.left;
            }
            //Remove replaceNode from replaceParent
            if (replaceParent.left == replaceNode) {//Usually it'll be replaceParent.left
                replaceParent.left = replaceNode.right;
            } else {//Sometimes when target.left == null, than means replaceParent.right will be replaceNode (while loop didn't start at all)
                replaceParent.right = replaceNode.right;
            }
            
            //Remove target from parent: not sure it's left or right node of parent
            if (parent.left == target) {
                parent.left = replaceNode;
            } else {
                parent.right = replaceNode;
            }
            
            replaceNode.left = target.left;
            replaceNode.right = target.right;
        }
    }

}









```