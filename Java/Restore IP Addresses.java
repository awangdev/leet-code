M

递归的终点:list.zie() == 4
递归在一个index上面（具体问题，具体分析的情况）
validate string要注意leading '0'

注意： 递归的时候可以用一个start/level/index来跑路
但是尽量不要去改变Input source， 会变得非常confusing.

```
/*

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

Hide Tags Backtracking String

*/

/*
	Thoughts:
	NOT DONE. NEED CLEAR MIND
	Break into 4 parts.
	At each index, either close it as one IP spot, or not.
		recursive down.
		If level == 4 validate if valid IP address. If so, add it.
	pass along: rst, list (store the 4 IP spots), level (0 ~ 3), s,
	for (0 ~ 2): can pick 1 digit, 2 digits, or 3 digits
*/
public class Solution {
   public  List<String> restoreIpAddresses(String s) {
        List<String> rst = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return rst;
        }
        if (s.length() < 4 || s.length() > 12) {
        	return rst;
        }
        ArrayList<String> list = new ArrayList<String>();
        helper(rst, list, 0, s);

        return rst;
    }

    public  void helper(List<String> rst, ArrayList<String>list,
        int start, String s) {
        if (list.size() == 4) {
        	if (start != s.length()) {
        		return;
        	}
            StringBuffer sb = new StringBuffer();
            for (String str : list) {
                sb.append(str + ".");
            }
            rst.add(sb.substring(0, sb.length() - 1).toString());
            return;
        }
        //run for loop 3 times: one IP spot has at most 3 digits
        for (int i = start; i < s.length() && i <= start + 3; i++) {
        	String temp = s.substring(start, i + 1);
        	if (isValid(temp)) {
	            list.add(temp);
	            helper(rst, list, i + 1, s);
	            list.remove(list.size() - 1);
            }
        }
    }
    //Valid the IP [0,255]; cannot start with 0 if it's not 0
    public boolean isValid(String str) {
    	if (str.charAt(0) == '0') {
    		return str.equals("0");
    	}
    	int num = Integer.parseInt(str);
    	return num <= 255 && num >= 0;
    }

}









```