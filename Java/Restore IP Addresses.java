M
1528094586
tags: String, Backtracking, DFS

给一串数字, 检查是否是valid IP, 如果合理, 给出所有valid 的IP组合方式.

#### Backtracking
- 递归的终点:list.zie() == 3, 解决最后一段
- 递归在一个index上面, pass s.toCharArray()
- validate string要注意leading '0'
- 注意: 递归的时候可以用一个start/level/index来跑路
- 但是尽量不要去改变Input source， 会变得非常confusing.
- note: code有点messy, 因为要考虑IP的valid情况
- 那个'remainValid', 其实是一个对于remain substring的判断优化, 不成立的就不dfs了

```
/*

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

Hide Tags Backtracking String

*/

public class Solution {
   public List<String> restoreIpAddresses(String s) {
        List<String> rst = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return rst;
        }
        if (s.length() < 4 || s.length() > 12) {
        	return rst;
        }
        List<String> list = new ArrayList<>();
        helper(rst, list, 0, s.toCharArray());

        return rst;
    }

    public void helper(List<String> rst, List<String>list, int index, char[] arr) {
        if (list.size() == 3) {
            StringBuffer sb = new StringBuffer();
            for (int i = index; i < arr.length; i++) {
                sb.append(arr[i]);
            }
            
            if (!isValid(sb.toString())) {
                return;
            }
            list.add(sb.toString());
            sb = new StringBuffer();
            for (String str: list) {
                sb.append(str + ".");
            }
            rst.add(sb.substring(0, sb.length() - 1).toString());
            list.remove(list.size() - 1);
            return;
        }
        //run for loop 3 times: one IP spot has at most 3 digits
        int end = index + 3;
        StringBuffer sb = new StringBuffer();
        for (int i = index; i < end && i < arr.length; i++) {
        	sb.append(arr[i]);
            int remainLength = (arr.length - i - 1);
            boolean remainValid = remainLength > 0 && remainLength <= (4 - list.size() - 1) * 3;
        	if (isValid(sb.toString()) && remainValid) {
	            list.add(sb.toString());
	            helper(rst, list, i + 1, arr);
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