H
1524551317
tags: Stack, Binary Tree, DFS, Expression Tree

给一串字符, 用来表示公式expression. 把这个expression转换成 Polish Notation (PN).

#### Expression Tree
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- 根据题意做出Expression Tree出来以后: 来个Pre-order-traversal 就能记录下 Polish Notation
- 本题没有给'ExpressionTreeNode', 所以把TreeNode就当做成我们需要的node, 里面扩展成有left/right child就可以了.
- Note: label需要是String. 虽然 Operator是长度为1的char, 但是数字可为多位

```
/*
LintCode Exercise

Given an expression string array, 
return the Polish notation of this expression. 
(remove the parentheses)

Example
For the expression [(5 − 6) * 7] 
(which represented by ["(", "5", "−", "6", ")", "*", "7"]), 

the corresponding polish notation is [* - 5 6 7] 
(which the return value should be ["*", "−", "5", "6", "7"]).

Clarification
Definition of Polish Notation:

http://en.wikipedia.org/wiki/Polish_notation
http://baike.baidu.com/view/7857952.htm
Tags Expand 
LintCode Copyright Stack
*/

/*
The expression tree will be something like below. '()' are not recorded by just used as weights:
            *
        /       \
       -         7
    /    \
   5      6
Thoughts:
Build the expression tree, and do a pre-order-traversal, 
and record all nodes in the array list.

Let's practice expression tree build again.
*/

public class Solution {
    /********* Build Expression Tree *********/
    class TreeNode {
        String s;
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val, String s) {
            this.val = val;
            this.s = s;
            this.left = null;
            this.right = null;
        }
    }

    public TreeNode build(String[] expression) {
        if (expression == null || expression.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int base = 0;
        int val = 0;

        for (int i = 0; i < expression.length; i++) {
            if (expression[i].equals("(")) {
                base += 10;
                continue;
            }
            if (expression[i].equals(")")) {
                base -= 10;
                continue;
            }
            val = getWeight(base, expression[i]);
            TreeNode node = new TreeNode(val, expression[i]);
            
            // Use monotonous stack to build minimum binary tree
            while (!stack.isEmpty() && node.val <= stack.peek().val) {
                node.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = node;
            }
            stack.push(node);
        }
        if (stack.isEmpty()) {
            return null;
        }

        // Find root, witch is the minimum value
        TreeNode rst = stack.pop(); 
        while (!stack.isEmpty()) {
            rst = stack.pop();
        }
        return rst;
    }

    public int getWeight(int base, String s) {
        if (s.equals("+") || s.equals("-")) {
            return base + 1;
        }
        if (s.equals("*") || s.equals("/")) {
            return base + 2;
        }
        return Integer.MAX_VALUE;
    }
    /********* Build Expression Tree *********/


    /**
     * @param expression: A string array
     * @return: The Polish notation of this expression
     */
    public ArrayList<String> convertToPN(String[] expression) {
        ArrayList<String> rst = new ArrayList<String>();
        if (expression == null || expression.length == 0) {
            return rst;
        }
        TreeNode root = build(expression);
        preTraversal(rst, root);

        return rst;
    }

    public void preTraversal(ArrayList<String> rst, TreeNode node){
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            rst.add(node.s);
            return;
        }
        rst.add(node.s);
        preTraversal(rst, node.left);
        preTraversal(rst, node.right);
    }
}


```