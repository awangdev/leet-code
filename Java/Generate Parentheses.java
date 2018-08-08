M
1528731679
tags: String, DFS, Backtracking, Sequence DFS

#### DFS
- start with empty string, need to go top->bottom
- 取或者不取`(`, `)`
- rule: open parentheses >= close parentheses
- Note: 在DFS时 pass a reference (StringBuffer) and maintain, instead of passing object (String) and re-create every time
- time: O(2^n), pick/not pick, the decision repat for all nodes at every level
- T(n) = 2 * T(n - 1) + O(1)

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
        if (n == 0) {
            return result;
        }
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

// Slightly slower because building new string every time.
class Solution {
    private final static String LEFT = "(";
    private final static String RIGHT = ")";
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        dfs(result, "", n, n);
        return result;
    }
    
    private void dfs(List<String> result, String str, int numL, int numR) {
        if (numL == 0 && numR == 0) {
            result.add(str);
            return;
        }
        if (numL > 0) {
            dfs(result, str + LEFT, numL - 1, numR);
        }
        if (numR > 0 && numL < numR) {
            dfs(result, str + RIGHT, numL, numR - 1);
        }
    }
}


/*
	Thoughts: 
	//http://fisherlei.blogspot.com/2012/12/leetcode-generate-parentheses.html
	Either put ( or )
	can only put ( when # of ( < n
	can only put ) when # of ) < # of (
	If # of single '(' > 0, then we can put ')'
	If n > 0, we can split: 1. close it with ')'; or 2. add '('
	when n-- becomese = 0 and #p = 0, rst.add
	
*/
public class Solution {
    /**
     * @param n n pairs
     * @return All combinations of well-formed parentheses
     */
    ArrayList<String> rst = new ArrayList<String>();
    public ArrayList<String> generateParenthesis(int n) {
    	if (n <= 0) {
    		return rst;
    	} 
    	ArrayList<String> list = new ArrayList<String>();
    	helper(list, 0, 0, n);
    	return rst;
    }

    public void helper(ArrayList<String> list, int left, int right, int n) {
    	if (left == n && right == n) {
    		StringBuffer sb = new StringBuffer();
    		for (String s : list) {
    			sb.append(s);
    		}
    		rst.add(sb.toString());
    		return;
    	}
    	if (left < n) {
    		list.add("(");
    		helper(list, left + 1, right, n);
    		list.remove(list.size() - 1);
    	}
    	if (right < left) {
    		list.add(")");
    		helper(list, left, right + 1, n);
    		list.remove(list.size() - 1);
    	}
    }
}

```