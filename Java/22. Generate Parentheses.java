M
tags: String, DFS, Backtracking, Sequence DFS
time: O(2^n)
space: O(2^n)

#### DFS
- start with empty string, need to go top->bottom
- 取或者不取`(`, `)`
- rule: open parentheses >= close parentheses
- Note: 在DFS时 pass a reference (StringBuffer) and maintain, instead of passing object (String) and re-create every time
- time: O(2^n), pick/not pick, the decision repat for all nodes at every level
- time: T(n) = 2 * T(n - 1) + O(1) = O(2^n)
- space: < than 2^n results = O(2^n)

#### bottom->up DFS
- figure out n=1, n=2 => build n=3, and n=4
- dfs(n-1) return a list of candidates
- add a pair of `()` to the candidates: either in front, at end, or contain the candidates

```
/*
Generate Parentheses

Given n pairs of parentheses, 
write a function to generate all combinations of well-formed parentheses.

Example
Given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

Tags Expand 
String Backtracking Recursion Zenefits Google

*/

/*
Thoughts:
1. We know left-parentheses numL <= numR; otherwise it'll be incorrect
2. Pick left or right parentheses always gives up to 2 options at one node: it becomes a tree. We can use DFS and find the leaf
3. Always populate the List<String>
*/
//Faster because StringBuffer object is reused (add/remove elements of it)
class Solution {
    private String LEFT = "(";
    private String RIGHT = ")";
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) return result;
        dfs(result, new StringBuffer(), n, n);
        return result;
    }
    
    private void dfs(List<String> result, StringBuffer sb, int numL, int numR) {
        if (numL == 0 && numR == 0) {
            result.add(sb.toString());
            return;
        }
        if (numL > 0) {
            dfs(result, sb.append(LEFT), numL - 1, numR);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (numR > 0 && numL < numR) { // hardcheck/validation
            dfs(result, sb.append(RIGHT), numL, numR - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

```