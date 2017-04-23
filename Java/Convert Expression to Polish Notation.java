H

还是Expression Tree (Min-Tree).

根据题意，Tree出来以后，来个Pre-order-traversal.

Note: label需要是String.虽然 Operator是长度为1的char, 但是数字可为多位。

```
/*
Given an expression string array, return the Polish notation of this expression. (remove the parentheses)

Have you met this question in a real interview? Yes
Example
For the expression [(5 − 6) * 7] (which represented by ["(", "5", "−", "6", ")", "*", "7"]), the corresponding polish notation is [* - 5 6 7] (which the return value should be ["*", "−", "5", "6", "7"]).

Clarification
Definition of Polish Notation:

http://en.wikipedia.org/wiki/Polish_notation
http://baike.baidu.com/view/7857952.htm
Tags Expand 
LintCode Copyright Stack
*/

/*
Thoughts:
Build the expression tree, and do a pre-order-traversal, and record all nodes in the array list.

Let's practice expression tree build again.
*/

public class Solution {
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

    public int getWeight(int base, String s) {
        if (s.equals("+") || s.equals("-")) {
            return base + 1;
        }
        if (s.equals("*") || s.equals("/")) {
            return base + 2;
        }
        return Integer.MAX_VALUE;
    }

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

Here is another approach which is Shunting-yard algorithm (https://en.wikipedia.org/wiki/Shunting-yard_algorithm).
```
public class Solution {
    /**
     * @param expression: A string array
     * @return: The Polish notation of this expression
     */
    public ArrayList<String> convertToPN(String[] expression) {
        // write your code here
        ArrayList<String> res = new ArrayList<String>();
        if (expression == null || expression.length == 0) return res;
        Stack<String> prefix = new Stack<>();
        Stack<String> op = new Stack<>();
        for (int i=expression.length-1; i>=0; --i) {
            String s = expression[i];
            if (isNumber(s)) {
                prefix.push(s);
            } else if (s.equals(")")) {
                op.push(s);
            } else if (isOperator(s)) {
                while (!op.isEmpty() && !op.peek().equals(")") && getPriority(op.peek()) > getPriority(s)) {
                    prefix.push(op.pop());
                }
                op.push(s);
            } else if (s.equals("(")) {
                while (!op.isEmpty() && !op.peek().equals(")")) {
                    prefix.push(op.pop());
                }
                if (!op.isEmpty() && op.peek().equals(")")) {
                    op.pop();
                } else {
                    return res;
                }
            }
        }
        while (!op.isEmpty()) {
            prefix.push(op.pop());
        }
        while (!prefix.isEmpty()) {
            res.add(prefix.pop());
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
