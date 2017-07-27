H

Build Expression Tree的另外一个变形，依然Min Tree.

build好Min Tree以后，做PostTraversal. Divde and Conquer：   
先recursively找到 left和right的大小， 然后evaluate中间的符号。

Note:
1. Handle数字时，若left&&right Child全Null,那必定是我们weight最大的数字node了。   
2. 若有个child是null,那就return另外一个node。    
3. prevent Integer overflow　during operation:过程中用个Long，最后结局在cast back to int.

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

Here is another approach which has fewer lines than above. 
	Step 1. Convert the infix expression to postfix expression 
		by Shunting-yard algorithm (https://en.wikipedia.org/wiki/Shunting-yard_algorithm)
	Step 2. Calculate the results from the postfix expression. This step can be merged to Step 1.

```
public class Solution {
    /**
     * @param expression: an array of strings;
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
        if (expression == null || expression.length == 0) {
            return 0; //invalid input expression
        }
        Stack<Integer> postfix = new Stack<>();
        Stack<String> op = new Stack<>(); // operators to eval
        for (String s : expression) {
            if (isNumber(s)) {
                postfix.push(Integer.parseInt(s));
            } else if (s.equals("(")) {
                op.push("(");  
            } else if (isOperator(s)) {
                while (!op.isEmpty() && isOperator(op.peek()) && getPriority(op.peek()) >= getPriority(s)) {
                    cal(postfix, op.pop());
                }
                op.push(s);
            } else if (s.equals(")")) {
                while (!op.isEmpty() && !op.peek().equals("(")) {
                    cal(postfix, op.pop());
                }
                if (!op.isEmpty() && op.peek().equals("(")) {
                    op.pop();
                } else {
                    return 0; //invalid input expression
                }
            }
        }
        while (!op.isEmpty()) {
            cal(postfix, op.pop());
        }
        if (postfix.size() == 1) {
            return postfix.pop();
        } else {
            return 0; //invalid input expression
        }
    }
    /**
    *  Check if String s is an operator
    **/
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
    /**
    *  Check if String s is a integer number
    **/
    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
    *  get the priority of an operator
    **/
    private int getPriority(String s) {
        if (s.equals("*") || s.equals("/")) return 2;
        return 1;
    }
    /**
    *  Calculate the top 2 integers in postfix with given operator and push the result in postfix
    **/
    private void cal(Stack<Integer> postfix, String op) {
        int b = postfix.pop();
        int a = postfix.pop();
        int res = 0;
        switch(op.charAt(0)) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
            case '/':
                res = a / b;
                break;
        }
        postfix.push(res);
    }
};
```
											       
