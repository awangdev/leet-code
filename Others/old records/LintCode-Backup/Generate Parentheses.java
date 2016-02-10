递归。
看thought.取或者不取(,  )
```
/*
Generate Parentheses

Given n pairs of parentheses, 
write a function to generate all combinations of well-formed parentheses.

Example
Given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"

Tags Expand 
String Backtracking Recursion Zenefits Google

*/

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


/*
	//
	1st attempt, timeout. 
	Thoughts:
	n = 0, null
	n = 1, trivial: ()
	Do i-- from n. For each i >= 2
		it can choose: close a paren
						open another paren
	front = (
	end = )
	helper(front, end, int n)
	if (n == 1) {
		front + "()" + end
		rst.add // check duplicate
	}

*/
public class Solution {
    /**
     * @param n n pairs
     * @return All combinations of well-formed parentheses
     */
    public ArrayList<String> rst = new ArrayList<String>();
    public ArrayList<String> generateParenthesis(int n) {
    	if (n <= 0) {
    		return rst;
    	} else if (n == 1){
    		rst.add("()");
    		return rst;
    	}
    	helper("", "", n);
    	Collections.sort(rst);
    	return rst;
    }
	//3
    public void helper(String front, String end, int n) {
    	if (n == 1) {
    		String rt = front + "()" + end;
    		if (!rst.contains(rt)){
    			rst.add(rt);
    		}
    		return;
    	}
    	n--;
    	
        helper(front + "(", ")" + end, n);
    	helper(front + "()", end, n);
    	helper(front, "()" + end, n);
        helper(front + end + "(", ")", n);
        helper(front + end, "()", n);
        helper(front + end + "()", "", n);
        helper("(", ")" + front + end, n);
        helper("()", front + end, n);
        helper("","()" + front + end, n);
        helper("(",front+end+")",n);
        helper("(" + front+end,")",n);
        helper("(" + front, end + ")",n);
        helper("("+front+end+")", "", n);
        helper("", "("+front+end+")", n);

    }

}

```