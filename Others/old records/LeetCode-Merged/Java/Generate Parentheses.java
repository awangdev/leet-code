/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"

Hide Company Tags Google Zenefits
Hide Tags Backtracking String
Hide Similar Problems (M) Letter Combinations of a Phone Number (E) Valid Parentheses

*/

/*
	Thoughts:
	Pick ( or not.
	Pick ) or not.
	Back tracking. 
	when #"(" and #")" are both n, return the result.
*/
public class Solution {
    public List<String> generateParenthesis(int n) {
    	List<String> rst = new ArrayList<String>();
    	if (n <= 0) {
    		return rst;
    	}  
    	ArrayList<String> list = new ArrayList<String>();
    	helper(rst, list, 0, 0, n);
    	
    	return rst;
    }

    public void helper(List<String> rst, ArrayList<String> list, 
    	int left, int right, int n) {
    	if (left == n && right == n) {
    		StringBuffer sb = new StringBuffer();
    		for (String s : list) {
    			sb.append(s);
    		}
    		rst.add(sb.toString());
    		return;
    	}
    	//add (
    	if (left < n) {
    		list.add("(");
    		helper(rst, list, left + 1, right, n);
    		list.remove(list.size() - 1);
    	}
    	//add )
    	if (right < left) {
    		list.add(")");
    		helper(rst, list, left, right + 1, n);
    		list.remove(list.size() - 1);
    	}
    }
}

















