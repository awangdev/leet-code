H
1526882596
tags: Stack, Math, Expression Tree, Binary Tree, Minimum Binary Tree

给一个expression String, 要evaluate这个expression的值.

Expression string 里面包括 +, -, 整数, 开合括号, 还有space.

#### Expression Tree
- Expression Tree是一个 weight-based的 min-tree 
- 基于 运算符号 + 数字的 tree: 数字永远在leaf, 然后符号是tree node,  括号不出现在tree里面
- 用 monotonuous stack 来构建这个tree

##### Thinking points
- Understand Expression Tree
- Use stack to build the expression tree + understand the weight system
- Use post-order traversal to evaluate the tree
- 注意, input里面的数字不会是single digit, 所以需要一个buffer存number string
- 整个题目的做法, 可以参照 `Expression Evaluation`

```
/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), 
the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.

*/

/*
build expression tree to evaluate expression
two functions:
1. build tree
    parse string
    skip space
    identify operator
    calculate weight of operator
    add parentheses to base weight
2. evaluate with post-order traversal
*/
class Solution {
    class TreeNode {
        int weight;
        String str;
        TreeNode left, right;
        public TreeNode(int weight, String str) {
            this.weight = weight;
            this.str = str;
        }
    }

    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        TreeNode root = buildTree(s); // build expression tree
        return (int)evaluate(root); // post-order traversal of the tree
    }

    // build tree based on input string, min-tree. return root
    private TreeNode buildTree(String s) {
        int n = s.length();
        char[] chars = s.trim().toCharArray();
        Stack<TreeNode> stack = new Stack<>();
        int base = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (c == ' ') {
                continue;
            } else if (c == '(') { // '()' are used to add weight, not stored in tree
                base = getWeight(base, c);
                continue;
            } else if (c == ')') {
                base = getWeight(base, c);
                continue;
            } else if (i < n - 1 && isDigit(chars[i]) && isDigit(chars[i + 1])) { // continue to get remaining of the int
                sb.append(c);
                continue;
            }
            String str;
            if (isDigit(c)) {
                sb.append(c);
                str = sb.toString();
                sb.setLength(0); // clean up
            } else {
                str = c + "";   
            }

            // use monotonuous stack to build min-tree
            TreeNode node = new TreeNode(getWeight(base, c), str);
            while (!stack.isEmpty() && node.weight <= stack.peek().weight) {
                node.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = node;
            }
            stack.push(node);
        }
        TreeNode root = null;
        while (!stack.isEmpty()) {
            root = stack.pop();
        }

        return root; // it's the root of tree, always a operator
    }
    
    // post-order traversal to evaluate the expression
    private long evaluate(TreeNode root) {
        if (root == null) return 0;
        long left = evaluate(root.left);
        long right = evaluate(root.right);
        long result = 0;
        switch(root.str) {
            case "+": 
                result = left + right;
                break;
            case "-": 
                result = left - right;
                break;
            case "*": 
                result = left * right;
                break;
            case "/": 
                result = left / right;
                break;
            default:
                result = Long.parseLong(root.str);
        }
        return result;
    }

    // get weight of the character. integer weights the most and will be leaf.
    // Remember to store the result using long
    private int getWeight(int base, char c) {
        if (c == '(') return base + 10;
        if (c == ')') return base - 10;
        if (c == '+' || c == '-') return base + 1;
        if (c == '*' || c == '/') return base + 2;
        return Integer.MAX_VALUE;
    }
    
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}

```
