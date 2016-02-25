Build Expression Tree的另外一个变形。
做的还是PostTraversal。先eval left, right, 然后eval符号。

注意Handle数字时，若左右Child全Null,那必定是我们weight最大的数字node了。
若有个child是null,那就return另外一个node。
还要注意：
过程中用个Long吧，最后结局在cast back to int.
```
/*
Given an expression string array, return the final result of this expression

Example
For the expression 2*6-(23+7)/(1+2), input is

[
  "2", "*", "6", "-", "(",
  "23", "+", "7", ")", "/",
  (", "1", "+", "2", ")"
],
return 2

Note
The expression contains only integer, +, -, *, /, (, ).

Tags Expand 
LintCode Copyright Stack
*/

/*
Thoughts:
Build expression tree, then traverse it in post-traversal order. 
Tricky point: Whenever that's a operator, do operation. return final result.
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
     * @param expression: an array of strings;
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
    	if (expression == null || expression.length == 0) {
    		return 0;
    	}
    	TreeNode root = build(expression);
    	if (root == null) {
    		return 0;
    	}
    	long rst = postTraversalEval(root);
    	return (int)rst;
    }

    public long postTraversalEval(TreeNode node) {
    	if (node == null) {
    		return 0;
    	}
    	if (node.left == null && node.right == null) {
    		return Long.parseLong(node.s);
    	}
    	long left = postTraversalEval(node.left);
    	long right = postTraversalEval(node.right);
   
    	if (node.left == null || node.right == null) {
    		return node.left == null ? right : left;
    	} 
    	long rst = 0;
    	switch (node.s) {
    		case "*":
    			rst = left * right;
    			break;
    		case "/":
    			rst = left / right;
    			break;
    		case "+":
    			rst = left + right;
    			break;
    		case "-":
    			rst= left - right;
    			break;
    	}
    	return rst;
    }
};


```