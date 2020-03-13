H
tags: String, Stack, DFS

#### Parse exp as sub problem
- Analyze the pattern: 1) single char, 2) with !, 3) with &, |
- Identify sub problem
    - Use stack to parse the data in "()", which is a sub problem to solve with recursive call
    - Handle &, | case: need to parse multiple
- Be comfortable with string parsing
- Slight improve: 
    - If see obvious result, directly return evaluation w/o further parsing
    - use memo to store evaluated exp

#### Evaluate inner exp and save back to Stack
- Use '(' and ')' to mark inner exp
- Evaluate the inner exp and save result back to Stack: the result will be 'f' or 't'
- This is slightly slow because:
    - It requires all stack items on top to be processed before reaching the operator
    - There is no room to optimize even there is simplification for specific operator

```
/**

Return the result of evaluating a given boolean expression, represented as a string.

An expression can either be:

"t", evaluating to True;
"f", evaluating to False;
"!(expr)", evaluating to the logical NOT of the inner expression expr;
"&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
"|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 

Example 1:

Input: expression = "!(f)"
Output: true
Example 2:

Input: expression = "|(f,t)"
Output: true
Example 3:

Input: expression = "&(t,f)"
Output: false
Example 4:

Input: expression = "|(&(t,f,t),!(t))"
Output: false

*/
class Solution {
    Map<String, Boolean> memo = new HashMap<>();
    public boolean parseBoolExpr(String exp) {
        if (exp.length() == 1) return exp.equals("t");
        if (memo.containsKey(exp)) return memo.get(exp);
    
        char op = exp.charAt(0);
        String s = exp.substring(2, exp.length() - 1);

        if (op == '!') return !parseBoolExpr(s);
        // process the inner list of & or |
        List<Boolean> values = parseBoolExprList(s, op);
        boolean result = evalList(values, op);

        memo.put(exp, result);
        return result;
    }

    // Parse expr list (w/o the outter "(", ")")
    private List<Boolean> parseBoolExprList(String s, char op) {
        int i = 0;
        List<Boolean> values = new ArrayList<>();
        while (i < s.length()) {
            char c = s.charAt(i++);
            if (c == ',') continue;

            // w/o oprator
            if(c == 't' || c == 'f') values.add(c == 't');
            else {
                // extract inner exp block including "(" and ")"
                String sub = findExpBlock(s, i);
                i += sub.length();
                
                // evaluate
                boolean val = parseBoolExpr(c + sub);
                values.add(val);

                // optimization
                if (op == '|' && val) return Arrays.asList(true);
                if (op == '&' && !val) return Arrays.asList(false);
            }
        }
        return values;
    }
    
    private String findExpBlock(String s, int i) {
        StringBuffer sb = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        while (sb.length() == 0 || !stack.isEmpty()) {
            char c = s.charAt(i++);
            sb.append(c);
            if (c == '(') stack.push(c);
            if (c == ')') stack.pop();
            if (stack.isEmpty()) break;
        }
        return sb.toString();
    }
    
    private boolean evalList(List<Boolean> list, char op) {
        if (op == '&') {
            for (boolean b : list) {
                if (!b) return false;
            }
            return true;
        }
        // op == '|'
        for (boolean b : list) {
            if (b) return true;
        }
        return false;
    }
}


// Use '(' and ')' to mark start and end, then add eval back to stack
class Solution {    
    public boolean parseBoolExpr(String exp) {
        Stack<Character> stack = new Stack<>();
        for (char c : exp.toCharArray()) {
            if (c == ')') {
                Set<Character> expItems = new HashSet<>();
                while (stack.peek() != '(') expItems.add(stack.pop());
                stack.pop(); // pop out '('
                char op = stack.pop();
                if (op == '&') stack.push(expItems.contains('f') ? 'f' : 't');
                else if (op == '|') stack.push(expItems.contains('t') ? 't' : 'f');
                else if (op == '!') stack.push(expItems.contains('t') ? 'f' : 't');
            } else if (c != ',') {
                stack.push(c);
            }
        }
        return stack.pop() == 't';
    }
}

```