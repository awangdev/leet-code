H
1524551537
tags: Stack, Binary Tree, DFS, Expression Tree

给一串字符, 用来表示公式expression. 把这个expression转换成 Reverse Polish Notation (RPN).

#### Expression Tree
- Expression Tree: Minimum Binary Tree (https://lintcode.com/en/problem/expression-tree-build/)
- 根据题意做出Expression Tree出来以后: 来个Post-order-traversal 就能记录下 Reverse Polish Notation
- 本题没有给'ExpressionTreeNode', 所以把TreeNode就当做成我们需要的node, 里面扩展成有left/right child就可以了.

```
/*
LintCode Exercise

Given an expression string array, 
return the Reverse Polish notation of this expression. 
(remove the parentheses)

Example
For the expression [3 - 4 + 5] (which denote by ["3", "-", "4", "+", "5"]), 
return [3 4 - 5 +] (which denote by ["3", "4", "-", "5", "+"])

Tags:
LintCode Copyright Stack
*/

/*
Thought:
Reverse Polish notation:
Put operator after operands.

First build the tree, then do post-traversal.
*/


public class Solution {
	/***** build expression tree******/
	class TreeNode {
        int val;
        String s;
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
        TreeNode rst = stack.pop();
        while (!stack.isEmpty()) {
            rst = stack.pop();
        }
        return rst;
    }
    //Calculate weight for characters
    public int getWeight(int base, String s) {
        if (s.equals("+") || s.equals("-")) {
            return base + 1;
        }
        if (s.equals("*") || s.equals("/")) {
            return base + 2;
        }
        return Integer.MAX_VALUE;
    }
	/***** build expression tree******/

	
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public ArrayList<String> convertToRPN(String[] expression) {
   		ArrayList<String> rst = new ArrayList<String>();
       	if (expression == null || expression.length == 0) {
            return rst;
        }
        TreeNode root = build(expression);
    	postTraversal(rst, root);
    	return rst;
    }

    public void postTraversal(ArrayList<String> rst, TreeNode node){
    	if (node == null) {
    		return;
    	}
		postTraversal(rst, node.left);    		
    	postTraversal(rst, node.right);
		rst.add(node.s);
    }
}

```