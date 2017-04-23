H

build expression tree。

这个里面把TreeNode就当做成我们需要的node,里面扩展成有left/right child的node.

建造Expression Tree,然后根据　Reverse Polish Notation 的定义，来个post-traversal就行了。

```
/*
Given an expression string array, return the Reverse Polish notation of this expression. (remove the parentheses)

Example
For the expression [3 - 4 + 5] (which denote by ["3", "-", "4", "+", "5"]), return [3 4 - 5 +] (which denote by ["3", "4", "-", "5", "+"])

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
        if (root == null) {
        	return rst;
        }
    	postTraversal(rst, root);
    	return rst;
    }

    public void postTraversal(ArrayList<String> rst, TreeNode node){
    	if (node == null) {
    		return;
    	}
    	if (node.left != null) {
			postTraversal(rst, node.left);    		
    	}
    	if (node.right != null) {
    		postTraversal(rst, node.right);
    	}
		rst.add(node.s);
    }
}

```

Here is another approach which is by Shunting-yard algorithm (https://en.wikipedia.org/wiki/Shunting-yard_algorithm)

```
public class Solution {
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public ArrayList<String> convertToRPN(String[] expression) {
        // write your code here
        ArrayList<String> res = new ArrayList<>();
        if (expression == null || expression.length == 0) return res;
        Stack<String> op = new Stack<>();
        for (String s : expression) {
            if (isNumber(s)) {
                res.add(s);
            } else if (isOperator(s)) {
                while (!op.isEmpty() && !op.peek().equals("(") && getPriority(op.peek()) >= getPriority(s)) {
                    res.add(op.pop());
                }
                op.push(s);
            } else if (s.equals("(")) {
                op.push(s);
            } else if (s.equals(")")) {
                while (!op.isEmpty() && !op.peek().equals("(")) {
                    res.add(op.pop());
                }
                if (!op.isEmpty() && op.peek().equals("(")) {
                    op.pop();
                } else {
                    return res;
                }
            }
        }
        while (!op.isEmpty()) {
           res.add(op.pop());
        }
        return res;
    }
    
    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
    private int getPriority(String s) {
        if (s.equals("*") || s.equals("/")) return 2;
        return 1;
    }
}
```
