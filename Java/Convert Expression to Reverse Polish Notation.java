用build expression tree开头。
这个里面把TreeNode就当做成我们需要的node,里面扩展成有left/right child的node.
这题，目的是建造tree,然后来个post-traversal就行了。
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