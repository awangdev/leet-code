/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example
Given [1, 20, 23, 4, 8], the largest formed number is 8423201.

Note
 The result may be very large, so you need to return a string instead of an integer.

Tags Expand 
Sort

Thoughts:
Use a comparator with String.comareTo, then uset Arrays.sort(...)

*/

class CustomComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return (s2 + s1).compareTo(s1 + s2);
    }
}
public class Solution {
    /**
     *@param num: A list of non negative integers
     *@return: A string
     */
    public String largestNumber(int[] num) {
        if (num == null || num.length == 0) {
            return "";
        }
        String[] strs = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            strs[i] = num[i] + "";
        }
        Arrays.sort(strs, new CustomComparator());
        StringBuffer sb=  new StringBuffer();
        for (int i = 0; i < num.length; i++) {
            sb.append(strs[i]);
        }
        String rst = sb.toString();
        if (rst.charAt(0) == '0') {
            return "0";
        }
        return rst;
    }
}

















